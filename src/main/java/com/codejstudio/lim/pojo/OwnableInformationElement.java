package com.codejstudio.lim.pojo;

import java.util.Collection;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.ObjectUtil;
import com.codejstudio.lim.pojo.i.IIntegratable;
import com.codejstudio.lim.pojo.i.IOwnable;
import com.codejstudio.lim.pojo.relation.BaseRelation;
import com.codejstudio.lim.pojo.role.Observer;
import com.codejstudio.lim.pojo.role.ObserverGroup;
import com.codejstudio.lim.pojo.role.Proposer;

/**
 * OwnableInformationElement.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = OwnableInformationElement.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
    "baseProposer",
    "baseObserverGroup",
})
@XmlSeeAlso({
    AbstractRelationableInformationElement.class,
    BaseRelation.class,
})
public class OwnableInformationElement extends AbstractElement implements IOwnable {
	
	/* constants */
	
	public static final String TYPE_NAME = "ownable-information-element";


	/* variables */

	@XmlElement(name = "proposer")
    protected BaseElement baseProposer;
	
	protected Proposer proposer;


	/* variables: collections, maps, sub-groups */
	
	@XmlElement(name = "observer-group")
	private BaseElement baseObserverGroup;
	
	private ObserverGroup observerGroup;

	
	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public OwnableInformationElement() throws LIMException {
		super();
	}
	
	public OwnableInformationElement(OwnableInformationElement element) throws LIMException {
		super(element);
		load(element);
	}

	public OwnableInformationElement(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public OwnableInformationElement(boolean ifInitId, boolean ifInitType, Proposer proposer) throws LIMException {
		super(ifInitId, ifInitType);
		setProposer(proposer);
	}

	
	/* initializers */
	
	private void initObserverGroup() throws LIMException {
		if(this.observerGroup == null) {
			this.observerGroup = new ObserverGroup(true);
			super.addInnerElementDelegate(this.observerGroup);
			this.baseObserverGroup = new BaseElement(observerGroup);
		}
	}
	

	/* destroyers */

	private void destroyObserverGroup() throws LIMException {
		if(this.observerGroup != null && this.observerGroup.size() == 0) {
			this.baseObserverGroup = null;
			super.removeInnerElementDelegate(this.observerGroup);
			this.observerGroup = null;
		}
	}


	/* getters & setters */

	public BaseElement getBaseProposer() {
		return baseProposer;
	}

	@Override
	@XmlTransient
	public Proposer getProposer() {
		return proposer;
	}

	@Override
	public void setProposer(Proposer proposer) throws LIMException {
		if(ObjectUtil.checkEquals(this.proposer, proposer)) {
			return;
		}
		
		if(this.proposer != null) {
			this.baseProposer = null;
			if(this.proposer.containProposedElement(this)) {
				this.proposer.removeProposedElement(this);
			}
			super.removeInnerElementDelegate(this.proposer);
			this.proposer = null;
		}
		if(proposer != null) {
			this.proposer = proposer;
			super.addInnerElementDelegate(proposer);
			if(!this.proposer.containProposedElement(this)) {
				this.proposer.addProposedElement(this);
			}
			this.baseProposer = new BaseElement(proposer);
		}
	}
	

	@Override
	public ObserverGroup getObserverGroup() {
		return observerGroup;
	}


	/* CRUD for collections, maps, sub-groups: observers */

	public boolean containObserver(Observer observer) throws LIMException{
		return (this.observerGroup != null) ? this.observerGroup.containGroupElement(observer) : false;
	}

	@Override
	public boolean addObserver(Observer... observers) throws LIMException {
		return addObserver(CollectionUtil.generateCollection(observers));
	}

	@Override
	public boolean addObserver(Collection<Observer> observers) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(observers)) {
			return false;
		}
		
		initObserverGroup();
		boolean flag = true;
		for (Observer observer : observers) {
			if(observer != null && !this.observerGroup.containGroupElement(observer)) {
				flag &= super.addInnerElementDelegate(observer) 
						& this.observerGroup.addGroupElement(observer) 
						& observer.addObservedElement(this);
			}
		}
		destroyObserverGroup();
		return flag;
	}

	@Override
	public boolean removeObserver(Observer... observers) throws LIMException {
		return removeObserver(CollectionUtil.generateCollection(observers));
	}

	@Override
	public boolean removeObserver(Collection<Observer> observers) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(observers) || this.observerGroup == null) {
			return false;
		}
		
		boolean flag = true;
		for (Observer observer : observers) {
			if(observer != null && this.observerGroup.containGroupElement(observer)) {
				flag &= super.removeInnerElementDelegate(observer) 
						& this.observerGroup.removeGroupElement(observer) 
						& observer.removeObservedElement(this);
			}
		}
		destroyObserverGroup();
		return flag;
	}


	/* overridden methods */

	@Override
	public IIntegratable reload(IIntegratable element, Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (this.absoluteEquals(element)) {
			return element;
		} else if (element instanceof OwnableInformationElement) {
			if(super.reload(element, rootElementMap) == null) {
				return null;
			}
			load((OwnableInformationElement) element);
			reloadFromRootElementMap(rootElementMap);
			return (IIntegratable) this;
		} else {
			return null;
		}
	}
	
	private void load(OwnableInformationElement element) {
		if(element != null) {
			this.baseProposer = element.baseProposer;
			this.proposer = element.proposer;
			this.baseObserverGroup = element.baseObserverGroup;
			this.observerGroup = element.observerGroup;
		}
	}

	private void reloadFromRootElementMap(Map<String, AbstractElement> rootElementMap) {
		if(CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		if(this.baseProposer != null && this.baseProposer.getId() != null) {
			AbstractElement element = rootElementMap.get(this.baseProposer.getId());
			this.proposer = (element instanceof Proposer) 
					? (Proposer) element : this.proposer;
		}
		if(this.baseObserverGroup != null && this.baseObserverGroup.getId() != null) {
			AbstractElement element = rootElementMap.get(this.baseObserverGroup.getId());
			this.observerGroup = (element instanceof ObserverGroup) 
					? (ObserverGroup) element : this.observerGroup;
		}
	}


	@Override
	public OwnableInformationElement cloneElement() throws LIMException {
		OwnableInformationElement cloneElement = (OwnableInformationElement) super.cloneElement();
		
		cloneElement.baseProposer = (this.baseProposer != null) 
				? (BaseElement) this.baseProposer.cloneElement() : cloneElement.baseProposer;
		cloneElement.proposer = (this.proposer != null) 
				? (Proposer) this.proposer.cloneElement() : cloneElement.proposer;
		
		cloneElement.baseObserverGroup = (this.baseObserverGroup != null) 
				? (BaseElement) this.baseObserverGroup.cloneElement() : cloneElement.baseObserverGroup;
		cloneElement.observerGroup = (this.observerGroup != null) 
				? (ObserverGroup) this.observerGroup.cloneElement() : cloneElement.observerGroup;

		return cloneElement;
	}

}
