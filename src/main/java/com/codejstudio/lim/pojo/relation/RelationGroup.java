package com.codejstudio.lim.pojo.relation;

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
 * RelationGroup.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class RelationGroup extends BaseRelation implements IGroupable<BaseRelation> {

	/* variables: collections, maps, sub-groups */
	
	private Collection<BaseRelation> innerGroupCollection;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public RelationGroup() throws LIMException {
		super();
	}

	public RelationGroup(boolean ifInitId) throws LIMException {
		super(ifInitId, true);
	}

	public RelationGroup(boolean ifInitId, BaseRelation... relations) throws LIMException {
		super(ifInitId, true);
		addGroupElement(relations);
	}


	public RelationGroup(BaseRelation... relations) throws LIMException {
		this(true, relations);
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
	public Collection<BaseRelation> getInnerGroupCollection() {
		return innerGroupCollection;
	}

	@Override
	public int size() {
		return (this.innerGroupCollection != null) ? this.innerGroupCollection.size() : 0;
	}


	/* CRUD for collections, maps, sub-groups: group elements */

	@Override
	public boolean containGroupElement(BaseRelation element) throws LIMException{
		return (this.innerGroupCollection != null) 
				? this.innerGroupCollection.contains(element) : false;
	}

	@Override
	public boolean addGroupElement(BaseRelation... elements) throws LIMException {
		return addGroupElement(CollectionUtil.generateCollection(elements));
	}

	@Override
	public boolean addGroupElement(Collection<BaseRelation> elements) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(elements)) {
			return false;
		}
		
		initInnerGroupCollection();
		boolean flag = true;
		for (BaseRelation element : elements) {
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
	public boolean removeGroupElement(BaseRelation... elements) throws LIMException {
		return removeGroupElement(CollectionUtil.generateCollection(elements));
	}

	@Override
	public boolean removeGroupElement(Collection<BaseRelation> elements) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(elements) 
				|| CollectionUtil.checkNullOrEmpty(this.innerGroupCollection)) {
			return false;
		}
		
		boolean flag = true;
		for (BaseRelation element : elements) {
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
				if(ae != null && ae instanceof BaseRelation) {
					BaseRelation element = (BaseRelation) ae;
					this.innerGroupCollection.add(element);
				}
			}
		}
		destroyInnerGroupCollection();
	}


	@Override
	public RelationGroup cloneElement() throws LIMException {
		RelationGroup cloneElement = (RelationGroup) super.cloneElement();
		
		if(!CollectionUtil.checkNullOrEmpty(this.innerGroupCollection)) {
			cloneElement.initInnerGroupCollection();
			for (BaseRelation element : this.innerGroupCollection) {
				if(element != null && !this.absoluteEquals(element)) {
					cloneElement.innerGroupCollection.add(element.cloneElement());
				}
			}
		}
		
		return cloneElement;
	}

}
