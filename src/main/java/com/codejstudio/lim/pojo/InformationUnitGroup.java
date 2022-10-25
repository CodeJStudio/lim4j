package com.codejstudio.lim.pojo;

import java.util.Collection;
import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CaseFormatUtil.WordSeparator;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.i.IGroupable;
import com.codejstudio.lim.pojo.i.IIntegratable;

/**
 * InformationUnitGroup.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class InformationUnitGroup extends InformationUnit implements IGroupable<InformationUnit> {

	/* variables: collections, maps, sub-groups */
	
	private Collection<InformationUnit> innerGroupCollection;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public InformationUnitGroup() throws LIMException {
		super();
	}

	public InformationUnitGroup(boolean ifInitId) throws LIMException {
		super(ifInitId, true);
	}

	public InformationUnitGroup(boolean ifInitId, InformationUnit... elements) throws LIMException {
		super(ifInitId, true);
		addGroupElement(elements);
	}


	public InformationUnitGroup(InformationUnit... elements) throws LIMException {
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
	public Collection<InformationUnit> getInnerGroupCollection() {
		return innerGroupCollection;
	}

	@Override
	public int size() {
		return (this.innerGroupCollection != null) ? this.innerGroupCollection.size() : 0;
	}


	/* CRUD for collections, maps, sub-groups: group elements */

	@Override
	public boolean containGroupElement(InformationUnit element) throws LIMException{
		return (this.innerGroupCollection != null) 
				? this.innerGroupCollection.contains(element) : false;
	}

	@Override
	public boolean addGroupElement(InformationUnit... elements) throws LIMException {
		return addGroupElement(CollectionUtil.generateCollection(elements));
	}

	@Override
	public boolean addGroupElement(Collection<InformationUnit> elements) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(elements)) {
			return false;
		}
		
		initInnerGroupCollection();
		boolean flag = true;
		for (InformationUnit element : elements) {
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
	public boolean removeGroupElement(InformationUnit... elements) throws LIMException {
		return removeGroupElement(CollectionUtil.generateCollection(elements));
	}

	@Override
	public boolean removeGroupElement(Collection<InformationUnit> elements) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(elements) 
				|| CollectionUtil.checkNullOrEmpty(this.innerGroupCollection)) {
			return false;
		}
		
		boolean flag = true;
		for (InformationUnit element : elements) {
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
				if(ae != null && ae instanceof InformationUnit) {
					InformationUnit element = (InformationUnit) ae;
					this.innerGroupCollection.add(element);
				}
			}
		}
		destroyInnerGroupCollection();
	}


	@Override
	public InformationUnitGroup cloneElement() throws LIMException {
		InformationUnitGroup cloneElement = (InformationUnitGroup) super.cloneElement();
		
		if(!CollectionUtil.checkNullOrEmpty(this.innerGroupCollection)) {
			cloneElement.initInnerGroupCollection();
			for (InformationUnit element : this.innerGroupCollection) {
				if(element != null && !this.absoluteEquals(element)) {
					cloneElement.innerGroupCollection.add((InformationUnit) element.cloneElement());
				}
			}
		}
		
		return cloneElement;
	}

}
