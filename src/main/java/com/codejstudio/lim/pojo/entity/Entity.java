package com.codejstudio.lim.pojo.entity;

import java.util.Collection;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.StringUtils;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.AbstractElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.OwnableInformationElement;
import com.codejstudio.lim.pojo.OwnableInformationElementGroup;
import com.codejstudio.lim.pojo.i.IIntegratable;
import com.codejstudio.lim.pojo.role.Proposer;

/**
 * Entity.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = Entity.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
    "baseProposedElementGroup",
    "baseObservedElementGroup",
})
public class Entity extends AbstractElement {
	
	/* constants */
	
	public static final String TYPE_NAME = "entity";


	/* variables */
	
	@XmlAttribute
	protected String name;


	/* variables: collections, maps, sub-groups */
	
	@XmlElement(name = "proposed-element-group")
	private BaseElement baseProposedElementGroup;
	
	private OwnableInformationElementGroup proposedElementGroup;
	
	@XmlElement(name = "observed-element-group")
	private BaseElement baseObservedElementGroup;
	
	private OwnableInformationElementGroup observedElementGroup;

	
	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Entity() throws LIMException {
		super();
	}
	
	public Entity(Entity entity) throws LIMException {
		super(entity);
		load(entity);
	}

	public Entity(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public Entity(boolean ifInitId, boolean ifInitType, String name) throws LIMException {
		super(ifInitId, ifInitType);
		this.name = name;
	}
	

	public Entity(String name) throws LIMException {
		this(true, false, name);
	}
	

	/* initializers */

	private void initProposedElementGroup() throws LIMException {
		if(this.proposedElementGroup == null) {
			this.proposedElementGroup = new OwnableInformationElementGroup(true);
			super.addInnerElementDelegate(this.proposedElementGroup);
			this.baseProposedElementGroup = new BaseElement(proposedElementGroup);
		}
	}

	private void initObservedElementGroup() throws LIMException {
		if(this.observedElementGroup == null) {
			this.observedElementGroup = new OwnableInformationElementGroup(true);
			super.addInnerElementDelegate(this.observedElementGroup);
			this.baseObservedElementGroup = new BaseElement(observedElementGroup);
		}
	}
	

	/* destroyers */

	private void destroyProposedElementGroup() throws LIMException {
		if(this.proposedElementGroup != null && this.proposedElementGroup.size() == 0) {
			this.baseProposedElementGroup = null;
			super.removeInnerElementDelegate(this.proposedElementGroup);
			this.proposedElementGroup = null;
		}
	}

	private void destroyObservedElementGroup() throws LIMException {
		if(this.observedElementGroup != null && this.observedElementGroup.size() == 0) {
			this.baseObservedElementGroup = null;
			super.removeInnerElementDelegate(this.observedElementGroup);
			this.observedElementGroup = null;
		}
	}
	

	/* getters & setters */

	@XmlTransient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OwnableInformationElementGroup getProposedElementGroup() {
		return proposedElementGroup;
	}

	public OwnableInformationElementGroup getObservedElementGroup() {
		return observedElementGroup;
	}


	/* CRUD for collections, maps, sub-groups: proposed elements */

	public boolean containProposedElement(OwnableInformationElement element) throws LIMException{
		return (this.proposedElementGroup != null) 
				? this.proposedElementGroup.containGroupElement(element) : false;
	}

	public boolean addProposedElement(OwnableInformationElement... elements) throws LIMException {
		return addProposedElement(CollectionUtil.generateCollection(elements));
	}

	public boolean addProposedElement(Collection<OwnableInformationElement> elements) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(elements)) {
			return false;
		}
		
		initProposedElementGroup();
		boolean flag = true;
		flag &= super.addInnerElementDelegate(elements) 
				& this.proposedElementGroup.addGroupElement(elements);
		destroyProposedElementGroup();
		return flag;
	}

	public boolean removeProposedElement(OwnableInformationElement... elements) throws LIMException {
		return removeProposedElement(CollectionUtil.generateCollection(elements));
	}

	public boolean removeProposedElement(Collection<OwnableInformationElement> elements) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(elements) || this.proposedElementGroup == null) {
			return false;
		}
		
		boolean flag = true;
		flag &= super.removeInnerElementDelegate(elements) 
				& this.proposedElementGroup.removeGroupElement(elements);
		destroyProposedElementGroup();
		return flag;
	}


	/* CRUD for collections, maps, sub-groups: observed elements */

	public boolean containObservedElement(OwnableInformationElement element) throws LIMException{
		return (this.observedElementGroup != null) 
				? this.observedElementGroup.containGroupElement(element) : false;
	}

	public boolean addObservedElement(OwnableInformationElement... elements) throws LIMException {
		return addObservedElement(CollectionUtil.generateCollection(elements));
	}

	public boolean addObservedElement(Collection<OwnableInformationElement> elements) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(elements)) {
			return false;
		}
		
		initObservedElementGroup();
		boolean flag = true;
		flag &= super.addInnerElementDelegate(elements) 
				& this.observedElementGroup.addGroupElement(elements);
		destroyObservedElementGroup();
		return flag;
	}

	public boolean removeObservedElement(OwnableInformationElement... elements) throws LIMException {
		return removeObservedElement(CollectionUtil.generateCollection(elements));
	}

	public boolean removeObservedElement(Collection<OwnableInformationElement> elements) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(elements) || this.observedElementGroup == null) {
			return false;
		}
		
		boolean flag = true;
		flag &= super.removeInnerElementDelegate(elements) 
				& this.observedElementGroup.removeGroupElement(elements);
		destroyObservedElementGroup();
		return flag;
	}


	/* overridden methods */

	@Override
	public AbstractElement getXmlElement() throws LIMException {
		if(this.xmlElement == null) {
			if(this.getClass().equals(Entity.class)) {
				this.xmlElement = this;
			}else {
				this.xmlElement = new Entity(this);
			}
		}
		return this.xmlElement;
	}

	@Override
	public AbstractElement getPojoElement(Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (this.pojoElement == null) {
			if (StringUtils.isEmpty(this.getType())  
					|| !this.getClass().equals(Entity.class)) {
				this.pojoElement = this;
			} else {
				this.pojoElement = super.generatePojoElementDelegate(rootElementMap);
			}
		}
		this.pojoElement.reload(this, rootElementMap);
		return this.pojoElement;
	}


	@Override
	public IIntegratable reload(IIntegratable element, Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (element instanceof Entity) {
			if (super.reload(element, rootElementMap) == null) {
				return null;
			}
			load((Entity) element);
			reloadFromRootElementMap(rootElementMap);
			return (IIntegratable) this;
		} else {
			return null;
		}
	}
	
	private void load(Entity element) {
		if(element != null) {
			this.name = element.name;
			this.baseProposedElementGroup = element.baseProposedElementGroup;
			this.proposedElementGroup = element.proposedElementGroup;
			this.baseObservedElementGroup = element.baseObservedElementGroup;
			this.observedElementGroup = element.observedElementGroup;
		}
	}

	private void reloadFromRootElementMap(Map<String, AbstractElement> rootElementMap) {
		if(CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		if(this.baseProposedElementGroup != null && this.baseProposedElementGroup.getId() != null) {
			AbstractElement element = rootElementMap.get(this.baseProposedElementGroup.getId());
			this.proposedElementGroup = (element instanceof OwnableInformationElementGroup) 
					? (OwnableInformationElementGroup) element : this.proposedElementGroup;
		}
		if(this.baseObservedElementGroup != null && this.baseObservedElementGroup.getId() != null) {
			AbstractElement element = rootElementMap.get(this.baseObservedElementGroup.getId());
			this.observedElementGroup = (element instanceof OwnableInformationElementGroup) 
					? (OwnableInformationElementGroup) element : this.observedElementGroup;
		}
	}


	@Override
	public Entity cloneElement() throws LIMException {
		Entity cloneElement = (Entity) super.cloneElement();
		cloneElement.name = this.name;
		
		cloneElement.baseProposedElementGroup = (this.baseProposedElementGroup != null) 
				? (BaseElement) this.baseProposedElementGroup.cloneElement() : cloneElement.baseProposedElementGroup;
		cloneElement.proposedElementGroup = (this.proposedElementGroup != null) 
				? (OwnableInformationElementGroup) this.proposedElementGroup.cloneElement() : cloneElement.proposedElementGroup;
		
		cloneElement.baseObservedElementGroup = (this.baseObservedElementGroup != null) 
				? (BaseElement) this.baseObservedElementGroup.cloneElement() : cloneElement.baseObservedElementGroup;
		cloneElement.observedElementGroup = (this.observedElementGroup != null) 
				? (OwnableInformationElementGroup) this.observedElementGroup.cloneElement() : cloneElement.observedElementGroup;
		
		return cloneElement;
	}


	/* other methods */
	
	public boolean propose(OwnableInformationElement element) throws LIMException {
		if(element == null) {
			return false;
		}
		
		Proposer p = new Proposer(this);
		element.setProposer(p);
		return true;
	}

}
