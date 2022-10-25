package com.codejstudio.lim.pojo.attribute;

import java.util.Collection;
import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CaseFormatUtil.WordSeparator;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.AbstractElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.i.IGroupable;
import com.codejstudio.lim.pojo.i.IIntegratable;

/**
 * AttributeGroup.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class AttributeGroup extends Attribute implements IGroupable<Attribute> {

	/* variables: collections, maps, sub-groups */
	
	private Collection<Attribute> innerGroupCollection;
	
	private Map<String, Attribute> attributeMap;

	
	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public AttributeGroup() throws LIMException {
		super();
	}

	public AttributeGroup(boolean ifInitId) throws LIMException {
		super(ifInitId, true);
	}

	public AttributeGroup(boolean ifInitId, Attribute... attributes) throws LIMException {
		super(ifInitId, true);
		addGroupElement(attributes);
	}


	public AttributeGroup(Attribute... attributes) throws LIMException {
		this(true, attributes);
	}
	

	/* initializers */
	
	private void initInnerGroupCollection() throws LIMException {
		if(this.innerGroupCollection == null) {
			this.innerGroupCollection = CollectionUtil.getNewCollection();
		}
	}

	private void initAttributeMap() throws LIMException {
		if(this.attributeMap == null) {
			this.attributeMap = CollectionUtil.getNewMap();
		}
	}

	
	/* destroyers */
	
	private void destroyInnerGroupCollection() throws LIMException {
		if(this.innerGroupCollection != null && this.innerGroupCollection.size() == 0) {
			this.innerGroupCollection = null;
		}
	}
	
	private void destroyAttributeMap() throws LIMException {
		if(this.attributeMap != null && this.attributeMap.size() == 0) {
			this.attributeMap = null;
		}
	}


	/* getters & setters */

	@Override
	public Collection<Attribute> getInnerGroupCollection() {
		return innerGroupCollection;
	}

	@Override
	public int size() {
		return (this.innerGroupCollection != null) ? this.innerGroupCollection.size() : 0;
	}


	/* CRUD for collections, maps, sub-groups: attributes */

	public Attribute getAttribute(String attributeKey) {
		return (this.attributeMap != null) ? this.attributeMap.get(attributeKey) : null;
	}

	public boolean containAttribute(Attribute element) throws LIMException{
		return (this.attributeMap != null) 
				? this.attributeMap.containsValue(element) : false;
	}

	public boolean putAttribute(Attribute... attributes) throws LIMException {
		return putAttribute(CollectionUtil.generateCollection(attributes));
	}

	public boolean putAttribute(Collection<Attribute> attributes) throws LIMException { 
		if(CollectionUtil.checkNullOrEmpty(attributes)) {
			return false;
		}
		
		initAttributeMap();
		for (Attribute attribute : attributes) {
			if(attribute != null) {
				this.attributeMap.put(attribute.getKey(), attribute);
			}
		}
		destroyAttributeMap();
		return true;
	}

	public boolean removeAttribute(String... keys) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(keys)
				|| CollectionUtil.checkNullOrEmpty(this.attributeMap)) {
			return false;
		}
		
		for (String key : keys) {
			if(key != null) {
				this.attributeMap.remove(key);
			}
		}
		return true;
	}

	public boolean removeAttribute(Attribute... attributes) throws LIMException {
		return removeAttribute(CollectionUtil.generateCollection(attributes));
	}

	public boolean removeAttribute(Collection<Attribute> attributes) throws LIMException { 
		if(CollectionUtil.checkNullOrEmpty(attributes)
				|| CollectionUtil.checkNullOrEmpty(this.attributeMap)) {
			return false;
		}
		
		for (Attribute attribute : attributes) {
			if(attribute != null) {
				this.attributeMap.remove(attribute.getKey(), attribute);
			}
		}
		destroyAttributeMap();
		return true;
	}


	/* CRUD for collections, maps, sub-groups: group elements */

	@Override
	public boolean containGroupElement(Attribute element) throws LIMException{
		return (this.innerGroupCollection != null) 
				? this.innerGroupCollection.contains(element) : false;
	}

	@Override
	public boolean addGroupElement(Attribute... elements) throws LIMException {
		return addGroupElement(CollectionUtil.generateCollection(elements)) 
				& this.putAttribute(elements);
	}

	@Override
	public boolean addGroupElement(Collection<Attribute> elements) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(elements)) {
			return false;
		}
		
		initInnerGroupCollection();
		boolean flag = true;
		for (Attribute element : elements) {
			if(element != null && !this.innerGroupCollection.contains(element)) {
				flag &= super.addInnerElementDelegate(element) 
						& this.innerGroupCollection.add(element) 
						& this.putAttribute(element) 
						& super.putIntegratedElementDelegate(
								IGroupable.GROUP_KEY + WordSeparator.UNDERSCORE.getSeparator() + element.getId(), 
								new BaseElement(element));	
			}
		}
		return flag;
	}

	@Override
	public boolean removeGroupElement(Attribute... elements) throws LIMException {
		return removeGroupElement(CollectionUtil.generateCollection(elements)) 
				& this.removeAttribute(elements);
	}

	@Override
	public boolean removeGroupElement(Collection<Attribute> elements) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(elements) 
				|| CollectionUtil.checkNullOrEmpty(this.innerGroupCollection)) {
			return false;
		}
		
		boolean flag = true;
		for (Attribute element : elements) {
			if(element != null && this.innerGroupCollection.contains(element)) {
				flag &= super.removeInnerElementDelegate(element) 
						& this.innerGroupCollection.remove(element) 
						& this.removeAttribute(elements);
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
		initAttributeMap();
		for (BaseElement be : c) {
			if(be != null && be.getId() != null 
					&& be.absoluteEquals(map.get(
							IGroupable.GROUP_KEY + WordSeparator.UNDERSCORE.getSeparator() + be.getId()))) {
				AbstractElement ae = rootElementMap.get(be.getId());
				if(ae != null && ae instanceof Attribute) {
					Attribute element = (Attribute) ae;
					this.innerGroupCollection.add(element);
					this.attributeMap.put(element.getKey(), element);
				}
			}
		}
		destroyInnerGroupCollection();
		destroyAttributeMap();
	}


	@Override
	public AttributeGroup cloneElement() throws LIMException {
		AttributeGroup cloneElement = (AttributeGroup) super.cloneElement();
		
		if(!CollectionUtil.checkNullOrEmpty(this.innerGroupCollection)) {
			cloneElement.initInnerGroupCollection();
			for (Attribute element : this.innerGroupCollection) {
				if(element != null && !this.absoluteEquals(element)) {
					cloneElement.innerGroupCollection.add(element.cloneElement());
				}
			}
		}
		if(this.attributeMap != null && this.attributeMap.size() > 0) {
			cloneElement.initAttributeMap();
			for (String key : this.attributeMap.keySet()) {
				Attribute value = this.attributeMap.get(key);
				if(value != null) {
					cloneElement.attributeMap.put(key, value.cloneElement());	
				}
			}
		}
		
		return cloneElement;
	}

}
