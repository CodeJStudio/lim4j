package com.codejstudio.lim.pojo.statement;

import java.util.Collection;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.StringUtils;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.AbstractElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.InformationUnit;
import com.codejstudio.lim.pojo.attribute.Attribute;
import com.codejstudio.lim.pojo.attribute.AttributeGroup;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.concept.ConceptGroup;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.i.IIntegratable;
import com.codejstudio.lim.pojo.relation.AffiliationRelation;

/**
 * Statement.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = Statement.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
    "baseConceptGroup",
    "baseConditionGroup",
    "baseAttributeGroup",
})
@XmlSeeAlso({
    JudgedStatement.class,
})
public class Statement extends InformationUnit {

	/* constants */
	
	public static final String TYPE_NAME = "statement";


	/* variables: collections, maps, sub-groups */

	@XmlElement(name = "concept-group")
	private BaseElement baseConceptGroup;

	private ConceptGroup conceptGroup;
	
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
	public Statement() throws LIMException {
		super();
	}

	public Statement(Statement statement) throws LIMException {
		super(statement);
		load(statement);
	}

	public Statement(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public Statement(boolean ifInitId, boolean ifInitType, String discription) throws LIMException {
		super(ifInitId, ifInitType, discription);
	}
	

	public Statement(String discription) throws LIMException {
		super(true, false, discription);
	}
	

	/* initializers */

	private void initConceptGroup() throws LIMException {
		if(this.conceptGroup == null) {
			this.conceptGroup = new ConceptGroup(true);
			super.addInnerElementDelegate(this.conceptGroup);
			this.baseConceptGroup = new BaseElement(conceptGroup);
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

	private void destroyConceptGroup() throws LIMException {
		if(this.conceptGroup != null && this.conceptGroup.size() == 0) {
			this.baseConceptGroup = null;
			super.removeInnerElementDelegate(this.conceptGroup);
			this.conceptGroup = null;
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

	public ConceptGroup getConceptGroup() {
		return conceptGroup;
	}

	public ConditionGroup getConditionGroup() {
		return conditionGroup;
	}

	public AttributeGroup getAttributeGroup() {
		return attributeGroup;
	}


	/* CRUD for collections, maps, sub-groups: concepts */

	public boolean containConcept(Concept concept) throws LIMException{
		return (this.conceptGroup != null) 
				? this.conceptGroup.containGroupElement(concept) : false;
	}

	public boolean addConcept(Concept... concepts) throws LIMException {
		return addConcept(CollectionUtil.generateCollection(concepts));
	}

	public boolean addConcept(Collection<Concept> concepts) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(concepts)) {
			return false;
		}
		
		initConceptGroup();
		boolean flag = true;
		flag &= super.addInnerElementDelegate(concepts) 
				& this.conceptGroup.addGroupElement(concepts);
		destroyConceptGroup();
		return flag;
	}
	
	public boolean removeConcept(Concept... concepts) throws LIMException {
		return removeConcept(CollectionUtil.generateCollection(concepts));
	}

	public boolean removeConcept(Collection<Concept> concepts) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(concepts) || this.conceptGroup == null) {
			return false;
		}
		
		boolean flag = true;
		flag &= super.removeInnerElementDelegate(concepts) 
				& this.conceptGroup.removeGroupElement(concepts);
		destroyConceptGroup();
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
	
	public boolean removeAttribute(String key) throws LIMException {
		return removeAttribute(getAttribute(key));
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
						& super.removeInnerElementDelegate(new AffiliationRelation(this, attribute));
			}
		}
		destroyAttributeGroup();
		return flag;
	}


	/* overridden methods */

	@Override
	public AbstractElement getXmlElement() throws LIMException {
		if(this.xmlElement == null) {
			if(this.getClass().equals(Statement.class)) {
				this.xmlElement = this;
			}else {
				this.xmlElement = new Statement(this);
			}
		}
		return this.xmlElement;
	}

	@Override
	public AbstractElement getPojoElement(Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (this.pojoElement == null) {
			if (StringUtils.isEmpty(this.getType())  
					|| !this.getClass().equals(Statement.class)) {
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
		if (element instanceof Statement) {
			if (super.reload(element, rootElementMap) == null) {
				return null;
			}
			load((Statement) element);
			reloadFromRootElementMap(rootElementMap);
			return (IIntegratable) this;
		} else {
			return null;
		}
	}
	
	private void load(Statement element) {
		if(element != null) {
			this.baseConceptGroup = element.baseConceptGroup;
			this.conceptGroup = element.conceptGroup;
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
		
		if(this.baseConceptGroup != null && this.baseConceptGroup.getId() != null) {
			AbstractElement element = rootElementMap.get(this.baseConceptGroup.getId());
			this.conceptGroup = (element instanceof ConceptGroup) 
					? (ConceptGroup) element : this.conceptGroup;
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
	public Statement cloneElement() throws LIMException {
		Statement cloneElement = (Statement) super.cloneElement();
		
		cloneElement.baseConceptGroup = (this.baseConceptGroup != null) 
				? (BaseElement) this.baseConceptGroup.cloneElement() : cloneElement.baseConceptGroup;
		cloneElement.conceptGroup = (this.conceptGroup != null) 
				? (ConceptGroup) this.conceptGroup.cloneElement() : cloneElement.conceptGroup;
		
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
