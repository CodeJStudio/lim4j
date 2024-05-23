package com.codejstudio.lim.pojo;

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
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.i.IOwnable;
import com.codejstudio.lim.pojo.role.Observer;
import com.codejstudio.lim.pojo.role.ObserverGroup;
import com.codejstudio.lim.pojo.role.Proposer;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.GroupableUtil;
import com.codejstudio.lim.pojo.util.JAXBBoundClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * OwnableInformationElement.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = OwnableInformationElement.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
	"baseProposer",
	"baseObserverGroup",
})
public class OwnableInformationElement extends GenericElement implements IOwnable {

	/* constants */

	private static final long serialVersionUID = -8586805936836614445L;

	public static final String TYPE_NAME = "ownable-information-element";


	/* variables */

	@XmlElement(name = "proposer")
	protected BaseElement baseProposer;

	protected Proposer proposer;


	/* variables: arrays, collections, maps, groups */

	private static final Collection<Class<? extends BaseElement>> SUB_POJO_CLAZZ_COLLECTION;

	@XmlElement(name = "observer-group")
	private BaseElement baseObserverGroup;

	private ObserverGroup observerGroup;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public OwnableInformationElement() {
		super();
	}

	public OwnableInformationElement(OwnableInformationElement element) throws LIMException {
		super(element);
		load(element);
	}

	public OwnableInformationElement(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public OwnableInformationElement(boolean initIdFlag, boolean initTypeFlag, Proposer proposer) 
			throws LIMException {
		super(initIdFlag, initTypeFlag);
		setProposer(proposer);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			SUB_POJO_CLAZZ_COLLECTION = CollectionUtil.generateNewCollection();

			PojoElementClassConstant.registerElementClassForInit(OwnableInformationElement.class);
			JAXBBoundClassConstant.registerBoundClassForInit(OwnableInformationElement.class);
			OwnableInformationElement.registerSubPojoClassForInit(OwnableInformationElement.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	private void initObserverGroup() throws LIMException {
		if (this.observerGroup == null) {
			this.observerGroup = new ObserverGroup(true);
			super.addInnerElementDelegate(this.observerGroup);
			this.baseObserverGroup = new BaseElement(observerGroup);
		}
	}


	/* destroyers */

	private void destroyObserverGroup() throws LIMException {
		if (this.observerGroup != null && this.observerGroup.size() == 0) {
			this.baseObserverGroup = null;
			super.removeInnerElementDelegate(this.observerGroup);
			this.observerGroup = null;
		}
	}


	/* getters & setters */

	@XmlTransient
	public BaseElement getBaseProposer() {
		return baseProposer;
	}

	public void setBaseProposer(BaseElement baseProposer) {
		this.baseProposer = baseProposer;
	}

	@Override
	@XmlTransient
	public Proposer getProposer() {
		return proposer;
	}

	@Override
	public boolean setProposer(final Proposer proposer) throws LIMException {
		if (Objects.equals(this.proposer, proposer)) {
			return true;
		}

		boolean flag = true;
		if (this.proposer != null) {
			this.baseProposer = null;
			flag &= this.proposer.removeProposedElement(this) 
					& super.removeInnerElementDelegate(this.proposer);
			this.proposer = null;
		}
		if (proposer != null) {
			this.proposer = proposer;
			flag &= super.addInnerElementDelegate(proposer) 
					& this.proposer.addProposedElement(this);
			this.baseProposer = new BaseElement(proposer);
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setProposer(" 
					+ ((proposer == null) ? null : proposer.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setProposer(" 
					+ ((proposer == null) ? null : proposer.toBaseString()) + ")");
		}
		return flag;
	}


	@XmlTransient
	public BaseElement getBaseObserverGroup() {
		return baseObserverGroup;
	}

	public void setBaseObserverGroup(BaseElement baseObserverGroup) {
		this.baseObserverGroup = baseObserverGroup;
	}

	@Override
	public ObserverGroup getObserverGroup() {
		return observerGroup;
	}


	/* CRUD for arrays, collections, maps, groups: observers */

	@Override
	public boolean containObserver(final Observer observer) {
		return (this.observerGroup == null) ? false : this.observerGroup.containGroupElement(observer);
	}

	@Override
	public boolean addObserver(final Observer... observers) throws LIMException {
		return addObserver((observers == null) ? null : Arrays.asList(observers));
	}

	@Override
	public boolean addObserver(final Collection<Observer> observerCollection) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(observerCollection)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to addObserver(" 
					+ BaseElement.toBaseString(observerCollection) + ")");
			return false;
		}

		try {
			initObserverGroup();
			boolean flag = true;
			for (Observer observer : observerCollection) {
				if (observer != null && !this.observerGroup.containGroupElement(observer)) {
					flag &= super.addInnerElementDelegate(observer) 
							& this.observerGroup.addGroupElement(observer) 
							& observer.addObservedElement(this);
				}
			}
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": addObserver(" 
						+ BaseElement.toBaseString(observerCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to addObserver(" 
						+ BaseElement.toBaseString(observerCollection) + ")");
			}
			return flag;
		} finally {
			destroyObserverGroup();
		}
	}

	@Override
	public boolean removeObserver(final Observer... observers) throws LIMException {
		return removeObserver((observers == null) ? null : Arrays.asList(observers));
	}

	@Override
	public boolean removeObserver(final Collection<Observer> observerCollection) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(observerCollection) 
				|| GroupableUtil.checkNullOrEmpty(this.observerGroup)) {
			ElementTrace.log.warn(this.toBaseString() + "fail to removeObserver(" 
					+ BaseElement.toBaseString(observerCollection) + ")");
			return false;
		}

		try {
			boolean flag = true;
			for (Observer observer : observerCollection) {
				if (observer != null && this.observerGroup.containGroupElement(observer)) {
					flag &= super.removeInnerElementDelegate(observer) 
							& this.observerGroup.removeGroupElement(observer) 
							& observer.removeObservedElement(this);
				}
			}
			if (flag) {
				ElementTrace.log.info(this.toBaseString() + ": removeObserver(" 
						+ BaseElement.toBaseString(observerCollection) + ")");
			} else {
				ElementTrace.log.warn(this.toBaseString() + "fail to removeObserver(" 
						+ BaseElement.toBaseString(observerCollection) + ")");
			}
			return flag;
		} finally {
			destroyObserverGroup();
		}
	}


	/* overridden methods */

	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (!(convertible instanceof OwnableInformationElement) 
				|| super.reload(convertible, rootElementMap, rootActionableElementMap) == null) {
			return null;
		}
		load((OwnableInformationElement) convertible);
		reloadFromRootElementMap(rootElementMap);
		return (IConvertible) this;
	}

