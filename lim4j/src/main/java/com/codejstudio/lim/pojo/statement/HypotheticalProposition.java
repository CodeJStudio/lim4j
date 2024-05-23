package com.codejstudio.lim.pojo.statement;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.condition.HypotheticalCondition;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.util.DynamicInformation;
import com.codejstudio.lim.pojo.util.DynamicableUtil;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.GroupableUtil;
import com.codejstudio.lim.pojo.util.HPPDynamicInformation;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * HypotheticalProposition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class HypotheticalProposition extends Proposition {

	/* constants */

	private static final long serialVersionUID = 272463665815021979L;

	public static final String ANTECEDENTS = "antecedents";
	public static final String CONSEQUENTS = "consequents";


	/* variables: arrays, collections, maps, groups */

	private ConditionGroup antecedentGroup;

	private JudgedStatementGroup consequentGroup;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public HypotheticalProposition() {
		super();
	}

	public HypotheticalProposition(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public HypotheticalProposition(boolean initIdFlag, boolean initTypeFlag, String description) 
			throws LIMException {
		super(initIdFlag, initTypeFlag, description);
	}

	public HypotheticalProposition(boolean initIdFlag, boolean initTypeFlag, JudgedStatement consequent, 
			HypotheticalCondition... antecedents) throws LIMException {
		super(initIdFlag, initTypeFlag);
		addConsequentAndAntecedent(consequent, antecedents);
	}

	public HypotheticalProposition(boolean initIdFlag, boolean initTypeFlag, 
			JudgedStatement[] consequentArray, HypotheticalCondition... antecedents) throws LIMException {
		super(initIdFlag, initTypeFlag);
		addConsequentAndAntecedent(consequentArray, antecedents);
	}

	public HypotheticalProposition(boolean initIdFlag, boolean initTypeFlag, 
			Collection<JudgedStatement> consequentCollection, HypotheticalCondition... antecedents) 
					throws LIMException {
		super(initIdFlag, initTypeFlag);
		addConsequentAndAntecedent(consequentCollection, antecedents);
	}


	public HypotheticalProposition(String description) throws LIMException {
		this(true, true, description);
	}

	public HypotheticalProposition(JudgedStatement consequent, HypotheticalCondition... antecedents) 
			throws LIMException {
		this(true, true, consequent, antecedents);
	}

	public HypotheticalProposition(JudgedStatement[] consequentArray, HypotheticalCondition... antecedents) 
			throws LIMException {
		this(true, true, consequentArray, antecedents);
	}

	public HypotheticalProposition(Collection<JudgedStatement> consequentCollection, 
			HypotheticalCondition... antecedents) throws LIMException {
		this(true, true, consequentCollection, antecedents);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(HypotheticalProposition.class);
		JudgedStatement.registerSubPojoClassForInit(HypotheticalProposition.class);
	}


	private void initAntecedentGroup() throws LIMException {
		if (this.antecedentGroup == null) {
			this.antecedentGroup = new ConditionGroup(true);
			super.addInnerElementDelegate(this.antecedentGroup);
			super.putIntegratedElementDelegate(ANTECEDENTS, new BaseElement(this.antecedentGroup));
		}
	}

	private void initConsequentGroup() throws LIMException {
		if (this.consequentGroup == null) {
			this.consequentGroup = new JudgedStatementGroup(true);
			super.addInnerElementDelegate(this.consequentGroup);
			super.putIntegratedElementDelegate(CONSEQUENTS, new BaseElement(this.consequentGroup));
		}
	}


	/* destroyers */

	private void destroyAntecedentGroup() throws LIMException {
		if (this.antecedentGroup != null && this.antecedentGroup.size() == 0) {
			super.removeInnerElementDelegate(this.antecedentGroup);
			super.removeIntegratedElementDelegate(ANTECEDENTS);
		}
	}

	private void destroyConsequentGroup() throws LIMException {
		if (this.consequentGroup != null && this.consequentGroup.size() == 0) {
			super.removeInnerElementDelegate(this.consequentGroup);
			super.removeIntegratedElementDelegate(CONSEQUENTS);
		}
	}


	/* getters & setters */

	public ConditionGroup getAntecedentGroup() {
		return antecedentGroup;
	}

	public JudgedStatementGroup getConsequentGroup() {
		return consequentGroup;
	}


	/* CRUD for arrays, collections, maps, groups: antecedents */

	public boolean addAntecedent(final HypotheticalCondition... antecedents) throws LIMException {
		return addAntecedent((antecedents == null) ? null : Arrays.asList(antecedents));
	}

	public boolean addAntecedent(final Collection<HypotheticalCondition> antecedentCollection) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(antecedentCollection)) {
			return false;
		}

		try {
			initAntecedentGroup();
			boolean flag = super.addInnerElementDelegate(antecedentCollection) 
					& this.antecedentGroup.addGroupElement(antecedentCollection);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": addAntecedent(" 
						+ BaseElement.toBaseString(antecedentCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to addAntecedent(" 
						+ BaseElement.toBaseString(antecedentCollection) + ")");
			}
			return flag;
		} finally {
			updateDynamicInformation();
			destroyAntecedentGroup();
		}
	}

	public boolean removeAntecedent(final HypotheticalCondition... antecedents) throws LIMException {
		return removeAntecedent((antecedents == null) ? null : Arrays.asList(antecedents));
	}

	public boolean removeAntecedent(final Collection<HypotheticalCondition> antecedentCollection) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(antecedentCollection) 
				|| GroupableUtil.checkNullOrEmpty(this.antecedentGroup)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeAntecedent(" 
					+ BaseElement.toBaseString(antecedentCollection) + ")");
			return false;
		}

		try {
			boolean flag = super.removeInnerElementDelegate(antecedentCollection) 
					& this.antecedentGroup.removeGroupElement(antecedentCollection);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeAntecedent(" 
						+ BaseElement.toBaseString(antecedentCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeAntecedent(" 
						+ BaseElement.toBaseString(antecedentCollection) + ")");
			}
			return flag;
		} finally {
			updateDynamicInformation();
			destroyAntecedentGroup();
		}
	}


	/* CRUD for arrays, collections, maps, groups: consequents */

	public boolean addConsequent(final JudgedStatement... consequents) throws LIMException {
		return addConsequent((consequents == null) ? null : Arrays.asList(consequents));
	}

	public boolean addConsequent(final Collection<JudgedStatement> consequentCollection) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(consequentCollection)) {
			return false;
		}

		try {
			initConsequentGroup();
			boolean flag = super.addInnerElementDelegate(consequentCollection) 
					& this.consequentGroup.addGroupElement(consequentCollection);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": addConsequent(" 
						+ BaseElement.toBaseString(consequentCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to addConsequent(" 
						+ BaseElement.toBaseString(consequentCollection) + ")");
			}
			return flag;
		} finally {
			updateDynamicInformation();
			destroyConsequentGroup();
		}
	}

	public boolean removeConsequent(final JudgedStatement... consequents) throws LIMException {
		return removeConsequent((consequents == null) ? null : Arrays.asList(consequents));
	}

	public boolean removeConsequent(final Collection<JudgedStatement> consequentCollection) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(consequentCollection) 
				|| GroupableUtil.checkNullOrEmpty(this.consequentGroup)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeConsequent(" 
					+ BaseElement.toBaseString(consequentCollection) + ")");
			return false;
		}

		try {
			boolean flag = super.removeInnerElementDelegate(consequentCollection) 
					& this.consequentGroup.removeGroupElement(consequentCollection);
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeConsequent(" 
						+ BaseElement.toBaseString(consequentCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeConsequent(" 
						+ BaseElement.toBaseString(consequentCollection) + ")");
			}
			return flag;
		} finally {
			updateDynamicInformation();
			destroyConsequentGroup();
		}
	}


	/* CRUD for arrays, collections, maps, groups: antecedents & consequents */

	public boolean addConsequentAndAntecedent(final JudgedStatement consequent, 
			final HypotheticalCondition... antecedents) throws LIMException {
		return addAntecedent(antecedents) & addConsequent(consequent);
	}

	public boolean addConsequentAndAntecedent(final JudgedStatement[] consequentArray, 
			final HypotheticalCondition... antecedents) throws LIMException {
		return addAntecedent(antecedents) & addConsequent(consequentArray);
	}

	public boolean addConsequentAndAntecedent(final Collection<JudgedStatement> consequentCollection, 
			final HypotheticalCondition... antecedents) throws LIMException {
		return addAntecedent(antecedents) & addConsequent(consequentCollection);
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

		BaseElement antecedentGroup = item.get(ANTECEDENTS);
		if (antecedentGroup != null && antecedentGroup.getId() != null) {
			GenericElement ge = rootElementMap.get(antecedentGroup.getId());
			this.antecedentGroup = (ge instanceof ConditionGroup) 
					? (ConditionGroup) ge : this.antecedentGroup;
			super.addInnerElementDelegate(this.antecedentGroup);
		}
		BaseElement consequentGroup = item.get(CONSEQUENTS);
		if (consequentGroup != null && consequentGroup.getId() != null) {
			GenericElement ge = rootElementMap.get(consequentGroup.getId());
			this.consequentGroup = (ge instanceof JudgedStatementGroup) 
					? (JudgedStatementGroup) ge : this.consequentGroup;
			super.addInnerElementDelegate(this.consequentGroup);
		}
	}


	@Override
	public HypotheticalProposition cloneElement(final Map<String, BaseElement> clonedElementMap) 
			throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				if (value != null && value.getClass().equals(HypotheticalProposition.class)) {
					return (HypotheticalProposition) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		HypotheticalProposition clonedElement 
				= (HypotheticalProposition) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public HypotheticalProposition cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		if (!(clonedElement instanceof HypotheticalProposition) 
				|| !((ce = super.cloneToElement(clonedElement)) instanceof HypotheticalProposition)) {
			return null;
		}
		return cloneToElement((HypotheticalProposition) ce, null);
	}

	private HypotheticalProposition cloneToElement(final HypotheticalProposition clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {

		clonedElement.antecedentGroup = (this.antecedentGroup != null) 
				? (ConditionGroup) this.antecedentGroup.cloneElement(clonedElementMap) 
				: clonedElement.antecedentGroup;
		clonedElement.consequentGroup = (this.consequentGroup != null) 
				? (JudgedStatementGroup) this.consequentGroup.cloneElement(clonedElementMap) 
				: clonedElement.consequentGroup;

		return clonedElement;
	}


	/* class methods */

	private void updateDynamicInformation() throws LIMException {
		super.setDynamicInformation(HPPDynamicInformation.newInstance(this));
	}


	public boolean verifyConditionsOfAntecedent(final Statement statement, final int index) 
			throws LIMException {
		ConditionGroup atdg;
		List<Condition> atdigl;
		Condition cd;
		Statement s;
		DynamicInformation di;
		return statement != null && !CollectionUtil.checkNullOrEmpty(atdg = getAntecedentGroup()) 
				&& index >= 0 && index < (atdigl = atdg.getInnerGroupList()).size()
				&& (cd = atdigl.get(index)) instanceof HypotheticalCondition 
				&& (s = ((HypotheticalCondition) cd).getAntecedent()) != null 
				&& (di = s.getDynamicInformation()) != null 
				&& di.verifyDynamicFragmentCondition(statement);
	}

	public boolean verifyDynamicAntecedentFragment(final InformationElement[] elementArray) 
			throws LIMException {
		ConditionGroup atdg;
		List<Condition> atdigl;
		if (CollectionUtil.checkNullOrEmpty(elementArray) 
				|| CollectionUtil.checkNullOrEmpty(atdg = getAntecedentGroup()) 
				|| elementArray.length != (atdigl = atdg.getInnerGroupList()).size()) {
			return false;
		}
		if (elementArray.length == 1) {
			return true;
		}

		Map<String, String> dynamicFragmentsMapping = CollectionUtil.generateNewMap();
		Map<String, String> staticFragmentsMapping = CollectionUtil.generateNewMap();
		for (int i = 0; i < elementArray.length; i++) {
			String dsc;
			Condition cd = atdigl.get(i);
			Statement s;
			DynamicInformation di;
			String[][] arrayOfFragmentArrays;
			String[] dfa;
			if (elementArray[i] == null || (dsc = elementArray[i].getDescription()) == null
					|| !(cd instanceof HypotheticalCondition) 
					|| (s = ((HypotheticalCondition) cd).getAntecedent()) == null 
					|| (di = s.getDynamicInformation()) == null 
					|| CollectionUtil.checkNullOrEmpty(arrayOfFragmentArrays = DynamicableUtil
							.analyzeStaticContentBasedOnDynamicContent(dsc, di)) 
					|| arrayOfFragmentArrays.length != 3 
					|| (dfa = di.getDynamicFragmentArray()).length != arrayOfFragmentArrays[2].length) {
				return false;
			}

			for (int j = 0; j < arrayOfFragmentArrays[2].length; j++) {
				String sf = dynamicFragmentsMapping.get(dfa[j]);
				String df = staticFragmentsMapping.get(arrayOfFragmentArrays[2][j]);
				if (sf == null && df == null) {
					dynamicFragmentsMapping.put(dfa[j], arrayOfFragmentArrays[2][j]);
					staticFragmentsMapping.put(arrayOfFragmentArrays[2][j], dfa[j]);
				} else if (sf == null || df == null 
						|| !sf.equals(arrayOfFragmentArrays[2][j]) || !df.equals(dfa[j])) {
					return false;
				}
			}
		}
		return true;
	}

}
