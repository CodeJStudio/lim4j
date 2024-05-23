package com.codejstudio.lim.pojo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.i.IGroupable;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.GroupableUtil;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * InformationUnitGroup.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class InformationUnitGroup extends InformationUnit implements IGroupable<InformationUnit> {

	/* constants */

	private static final long serialVersionUID = -1605475870868329092L;


	/* variables: arrays, collections, maps, groups */

	private List<InformationUnit> innerGroupList;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public InformationUnitGroup() {
		super();
	}

	public InformationUnitGroup(boolean initIdFlag) throws LIMException {
		super(initIdFlag, true);
	}

	public InformationUnitGroup(boolean initIdFlag, InformationUnit... elements) throws LIMException {
		super(initIdFlag, true);
		addGroupElement(elements);
	}


	public InformationUnitGroup(InformationUnit... elements) throws LIMException {
		this(true, elements);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(InformationUnitGroup.class);
		GroupableUtil.registerGroupableClassForInit(InformationUnit.class, InformationUnitGroup.class);
		InformationUnit.registerSubPojoClassForInit(InformationUnitGroup.class);
	}


	private void initInnerGroupList() throws LIMException {
		if (this.innerGroupList == null) {
			this.innerGroupList = CollectionUtil.generateNewList();
		}
	}


	/* destroyers */

	private void destroyInnerGroupList() {
		if (this.innerGroupList != null && this.innerGroupList.size() == 0) {
			this.innerGroupList = null;
		}
	}


	/* getters & setters */

	@Override
	public List<InformationUnit> getInnerGroupList() {
		return innerGroupList;
	}


	/* CRUD for arrays, collections, maps, groups: group elements */

	@Override
	public int size() {
		return (this.innerGroupList == null) ? 0 : this.innerGroupList.size();
	}

	@Override
	public boolean containGroupElement(final InformationUnit groupElement) {
		return (this.innerGroupList == null) ? false : this.innerGroupList.contains(groupElement);
	}

	@Override
	public boolean addGroupElement(final InformationUnit... groupElements) throws LIMException {
		return addGroupElement((groupElements == null) ? null : Arrays.asList(groupElements));
	}

	@Override
	public boolean addGroupElement(final Collection<? extends InformationUnit> groupElementCollection) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(groupElementCollection)) {
			return false;
		}

		try {
			initInnerGroupList();
			boolean flag = true;
			for (InformationUnit e : groupElementCollection) {
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
	public boolean removeGroupElement(final InformationUnit... groupElements) throws LIMException {
		return removeGroupElement((groupElements == null) ? null : Arrays.asList(groupElements));
	}

	@Override
	public boolean removeGroupElement(final Collection<? extends InformationUnit> groupElementCollection) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(groupElementCollection) 
				|| CollectionUtil.checkNullOrEmpty(this.innerGroupList)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeGroupElement(" 
					+ BaseElement.toBaseString(groupElementCollection) + ")");
			return false;
		}

		try {
			boolean flag = true;
			for (InformationUnit e : groupElementCollection) {
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
	public boolean replaceGroupElement(final InformationUnit replacee, final InformationUnit replacer) 
			throws LIMException {
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
		for (BaseElement be : vc) {
			GenericElement ge;
			if (be == null || be.getId() == null 
					|| !((ge = rootElementMap.get(be.getId())) instanceof InformationUnit)) {
				continue;
			}
			super.addInnerElementDelegate(ge);
			this.innerGroupList.add((InformationUnit) ge);
		}
		destroyInnerGroupList();
	}


	@Override
	public InformationUnitGroup cloneElement(final Map<String, BaseElement> clonedElementMap) 
			throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				if (value != null && value.getClass().equals(InformationUnitGroup.class)) {
					return (InformationUnitGroup) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		InformationUnitGroup clonedElement = (InformationUnitGroup) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public InformationUnitGroup cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof InformationUnitGroup) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof InformationUnitGroup)) 
				? null : cloneToElement((InformationUnitGroup) ce, null);
	}

	private InformationUnitGroup cloneToElement(final InformationUnitGroup clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(this.innerGroupList)) {
			clonedElement.initInnerGroupList();
			for (InformationUnit element : this.innerGroupList) {
				if (element != null && !this.equals(element)) {
					clonedElement.innerGroupList.add(
							(InformationUnit) element.cloneElement(clonedElementMap));
				}
			}
			clonedElement.destroyInnerGroupList();
		}

		return clonedElement;
	}

}