	private void load(final OwnableInformationElement element) {
		if (element != null) {
			this.baseProposer = element.baseProposer;
			this.proposer = element.proposer;
			this.baseObserverGroup = element.baseObserverGroup;
			this.observerGroup = element.observerGroup;
		}
	}

	private void reloadFromRootElementMap(final Map<String, GenericElement> rootElementMap) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		if (this.baseProposer != null && this.baseProposer.getId() != null) {
			GenericElement ge = rootElementMap.get(this.baseProposer.getId());
			this.proposer = (ge instanceof Proposer) ? (Proposer) ge : this.proposer;
			super.addInnerElementDelegate(this.proposer);
		}
		if (this.baseObserverGroup != null && this.baseObserverGroup.getId() != null) {
			GenericElement ge = rootElementMap.get(this.baseObserverGroup.getId());
			this.observerGroup = (ge instanceof ObserverGroup) ? (ObserverGroup) ge : this.observerGroup;
			super.addInnerElementDelegate(this.observerGroup);
		}
	}


	@Override
	public OwnableInformationElement cloneElement(final Map<String, BaseElement> clonedElementMap) 
			throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				String type;
				if (value != null && value.getClass().equals(OwnableInformationElement.class) 
						&& ((type = value.getType()) == null || type.equals(TYPE_NAME))) {
					return (OwnableInformationElement) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		OwnableInformationElement clonedElement 
				= (OwnableInformationElement) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public OwnableInformationElement cloneToElement(final GenericElement clonedElement) 
			throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof OwnableInformationElement) 
					|| !((ce = super.cloneToElement(clonedElement)) instanceof OwnableInformationElement)) 
				? null : cloneToElement((OwnableInformationElement) ce, null);
	}

	private OwnableInformationElement cloneToElement(final OwnableInformationElement clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.baseProposer = (this.baseProposer != null) 
				? (BaseElement) this.baseProposer.cloneElement(clonedElementMap) 
				: clonedElement.baseProposer;
		clonedElement.proposer = (this.proposer != null) 
				? (Proposer) this.proposer.cloneElement(clonedElementMap) 
						: clonedElement.proposer;

		clonedElement.baseObserverGroup = (this.baseObserverGroup != null) 
				? (BaseElement) this.baseObserverGroup.cloneElement(clonedElementMap) 
				: clonedElement.baseObserverGroup;
		clonedElement.observerGroup = (this.observerGroup != null) 
				? (ObserverGroup) this.observerGroup.cloneElement(clonedElementMap) 
				: clonedElement.observerGroup;

		return clonedElement;
	}


	/* static methods */

	protected static void registerSubPojoClassForInit(final Class<? extends BaseElement> clazz) {
		if (OwnableInformationElement.class.isAssignableFrom(clazz)) {
			SUB_POJO_CLAZZ_COLLECTION.add(clazz);
		}
	}

}
