package com.codejstudio.lim.pojo.role;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
 * ObserverGroup.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class ObserverGroup extends Observer implements IGroupable<Observer> {

	/* constants */

	private static final long serialVersionUID = 8776998723885986113L;


	/* variables: arrays, collections, maps, groups */

	private List<Observer> innerGroupList;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public ObserverGroup() {
		super();
	}

	public ObserverGroup(boolean initIdFlag) throws LIMException {
		super(initIdFlag, true);
	}

	public ObserverGroup(boolean initIdFlag, Observer... observers) throws LIMException {
		super(initIdFlag, true);
		addGroupElement(observers);
	}


	public ObserverGroup(Observer... observers) throws LIMException {
		this(true, observers);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(ObserverGroup.class);
		GroupableUtil.registerGroupableClassForInit(Observer.class, ObserverGroup.class);
		BaseRole.registerSubPojoClassForInit(ObserverGroup.class);
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
	public List<Observer> getInnerGroupList() {
		return innerGroupList;
	}


	/* CRUD for arrays, collections, maps, groups: group elements */

	@Override
	public int size() {
		return (this.innerGroupList == null) ? 0 : this.innerGroupList.size();
	}

	@Override
	public boolean containGroupElement(final Observer groupElement) {
		return (this.innerGroupList == null) ? false : this.innerGroupList.contains(groupElement);
	}

	@Override
	public boolean addGroupElement(final Observer... groupElements) throws LIMException {
		return addGroupElement((groupElements == null) ? null : Arrays.asList(groupElements));
	}

	@Override
	public boolean addGroupElement(final Collection<? extends Observer> groupElementCollection) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(groupElementCollection)) {
			return false;
		}

		try {
			initInnerGroupList();
			boolean flag = true;
			for (Observer e : groupElementCollection) {
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
	public boolean removeGroupElement(final Observer... groupElements) throws LIMException {
		return removeGroupElement((groupElements == null) ? null : Arrays.asList(groupElements));
	}

	@Override
	public boolean removeGroupElement(final Collection<? extends Observer> groupElementCollection) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(groupElementCollection) 
				|| CollectionUtil.checkNullOrEmpty(this.innerGroupList)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeGroupElement(" 
					+ BaseElement.toBaseString(groupElementCollection) + ")");
			return false;
		}

		try {
			boolean flag = true;
			for (Observer e : groupElementCollection) {
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
	public boolean replaceGroupElement(final Observer replacee, final Observer replacer) 
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
					|| !((ge = rootElementMap.get(be.getId())) instanceof Observer)) {
				continue;
			}
			super.addInnerElementDelegate(ge);
			this.innerGroupList.add((Observer) ge);
		}
		destroyInnerGroupList();
	}


	@Override
	public ObserverGroup cloneElement(final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				if (value != null && value.getClass().equals(ObserverGroup.class)) {
					return (ObserverGroup) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		ObserverGroup clonedElement = (ObserverGroup) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public ObserverGroup cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof ObserverGroup) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof ObserverGroup)) 
				? null : cloneToElement((ObserverGroup) ce, null);
	}

	private ObserverGroup cloneToElement(final ObserverGroup clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(this.innerGroupList)) {
			clonedElement.initInnerGroupList();
			for (Observer element : this.innerGroupList) {
				if (element != null && !this.equals(element)) {
					clonedElement.innerGroupList.add((Observer) element.cloneElement(clonedElementMap));
				}
			}
			clonedElement.destroyInnerGroupList();
		}

		return clonedElement;
	}

}
