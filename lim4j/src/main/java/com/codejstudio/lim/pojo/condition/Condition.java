package com.codejstudio.lim.pojo.condition;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
import com.codejstudio.lim.pojo.InformationUnit;
import com.codejstudio.lim.pojo.LogicValue;
import com.codejstudio.lim.pojo.i.IActionable;
import com.codejstudio.lim.pojo.i.IConditionable;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.i.IValuable;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.InputParameter;
import com.codejstudio.lim.pojo.util.JAXBBoundClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * Condition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = Condition.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
	"baseDescriptionElement",
	"baseVerifier",
})
public class Condition extends InformationUnit {

	/* constants */

	private static final long serialVersionUID = 1309905925733373730L;

	public static final String TYPE_NAME = "condition";

	public static final String INPUT_PARAMETER_NAME_CONDITION = "condition";
	public static final String INPUT_PARAMETER_NAME_VERIFIEE = "verifiee";


	/* variables */

	@XmlAttribute
	private Boolean implicit;

	@XmlElement(name = "description-element")
	protected BaseElement baseDescriptionElement;

	protected InformationElement descriptionElement;


	/* variables: arrays, collections, maps, groups */

	private static final Collection<Class<? extends BaseElement>> SUB_POJO_CLAZZ_COLLECTION;


	@XmlElement(name = "verifier")
	protected BaseElement baseVerifier;

	protected GenericActionableElement verifier;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Condition() {
		super();
	}

	public Condition(Condition condition) throws LIMException {
		super(condition);
		load(condition);
	}

