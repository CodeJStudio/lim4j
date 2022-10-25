package com.codejstudio.lim.pojo;

import java.util.Collection;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CaseFormatUtil.WordSeparator;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.i.IGroupable;
import com.codejstudio.lim.pojo.i.IIntegratable;

/**
 * OwnableInformationElementGroup.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = OwnableInformationElement.TYPE_NAME)
public class OwnableInformationElementGroup extends OwnableInformationElement implements IGroupable<OwnableInformationElement> {

	/* variables: collections, maps, sub-groups */
	
	private Collection<OwnableInformationElement> innerGroupCollection;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public OwnableInformationElementGroup() throws LIMException {
		super();
	}

	public OwnableInformationElementGroup(boolean ifInitId) throws LIMException {
		super(ifInitId, true);
	}

	public OwnableInformationElementGroup(boolean ifInitId, OwnableInformationElement... elements) throws LIMException {
		super(ifInitId, true);
		addGroupElement(elements);
	}


	public OwnableInformationElementGroup(OwnableInformationElement... elements) throws LIMException {
		this(true, elements);
	}

	
	/* initializers */
	
	private void initInnerGroupCollection() throws LIMException {
		if(this.innerGroupCollection == null) {
			this.innerGroupCollection = CollectionUtil.getNewCollection();
		}
	}

	
	/* destroyers */
	
	private void destroyInnerGroupCollection() throws LIMException {
		if(this.innerGroupCollection != null && this.innerGroupCollection.size() == 0) {
			this.innerGroupCollection = null;
		}
	}
	

	/* getters & setters */

	@Override
	public Collection<OwnableInformationElement> getInnerGroupCollection() {
		return innerGroupCollection;
	}

	@Override
	public int size() {
		return (this.innerGroupCollection != null) ? this.innerGroupCollection.size() : 0;
	}


	/* CRUD for collections, maps, sub-groups: group elements */

	@Override
	public boolean containGroupElement(OwnableInformationElement element) throws LIMException{
		return (this.innerGroupCollection != null) 
				? this.innerGroupCollection.contains(element) : false;
	}

	@Override
	public boolean addGroupElement(OwnableInformationElement... elements) throws LIMException {
		return addGroupElement(CollectionUtil.generateCollection(elements));
	}

	@Override
	public boolean addGroupElement(Collection<OwnableInformationElement> elements) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(elements)) {
			return false;
		}
		
		initInnerGroupCollection();
		boolean flag = true;
		for (OwnableInformationElement element : elements) {
			if(element != null && !this.innerGroupCollection.contains(element)) {
				flag &= super.addInnerElementDelegate(element) 
						& this.innerGroupCollection.add(element) 
						& super.putIntegratedElementDelegate(
								IGroupable.GROUP_KEY + WordSeparator.UNDERSCORE.getSeparator() + element.getId(), 
								new BaseElement(element));	
			}
		}
		destroyInnerGroupCollection();
		return flag;
	}

	@Override
	public boolean removeGroupElement(OwnableInformationElement... elements) throws LIMException {
		return removeGroupElement(CollectionUtil.generateCollection(elements));
	}

	@Override
	public boolean removeGroupElement(Collection<OwnableInformationElement> elements) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(elements) 
				|| CollectionUtil.checkNullOrEmpty(this.innerGroupCollection)) {
			return false;
		}
		
		boolean flag = true;
		for (OwnableInformationElement element : elements) {
			if(element != null && this.innerGroupCollection.contains(element)) {
				flag &= super.removeInnerElementDelegate(element) 
						& this.innerGroupCollection.remove(element);
				super.removeIntegratedElementDelegate(IGroupable.GROUP_KEY + WordSeparator.UNDERSCORE.getSeparator() + element.getId());
			}
		}
		destroyInnerGroupCollection();
		return flag;
	}


	/* overridden methods */
	
	@Override
	public IIntegratable reload(IIntegratable element, Map<String, AbstractElement> rootElementMap) throws LIMException {
		if(super.reload(element, rootElementMap) != null) {
			reloadFromRootElementMap(rootElementMap);
			return (IIntegratable) this;
		} else {
			return null;
		}
	}
	
	private void reloadFromRootElementMap(Map<String, AbstractElement> rootElementMap) throws LIMException {
		Map<String, BaseElement> map = getIntegratedElement();
		Collection<BaseElement> c = (map != null) ? map.values() : null;
		if(CollectionUtil.checkNullOrEmpty(c) 
				|| CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		initInnerGroupCollection();
		for (BaseElement be : c) {
			if(be != null && be.getId() != null) {
				AbstractElement ae = rootElementMap.get(be.getId());
				if(ae != null && ae instanceof OwnableInformationElement) {
					OwnableInformationElement element = (OwnableInformationElement) ae;
					this.innerGroupCollection.add(element);
				}
			}
		}
		destroyInnerGroupCollection();
	}


	@Override
	public OwnableInformationElementGroup cloneElement() throws LIMException {
		OwnableInformationElementGroup cloneElement = (OwnableInformationElementGroup) super.cloneElement();
		
		if(!CollectionUtil.checkNullOrEmpty(this.innerGroupCollection)) {
			cloneElement.initInnerGroupCollection();
			for (OwnableInformationElement element : this.innerGroupCollection) {
				if(element != null && !this.absoluteEquals(element)) {
					cloneElement.innerGroupCollection.add(element.cloneElement());
				}
			}
		}
		
		return cloneElement;
	}

}
