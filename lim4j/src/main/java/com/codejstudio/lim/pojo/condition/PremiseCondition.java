package com.codejstudio.lim.pojo.condition;

import java.util.Map;
import java.util.Objects;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.statement.Statement;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * PremiseCondition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class PremiseCondition extends Condition {

	/* constants */

	private static final long serialVersionUID = -4523049574775238913L;

	public static final String PREMISE = "premise";


	/* variables */

	protected Statement premise;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public PremiseCondition() {
		super();
	}

	public PremiseCondition(boolean initIdFlag, boolean initTypeFlag, Statement premise) 
			throws LIMException {
		super(initIdFlag, initTypeFlag);
		setPremise(premise);
	}


	public PremiseCondition(Statement premise) throws LIMException {
		this(true, true, premise);
	}

	public PremiseCondition(Statement premise, Boolean implicit) throws LIMException {
		this(true, true, premise);
		setImplicit(implicit);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(PremiseCondition.class);
		Condition.registerSubPojoClassForInit(PremiseCondition.class);
	}


	/* getters & setters */

	public Statement getPremise() {
		return premise;
	}

	public boolean setPremise(final Statement premise) throws LIMException {
		if (Objects.equals(this.premise, premise)) {
			return true;
		}

		boolean flag = true;
		if (this.premise != null) {
			flag &= super.removeInnerElementDelegate(this.premise) 
					& super.removeIntegratedElementDelegate(PREMISE);
			this.premise = null;
		}
		if (premise != null) {
			this.premise = premise;
			flag &= super.addInnerElementDelegate(premise) 
					& super.putIntegratedElementDelegate(PREMISE, new BaseElement(premise));
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setPremise(" 
					+ ((premise == null) ? null : premise.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setPremise(" 
					+ ((premise == null) ? null : premise.toBaseString()) + ")");
		}

		flag &= super.setDescriptionElement(premise);
		return flag;
	}


	/* overridden methods */

	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (super.reload(convertible, rootElementMap, rootActionableElementMap) == null) {
			return null;
		}
		reloadFromRootElementMap(rootElementMap);
		return (IConvertible) this;
	}

	private void reloadFromRootElementMap(final Map<String, GenericElement> rootElementMap) 
			throws LIMException {
		Map<String, BaseElement> item;
		if (CollectionUtil.checkNullOrEmpty(rootElementMap) 
				|| CollectionUtil.checkNullOrEmpty(item = super.getIntegratedElementMap())) {
			return;
		}

		BaseElement premise = item.get(PREMISE);
		if (premise != null && premise.getId() != null) {
			GenericElement ge = rootElementMap.get(premise.getId());
			this.premise = (ge instanceof Statement) ? (Statement) ge : this.premise;
			super.addInnerElementDelegate(this.premise);
		}
	}


	@Override
	public PremiseCondition cloneElement(final Map<String, BaseElement> clonedElementMap) 
			throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				if (value != null && value.getClass().equals(PremiseCondition.class)) {
					return (PremiseCondition) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		PremiseCondition clonedElement = (PremiseCondition) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public PremiseCondition cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		if (!(clonedElement instanceof PremiseCondition) 
				|| !((ce = super.cloneToElement(clonedElement)) instanceof PremiseCondition)) {
			return null;
		}
		return cloneToElement((PremiseCondition) ce, null);
	}

	private PremiseCondition cloneToElement(final PremiseCondition clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.premise = (this.premise != null) 
				? (Statement) this.premise.cloneElement(clonedElementMap) : clonedElement.premise;
		return clonedElement;
	}

}