	public Condition(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public Condition(boolean initIdFlag, boolean initTypeFlag, InformationElement descriptionElement) 
			throws LIMException {
		super(initIdFlag, initTypeFlag);
		setDescriptionElement(descriptionElement);
	}


	public Condition(InformationElement descriptionElement) throws LIMException {
		this(true, false, descriptionElement);
	}

	public Condition(InformationElement descriptionElement, Boolean implicit) throws LIMException {
		this(true, false, descriptionElement);
		setImplicit(implicit);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			SUB_POJO_CLAZZ_COLLECTION = CollectionUtil.generateNewCollection();

			PojoElementClassConstant.registerElementClassForInit(Condition.class);
			JAXBBoundClassConstant.registerBoundClassForInit(Condition.class);
			Condition.registerSubPojoClassForInit(Condition.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	/* getters & setters */

	@XmlTransient
	public Boolean getImplicit() {
		return implicit;
	}

	public void setImplicit(Boolean implicit) {
		this.implicit = (implicit == null || !implicit) ? null : true;
	}

	@XmlTransient
	public BaseElement getBaseDescriptionElement() {
		return baseDescriptionElement;
	}

	public void setBaseDescriptionElement(BaseElement baseDescriptionElement) {
		this.baseDescriptionElement = baseDescriptionElement;
	}

	@XmlTransient
	public InformationElement getDescriptionElement() {
		return descriptionElement;
	}

	public boolean setDescriptionElement(final InformationElement descriptionElement) throws LIMException {
		if (Objects.equals(this.descriptionElement, descriptionElement)) {
			return true;
		}

		boolean flag = true;
		if (this.descriptionElement != null) {
			this.baseDescriptionElement = null;
			flag &= super.removeInnerElementDelegate(this.descriptionElement);
			this.descriptionElement = null;
			super.setDescription(null, true, false);
		}
		if (descriptionElement != null) {
			this.descriptionElement = descriptionElement;
			flag &= super.addInnerElementDelegate(descriptionElement);
			this.baseDescriptionElement = new BaseElement(descriptionElement);
			super.setDescription(descriptionElement.getDescription(), true, false);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setDescriptionElement(" 
					+ ((descriptionElement == null) ? null : descriptionElement.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setDescriptionElement(" 
					+ ((descriptionElement == null) ? null : descriptionElement.toBaseString()) + ")");
		}
		return flag;
	}

	@Override
	public String getDescription() {
		String dsc = super.getDescription();
		return (dsc != null) ? dsc 
				: ((this.descriptionElement == null) ? null : this.descriptionElement.getDescription());
	}

	@Override
	public void setDescription(String description) throws LIMException {
		if (this.descriptionElement == null) {
			super.setDescription(description, true, false);
		}
	}


	@XmlTransient
	public BaseElement getBaseVerifier() {
		return baseVerifier;
	}

	public void setBaseVerifier(BaseElement baseVerifier) {
		this.baseVerifier = baseVerifier;
	}

	@XmlTransient
	public GenericActionableElement getVerifier() {
		return verifier;
	}

	public boolean setVerifier(GenericActionableElement verifier) throws LIMException {
		if (Objects.equals(this.verifier, verifier)) {
			return true;
		}

		boolean flag = true;
		if (this.verifier != null) {
			this.baseVerifier = null;
			flag &= super.removeInnerActionableElementDelegate(this.verifier);
			this.verifier = null;
		}
		if (verifier != null) {
			this.verifier = verifier;
			flag &= super.addInnerActionableElementDelegate(verifier);
			this.baseVerifier = new BaseElement(verifier);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setVerifier(" 
					+ ((verifier == null) ? null : verifier.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setVerifier(" 
					+ ((verifier == null) ? null : verifier.toBaseString()) + ")");
		}
		return flag;
	}


	/* class methods */

	public boolean logicValueEquals(final LogicValue logicValue) {
		InformationElement dsce;
		return (logicValue == null || (dsce = this.getDescriptionElement()) == null 
						|| !(dsce instanceof IValuable)) 
				? false : ((IValuable) dsce).getLogicValue().valueEquals(logicValue);
	}


	public boolean verifyDescription(final String description) throws LIMException {
		return verifyDescription(description, false);
	}

	public boolean verifyDescription(final String description, final boolean noneConditionFlag) 
			throws LIMException {
		GenericActionableElement v = this.getVerifier();
		if (!(v instanceof IActionable)) {
			return verifyDescriptionWithoutVerifier(description, noneConditionFlag);
		}

		Object[] obja = ((IActionable) v).execute(InputParameter.generateInstances(
				INPUT_PARAMETER_NAME_CONDITION, this, INPUT_PARAMETER_NAME_VERIFIEE, description));
		return !CollectionUtil.checkNullOrEmpty(obja) && obja[0] instanceof Boolean && ((Boolean) obja[0]);
	}

	protected boolean verifyDescriptionWithoutVerifier(final String description, 
			final boolean noneConditionFlag) throws LIMException {
		return noneConditionFlag;
	}


	public boolean verifyInformation(final InformationElement element) throws LIMException {
		return element instanceof IConditionable && ((IConditionable) element).containCondition(this);
	}


	/* overridden methods */

	@Override
	public IConvertible getXmlElement() throws LIMException {
		if (this.xmlElement == null) {
			this.xmlElement = this.getClass().equals(Condition.class) ? this : new Condition(this);
		}
		return this.xmlElement;
	}

	@Override
	public IConvertible getPojoElement(final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (this.pojoElement == null) {
			this.pojoElement = (StringUtil.isEmpty(this.getType()) 
							|| !this.getClass().equals(Condition.class)) 
					? this : super.generatePojoElementDelegate();
		}
		this.pojoElement.reload(this, rootElementMap, rootActionableElementMap);
		return this.pojoElement;
	}


	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (!(convertible instanceof Condition) 
				|| super.reload(convertible, rootElementMap, rootActionableElementMap) == null) {
			return null;
		}
		load((Condition) convertible);
		reloadFromRootElementMap(rootElementMap);
		reloadFromRootActionableElementMap(rootActionableElementMap);
		return (IConvertible) this;
	}

	private void load(final Condition element) {
		if (element != null) {
			this.implicit = element.implicit;
			this.baseDescriptionElement = element.baseDescriptionElement;
			this.descriptionElement = element.descriptionElement;
			this.baseVerifier = element.baseVerifier;
			this.verifier = element.verifier;
		}
	}

	private void reloadFromRootElementMap(final Map<String, GenericElement> rootElementMap) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		if (this.baseDescriptionElement != null && this.baseDescriptionElement.getId() != null) {
			GenericElement ge = rootElementMap.get(this.baseDescriptionElement.getId());
			this.descriptionElement = (ge instanceof InformationElement) 
					? (InformationElement) ge : this.descriptionElement;
			super.addInnerElementDelegate(this.descriptionElement);
		}
	}

	private void reloadFromRootActionableElementMap(
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(rootActionableElementMap)) {
			return;
		}

		if (this.baseVerifier != null && this.baseVerifier.getId() != null) {
			GenericActionableElement gae = rootActionableElementMap.get(this.baseVerifier.getId());
			this.verifier = gae;
			super.addInnerActionableElementDelegate(this.verifier);
		}
	}


	@Override
	public Condition cloneElement(final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				String type;
				if (value != null && value.getClass().equals(Condition.class) 
						&& ((type = value.getType()) == null || type.equals(TYPE_NAME))) {
					return (Condition) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		Condition clonedElement = (Condition) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public Condition cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof Condition) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof Condition)) 
				? null : cloneToElement((Condition) ce, null);
	}

	private Condition cloneToElement(final Condition clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.implicit = this.implicit;

		clonedElement.baseDescriptionElement = (this.baseDescriptionElement != null) 
				? (BaseElement) this.baseDescriptionElement.cloneElement(clonedElementMap) 
				: clonedElement.baseDescriptionElement;
		clonedElement.descriptionElement = (this.descriptionElement != null) 
				? (InformationElement) this.descriptionElement.cloneElement(clonedElementMap) 
				: clonedElement.descriptionElement;

		clonedElement.baseVerifier = (this.baseVerifier != null) 
				? (BaseElement) this.baseVerifier.cloneElement(clonedElementMap) 
				: clonedElement.baseVerifier;
		clonedElement.verifier = (this.verifier != null) 
				? this.verifier.cloneElement(clonedElementMap) : clonedElement.verifier;

		return clonedElement;
	}


	/* static methods */

	protected static void registerSubPojoClassForInit(final Class<? extends BaseElement> clazz) {
		if (Condition.class.isAssignableFrom(clazz)) {
			SUB_POJO_CLAZZ_COLLECTION.add(clazz);
		}
	}

}
