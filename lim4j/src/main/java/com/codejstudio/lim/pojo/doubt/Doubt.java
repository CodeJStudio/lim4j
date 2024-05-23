package com.codejstudio.lim.pojo.doubt;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.GroupableUtil;
import com.codejstudio.lim.pojo.util.JAXBBoundClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementEnumConstant;

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

	public enum DoubtType {
		WHAT("what"),
		WHEN("when"),
		WHERE("where"),
		HOW("how"),
		WHY("why"),
		HOW_FAR("how far"),
		;


		/* variables */

		private String typeName;


		/* constructors */

		DoubtType(String typeName) {
			this.typeName = typeName;
		}


		/* getters & setters */

		public String getTypeName() {
			return typeName;
		}
	}


	/* constants */

	private static final long serialVersionUID = -9098728464037744617L;

	public static final String TYPE_NAME = "doubt";


	/* variables */

	@XmlElement(name = "doubt-type")
	protected DoubtType doubtType;

	@XmlElement(name = "target")
	protected BaseElement baseTarget;

	protected GenericElement target;


	/* variables: arrays, collections, maps, groups */

	private static final Collection<Class<? extends BaseElement>> SUB_POJO_CLAZZ_COLLECTION;

	@XmlElement(name = "explanation-group")
	private BaseElement baseExplanationGroup;

	private ExplanationGroup explanationGroup;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Doubt() {
		super();
	}

	public Doubt(Doubt doubt) throws LIMException {
		super(doubt);
		load(doubt);
	}

	public Doubt(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public Doubt(boolean initIdFlag, boolean initTypeFlag, String description) throws LIMException {
		super(initIdFlag, initTypeFlag, description);
	}

	public Doubt(boolean initIdFlag, boolean initTypeFlag, GenericElement target, DoubtType doubtType) 
			throws LIMException {
		super(initIdFlag, initTypeFlag);
		setTarget(target);
		setDoubtType(doubtType);
	}


	public Doubt(String description) throws LIMException {
		super(true, false, description);
	}

	public Doubt(GenericElement target, DoubtType doubtType) throws LIMException {
		this(true, false, target, doubtType);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			SUB_POJO_CLAZZ_COLLECTION = CollectionUtil.generateNewCollection();

			PojoElementClassConstant.registerElementClassForInit(Doubt.class);
			JAXBBoundClassConstant.registerBoundClassForInit(Doubt.class);
			PojoElementEnumConstant.registerEnumClassForInit(DoubtType.class);
			Doubt.registerSubPojoClassForInit(Doubt.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	private void initExplanationGroup() throws LIMException {
		if (this.explanationGroup == null) {
			this.explanationGroup = new ExplanationGroup(true);
			super.addInnerElementDelegate(this.explanationGroup);
			this.baseExplanationGroup = new BaseElement(explanationGroup);
		}
	}


	/* destroyers */

	private void destroyExplanationGroup() throws LIMException {
		if (this.explanationGroup != null && this.explanationGroup.size() == 0) {
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
		ElementTrace.log.info(this.toBaseString() + ": setDoubtType(" + doubtType + ")");
	}

	@XmlTransient
	public BaseElement getBaseTarget() {
		return baseTarget;
	}

	public void setBaseTarget(BaseElement baseTarget) {
		this.baseTarget = baseTarget;
	}

	@XmlTransient
	public GenericElement getTarget() {
		return target;
	}

	public boolean setTarget(final GenericElement target) throws LIMException {
		if (Objects.equals(this.target, target)) {
			return true;
		}

		boolean flag = true;
		if (this.target != null) {
			this.baseTarget = null;
			flag &= super.removeInnerElementDelegate(this.target);
			this.target = null;
		}
		if (target != null) {
			this.target = target;
			flag &= super.addInnerElementDelegate(target);
			this.baseTarget = new BaseElement(target);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setTarget(" 
					+ ((target == null) ? null : target.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setTarget(" 
					+ ((target == null) ? null : target.toBaseString()) + ")");
		}
		return flag;
	}


	@XmlTransient
	public BaseElement getBaseExplanationGroup() {
		return baseExplanationGroup;
	}

	public void setBaseExplanationGroup(BaseElement baseExplanationGroup) {
		this.baseExplanationGroup = baseExplanationGroup;
	}

	public ExplanationGroup getExplanationGroup() {
		return explanationGroup;
	}


	/* CRUD for arrays, collections, maps, groups: explanations */

	public boolean containExplanation(final Explanation explanation) {
		return (this.explanationGroup == null) 
				? false : this.explanationGroup.containGroupElement(explanation);
	}

	public boolean addExplanation(final Explanation... explanations) throws LIMException {
		return addExplanation((explanations == null) ? null : Arrays.asList(explanations));
	}

	public boolean addExplanation(final Collection<Explanation> explanationCollection) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(explanationCollection)) {
			return false;
		}

		try {
			initExplanationGroup();
			boolean flag = true;
			for (Explanation explanation : explanationCollection) {
				if (explanation == null || this.explanationGroup.containGroupElement(explanation)) {
					continue;
				}
				flag &= super.addInnerElementDelegate(explanation) 
						& this.explanationGroup.addGroupElement(explanation);
				if (explanation.getTargetDoubt() == null || explanation.getTargetDoubt() != this) {
					explanation.setTargetDoubt(this);
				}
			}
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": addExplanation(" 
						+ BaseElement.toBaseString(explanationCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to addExplanation(" 
						+ BaseElement.toBaseString(explanationCollection) + ")");
			}
			return flag;
		} finally {
			destroyExplanationGroup();
		}
	}

	public boolean removeExplanation(final Explanation... explanations) throws LIMException {
		return removeExplanation((explanations == null) ? null : Arrays.asList(explanations));
	}

	public boolean removeExplanation(final Collection<Explanation> explanationCollection) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(explanationCollection) 
				|| GroupableUtil.checkNullOrEmpty(this.explanationGroup)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeExplanation(" 
					+ BaseElement.toBaseString(explanationCollection) + ")");
			return false;
		}

		try {
			boolean flag = super.removeInnerElementDelegate(explanationCollection) 
					& this.explanationGroup.removeGroupElement(explanationCollection);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeExplanation(" 
						+ BaseElement.toBaseString(explanationCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeExplanation(" 
						+ BaseElement.toBaseString(explanationCollection) + ")");
			}
			return flag;
		} finally {
			destroyExplanationGroup();
		}
	}


	/* overridden methods */

	@Override
	public IConvertible getXmlElement() throws LIMException {
		if (this.xmlElement == null) {
			this.xmlElement = this.getClass().equals(Doubt.class) ? this : new Doubt(this);
		}
		return this.xmlElement;
	}

	@Override
	public IConvertible getPojoElement(final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (this.pojoElement == null) {
			this.pojoElement = (StringUtil.isEmpty(this.getType()) 
							|| !this.getClass().equals(Doubt.class)) 
					? this : super.generatePojoElementDelegate();
		}
		this.pojoElement.reload(this, rootElementMap, rootActionableElementMap);
		return this.pojoElement;
	}


	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (!(convertible instanceof Doubt) 
				|| super.reload(convertible, rootElementMap, rootActionableElementMap) == null) {
			return null;
		}
		load((Doubt) convertible);
		reloadFromRootElementMap(rootElementMap);
		return (IConvertible) this;
	}

	private void load(final Doubt element) {
		if (element != null) {
			this.doubtType = element.doubtType;
			this.baseTarget = element.baseTarget;
			this.target = element.target;
			this.baseExplanationGroup = element.baseExplanationGroup;
			this.explanationGroup = element.explanationGroup;
		}
	}

	private void reloadFromRootElementMap(final Map<String, GenericElement> rootElementMap) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		if (this.baseTarget != null && this.baseTarget.getId() != null) {
			GenericElement ge = rootElementMap.get(this.baseTarget.getId());
			this.target = (ge != null) ? ge : this.target;
			super.addInnerElementDelegate(this.target);
		}
		if (this.baseExplanationGroup != null && this.baseExplanationGroup.getId() != null) {
			GenericElement ge = rootElementMap.get(this.baseExplanationGroup.getId());
			this.explanationGroup = (ge instanceof ExplanationGroup) 
					? (ExplanationGroup) ge : this.explanationGroup;
			super.addInnerElementDelegate(this.explanationGroup);
		}
	}


	@Override
	public Doubt cloneElement(final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				String type;
				if (value != null && value.getClass().equals(Doubt.class) 
						&& ((type = value.getType()) == null || type.equals(TYPE_NAME))) {
					return (Doubt) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		Doubt clonedElement = (Doubt) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public Doubt cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof Doubt) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof Doubt)) 
				? null : cloneToElement((Doubt) ce, null);
	}

	private Doubt cloneToElement(final Doubt clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.doubtType = this.doubtType;

		clonedElement.baseTarget = (this.baseTarget != null) 
				? (BaseElement) this.baseTarget.cloneElement(clonedElementMap) : clonedElement.baseTarget;
		clonedElement.target = (this.target != null) 
				? this.target.cloneElement(clonedElementMap) : clonedElement.target;

		clonedElement.baseExplanationGroup = (this.baseExplanationGroup != null) 
				? (BaseElement) this.baseExplanationGroup.cloneElement(clonedElementMap) 
				: clonedElement.baseExplanationGroup;
		clonedElement.explanationGroup = (this.explanationGroup != null) 
				? (ExplanationGroup) this.explanationGroup.cloneElement(clonedElementMap) 
				: clonedElement.explanationGroup;

		return clonedElement;
	}


	/* static methods */

	protected static void registerSubPojoClassForInit(final Class<? extends BaseElement> clazz) {
		if (Doubt.class.isAssignableFrom(clazz)) {
			SUB_POJO_CLAZZ_COLLECTION.add(clazz);
		}
	}

}
