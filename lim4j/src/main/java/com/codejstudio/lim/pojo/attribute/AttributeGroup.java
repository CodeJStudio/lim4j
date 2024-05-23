package com.codejstudio.lim.pojo.attribute;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.i.IGroupable;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.GroupableUtil;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * AttributeGroup.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class AttributeGroup extends Attribute implements IGroupable<Attribute> {

	/* constants */

	private static final long serialVersionUID = -6848922756342140212L;


	/* variables: arrays, collections, maps, groups */

	private List<Attribute> innerGroupList;

	private Map<String, Attribute> attributeMap;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public AttributeGroup() {
		super();
	}

	public AttributeGroup(boolean initIdFlag) throws LIMException {
		super(initIdFlag, true);
	}

	public AttributeGroup(boolean initIdFlag, Attribute... attributes) throws LIMException {
		super(initIdFlag, true);
		addGroupElement(attributes);
	}


	public AttributeGroup(Attribute... attributes) throws LIMException {
		this(true, attributes);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(AttributeGroup.class);
		GroupableUtil.registerGroupableClassForInit(Attribute.class, AttributeGroup.class);
		Attribute.registerSubPojoClassForInit(AttributeGroup.class);
	}


	private void initInnerGroupList() throws LIMException {
		if (this.innerGroupList == null) {
			this.innerGroupList = CollectionUtil.generateNewList();
		}
	}

	private void initAttributeMap() throws LIMException {
		if (this.attributeMap == null) {
			this.attributeMap = CollectionUtil.generateNewMap();
		}
	}


	/* destroyers */

	private void destroyInnerGroupList() {
		if (this.innerGroupList != null && this.innerGroupList.size() == 0) {
			this.innerGroupList = null;
		}
	}

	private void destroyAttributeMap() {
		if (this.attributeMap != null && this.attributeMap.size() == 0) {
			this.attributeMap = null;
		}
	}


	/* getters & setters */

	@Override
	public List<Attribute> getInnerGroupList() {
		return innerGroupList;
	}


	/* CRUD for arrays, collections, maps, groups: attributes */

	public boolean containAttributeByKey(final String key) {
		return (this.attributeMap == null) ? false : this.attributeMap.containsKey(key);
	}

	public boolean containAttribute(final Attribute attribute) {
		return (this.attributeMap == null || attribute == null) ? false 
				: (this.attributeMap.containsKey(attribute.key) 
						|| this.attributeMap.containsValue(attribute));
	}


	public Attribute getAttributeByKey(final String key) {
		return (this.attributeMap == null) ? null : this.attributeMap.get(key);
	}

	public Attribute getAttribute(final Attribute attribute) {
		if (this.attributeMap == null || attribute == null) {
			return null;
		}
		if (this.attributeMap.containsKey(attribute.key)) {
			return this.attributeMap.get(attribute.key);
		} else if (this.attributeMap.containsValue(attribute)) {
			return attribute;
		}
		return null;
	}


	public boolean putAttribute(final Attribute... attributes) throws LIMException {
		return putAttribute((attributes == null) ? null : Arrays.asList(attributes));
	}

	public boolean putAttribute(final Collection<Attribute> attributeCollection) throws LIMException { 
		if (CollectionUtil.checkNullOrEmpty(attributeCollection)) {
			return false;
		}

		try {
			initAttributeMap();
			boolean flag = true;
			for (Attribute at : attributeCollection) {
				if (at == null) {
					continue;
				}

				Attribute containedAt = getAttribute(at);
				if (containedAt != null) {
					if (!containedAt.equals(at)) {
						this.attributeMap.put(at.getKey(), at);
						flag &= replaceGroupElement(containedAt, at);
					}
				} else {
					this.attributeMap.put(at.getKey(), at);
					flag &= addGroupElement(at);
				}
			}

			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": putAttribute(" 
						+ BaseElement.toBaseString(attributeCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to putAttribute(" 
						+ BaseElement.toBaseString(attributeCollection) + ")");
			}
			return flag;
		} finally {
			destroyAttributeMap();
		}
	}


	public boolean removeAttributeByKey(final String... keys) throws LIMException {
		return removeAttributeByKey((keys == null) ? null : Arrays.asList(keys));
	}

	public boolean removeAttributeByKey(final Collection<String> keyCollection) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(keyCollection) 
				|| CollectionUtil.checkNullOrEmpty(this.attributeMap)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeAttributeByKey(" + keyCollection + ")");
			return false;
		}

		boolean flag = true;
		for (String key : keyCollection) {
			if (key != null) {
				flag &= removeGroupElement(this.attributeMap.remove(key));
			}
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": removeAttributeByKey(" + keyCollection + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeAttributeByKey(" + keyCollection + ")");
		}
		return flag;
	}

	public boolean removeAttribute(final Attribute... attributes) throws LIMException {
		return removeAttribute((attributes == null) ? null : Arrays.asList(attributes));
	}

	public boolean removeAttribute(Collection<Attribute> attributeCollection) throws LIMException { 
		if (CollectionUtil.checkNullOrEmpty(attributeCollection)
				|| CollectionUtil.checkNullOrEmpty(this.attributeMap)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeAttribute(" 
					+ BaseElement.toBaseString(attributeCollection) + ")");
			return false;
		}

		try {
			boolean flag = true;
			for (Attribute at : attributeCollection) {
				if (!containAttribute(at)) {
					flag = false;
				} else if (at != null) {
					flag &= this.attributeMap.remove(at.getKey(), at) 
							& removeGroupElement(at);
				}
			}

			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeAttribute(" 
						+ BaseElement.toBaseString(attributeCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeAttribute(" 
						+ BaseElement.toBaseString(attributeCollection) + ")");
			}
			return flag;
		} finally {
			destroyAttributeMap();
		}
	}


	/* CRUD for arrays, collections, maps, groups: group elements */

	@Override
	public int size() {
		return (this.innerGroupList == null) ? 0 : this.innerGroupList.size();
	}

	@Override
	public boolean containGroupElement(final Attribute groupElement) {
		return (this.innerGroupList == null) ? false : this.innerGroupList.contains(groupElement);
	}

	@Override
	public boolean addGroupElement(final Attribute... groupElements) throws LIMException {
		return addGroupElement((groupElements == null) ? null : Arrays.asList(groupElements));
	}

	@Override
	public boolean addGroupElement(final Collection<? extends Attribute> groupElementCollection) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(groupElementCollection)) {
			return false;
		}

		try {
			initInnerGroupList();
			boolean flag = true;
			for (Attribute e : groupElementCollection) {
				if (e == null || e.getId() == null) {
					continue;
				}
				if (this.innerGroupList.contains(e)) {
					flag = false;
					continue;
				}

				flag &= this.innerGroupList.add(e) 
						& super.addInnerElementDelegate(e) 
						& super.putIntegratedElementDelegate(
								IGroupable.GROUP_KEY_PREFIX_IEM + e.getId(), new BaseElement(e));
			}

			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": addGroupElement(" 
						+ BaseElement.toBaseString(groupElementCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to addGroupElement(" 
						+ BaseElement.toBaseString(groupElementCollection) + ")");
			}
			return flag;
		} finally {
			destroyInnerGroupList();
		}
	}

	@Override
	public boolean removeGroupElement(final Attribute... groupElements) throws LIMException {
		return removeGroupElement((groupElements == null) ? null : Arrays.asList(groupElements)); 
	}

	@Override
	public boolean removeGroupElement(final Collection<? extends Attribute> groupElementCollection) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(groupElementCollection) 
				|| CollectionUtil.checkNullOrEmpty(this.innerGroupList)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeGroupElement(" 
					+ BaseElement.toBaseString(groupElementCollection) + ")");
			return false;
		}

		try {
			boolean flag = true;
			for (Attribute e : groupElementCollection) {
				if (e == null || e.getId() == null) {
					continue;
				}
				if (!this.innerGroupList.contains(e)) {
					flag = false;
					continue;
				}

				flag &= super.removeIntegratedElementDelegate(IGroupable.GROUP_KEY_PREFIX_IEM + e.getId()) 
						& super.removeInnerElementDelegate(e) 
						& this.innerGroupList.remove(e);
			}

			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeGroupElement(" 
						+ BaseElement.toBaseString(groupElementCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeGroupElement(" 
						+ BaseElement.toBaseString(groupElementCollection) + ")");
			}
			return flag;
		} finally {
			destroyInnerGroupList();
		}
	}

	@Override
	public boolean replaceGroupElement(final Attribute replacee, final Attribute replacer) throws LIMException {
		return (Objects.equals(replacee, replacer)) ? false 
				: (removeGroupElement((replacee == null) ? null : Arrays.asList(replacee)) 
						& addGroupElement((replacer == null) ? null : Arrays.asList(replacer)));
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
		Collection<BaseElement> vc;
		if (CollectionUtil.checkNullOrEmpty(rootElementMap) 
				|| (item = super.getIntegratedElementMap()) == null 
				|| CollectionUtil.checkNullOrEmpty(vc = item.values())) {
			return;
		}

		initInnerGroupList();
		initAttributeMap();
		for (BaseElement be : vc) {
			GenericElement ge;
			if (be == null || be.getId() == null 
					|| !((ge = rootElementMap.get(be.getId())) instanceof Attribute)) {
				continue;
			}
			Attribute at = (Attribute) ge;
			super.addInnerElementDelegate(ge);
			this.innerGroupList.add(at);
			this.attributeMap.put(at.getKey(), at);
		}
		destroyInnerGroupList();
		destroyAttributeMap();
	}


	@Override
	public AttributeGroup cloneElement(final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				if (value != null && value.getClass().equals(AttributeGroup.class)) {
					return (AttributeGroup) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		AttributeGroup clonedElement = (AttributeGroup) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public AttributeGroup cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof AttributeGroup) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof AttributeGroup)) 
				? null : cloneToElement((AttributeGroup) ce, null);
	}

	private AttributeGroup cloneToElement(final AttributeGroup clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(this.innerGroupList)) {
			clonedElement.initInnerGroupList();
			for (Attribute element : this.innerGroupList) {
				if (element != null && !this.equals(element)) {
					clonedElement.innerGroupList.add(element.cloneElement(clonedElementMap));
				}
			}
			clonedElement.destroyInnerGroupList();
		}
		if (this.attributeMap != null && this.attributeMap.size() > 0) {
			clonedElement.initAttributeMap();
			Set<String> atks = this.attributeMap.keySet();
			for (String k : atks) {
				Attribute value = this.attributeMap.get(k);
				if (value != null) {
					clonedElement.attributeMap.put(k, value.cloneElement(clonedElementMap));
				}
			}
			clonedElement.destroyAttributeMap();
		}

		return clonedElement;
	}

}
