package com.codejstudio.lim.pojo.concept;

import java.util.Collection;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.StringUtils;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.AbstractElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.InformationUnit;
import com.codejstudio.lim.pojo.attribute.Attribute;
import com.codejstudio.lim.pojo.attribute.AttributeGroup;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.i.IIntegratable;
import com.codejstudio.lim.pojo.relation.AffiliationRelation;

/**
 * Concept.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = Concept.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
    "baseSubConceptGroup",
    "baseConditionGroup",
    "baseAttributeGroup",
})
public class Concept extends InformationUnit {

	/* constants */
	
	public static final String TYPE_NAME = "concept";


	/* variables: collections, maps, sub-groups */

	@XmlElement(name = "sub-concept-group")
	private BaseElement baseSubConceptGroup;

	private ConceptGroup subConceptGroup;
	
	@XmlElement(name = "condition-group")
	private BaseElement baseConditionGroup;
	
	private ConditionGroup conditionGroup;

	@XmlElement(name = "attribute-group")
	private BaseElement baseAttributeGroup;

	private AttributeGroup attributeGroup;

	
	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Concept() throws LIMException {
		super();
	}
	
	public Concept(Concept concept) throws LIMException {
		super(concept);
		load(concept);
	}

	public Concept(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public Concept(boolean ifInitId, boolean ifInitType, String discription) throws LIMException {
		super(ifInitId, ifInitType, discription);
	}
	

	public Concept(String discription) throws LIMException {
		super(true, false, discription);
	}
	

	/* initializers */

	private void initSubConceptGroup() throws LIMException {
		if(this.subConceptGroup == null) {
			this.subConceptGroup = new ConceptGroup(true);
			super.addInnerElementDelegate(this.subConceptGroup);
			this.baseSubConceptGroup = new BaseElement(subConceptGroup);
		}
	}
	
	private void initConditionGroup() throws LIMException {
		if(this.conditionGroup == null) {
			this.conditionGroup = new ConditionGroup(true);
			super.addInnerElementDelegate(this.conditionGroup);
			this.baseConditionGroup = new BaseElement(conditionGroup);
		}
	}

	private void initAttributeGroup() throws LIMException {
		if(this.attributeGroup == null) {
			this.attributeGroup = new AttributeGroup(true);
			super.addInnerElementDelegate(this.attributeGroup);
			this.baseAttributeGroup = new BaseElement(attributeGroup);
		}
	}
	

	/* destroyers */

	private void destroySubConceptGroup() throws LIMException {
		if(this.subConceptGroup != null && this.subConceptGroup.size() == 0) {
			this.baseSubConceptGroup = null;
			super.removeInnerElementDelegate(this.subConceptGroup);
			this.subConceptGroup = null;
		}
	}
	
	private void destroyConditionGroup() throws LIMException {
		if(this.conditionGroup != null && this.conditionGroup.size() == 0) {
			this.baseConditionGroup = null;
			super.removeInnerElementDelegate(this.conditionGroup);
			this.conditionGroup = null;
		}
	}

	private void destroyAttributeGroup() throws LIMException {
		if(this.attributeGroup != null && this.attributeGroup.size() == 0) {
			this.baseAttributeGroup = null;
			super.removeInnerElementDelegate(this.attributeGroup);
			this.attributeGroup = null;
		}
	}
	

	/* getters & setters */

	public ConceptGroup getSubConceptGroup() {
		return subConceptGroup;
	}

	public ConditionGroup getConditionGroup() {
		return conditionGroup;
	}
	
	public AttributeGroup getAttributeGroup() {
		return attributeGroup;
	}
	

	/* CRUD for collections, maps, sub-groups: sub-concepts */

	public boolean containSubConcept(Concept concept) throws LIMException{
		return (this.subConceptGroup != null) 
				? this.subConceptGroup.containGroupElement(concept) : false;
	}

	public boolean addSubConcept(Concept... concepts) throws LIMException {
		return addSubConcept(CollectionUtil.generateCollection(concepts));
	}

	public boolean addSubConcept(Collection<Concept> concepts) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(concepts) 
				|| CollectionUtil.onlyContains(concepts, this)) {
			return false;
		}
		
		Collection<Concept> c = CollectionUtil.removeElements(concepts, this);
		initSubConceptGroup();
		boolean flag = true;
		flag &= super.addInnerElementDelegate(c) 
				& this.subConceptGroup.addGroupElement(c);
		destroySubConceptGroup();
		return flag;
	}
	
	public boolean removeSubConcept(Concept... concepts) throws LIMException {
		return removeSubConcept(CollectionUtil.generateCollection(concepts));
	}

	public boolean removeSubConcept(Collection<Concept> concepts) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(concepts) || this.subConceptGroup == null) {
			return false;
		}
		
		boolean flag = true;
		flag &= super.removeInnerElementDelegate(concepts) 
				& this.subConceptGroup.removeGroupElement(concepts);
		destroySubConceptGroup();
		return flag;
	}


	/* CRUD for collections, maps, sub-groups: conditions */

	public boolean containCondition(Condition condition) throws LIMException{
		return (this.conditionGroup != null) 
				? this.conditionGroup.containGroupElement(condition) : false;
	}

	public boolean addCondition(Condition... conditions) throws LIMException {
		return addCondition(CollectionUtil.generateCollection(conditions));
	}

	public boolean addCondition(Collection<Condition> conditions) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(conditions)) {
			return false;
		}
		
		initConditionGroup();
		boolean flag = true;
		flag &= super.addInnerElementDelegate(conditions) 
				& this.conditionGroup.addGroupElement(conditions);
		destroyConditionGroup();
		return flag;
	}

	public boolean removeCondition(Condition... conditions) throws LIMException {
		return removeCondition(CollectionUtil.generateCollection(conditions));
	}

	public boolean removeCondition(Collection<Condition> conditions) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(conditions) || this.conditionGroup == null) {
			return false;
		}
		
		boolean flag = true;
		flag &= super.removeInnerElementDelegate(conditions) 
				& this.conditionGroup.removeGroupElement(conditions);
		destroyConditionGroup();
		return flag;
	}
	

	/* CRUD for collections, maps, sub-groups: attributes */

	public Attribute getAttribute(String key) {
		return (this.attributeGroup != null) ? this.attributeGroup.getAttribute(key) : null;
	}

	public boolean containAttribute(Attribute attribute) throws LIMException{
		return (this.attributeGroup != null) 
				? this.attributeGroup.containGroupElement(attribute) : false;
	}

	public boolean addAttribute(String key, AbstractElement element) throws LIMException {
		if(key == null || element == null) {
			return false;
		}
		return super.addInnerElementDelegate(element) 
				& addAttribute(new Attribute(key, element));
	}
	
	public boolean addAttribute(Attribute... attributes) throws LIMException {
		return addAttribute(CollectionUtil.generateCollection(attributes));
	}
	
	public boolean addAttribute(Collection<Attribute> attributes) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(attributes)) {
			return false;
		}

		initAttributeGroup();
		boolean flag = true;
		for (Attribute attribute : attributes) {
			if(attribute != null && !this.attributeGroup.containGroupElement(attribute)) {
				flag &= super.addInnerElementDelegate(attribute) 
						& this.attributeGroup.addGroupElement(attribute) 
						& super.addInnerElementDelegate(new AffiliationRelation(this, attribute));
			}
		}
		destroyAttributeGroup();
		return flag;
	}
	
	public boolean removeAttribute(Attribute... attributes) throws LIMException {
		return removeAttribute(CollectionUtil.generateCollection(attributes));
	}
	
	public boolean removeAttribute(Collection<Attribute> attributes) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(attributes) || this.attributeGroup == null) {
			return false;
		}

		boolean flag = true;
		for (Attribute attribute : attributes) {
			if(attribute != null) {
				flag &= super.removeInnerElementDelegate(attribute) 
						& this.attributeGroup.removeGroupElement(attribute) 
						& super.removeRelationInInnerElementDelegate(this, attribute, AffiliationRelation.class);
			}
		}
		destroyAttributeGroup();
		return flag;
	}


	/* overridden methods */

	@Override
	public AbstractElement getXmlElement() throws LIMException {
		if(this.xmlElement == null) {
			if(this.getClass().equals(Concept.class)) {
				this.xmlElement = this;
			}else {
				this.xmlElement = new Concept(this);
			}
		}
		return this.xmlElement;
	}

	@Override
	public AbstractElement getPojoElement(Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (this.pojoElement == null) {
			if (StringUtils.isEmpty(this.getType())  
					|| !this.getClass().equals(Concept.class)) {
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
		if (element instanceof Concept) {
			if (super.reload(element, rootElementMap) == null) {
				return null;
			}
			load((Concept) element);
			reloadFromRootElementMap(rootElementMap);
			return (IIntegratable) this;
		} else {
			return null;
		}
	}
	
	private void load(Concept element) {
		if(element != null) {
			this.baseSubConceptGroup = element.baseSubConceptGroup;
			this.subConceptGroup = element.subConceptGroup;
			this.baseConditionGroup = element.baseConditionGroup;
			this.conditionGroup = element.conditionGroup;
			this.baseAttributeGroup = element.baseAttributeGroup;
			this.attributeGroup = element.attributeGroup;
		}
	}

	private void reloadFromRootElementMap(Map<String, AbstractElement> rootElementMap) {
		if(CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		if(this.baseSubConceptGroup != null && this.baseSubConceptGroup.getId() != null) {
			AbstractElement element = rootElementMap.get(this.baseSubConceptGroup.getId());
			this.subConceptGroup = (element instanceof ConceptGroup) 
					? (ConceptGroup) element : this.subConceptGroup;
		}
		if(this.baseConditionGroup != null && this.baseConditionGroup.getId() != null) {
			AbstractElement element = rootElementMap.get(this.baseConditionGroup.getId());
			this.conditionGroup = (element instanceof ConditionGroup) 
					? (ConditionGroup) element : this.conditionGroup;
		}
		if(this.baseAttributeGroup != null && this.baseAttributeGroup.getId() != null) {
			AbstractElement element = rootElementMap.get(this.baseAttributeGroup.getId());
			this.attributeGroup = (element instanceof AttributeGroup) 
					? (AttributeGroup) element : this.attributeGroup;
		}
	}


	@Override
	public Concept cloneElement() throws LIMException {
		Concept cloneElement = (Concept) super.cloneElement();
		
		cloneElement.baseSubConceptGroup = (this.baseSubConceptGroup != null) 
				? (BaseElement) this.baseSubConceptGroup.cloneElement() : cloneElement.baseSubConceptGroup;
		cloneElement.subConceptGroup = (this.subConceptGroup != null) 
				? (ConceptGroup) this.subConceptGroup.cloneElement() : cloneElement.subConceptGroup;
		
		cloneElement.baseConditionGroup = (this.baseConditionGroup != null) 
				? (BaseElement) this.baseConditionGroup.cloneElement() : cloneElement.baseConditionGroup;
		cloneElement.conditionGroup = (this.conditionGroup != null) 
				? (ConditionGroup) this.conditionGroup.cloneElement() : cloneElement.conditionGroup;
		
		cloneElement.baseAttributeGroup = (this.baseAttributeGroup != null) 
				? (BaseElement) this.baseAttributeGroup.cloneElement() : cloneElement.baseAttributeGroup;
		cloneElement.attributeGroup = (this.attributeGroup != null) 
				? (AttributeGroup) this.attributeGroup.cloneElement() : cloneElement.attributeGroup;

		return cloneElement;
	}

}
