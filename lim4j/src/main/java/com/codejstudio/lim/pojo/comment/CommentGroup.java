package com.codejstudio.lim.pojo.comment;

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
 * CommentGroup.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class CommentGroup extends Comment implements IGroupable<Comment> {

	/* constants */

	private static final long serialVersionUID = 3911405907687371162L;


	/* variables: arrays, collections, maps, groups */

	private List<Comment> innerGroupList;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public CommentGroup() {
		super();
	}

	public CommentGroup(boolean initIdFlag) throws LIMException {
		super(initIdFlag, true);
	}

	public CommentGroup(boolean initIdFlag, Comment... comments) throws LIMException {
		super(initIdFlag, true);
		addGroupElement(comments);
	}


	public CommentGroup(Comment... comments) throws LIMException {
		this(true, comments);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(CommentGroup.class);
		GroupableUtil.registerGroupableClassForInit(Comment.class, CommentGroup.class);
		Comment.registerSubPojoClassForInit(CommentGroup.class);
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
	public List<Comment> getInnerGroupList() {
		return innerGroupList;
	}


	/* CRUD for arrays, collections, maps, groups: group elements */

	@Override
	public int size() {
		return (this.innerGroupList == null) ? 0 : this.innerGroupList.size();
	}

	@Override
	public boolean containGroupElement(final Comment groupElement) {
		return (this.innerGroupList == null) ? false : this.innerGroupList.contains(groupElement);
	}

	@Override
	public boolean addGroupElement(final Comment... groupElements) throws LIMException {
		return addGroupElement((groupElements == null) ? null : Arrays.asList(groupElements));
	}

	@Override
	public boolean addGroupElement(final Collection<? extends Comment> groupElementCollection) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(groupElementCollection)) {
			return false;
		}

		try {
			initInnerGroupList();
			boolean flag = true;
			for (Comment e : groupElementCollection) {
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
	public boolean removeGroupElement(final Comment... groupElements) throws LIMException {
		return removeGroupElement((groupElements == null) ? null : Arrays.asList(groupElements));
	}

	@Override
	public boolean removeGroupElement(final Collection<? extends Comment> groupElementCollection) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(groupElementCollection) 
				|| CollectionUtil.checkNullOrEmpty(this.innerGroupList)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeGroupElement(" 
					+ BaseElement.toBaseString(groupElementCollection) + ")");
			return false;
		}

		try {
			boolean flag = true;
			for (Comment e : groupElementCollection) {
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
	public boolean replaceGroupElement(final Comment replacee, final Comment replacer) throws LIMException {
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
					|| !((ge = rootElementMap.get(be.getId())) instanceof Comment)) {
				continue;
			}
			super.addInnerElementDelegate(ge);
			this.innerGroupList.add((Comment) ge);
		}
		destroyInnerGroupList();
	}


	@Override
	public CommentGroup cloneElement(final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				if (value != null && value.getClass().equals(CommentGroup.class)) {
					return (CommentGroup) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		CommentGroup clonedElement = (CommentGroup) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public CommentGroup cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof CommentGroup) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof CommentGroup)) 
				? null : cloneToElement((CommentGroup) ce, null);
	}

	private CommentGroup cloneToElement(final CommentGroup clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(this.innerGroupList)) {
			clonedElement.initInnerGroupList();
			for (Comment element : this.innerGroupList) {
				if (element != null && !this.equals(element)) {
					clonedElement.innerGroupList.add(element.cloneElement(clonedElementMap));
				}
			}
			clonedElement.destroyInnerGroupList();
		}

		return clonedElement;
	}

}
