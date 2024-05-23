package com.codejstudio.lim.pojo.argument;

import java.util.Map;
import java.util.Objects;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.statement.JudgedStatement;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * Syllogism.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class Syllogism extends Argument {

	/* constants */

	private static final long serialVersionUID = 7984524102210068435L;

	public static final String MAJOR_PREMISE = "major-premise";
	public static final String MINOR_PREMISE = "minor-premise";
	public static final String CONCLUSION = "conclusion";


	/* variables */

	protected JudgedStatement majorPremise;
	protected JudgedStatement minorPremise;
	protected JudgedStatement conclusion;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Syllogism() {
		super();
	}

	public Syllogism(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public Syllogism(boolean initIdFlag, boolean initTypeFlag, String description) throws LIMException {
		super(initIdFlag, initTypeFlag, description);
	}


	public Syllogism(String description) throws LIMException {
		super(true, true, description);
	}

	public Syllogism(JudgedStatement majorPremise, JudgedStatement minorPremise, 
			JudgedStatement conclusion) throws LIMException {
		super(true, true);
		setElementsOfSyllogism(majorPremise, minorPremise, conclusion);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(Syllogism.class);
		Argument.registerSubPojoClassForInit(Syllogism.class);
	}


	/* getters & setters */

	public JudgedStatement getMajorPremise() {
		return majorPremise;
	}

	public boolean setMajorPremise(final JudgedStatement majorPremise) throws LIMException {
		if (Objects.equals(this.majorPremise, majorPremise)) {
			return true;
		}

		boolean flag = true;
		if (this.majorPremise != null) {
			flag &= super.removeJudgedStatement(this.majorPremise) 
					& super.removeEvidence(this.majorPremise) 
					& super.removeIntegratedElementDelegate(MAJOR_PREMISE) 
					& super.removeInnerElementDelegate(this.majorPremise);
			this.majorPremise = null;
		}
		if (majorPremise != null) {
			this.majorPremise = majorPremise;
			flag &= super.addInnerElementDelegate(majorPremise) 
					& super.putIntegratedElementDelegate(MAJOR_PREMISE, new BaseElement(majorPremise)) 
					& super.addJudgedStatement(majorPremise) 
					& super.addEvidence(majorPremise);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setMajorPremise(" 
					+ ((majorPremise == null) ? null : majorPremise.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setMajorPremise(" 
					+ ((majorPremise == null) ? null : majorPremise.toBaseString()) + ")");
		}
		return flag;
	}

	public JudgedStatement getMinorPremise() {
		return minorPremise;
	}

	public boolean setMinorPremise(final JudgedStatement minorPremise) throws LIMException {
		if (Objects.equals(this.minorPremise, minorPremise)) {
			return true;
		}

		boolean flag = true;
		if (this.minorPremise != null) {
			flag &= super.removeJudgedStatement(this.minorPremise) 
					& super.removeEvidence(this.minorPremise) 
					& super.removeIntegratedElementDelegate(MINOR_PREMISE) 
					& super.removeInnerElementDelegate(this.minorPremise);
			this.minorPremise = null;
		}
		if (minorPremise != null) {
			this.minorPremise = minorPremise;
			flag &= super.addInnerElementDelegate(minorPremise) 
					& super.putIntegratedElementDelegate(MINOR_PREMISE, new BaseElement(minorPremise)) 
					& super.addJudgedStatement(minorPremise) 
					& super.addEvidence(minorPremise);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setMinorPremise(" 
					+ ((minorPremise == null) ? null : minorPremise.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setMinorPremise(" 
					+ ((minorPremise == null) ? null : minorPremise.toBaseString()) + ")");
		}
		return flag;
	}

	public JudgedStatement getConclusion() {
		return conclusion;
	}

	public boolean setConclusion(final JudgedStatement conclusion) throws LIMException {
		if (Objects.equals(this.conclusion, conclusion)) {
			return true;
		}

		boolean flag = true;
		if (this.conclusion != null) {
			flag &= super.removeJudgedStatement(this.conclusion) 
					& super.removeConclusion(this.conclusion) 
					& super.removeIntegratedElementDelegate(CONCLUSION) 
					& super.removeInnerElementDelegate(this.conclusion);
			this.conclusion = null;
		}
		if (conclusion != null) {
			this.conclusion = conclusion;
			flag &= super.addInnerElementDelegate(conclusion) 
					& super.putIntegratedElementDelegate(CONCLUSION, new BaseElement(conclusion)) 
					& super.addJudgedStatement(conclusion) 
					& super.addConclusion(conclusion);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setConclusion(" 
					+ ((conclusion == null) ? null : conclusion.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setConclusion(" 
					+ ((conclusion == null) ? null : conclusion.toBaseString()) + ")");
		}
		return flag;
	}


	public void setElementsOfSyllogism(final JudgedStatement majorPremise, 
			final JudgedStatement minorPremise, final JudgedStatement conclusion) throws LIMException {
		setMajorPremise(majorPremise);
		setMinorPremise(minorPremise);
		setConclusion(conclusion);
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

		BaseElement majorPremise = item.get(MAJOR_PREMISE);
		if (majorPremise != null && majorPremise.getId() != null) {
			GenericElement ge = rootElementMap.get(majorPremise.getId());
			this.majorPremise = (ge instanceof JudgedStatement) 
					? (JudgedStatement) ge : this.majorPremise;
			super.addInnerElementDelegate(this.majorPremise);
		}
		BaseElement minorPremise = item.get(MINOR_PREMISE);
		if (minorPremise != null && minorPremise.getId() != null) {
			GenericElement ge = rootElementMap.get(minorPremise.getId());
			this.minorPremise = (ge instanceof JudgedStatement) 
					? (JudgedStatement) ge : this.minorPremise;
			super.addInnerElementDelegate(this.minorPremise);
		}
		BaseElement conclusion = item.get(CONCLUSION);
		if (conclusion != null && conclusion.getId() != null) {
			GenericElement ge = rootElementMap.get(conclusion.getId());
			this.conclusion = (ge instanceof JudgedStatement) 
					? (JudgedStatement) ge : this.conclusion;
			super.addInnerElementDelegate(this.conclusion);
		}
	}


	@Override
	public Syllogism cloneElement(final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				if (value != null && value.getClass().equals(Syllogism.class)) {
					return (Syllogism) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		Syllogism clonedElement = (Syllogism) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public Syllogism cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof Syllogism) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof Syllogism)) 
				? null : cloneToElement((Syllogism) ce, null);
	}

	private Syllogism cloneToElement(final Syllogism clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.majorPremise = (this.majorPremise != null) 
				? (JudgedStatement) this.majorPremise.cloneElement(clonedElementMap) 
				: clonedElement.majorPremise;
		clonedElement.minorPremise = (this.minorPremise != null) 
				? (JudgedStatement) this.minorPremise.cloneElement(clonedElementMap) 
				: clonedElement.minorPremise;
		clonedElement.conclusion = (this.conclusion != null) 
				? (JudgedStatement) this.conclusion.cloneElement(clonedElementMap) 
				: clonedElement.conclusion;

		return clonedElement;
	}

}
