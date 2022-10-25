package com.codejstudio.lim.pojo;

import java.util.Collection;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.i.IIntegratable;
import com.codejstudio.lim.pojo.i.IRelationable;
import com.codejstudio.lim.pojo.relation.BaseRelation;
import com.codejstudio.lim.pojo.relation.EquivalenceRelation;
import com.codejstudio.lim.pojo.relation.RelationGroup;

/**
 * AbstractRelationableInformationElement.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = AbstractRelationableInformationElement.TYPE_NAME, propOrder = {
    "baseRelationGroup",
})
@XmlSeeAlso({
    InformationElement.class,
})
public abstract class AbstractRelationableInformationElement extends OwnableInformationElement implements IRelationable {

	/* constants */
	
	public static final String TYPE_NAME = "relationable-information-element";


	/* variables: collections, maps, sub-groups */
	
	@XmlElement(name = "relation-group")
	private BaseElement baseRelationGroup;
	
	private RelationGroup relationGroup;

	
	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public AbstractRelationableInformationElement() throws LIMException {
		super();
	}

	public AbstractRelationableInformationElement(AbstractRelationableInformationElement element) throws LIMException {
		super(element);
		this.load(element);
	}

	public AbstractRelationableInformationElement(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}
	

	/* initializers */
	
	private void initRelationGroup() throws LIMException {
		if(this.relationGroup == null) {
			this.relationGroup = new RelationGroup(true);
			super.addInnerElementDelegate(this.relationGroup);
			this.baseRelationGroup = new BaseElement(relationGroup);
		}
	}
	

	/* destroyers */

	private void destroyRelationGroup() throws LIMException {
		if(this.relationGroup != null && this.relationGroup.size() == 0) {
			this.baseRelationGroup = null;
			super.removeInnerElementDelegate(this.relationGroup);
			this.relationGroup = null;
		}
	}


	/* getters & setters */

	@Override
	public RelationGroup getRelationGroup() {
		return relationGroup;
	}


	/* CRUD for collections, maps, sub-groups: relations */

	@Override
	public boolean containRelation(BaseRelation relation) throws LIMException{
		return (this.relationGroup != null) ? this.relationGroup.containGroupElement(relation) : false;
	}

	@Override
	public boolean addRelation(BaseRelation... relations) throws LIMException {
		return addRelation(CollectionUtil.generateCollection(relations));
	}

	@Override
	public boolean addRelation(Collection<BaseRelation> relations) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(relations)) {
			return false;
		}
		
		initRelationGroup();
		boolean flag = true;
		flag &= super.addInnerElementDelegate(relations) 
				& this.relationGroup.addGroupElement(relations);
		destroyRelationGroup();
		return flag;
	}

	@Override
	public boolean removeRelation(BaseRelation... relations) throws LIMException {
		return removeRelation(CollectionUtil.generateCollection(relations));
	}

	@Override
	public boolean removeRelation(Collection<BaseRelation> relations) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(relations) || this.relationGroup == null) {
			return false;
		}
		
		boolean flag = true;
		flag &= super.removeInnerElementDelegate(relations) 
				& this.relationGroup.removeGroupElement(relations);
		destroyRelationGroup();
		return flag;
	}


	/* overridden methods */

	@Override
	public boolean equivalents(IRelationable obj) throws LIMException {
		if(!(this instanceof IRelationable)) {
			return false;
		}
		
		Collection<BaseRelation> relations = null;
		IRelationable local = (IRelationable)this;
		if(this.relationGroup != null && (relations = this.relationGroup.getInnerGroupCollection()) != null) {
			for (BaseRelation relation : relations) {
				if(relation instanceof EquivalenceRelation && relation.getAnotherElement(local).equals(obj)) {
					return true;
				}
			} 
		}
		
		return false;
	}


	@Override
	public IIntegratable reload(IIntegratable element, Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (this.absoluteEquals(element)) {
			return element;
		} else if (element instanceof AbstractRelationableInformationElement) {
			if(super.reload(element, rootElementMap) == null) {
				return null;
			}
			load((AbstractRelationableInformationElement) element);
			reloadFromRootElementMap(rootElementMap);
			return (IIntegratable) this;
		} else {
			return null;
		}
	}
	
	private void load(AbstractRelationableInformationElement element) {
		if(element != null) {
			this.baseRelationGroup = element.baseRelationGroup;
			this.relationGroup = element.relationGroup;
		}
	}

	private void reloadFromRootElementMap(Map<String, AbstractElement> rootElementMap) {
		if(CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		if(this.baseRelationGroup != null && this.baseRelationGroup.getId() != null) {
			AbstractElement element = rootElementMap.get(this.baseRelationGroup.getId());
			this.relationGroup = (element instanceof RelationGroup) 
					? (RelationGroup) element : this.relationGroup;
		}
	}


	@Override
	public AbstractRelationableInformationElement cloneElement() throws LIMException {
		AbstractRelationableInformationElement cloneElement = (AbstractRelationableInformationElement) super.cloneElement();
		
		cloneElement.baseRelationGroup = (this.baseRelationGroup != null) 
				? (BaseElement) this.baseRelationGroup.cloneElement() : cloneElement.baseRelationGroup;
		cloneElement.relationGroup = (this.relationGroup != null) 
				? (RelationGroup) this.relationGroup.cloneElement() : cloneElement.relationGroup;

		return cloneElement;
	}

}
