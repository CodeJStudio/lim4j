package com.codejstudio.lim.pojo.argument;

import java.util.Collection;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.StringUtils;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.ObjectUtil;
import com.codejstudio.lim.pojo.AbstractElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.InformationUnit;
import com.codejstudio.lim.pojo.attribute.Attribute;
import com.codejstudio.lim.pojo.attribute.AttributeGroup;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.i.IGroupable;
import com.codejstudio.lim.pojo.i.IIntegratable;
import com.codejstudio.lim.pojo.relation.AffiliationRelation;
import com.codejstudio.lim.pojo.relation.CausalityRelation;
import com.codejstudio.lim.pojo.statement.JudgedStatement;
import com.codejstudio.lim.pojo.statement.JudgedStatementGroup;

/**
 * Argument.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = Argument.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
	"baseValidity",
	"baseCausalityRelation",
    "baseSubArgumentGroup",
    "baseJudgedStatementGroup",
    "baseConditionGroup",
    "baseAttributeGroup",
})
public class Argument extends InformationUnit {

	/* enumeration */
	
	public enum ArgumentAttributeType{
		VALIDITY,
		EVIDENCE,
		CONCLUSION,
		;
	}


	/* constants */
	
	public static final String TYPE_NAME = "argument";


	/* variables */

	@XmlElement(name = "validity")
	protected BaseElement baseValidity;

	@XmlElement(name = "causality-relation")
	protected BaseElement baseCausalityRelation;

	protected CausalityRelation causalityRelation;


	/* variables: collections, maps, sub-groups */

	@XmlElement(name = "sub-Argument-group")
	private BaseElement baseSubArgumentGroup;

	private ArgumentGroup subArgumentGroup;
	
	@XmlElement(name = "judged-statement-group")
	private BaseElement baseJudgedStatementGroup;
	
	private JudgedStatementGroup judgedStatementGroup;
	
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
	public Argument() throws LIMException {
		super();
	}
	
	public Argument(Argument argument) throws LIMException {
		super(argument);
		load(argument);
	}

	public Argument(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public Argument(boolean ifInitId, boolean ifInitType, String discription) throws LIMException {
		super(ifInitId, ifInitType, discription);
	}
	

	public Argument(String discription) throws LIMException {
		super(true, false, discription);
	}
	

	/* initializers */

	private void initSubArgumentGroup() throws LIMException {
		if(this.subArgumentGroup == null) {
			this.subArgumentGroup = new ArgumentGroup(true);
			super.addInnerElementDelegate(this.subArgumentGroup);
			this.baseSubArgumentGroup = new BaseElement(subArgumentGroup);
		}
	}

	private void initJudgedStatementGroup() throws LIMException {
		if(this.judgedStatementGroup == null) {
			this.judgedStatementGroup = new JudgedStatementGroup(true);
			super.addInnerElementDelegate(this.judgedStatementGroup);
			this.baseJudgedStatementGroup = new BaseElement(judgedStatementGroup);
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

	private void destroySubArgumentGroup() throws LIMException {
		if(this.subArgumentGroup != null && this.subArgumentGroup.size() == 0) {
			this.baseSubArgumentGroup = null;
			super.removeInnerElementDelegate(this.subArgumentGroup);
			this.subArgumentGroup = null;
		}
	}

	private void destroyJudgedStatementGroup() throws LIMException {
		if(this.judgedStatementGroup != null && this.judgedStatementGroup.size() == 0) {
			this.baseJudgedStatementGroup = null;
			super.removeInnerElementDelegate(this.judgedStatementGroup);
			this.judgedStatementGroup = null;
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

	public BaseElement getBaseValidity() {
		return baseValidity;
	}

	@XmlTransient
	public Validity getValidity() {
		Attribute attribute = getAttribute(ArgumentAttributeType.VALIDITY.name());
		return (attribute != null) ? (Validity) attribute.getValue() : null;
	}

	public void setValidity(Validity validity) throws LIMException {
		Validity v = getValidity();
		if(ObjectUtil.checkEquals(v, validity)) {
			return;
		}

		if(v != null) {
			this.baseValidity = null;
			super.removeInnerElementDelegate(v);
			removeAttribute(ArgumentAttributeType.VALIDITY.name());
		}
		if(validity != null) {
			addAttribute(new Attribute(ArgumentAttributeType.VALIDITY.name(), validity));
			super.addInnerElementDelegate(validity);
			this.baseValidity = new BaseElement(validity);
		}
	}


	public BaseElement getBaseCausalityRelation() {
		return baseCausalityRelation;
	}

	public CausalityRelation getCausalityRelation() {
		return causalityRelation;
	}

	@XmlTransient
	private void setCausalityRelation(CausalityRelation causalityRelation) throws LIMException {
		if(ObjectUtil.checkEquals(this.causalityRelation, causalityRelation)) {
			return;
		}
		
		if(this.causalityRelation != null) {
			this.baseCausalityRelation = null;
			super.removeInnerElementDelegate(this.causalityRelation);
			this.causalityRelation = null;
		}
		if(causalityRelation != null) {
			this.causalityRelation = causalityRelation;
			super.addInnerElementDelegate(causalityRelation);
			this.baseCausalityRelation = new BaseElement(causalityRelation);
		}
	}

	private void updateCausalityRelation() throws LIMException {
		JudgedStatementGroup evidenceGroup = getEvidenceGroup();
		JudgedStatementGroup conclusionGroup = getConclusionGroup();
		if(IGroupable.checkNullOrEmpty(evidenceGroup) 
				|| IGroupable.checkNullOrEmpty(conclusionGroup)) {
			setCausalityRelation(null);
			return;
		}

		CausalityRelation cr = getCausalityRelation();
		if(cr == null) {
			cr = new CausalityRelation(true);
		}
		cr.setCause(evidenceGroup);
		cr.setEffect(conclusionGroup);
		setCausalityRelation(cr);
	}

	public ArgumentGroup getSubArgumentGroup() {
		return subArgumentGroup;
	}

	public JudgedStatementGroup getJudgedStatementGroup() {
		return judgedStatementGroup;
	}

	public ConditionGroup getConditionGroup() {
		return conditionGroup;
	}

	public AttributeGroup getAttributeGroup() {
		return attributeGroup;
	}
	

	/* CRUD for collections, maps, sub-groups: evidences */

	@XmlTransient
	public JudgedStatementGroup getEvidenceGroup() throws LIMException {
		return getEvidenceGroup(false);
	}

	public JudgedStatementGroup getEvidenceGroup(boolean ifInit) throws LIMException {
		Attribute attribute = getAttribute(ArgumentAttributeType.EVIDENCE.name());
		if(attribute == null && ifInit) {
			JudgedStatementGroup evidenceGroup = new JudgedStatementGroup(true);
			super.addInnerElementDelegate(evidenceGroup);

			attribute = new Attribute(ArgumentAttributeType.EVIDENCE.name(), evidenceGroup);
			addAttribute(attribute);
		}

		return (attribute != null) ? (JudgedStatementGroup) attribute.getValue() : null;
	}

	
	public boolean addEvidence(JudgedStatement... evidences) throws LIMException {
		return addEvidence(CollectionUtil.generateCollection(evidences));
	}
	
	public boolean addEvidence(Collection<JudgedStatement> evidences) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(evidences)) {
			return false;
		}
		
		JudgedStatementGroup evidenceGroup = getEvidenceGroup(true);
		boolean flag = true;
		flag &= super.addInnerElementDelegate(evidences) 
				& addJudgedStatement(evidences)
				& evidenceGroup.addGroupElement(evidences);
		updateCausalityRelation();
		return flag;
	}

	public boolean removeEvidence(JudgedStatement... evidences) throws LIMException {
		return removeEvidence(CollectionUtil.generateCollection(evidences));
	}
	
	public boolean removeEvidence(Collection<JudgedStatement> evidences) throws LIMException {
		JudgedStatementGroup evidenceGroup = getEvidenceGroup(false);
		if(CollectionUtil.checkNullOrEmpty(evidences) || evidenceGroup == null) {
			return false;
		}
		
		boolean flag = true;
		flag &= super.removeInnerElementDelegate(evidences) 
				& removeJudgedStatement(evidences)
				& evidenceGroup.removeGroupElement(evidences);
		updateCausalityRelation();
		return flag;
	}
	

	/* CRUD for collections, maps, sub-groups: conclusions */

	@XmlTransient
	public JudgedStatementGroup getConclusionGroup() throws LIMException {
		return getConclusionGroup(false);
	}

	public JudgedStatementGroup getConclusionGroup(boolean ifInit) throws LIMException {
		Attribute attribute = getAttribute(ArgumentAttributeType.CONCLUSION.name());
		if(attribute == null) {
			JudgedStatementGroup conclusionGroup = new JudgedStatementGroup(true);
			super.addInnerElementDelegate(conclusionGroup);

			attribute = new Attribute(ArgumentAttributeType.CONCLUSION.name(), conclusionGroup);
			addAttribute(attribute);
		}
		return (attribute != null) ? (JudgedStatementGroup) attribute.getValue() : null;
	}

	
	public boolean addConclusion(JudgedStatement... conclusions) throws LIMException {
		return addConclusion(CollectionUtil.generateCollection(conclusions));
	}
	
	public boolean addConclusion(Collection<JudgedStatement> conclusions) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(conclusions)) {
			return false;
		}
		
		JudgedStatementGroup conclusionGroup = getConclusionGroup(true);
		boolean flag = true;
		flag &= super.addInnerElementDelegate(conclusions) 
				& addJudgedStatement(conclusions)
				& conclusionGroup.addGroupElement(conclusions);
		updateCausalityRelation();
		return flag;
	}
	
	public boolean removeConclusion(JudgedStatement... conclusions) throws LIMException {
		return removeConclusion(CollectionUtil.generateCollection(conclusions));
	}
	
	public boolean removeConclusion(Collection<JudgedStatement> conclusions) throws LIMException {
		JudgedStatementGroup conclusionGroup = getConclusionGroup(false);
		if(CollectionUtil.checkNullOrEmpty(conclusions) || conclusionGroup == null) {
			return false;
		}

		boolean flag = true;
		flag &= super.removeInnerElementDelegate(conclusions) 
				& removeJudgedStatement(conclusions)
				& conclusionGroup.removeGroupElement(conclusions);
		updateCausalityRelation();
		return flag;
	}
	

	/* CRUD for collections, maps, sub-groups: sub-arguments */

	public boolean containSubArgument(Argument argument) throws LIMException{
		return (this.subArgumentGroup != null) 
				? this.subArgumentGroup.containGroupElement(argument) : false;
	}

	public boolean addSubArgument(Argument... arguments) throws LIMException {
		return addSubArgument(CollectionUtil.generateCollection(arguments));
	}

	public boolean addSubArgument(Collection<Argument> arguments) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(arguments) 
				|| CollectionUtil.onlyContains(arguments, this)) {
			return false;
		}
		
		Collection<Argument> c = CollectionUtil.removeElements(arguments, this);
		initSubArgumentGroup();
		boolean flag = true;
		flag &= super.addInnerElementDelegate(c) 
				& this.subArgumentGroup.addGroupElement(c);
		destroySubArgumentGroup();
		return flag;
	}
	
	public boolean removeSubArgument(Argument... arguments) throws LIMException {
		return removeSubArgument(CollectionUtil.generateCollection(arguments));
	}

	public boolean removeSubArgument(Collection<Argument> arguments) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(arguments) || this.subArgumentGroup == null) {
			return false;
		}
		
		boolean flag = true;
		flag &= super.removeInnerElementDelegate(arguments) 
				& this.subArgumentGroup.removeGroupElement(arguments);
		destroySubArgumentGroup();
		return flag;
	}


	/* CRUD for collections, maps, sub-groups: judged-statements */

	public boolean containJudgedStatement(JudgedStatement statement) throws LIMException{
		return (this.judgedStatementGroup != null) 
				? this.judgedStatementGroup.containGroupElement(statement) : false;
	}

	public boolean addJudgedStatement(JudgedStatement... statements) throws LIMException {
		return addJudgedStatement(CollectionUtil.generateCollection(statements));
	}

	public boolean addJudgedStatement(Collection<JudgedStatement> statements) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(statements)) {
			return false;
		}
		
		initJudgedStatementGroup();
		boolean flag = true;
		flag &= super.addInnerElementDelegate(statements) 
				& this.judgedStatementGroup.addGroupElement(statements);
		destroyJudgedStatementGroup();
		return flag;
	}
	
	public boolean removeJudgedStatement(JudgedStatement... statements) throws LIMException {
		return removeJudgedStatement(CollectionUtil.generateCollection(statements));
	}

	public boolean removeJudgedStatement(Collection<JudgedStatement> statements) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(statements) || this.judgedStatementGroup == null) {
			return false;
		}
		
		boolean flag = true;
		flag &= super.removeInnerElementDelegate(statements) 
				& this.judgedStatementGroup.removeGroupElement(statements);
		destroyJudgedStatementGroup();
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
			if(this.getClass().equals(Argument.class)) {
				this.xmlElement = this;
			}else {
				this.xmlElement = new Argument(this);
			}
		}
		return this.xmlElement;
	}

	@Override
	public AbstractElement getPojoElement(Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (this.pojoElement == null) {
			if (StringUtils.isEmpty(this.getType())  
					|| !this.getClass().equals(Argument.class)) {
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
		if (element instanceof Argument) {
			if (super.reload(element, rootElementMap) == null) {
				return null;
			}
			load((Argument) element);
			reloadFromRootElementMap(rootElementMap);
			return (IIntegratable) this;
		} else {
			return null;
		}
	}
	
	private void load(Argument element) {
		if(element != null) {
			this.baseValidity = element.baseValidity;
			this.baseCausalityRelation = element.baseCausalityRelation;
			this.causalityRelation = element.causalityRelation;
			this.baseSubArgumentGroup = element.baseSubArgumentGroup;
			this.subArgumentGroup = element.subArgumentGroup;
			this.baseJudgedStatementGroup = element.baseJudgedStatementGroup;
			this.judgedStatementGroup = element.judgedStatementGroup;
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
		
		if(this.baseCausalityRelation != null && this.baseCausalityRelation.getId() != null) {
			AbstractElement element = rootElementMap.get(this.baseCausalityRelation.getId());
			this.causalityRelation = (element instanceof CausalityRelation) 
					? (CausalityRelation) element : this.causalityRelation;
		}
		if(this.baseSubArgumentGroup != null && this.baseSubArgumentGroup.getId() != null) {
			AbstractElement element = rootElementMap.get(this.baseSubArgumentGroup.getId());
			this.subArgumentGroup = (element instanceof ArgumentGroup) 
					? (ArgumentGroup) element : this.subArgumentGroup;
		}
		if(this.baseJudgedStatementGroup != null && this.baseJudgedStatementGroup.getId() != null) {
			AbstractElement element = rootElementMap.get(this.baseJudgedStatementGroup.getId());
			this.judgedStatementGroup = (element instanceof JudgedStatementGroup) 
					? (JudgedStatementGroup) element : this.judgedStatementGroup;
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
	public Argument cloneElement() throws LIMException {
		Argument cloneElement = (Argument) super.cloneElement();
		
		cloneElement.baseValidity = (this.baseValidity != null) 
				? (BaseElement) this.baseValidity.cloneElement() : cloneElement.baseValidity;
		
		cloneElement.baseCausalityRelation = (this.baseCausalityRelation != null) 
				? (BaseElement) this.baseCausalityRelation.cloneElement() : cloneElement.baseCausalityRelation;
		cloneElement.causalityRelation = (this.causalityRelation != null) 
				? (CausalityRelation) this.causalityRelation.cloneElement() : cloneElement.causalityRelation;
		
		cloneElement.baseSubArgumentGroup = (this.baseSubArgumentGroup != null) 
				? (BaseElement) this.baseSubArgumentGroup.cloneElement() : cloneElement.baseSubArgumentGroup;
		cloneElement.subArgumentGroup = (this.subArgumentGroup != null) 
				? (ArgumentGroup) this.subArgumentGroup.cloneElement() : cloneElement.subArgumentGroup;
		
		cloneElement.baseJudgedStatementGroup = (this.baseJudgedStatementGroup != null) 
				? (BaseElement) this.baseJudgedStatementGroup.cloneElement() : cloneElement.baseJudgedStatementGroup;
		cloneElement.judgedStatementGroup = (this.judgedStatementGroup != null) 
				? (JudgedStatementGroup) this.judgedStatementGroup.cloneElement() : cloneElement.judgedStatementGroup;
		
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
