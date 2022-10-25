package com.codejstudio.lim.pojo.doubt;

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
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.i.IIntegratable;

/**
 * Doubt.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = Doubt.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
    "doubtType",
    "baseTarget",
    "baseExplanationGroup",
})
public class Doubt extends InformationElement {

	/* enumeration */
	
	public enum DoubtType{
		WHAT("what"),
		WHEN("when"),
		WHERE("where"),
		HOW("how"),
		WHY("why"),
		HOW_FAR("how far"),
		;
		
		private String typeName;

		DoubtType(String typeName) {
			this.typeName = typeName;
		}

		public String getTypeName() {
			return typeName;
		}
	}

	
	/* constants */
	
	public static final String TYPE_NAME = "doubt";

	
	/* variables */
	
	@XmlElement(name = "doubt-type")
	protected DoubtType doubtType;

	@XmlElement(name = "target")
	protected BaseElement baseTarget;
	
	protected AbstractElement target;


	/* variables: collections, maps, sub-groups */
	
	@XmlElement(name = "explanation-group")
	private BaseElement baseExplanationGroup;
	
	private ExplanationGroup explanationGroup;

	
	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Doubt() throws LIMException {
		super();
	}
	
	public Doubt(Doubt doubt) throws LIMException {
		super(doubt);
		load(doubt);
	}

	public Doubt(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public Doubt(boolean ifInitId, boolean ifInitType, String discription) throws LIMException {
		super(ifInitId, ifInitType, discription);
	}

	public Doubt(boolean ifInitId, boolean ifInitType, AbstractElement target, DoubtType doubtType) throws LIMException {
		super(ifInitId, ifInitType);
		setTarget(target);
		setDoubtType(doubtType);
	}


	public Doubt(String discription) throws LIMException {
		super(true, false, discription);
	}

	public Doubt(AbstractElement target, DoubtType doubtType) throws LIMException {
		this(true, false, target, doubtType);
	}

	
	/* initializers */
	
	private void initExplanationGroup() throws LIMException {
		if(this.explanationGroup == null) {
			this.explanationGroup = new ExplanationGroup(true);
			super.addInnerElementDelegate(this.explanationGroup);
			this.baseExplanationGroup = new BaseElement(explanationGroup);
		}
	}

	
	/* destroyers */
	
	private void destroyExplanationGroup() throws LIMException {
		if(this.explanationGroup != null && this.explanationGroup.size() == 0) {
			this.baseExplanationGroup = null;
			super.removeInnerElementDelegate(this.explanationGroup);
			this.explanationGroup = null;
		}
	}
	

	/* getters & setters */

	@XmlTransient
	public DoubtType getDoubtType() {
		return doubtType;
	}

	public void setDoubtType(DoubtType doubtType) {
		this.doubtType = doubtType;
	}

	public BaseElement getBaseTarget() {
		return baseTarget;
	}

	@XmlTransient
	public AbstractElement getTarget() {
		return target;
	}

	public void setTarget(AbstractElement target) throws LIMException {
		if(ObjectUtil.checkEquals(this.target, target)) {
			return;
		}
		
		if(this.target != null) {
			this.baseTarget = null;
			super.removeInnerElementDelegate(this.target);
			this.target = null;
		}
		if(target != null) {
			this.target = target;
			super.addInnerElementDelegate(target);
			this.baseTarget = new BaseElement(target);
		}
	}

	public ExplanationGroup getExplanationGroup() {
		return explanationGroup;
	}

	/* CRUD for collections, maps, sub-groups: explanations */

	public boolean containExplanation(Explanation explanation) throws LIMException{
		return (this.explanationGroup != null) 
				? this.explanationGroup.containGroupElement(explanation) : false;
	}

	public boolean addExplanation(Explanation... explanations) throws LIMException {
		return addExplanation(CollectionUtil.generateCollection(explanations));
	}

	public boolean addExplanation(Collection<Explanation> explanations) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(explanations)) {
			return false;
		}
		
		initExplanationGroup();
		boolean flag = true;
		for (Explanation explanation : explanations) {
			if(explanation != null && !this.explanationGroup.containGroupElement(explanation)) {
				flag &= super.addInnerElementDelegate(explanation) 
						& this.explanationGroup.addGroupElement(explanation);
				if(explanation.targetDoubt == null || explanation.targetDoubt != this) {
					explanation.setTargetDoubt(this);	
				}
			}
		}
		destroyExplanationGroup();
		return flag;
	}

	public boolean removeExplanation(Explanation... explanations) throws LIMException {
		return removeExplanation(CollectionUtil.generateCollection(explanations));
	}

	public boolean removeExplanation(Collection<Explanation> explanations) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(explanations) || this.explanationGroup == null) {
			return false;
		}
		
		boolean flag = true;
		flag &= super.removeInnerElementDelegate(explanations) 
				& this.explanationGroup.removeGroupElement(explanations);
		destroyExplanationGroup();
		return flag;
	}


	/* overridden methods */

	@Override
	public AbstractElement getXmlElement() throws LIMException {
		if(this.xmlElement == null) {
			if(this.getClass().equals(Doubt.class)) {
				this.xmlElement = this;
			}else {
				this.xmlElement = new Doubt(this);
			}
		}
		return this.xmlElement;
	}

	@Override
	public AbstractElement getPojoElement(Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (this.pojoElement == null) {
			if (StringUtils.isEmpty(this.getType())  
					|| !this.getClass().equals(Doubt.class)) {
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
		if (element instanceof Doubt) {
			if (super.reload(element, rootElementMap) == null) {
				return null;
			}
			load((Doubt) element);
			reloadFromRootElementMap(rootElementMap);
			return (IIntegratable) this;
		} else {
			return null;
		}
	}
	
	private void load(Doubt element) {
		if(element != null) {
			this.doubtType = element.doubtType;
			this.baseTarget = element.baseTarget;
			this.target = element.target;
			this.baseExplanationGroup = element.baseExplanationGroup;
			this.explanationGroup = element.explanationGroup;
		}
	}

	private void reloadFromRootElementMap(Map<String, AbstractElement> rootElementMap) {
		if(CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		if(this.baseTarget != null && this.baseTarget.getId() != null) {
			AbstractElement element = rootElementMap.get(this.baseTarget.getId());
			this.target = (element != null) ? element : this.target;
		}
		if(this.baseExplanationGroup != null && this.baseExplanationGroup.getId() != null) {
			AbstractElement element = rootElementMap.get(this.baseExplanationGroup.getId());
			this.explanationGroup = (element instanceof ExplanationGroup) 
					? (ExplanationGroup) element : this.explanationGroup;
		}
	}


	@Override
	public Doubt cloneElement() throws LIMException {
		Doubt cloneElement = (Doubt) super.cloneElement();
		cloneElement.doubtType = this.doubtType;
		
		cloneElement.baseTarget = (this.baseTarget != null) 
				? (BaseElement) this.baseTarget.cloneElement() : cloneElement.baseTarget;
		cloneElement.target = (this.target != null) 
				? this.target.cloneElement() : cloneElement.target;
		
		cloneElement.baseExplanationGroup = (this.baseExplanationGroup != null) 
				? (BaseElement) this.baseExplanationGroup.cloneElement() : cloneElement.baseExplanationGroup;
		cloneElement.explanationGroup = (this.explanationGroup != null) 
				? (ExplanationGroup) this.explanationGroup.cloneElement() : cloneElement.explanationGroup;

		return cloneElement;
	}

}
