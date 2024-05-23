package com.codejstudio.lim.pojo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.i.IDynamicable.DynamicParameterType;
import com.codejstudio.lim.pojo.i.IGroupable;
import com.codejstudio.lim.pojo.i.IIntegratable;
import com.codejstudio.lim.pojo.relation.BaseRelation;
import com.codejstudio.lim.pojo.relation.DummyRelation;
import com.codejstudio.lim.pojo.util.DynamicInformation;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.JAXBBoundClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;
import com.codejstudio.lim.pojo.util.XmlAttributeMapAdapter;
import com.codejstudio.lim.pojo.util.XmlElementMapAdapter;

/**
 * GenericElement.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = GenericElement.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
	"integratedAttributeMap",
	"integratedElementMap",
})
public class GenericElement extends BaseElement implements IIntegratable, IConvertible {

	/* constants */

	private static final long serialVersionUID = 19928202522004844L;

	public static final String TYPE_NAME = "element";

	public static final String WHOLE_DYNAMIC_CONDITION = "whole-dynamic-condition";
	public static final String NET_DYNAMIC_CONDITIONS = "net-dynamic-conditions";
	public static final String DYNAMIC_FRAGMENT_PARAMETER_TYPES = "dynamic-fragment-parameter-types";


	/* variables */

	@XmlAttribute
	private Boolean dynamic;

	private DynamicInformation dynamicInformation;

	protected GenericElement xmlElement;
	protected GenericElement pojoElement;


	/* variables: arrays, collections, maps, groups */

	private static final Collection<Class<? extends BaseElement>> SUB_POJO_CLAZZ_COLLECTION;

	/**
	 * the collection to contain all sub-elements and this element itself<br/>
	 * for JAXB usage in <code>Root</code> class
	 */
	private Map<String, GenericElement> innerElementMap;

	/**
	 * the collection to contain all sub actionable elements<br/>
	 * for JAXB usage in <code>Root</code> class
	 */
	private Map<String, GenericActionableElement> innerActionableElementMap;

	private Map<String, String> integratedAttributeMap;
	private Map<String, BaseElement> integratedElementMap;


	/* constructors */

	/**
	 * for JAXB usage of unmarshalling
	 */
	public GenericElement() {
		super();
	}

	public GenericElement(GenericElement element) throws LIMException {
		super(element, false);
		load(element);
	}

	public GenericElement(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
		addThisAsInnerElement();
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			SUB_POJO_CLAZZ_COLLECTION = CollectionUtil.generateNewCollection();

			PojoElementClassConstant.registerElementClassForInit(GenericElement.class);
			JAXBBoundClassConstant.registerBoundClassForInit(GenericElement.class);
			GenericElement.registerSubPojoClassForInit(GenericElement.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	private void initInnerElementMap() throws LIMException {
		if (this.innerElementMap == null) {
			this.innerElementMap = CollectionUtil.generateNewMap();
		}
	}

	private void initInnerActionableElementMap() throws LIMException {
		if (this.innerActionableElementMap == null) {
			this.innerActionableElementMap = CollectionUtil.generateNewMap();
		}
	}

	private void initIntegratedAttributeMap() throws LIMException {
		if (this.integratedAttributeMap == null) {
			this.integratedAttributeMap = CollectionUtil.generateNewMap();
		}
	}

	private void initIntegratedElementMap() throws LIMException {
		if (this.integratedElementMap == null) {
			this.integratedElementMap = CollectionUtil.generateNewMap();
		}
	}


	/* destroyers */

	private void destroyInnerElementMap() {
		if (this.innerElementMap != null && this.innerElementMap.size() == 0) {
			this.innerElementMap = null;
		}
	}

	private void destroyInnerActionableElementMap() {
		if (this.innerActionableElementMap != null && this.innerActionableElementMap.size() == 0) {
			this.innerActionableElementMap = null;
		}
	}

	private void destroyIntegratedAttributeMap() {
		if (this.integratedAttributeMap != null && this.integratedAttributeMap.size() == 0) {
			this.integratedAttributeMap = null;
		}
	}

	private void destroyIntegratedElementMap() {
		if (this.integratedElementMap != null && this.integratedElementMap.size() == 0) {
			this.integratedElementMap = null;
		}
	}


	/* getters & setters */

	@XmlTransient
	public Boolean getDynamic() {
		return dynamic;
	}

	public void setDynamic(Boolean dynamic) {
		this.dynamic = (dynamic == null || !dynamic) ? null : dynamic;
	}

	@XmlTransient
	public DynamicInformation getDynamicInformation() {
		return dynamicInformation;
	}

	protected void updateDynamicInformationByDescription(final String description) {
		setDynamicInformation(DynamicInformation.newInstance(description));
	}

	protected void setDynamicInformation(final DynamicInformation dynamicInformation) {
		if (dynamicInformation != null && this.dynamicInformation != null) {
			dynamicInformation.setWholeDynamicCondition(
					this.dynamicInformation.getWholeDynamicCondition());
			dynamicInformation.setNetDynamicConditions(
					this.dynamicInformation.getNetDynamicFragmentConditionArray());
		}
		this.dynamicInformation = dynamicInformation;
		setDynamic((this.dynamicInformation != null) && this.dynamicInformation.isDynamic());
	}

	@XmlTransient
	public ConditionGroup getWholeDynamicCondition() {
		return (this.dynamicInformation == null) ? null : this.dynamicInformation.getWholeDynamicCondition();
	}

	public void setWholeDynamicCondition(final ConditionGroup conditionGroup) throws LIMException {
		if (conditionGroup != null && this.dynamicInformation == null) {
			this.dynamicInformation = DynamicInformation.newInstance();
		}
		ConditionGroup wdcd;
		if (this.dynamicInformation == null 
				|| Objects.equals((wdcd = this.dynamicInformation.getWholeDynamicCondition()), 
						conditionGroup)) {
			return;
		}

		this.dynamicInformation.setWholeDynamicCondition(conditionGroup);

		boolean flag = true;
		if (wdcd != null) {
			flag &= removeIntegratedElementDelegate(WHOLE_DYNAMIC_CONDITION) 
					& removeInnerElementDelegate(wdcd);
		}
		if (conditionGroup != null) {
			flag &= addInnerElementDelegate(conditionGroup) 
					& putIntegratedElementDelegate(WHOLE_DYNAMIC_CONDITION, new BaseElement(conditionGroup));
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setWholeDynamicCondition(" 
					+ ((conditionGroup == null) ? null : conditionGroup.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setWholeDynamicCondition(" 
					+ ((conditionGroup == null) ? null : conditionGroup.toBaseString()) + ")");
		}
	}

	@XmlTransient
	public ConditionGroup[] getNetDynamicConditions() {
		return (this.dynamicInformation == null) 
				? null : this.dynamicInformation.getNetDynamicFragmentConditionArray();
	}

	public void setNetDynamicConditions(final ConditionGroup... conditionGroups) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(conditionGroups) && this.dynamicInformation == null) {
			this.dynamicInformation = DynamicInformation.newInstance();
		}
		if (this.dynamicInformation == null 
				|| Objects.equals(this.dynamicInformation.getNetDynamicFragmentConditionArray(), 
						conditionGroups)) {
			return;
		}

		this.dynamicInformation.setNetDynamicConditions(conditionGroups);

		boolean flag = true;
		Collection<BaseElement> bec = removeIntegratedElementByKeyPrefixDelegate(NET_DYNAMIC_CONDITIONS);
		if (!CollectionUtil.checkNullOrEmpty(bec)) {
			for (BaseElement be : bec) {
				if (be != null) {
					flag &= removeInnerElementDelegate(be.id);
				}
			}
		}
		if (!CollectionUtil.checkNullOrEmpty(conditionGroups)) {
			for (int i = 0; i < conditionGroups.length; i++) {
				if (conditionGroups[i] != null) {
					flag &= addInnerElementDelegate(conditionGroups[i]) 
							& putIntegratedElementDelegate(NET_DYNAMIC_CONDITIONS + i, 
									new BaseElement(conditionGroups[i]));
				}
			}
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setNetDynamicConditions(" 
					+ BaseElement.toBaseString(conditionGroups) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setNetDynamicConditions(" 
					+ BaseElement.toBaseString(conditionGroups) + ")");
		}
	}

	@XmlTransient
	public DynamicParameterType[] getDynamicFragmentParameterTypes() {
		return (this.dynamicInformation == null) 
				? null : this.dynamicInformation.getDynamicFragmentParameterTypeArray();
	}

	public void setDynamicFragmentParameterTypes(final DynamicParameterType... types) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(types) && this.dynamicInformation == null) {
			this.dynamicInformation = DynamicInformation.newInstance();
		}
		if (this.dynamicInformation == null 
				|| Objects.equals(this.dynamicInformation.getDynamicFragmentParameterTypeArray(), types)) {
			return;
		}

		this.dynamicInformation.setDynamicFragmentParameterTypes(types);

		boolean flag = true;
		flag &= removeIntegratedAttributeDelegate(DYNAMIC_FRAGMENT_PARAMETER_TYPES);
		if (!CollectionUtil.checkNullOrEmpty(types)) {
			for (int i = 0; i < types.length; i++) {
				if (types[i] != null) {
					flag &= putIntegratedAttributeDelegate(
							DYNAMIC_FRAGMENT_PARAMETER_TYPES + i, types[i].name());
				}
			}
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setDynamicFragmentParameterTypes(" 
					+ Arrays.asList(types) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setDynamicFragmentParameterTypes(" 
					+ Arrays.asList(types) + ")");
		}
	}


	public void setPojoElement(GenericElement pojoElement) {
		this.pojoElement = pojoElement;
	}


	/**
	 * for JAXB usage in <code>Root</code> class
	 */
	public Collection<GenericElement> getInnerElementCollection() {
		return CollectionUtil.checkNullOrEmpty(this.innerElementMap) ? null : this.innerElementMap.values();
	}

	/**
	 * for JAXB usage in <code>Root</code> class
	 */
	public Map<String, GenericElement> getInnerElementMap() {
		return innerElementMap;
	}


	/**
	 * for JAXB usage in <code>Root</code> class
	 */
	public Collection<GenericActionableElement> getInnerActionableElementCollection() throws LIMException {
		return CollectionUtil.checkNullOrEmpty(this.innerActionableElementMap) 
				? null : this.innerActionableElementMap.values();
	}

	/**
	 * for JAXB usage in <code>Root</code> class
	 */
	public Map<String, GenericActionableElement> getInnerActionableElementMap() {
		return innerActionableElementMap;
	}


	/**
	 * for JAXB usage of unmarshalling
	 */
	@Override
	@XmlElement(name = IIntegratable.INTEGRATED_ATTACHMENT_XMLATTRIBUTE_NAME)
	@XmlJavaTypeAdapter(value = XmlAttributeMapAdapter.class)
	public Map<String, String> getIntegratedAttributeMap() {
		return integratedAttributeMap;
	}

	/**
	 * for JAXB usage of unmarshalling
	 */
	@Override
	public void setIntegratedAttributeMap(Map<String, String> attributeMap) {
		this.integratedAttributeMap = attributeMap;
	}


	/**
	 * for JAXB usage of unmarshalling
	 */
	@Override
	@XmlElement(name = IIntegratable.INTEGRATED_ATTACHMENT_XMLELEMENT_NAME)
	@XmlJavaTypeAdapter(value = XmlElementMapAdapter.class)
	public Map<String, BaseElement> getIntegratedElementMap() {
		return integratedElementMap;
	}

	/**
	 * for JAXB usage of unmarshalling
	 */
	@Override
	public void setIntegratedElementMap(Map<String, BaseElement> elementMap) {
		this.integratedElementMap = elementMap;
	}


	/* CRUD for arrays, collections, maps, groups: inner elements */

	protected boolean containInnerElementDelegate(final GenericElement element) {
		return (element == null || element.id == null || this.innerElementMap == null) 
				? false : this.innerElementMap.containsKey(element.id);
	}


	protected GenericElement getInnerElementDelegate(final BaseElement baseElement) {
		return (baseElement == null) ? null : getInnerElementDelegate(baseElement.id);
	}

	protected GenericElement getInnerElementDelegate(final String elementId) {
		return (elementId == null || this.innerElementMap == null) 
				? null : this.innerElementMap.get(elementId);
	}


	protected boolean addInnerElementDelegate(final IGroupable<?> groupElement) throws LIMException {
		if (groupElement == null || !(groupElement instanceof GenericElement)) {
			return false;
		}

		List<GenericElement> geigl 
				= CollectionUtil.convertToListOfSuperClass(groupElement.getInnerGroupList());
		if (geigl == null) {
			geigl = CollectionUtil.generateNewList();
		}
		geigl.add((GenericElement) groupElement);
		return addInnerElementDelegate(geigl);
	}

	protected boolean addInnerElementDelegate(final GenericElement... elements) throws LIMException {
		return addInnerElementDelegate((elements == null) ? null : Arrays.asList(elements));
	}

	protected boolean addInnerElementDelegate(final Collection<? extends GenericElement> elementCollection) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(elementCollection)) {
			return false;
		}

		try {
			initInnerElementMap();
			boolean flag = true;
			for (GenericElement ge : elementCollection) {
				if (ge != null && ge.id != null) {
					if (this.innerElementMap.containsKey(ge.id)
					) {
						flag = false;
					} else {
						this.innerElementMap.put(ge.id, ge);
						flag &= true;
					}
				}
			}
			return flag;
		} finally {
			destroyInnerElementMap();
		}
	}


	protected boolean removeInnerElementDelegate(final String elementId) {
		if (elementId == null || CollectionUtil.checkNullOrEmpty(this.innerElementMap) 
				|| !this.innerElementMap.containsKey(elementId)) {
			return false;
		}

		try {
			this.innerElementMap.remove(elementId);
			return true;
		} finally {
			destroyInnerElementMap();
		}
	}

	protected boolean removeInnerElementDelegate(final GenericElement... elements) throws LIMException {
		return removeInnerElementDelegate((elements == null) ? null : Arrays.asList(elements));
	}

	protected boolean removeInnerElementDelegate(
			final Collection<? extends GenericElement> elementCollection) {
		if (CollectionUtil.checkNullOrEmpty(elementCollection) 
				|| CollectionUtil.checkNullOrEmpty(this.innerElementMap)) {
			return false;
		}

		try {
			boolean flag = true;
			for (GenericElement ge : elementCollection) {
				if (ge != null && ge.id != null) {
					if (!this.innerElementMap.containsKey(ge.id)) {
						flag = false;
					} else {
						flag &= this.innerElementMap.remove(ge.id, ge)
						;
					}
				}
			}
			return flag;
		} finally {
			destroyInnerElementMap();
		}
	}

	protected boolean removeInnerElementDelegate(final DummyRelation relation) throws LIMException {
		if (relation == null || relation.checkNull() 
				|| CollectionUtil.checkNullOrEmpty(this.innerElementMap)) {
			return false;
		}

		BaseRelation br = null;
		Collection<GenericElement> iec = getInnerElementCollection();
		for (GenericElement ge : iec) {
			if (relation.equals(ge)) {
				br = (BaseRelation) ge;
				break;
			}
		}
		if (br == null || !this.innerElementMap.containsKey(br.id) 
				|| !(this instanceof AbstractRelationableInformationElement)) {
			return false;
		}

		try {
			return this.innerElementMap.remove(br.id, br) 
					& ((AbstractRelationableInformationElement) this).removeRelation(br);
		} finally {
			destroyInnerElementMap();
		}
	}


	protected boolean replaceInnerElementDelegate(final GenericElement replacee, 
			final GenericElement replacer) throws LIMException {
		return (Objects.equals(replacee, replacer)) ? false 
				: (removeInnerElementDelegate((replacee == null) ? null : Arrays.asList(replacee)) 
						& addInnerElementDelegate((replacer == null) ? null : Arrays.asList(replacer)));
	}

	protected boolean replaceInnerElementDelegate(final DummyRelation replacee, 
			final BaseRelation replacer) throws LIMException {
		return removeInnerElementDelegate(replacee) 
				& addInnerElementDelegate((replacer == null) ? null : Arrays.asList(replacer));
	}


	/* CRUD for arrays, collections, maps, groups: inner actionable elements */

	protected boolean containInnerActionableElementDelegate(
			final GenericActionableElement actionableElement) {
		return (actionableElement == null || actionableElement.id == null 
				|| CollectionUtil.checkNullOrEmpty(this.innerActionableElementMap)) 
				? false : this.innerActionableElementMap.containsKey(actionableElement.id);
	}


	protected GenericActionableElement getInnerActionableElementDelegate(final BaseElement baseElement) {
		return (baseElement == null) ? null : getInnerActionableElementDelegate(baseElement.id);
	}

	protected GenericActionableElement getInnerActionableElementDelegate(final String elementId) {
		return (elementId == null || CollectionUtil.checkNullOrEmpty(this.innerActionableElementMap)) 
				? null : this.innerActionableElementMap.get(elementId);
	}


	protected boolean addInnerActionableElementDelegate(
			final GenericActionableElement... actionableElements) throws LIMException {
		return addInnerActionableElementDelegate(
				(actionableElements == null) ? null : Arrays.asList(actionableElements));
	}

	protected boolean addInnerActionableElementDelegate(
			final Collection<? extends GenericActionableElement> actionableElementCollection) 
					throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(actionableElementCollection)) {
			return false;
		}

		try {
			initInnerActionableElementMap();
			boolean flag = true;
			for (GenericActionableElement gae : actionableElementCollection) {
				if (gae != null && gae.id != null) {
					if (this.innerActionableElementMap.containsKey(gae.id)
					) {
						flag = false;
					} else {
						this.innerActionableElementMap.put(gae.id, gae);
						flag &= true;
					}
				}
			}
			return flag;
		} finally {
			destroyInnerActionableElementMap();
		}
	}

	protected boolean removeInnerActionableElementDelegate(final GenericActionableElement... elements) {
		return removeInnerActionableElementDelegate((elements == null) ? null : Arrays.asList(elements));
	}

	protected boolean removeInnerActionableElementDelegate(
			final Collection<? extends GenericActionableElement> actionableElementCollection) {
		if (CollectionUtil.checkNullOrEmpty(actionableElementCollection) 
				|| CollectionUtil.checkNullOrEmpty(this.innerActionableElementMap)) {
			return false;
		}

		try {
			boolean flag = true;
			for (GenericActionableElement gae : actionableElementCollection) {
				if (gae != null && gae.id != null) {
					if (!this.innerActionableElementMap.containsKey(gae.id)) {
						flag = false;
					} else {
						flag &= this.innerActionableElementMap.remove(gae.id, gae)
						;
					}
				}
			}
			return flag;
		} finally {
			destroyInnerActionableElementMap();
		}
	}

	protected boolean replaceInnerActionableElementDelegate(final GenericActionableElement replacee, 
			final GenericActionableElement replacer) throws LIMException {
		return (Objects.equals(replacee, replacer)) ? false 
				: (removeInnerActionableElementDelegate((replacee == null) ? null : Arrays.asList(replacee)) 
						& addInnerActionableElementDelegate((replacer == null) ? null : Arrays.asList(replacer)));
	}


	/* CRUD for arrays, collections, maps, groups: integrated XmlAttribute attachments */

	protected String getIntegratedAttributeDelegate(final String key) {
		if (key == null || CollectionUtil.checkNullOrEmpty(this.integratedAttributeMap)) {
			return null;
		}
		return this.integratedAttributeMap.get(key);
	}

	protected boolean putIntegratedAttributeDelegate(final String key, final String value) 
			throws LIMException {
		if (key == null || value == null) {
			return false;
		}

		try {
			initIntegratedAttributeMap();
			this.integratedAttributeMap.put(key, value);
			return true;
		} finally {
			destroyIntegratedAttributeMap();
		}
	}

	protected boolean removeIntegratedAttributeDelegate(final String key) {
		if (key == null || CollectionUtil.checkNullOrEmpty(this.integratedAttributeMap)) {
			return false;
		}

		try {
			this.integratedAttributeMap.remove(key);
			return true;
		} finally {
			destroyIntegratedAttributeMap();
		}
	}

	protected boolean removeIntegratedAttributeByKeyPrefixDelegate(final String keyPrefix) 
			throws LIMException {
		if (keyPrefix == null || CollectionUtil.checkNullOrEmpty(this.integratedAttributeMap)) {
			return false;
		}

		try {
			Set<String> itatks = CollectionUtil.copySet(this.integratedAttributeMap.keySet());
			for (String k : itatks) {
				if (k != null && k.startsWith(keyPrefix)) {
					this.integratedAttributeMap.remove(k);
				}
			}
			return true;
		} finally {
			destroyIntegratedElementMap();
		}
	}


	/* CRUD for arrays, collections, maps, groups: integrated XmlElement attachments */

	protected BaseElement getIntegratedElementDelegate(final String key) {
		if (key == null || CollectionUtil.checkNullOrEmpty(this.integratedElementMap)) {
			return null;
		}
		return this.integratedElementMap.get(key);
	}

	protected boolean putIntegratedElementDelegate(final String key, final BaseElement value) 
			throws LIMException {
		if (key == null || value == null) {
			return false;
		}

		try {
			initIntegratedElementMap();
			this.integratedElementMap.put(key, value);
			return true;
		} finally {
			destroyIntegratedElementMap();
		}
	}

	protected boolean removeIntegratedElementDelegate(final String key) {
		if (key == null || CollectionUtil.checkNullOrEmpty(this.integratedElementMap)) {
			return false;
		}

		try {
			this.integratedElementMap.remove(key);
			return true;
		} finally {
			destroyIntegratedElementMap();
		}
	}

	protected Collection<BaseElement> removeIntegratedElementByKeyPrefixDelegate(final String keyPrefix) 
			throws LIMException {
		if (keyPrefix == null || CollectionUtil.checkNullOrEmpty(this.integratedElementMap)) {
			return null;
		}

		try {
			Set<String> iteks = CollectionUtil.copySet(this.integratedElementMap.keySet());
			Collection<BaseElement> bec = CollectionUtil.generateNewCollection();
			for (String k : iteks) {
				BaseElement be;
				if (k != null && k.startsWith(keyPrefix) 
						&& (be = this.integratedElementMap.remove(k)) != null) {
					bec.add(be);
				}
			}
			return bec;
		} finally {
			destroyIntegratedElementMap();
		}
	}


	/* class methods */

	@SuppressWarnings("unchecked")
	protected GenericElement generatePojoElementDelegate() throws LIMException {
		try {
			String sn = super.generateClassSimpleNameFromType(this.getType());
			Class<? extends BaseElement> becl = PojoElementClassConstant.getElementClass(sn);
			if (becl == null) {
				becl = (Class<? extends BaseElement>) Class.forName(
						this.getClass().getPackage().getName() + "." + sn);
			}
			return !GenericElement.class.isAssignableFrom(becl) ? null : (GenericElement) becl.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw LIMException.getLIMException(e);
		}
	}


	/* overridden methods */

	@Override
	protected void addThisAsInnerElement() throws LIMException {
		addInnerElementDelegate(this);
	}


	@Override
	public IConvertible getXmlElement() throws LIMException {
		if (this.xmlElement == null) {
			this.xmlElement = this;
		}
		return this.xmlElement;
	}

	@Override
	public IConvertible getPojoElement(final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (this.pojoElement == null) {
			this.pojoElement = (StringUtil.isEmpty(this.getType()) 
							|| !this.getClass().equals(GenericElement.class)) 
					? this : generatePojoElementDelegate();
		}
		this.pojoElement.reload(this, rootElementMap, rootActionableElementMap);
		return this.pojoElement;
	}

	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (!(convertible instanceof GenericElement) || super.reload(convertible) == null) {
			return null;
		}
		load((GenericElement) convertible);
		reloadFromRootElementMap(rootElementMap);
		addThisAsInnerElement();
		return (IConvertible) this;
	}

	private void load(final GenericElement element) throws LIMException {
		if (element != null) {
			this.dynamic = element.dynamic;
			this.dynamicInformation = element.dynamicInformation;
			this.integratedAttributeMap = element.integratedAttributeMap;
			this.integratedElementMap = element.integratedElementMap;
		}

		if (CollectionUtil.checkNullOrEmpty(this.integratedAttributeMap)) {
			return;
		}

		Set<String> itatks = this.integratedAttributeMap.keySet();
		Set<String> dfptks = CollectionUtil.generateNewSet();
		for (String k : itatks) {
			if (k.startsWith(DYNAMIC_FRAGMENT_PARAMETER_TYPES) 
					&& StringUtil.isNumeric(k.substring(DYNAMIC_FRAGMENT_PARAMETER_TYPES.length()))) {
				dfptks.add(k);
			}
		}
		if (CollectionUtil.checkNullOrEmpty(dfptks)) {
			return;
		}
		Set<String> ks = CollectionUtil.copySet(dfptks);
		for (String k : dfptks) {
			Integer i = StringUtil.integerValue(k.substring(DYNAMIC_FRAGMENT_PARAMETER_TYPES.length()));
			if (i == null || i < 0 || i > dfptks.size()) {
				ks.remove(k);
			}
		}
		if (CollectionUtil.checkNullOrEmpty(ks)) {
			return;
		}
		dfptks = ks;
		DynamicParameterType[] dpta = new DynamicParameterType[dfptks.size()];
		for (String k : dfptks) {
			Integer i = StringUtil.integerValue(k.substring(NET_DYNAMIC_CONDITIONS.length()));
			String value;
			if (i == null || i < 0 || i > dfptks.size() 
					|| (value = this.integratedAttributeMap.get(k)) == null) {
				continue;
			}
			dpta[i] = DynamicParameterType.valueOf(value);
		}
		setDynamicFragmentParameterTypes(CollectionUtil.checkNullOrEmpty(dpta) ? null : dpta);
	}

	private void reloadFromRootElementMap(final Map<String, GenericElement> rootElementMap) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(rootElementMap) 
				|| CollectionUtil.checkNullOrEmpty(this.integratedElementMap)) {
			return;
		}

		BaseElement wholeDynamicCondition = this.integratedElementMap.get(WHOLE_DYNAMIC_CONDITION);
		if (wholeDynamicCondition != null && wholeDynamicCondition.getId() != null) {
			GenericElement ge = rootElementMap.get(wholeDynamicCondition.getId());
			if (ge instanceof ConditionGroup) {
				setWholeDynamicCondition((ConditionGroup) ge);
			}
		}

		Set<String> iteks = this.integratedElementMap.keySet();
		Set<String> ndcdks = CollectionUtil.generateNewSet();
		for (String k : iteks) {
			if (k.startsWith(NET_DYNAMIC_CONDITIONS) 
					&& StringUtil.isNumeric(k.substring(NET_DYNAMIC_CONDITIONS.length()))) {
				ndcdks.add(k);
			}
		}
		if (CollectionUtil.checkNullOrEmpty(ndcdks)) {
			return;
		}
		Set<String> ks = CollectionUtil.copySet(ndcdks);
		for (String k : ndcdks) {
			Integer i = StringUtil.integerValue(k.substring(NET_DYNAMIC_CONDITIONS.length()));
			if (i == null || i < 0 || i > ndcdks.size()) {
				ks.remove(k);
			}
		}
		if (CollectionUtil.checkNullOrEmpty(ks)) {
			return;
		}
		ndcdks = ks;
		ConditionGroup[] cga = new ConditionGroup[ndcdks.size()];
		for (String k : ndcdks) {
			Integer i = StringUtil.integerValue(k.substring(NET_DYNAMIC_CONDITIONS.length()));
			BaseElement be;
			GenericElement ge;
			if (i == null || i < 0 || i > ndcdks.size() 
					|| (be = this.integratedElementMap.get(k)) == null || be.getId() == null 
					|| !((ge = rootElementMap.get(be.getId())) instanceof ConditionGroup)) {
				continue;
			}
			cga[i] = (ConditionGroup) ge;
		}
		setNetDynamicConditions(CollectionUtil.checkNullOrEmpty(cga) ? null : cga);
	}


	@Override
	public GenericElement cloneElement(final Map<String, BaseElement> clonedElementMap) 
			throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				String type;
				if (value != null && value.getClass().equals(GenericElement.class) 
						&& ((type = value.getType()) == null || type.equals(TYPE_NAME))) {
					return (GenericElement) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		GenericElement clonedElement = (GenericElement) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	public GenericElement cloneToElement(final GenericElement clonedElement) throws LIMException {
		return cloneToElement(clonedElement, null);
	}

	private GenericElement cloneToElement(final GenericElement clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (clonedElement == null) {
			return null;
		}

		clonedElement.dynamic = this.dynamic;

		clonedElement.dynamicInformation = (this.dynamicInformation != null) 
				? (DynamicInformation) this.dynamicInformation.cloneElement(clonedElementMap) 
				: clonedElement.dynamicInformation;

		if (!CollectionUtil.checkNullOrEmpty(this.integratedAttributeMap)) {
			clonedElement.initIntegratedAttributeMap();
			Set<String> itatks = this.integratedAttributeMap.keySet();
			for (String k : itatks) {
				String value = this.integratedAttributeMap.get(k);
				clonedElement.integratedAttributeMap.put(k, value);
			}
			clonedElement.destroyIntegratedAttributeMap();
		}
		if (!CollectionUtil.checkNullOrEmpty(this.integratedElementMap)) {
			clonedElement.initIntegratedElementMap();
			Set<String> iteks = this.integratedElementMap.keySet();
			for (String k : iteks) {
				BaseElement value = this.integratedElementMap.get(k);
				if (value != null) {
					clonedElement.integratedElementMap.put(k, value.cloneElement(clonedElementMap));
				}
			}
			clonedElement.destroyIntegratedElementMap();
		}

		return clonedElement;
	}


	/* static methods */

	protected static void registerSubPojoClassForInit(final Class<? extends BaseElement> clazz) {
		if (GenericElement.class.isAssignableFrom(clazz)) {
			SUB_POJO_CLAZZ_COLLECTION.add(clazz);
		}
	}

}
