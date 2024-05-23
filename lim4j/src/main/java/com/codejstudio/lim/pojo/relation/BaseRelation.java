package com.codejstudio.lim.pojo.relation;

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
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.OwnableInformationElement;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.i.IRelationable;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.JAXBBoundClassConstant;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * BaseRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = BaseRelation.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
	"basePrimaryElement",
	"baseSecondaryElement",
})
public class BaseRelation extends OwnableInformationElement {

	/* constants */

//	static final Logger log = LoggerFactory.getLogger(BaseRelation.class);

	private static final long serialVersionUID = -5900197204744370529L;

	public static final String TYPE_NAME = "relation";


	/* variables */

	@XmlTransient
	protected BaseElement basePrimaryElement;

	protected AbstractRelationableInformationElement primaryElement;

	@XmlTransient
	protected BaseElement baseSecondaryElement;

	protected AbstractRelationableInformationElement secondaryElement;


	/* variables: arrays, collections, maps, groups */

	private static final Collection<Class<? extends BaseElement>> SUB_POJO_CLAZZ_COLLECTION;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public BaseRelation() {
		super();
	}

	public BaseRelation(BaseRelation relation) throws LIMException {
		super(relation);
		load(relation);
	}

	public BaseRelation(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public BaseRelation(boolean initIdFlag, boolean initTypeFlag, 
			AbstractRelationableInformationElement primaryElement, 
			AbstractRelationableInformationElement secondaryElement) throws LIMException {
		super(initIdFlag, initTypeFlag);
		setPrimaryElement(primaryElement, true);
		setSecondaryElement(secondaryElement, true);
	}


	public BaseRelation(AbstractRelationableInformationElement primaryElement, 
			AbstractRelationableInformationElement secondaryElement) throws LIMException {
		this(true, false, primaryElement, secondaryElement);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			SUB_POJO_CLAZZ_COLLECTION = CollectionUtil.generateNewCollection();

			PojoElementClassConstant.registerElementClassForInit(BaseRelation.class);
			JAXBBoundClassConstant.registerBoundClassForInit(BaseRelation.class);
			BaseRelation.registerSubPojoClassForInit(BaseRelation.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	/* getters & setters */

	@XmlElement(name = "primary")
	public BaseElement getBasePrimaryElement() {
		Class<? extends GenericElement> gecl;
		try {
			if (this.pojoElement instanceof BaseRelation 
					&& !(gecl = this.pojoElement.getClass()).equals(BaseRelation.class) 
					&& gecl.getDeclaredMethod("getBasePrimaryElement") != null) {
				return ((BaseRelation) this.pojoElement).getBasePrimaryElement();
			}
		} catch (NoSuchMethodException | SecurityException e) {
//			log.debug(e.toString());
		}
		return basePrimaryElement;
	}

	public void setBasePrimaryElement(BaseElement basePrimaryElement) {
		this.basePrimaryElement = basePrimaryElement;
	}

	@XmlTransient
	public AbstractRelationableInformationElement getPrimaryElement() {
		return primaryElement;
	}

	protected boolean setPrimaryElement(final AbstractRelationableInformationElement primaryElement, 
			final boolean baseElementFlag) throws LIMException {
		if (Objects.equals(this.primaryElement, primaryElement)) {
			return true;
		}

		boolean flag = true;
		if (this.primaryElement != null) {
			this.basePrimaryElement = baseElementFlag ? null : this.basePrimaryElement;
			flag &= this.primaryElement.removeRelation(this) 
					& super.removeInnerElementDelegate(this.primaryElement);
			this.primaryElement = null;
		}
		if (primaryElement != null) {
			this.primaryElement = primaryElement;
			flag &= super.addInnerElementDelegate(primaryElement) 
					& this.primaryElement.addRelation(this);
			this.basePrimaryElement = baseElementFlag 
					? new BaseElement(primaryElement) : this.basePrimaryElement;
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setPrimaryElement(" 
					+ ((primaryElement == null) ? null : primaryElement.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setPrimaryElement(" 
					+ ((primaryElement == null) ? null : primaryElement.toBaseString()) + ")");
		}
		return flag;
	}


	@XmlElement(name = "secondary")
	public BaseElement getBaseSecondaryElement() {
		Class<? extends GenericElement> gecl;
		try {
			if (this.pojoElement instanceof BaseRelation 
					&& !(gecl = this.pojoElement.getClass()).equals(BaseRelation.class) 
					&& gecl.getDeclaredMethod("getBaseSecondaryElement") != null) {
				return ((BaseRelation) this.pojoElement).getBaseSecondaryElement();
			}
		} catch (NoSuchMethodException | SecurityException e) {
//			log.debug(e.toString());
		}
		return baseSecondaryElement;
	}

	public void setBaseSecondaryElement(BaseElement baseSecondaryElement) {
		this.baseSecondaryElement = baseSecondaryElement;
	}

	@XmlTransient
	public AbstractRelationableInformationElement getSecondaryElement() {
		return secondaryElement;
	}

	protected boolean setSecondaryElement(final AbstractRelationableInformationElement secondaryElement, 
			final boolean baseElementFlag) throws LIMException {
		if (Objects.equals(this.secondaryElement, secondaryElement)) {
			return true;
		}

		boolean flag = true;
		if (this.secondaryElement != null) {
			this.baseSecondaryElement = baseElementFlag ? null : this.baseSecondaryElement;
			flag &= this.secondaryElement.removeRelation(this) 
					& super.removeInnerElementDelegate(this.secondaryElement);
			this.secondaryElement = null;
		}
		if (secondaryElement != null) {
			this.secondaryElement = secondaryElement;
			flag &= super.addInnerElementDelegate(secondaryElement) 
					& this.secondaryElement.addRelation(this);
			this.baseSecondaryElement = baseElementFlag 
					? new BaseElement(secondaryElement) : this.baseSecondaryElement;
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setSecondaryElement(" 
					+ ((secondaryElement == null) ? null : secondaryElement.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setSecondaryElement(" 
					+ ((secondaryElement == null) ? null : secondaryElement.toBaseString()) + ")");
		}
		return flag;
	}


	public IRelationable getAnotherElement(final IRelationable element) {
		if (getPrimaryElement().equals(element)) {
			return getSecondaryElement();
		} else if (getSecondaryElement().equals(element)) {
			return getPrimaryElement();
		} else {
			return null;
		}
	}


	/* overridden methods */

	@Override
	public IConvertible getXmlElement() throws LIMException {
		if (this.xmlElement == null) {
			if (this.getClass().equals(BaseRelation.class)) {
				this.xmlElement = this;
			} else {
				this.xmlElement = new BaseRelation(this);
				this.xmlElement.setPojoElement(this);
			}
		}
		return this.xmlElement;
	}

	@Override
	public IConvertible getPojoElement(final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (this.pojoElement == null) {
			this.pojoElement = (StringUtil.isEmpty(this.getType()) 
							|| !this.getClass().equals(BaseRelation.class)) 
					? this : super.generatePojoElementDelegate();
		}
		this.pojoElement.reload(this, rootElementMap, rootActionableElementMap);
		return this.pojoElement;
	}


	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (!(convertible instanceof BaseRelation) 
				|| super.reload(convertible, rootElementMap, rootActionableElementMap) == null) {
			return null;
		}
		load((BaseRelation) convertible);
		reloadFromRootElementMap(rootElementMap);
		return (IConvertible) this;
	}

	private void load(final BaseRelation element) {
		if (element != null) {
			this.basePrimaryElement = element.basePrimaryElement;
			this.primaryElement = element.primaryElement;
			this.baseSecondaryElement = element.baseSecondaryElement;
			this.secondaryElement = element.secondaryElement;
		}
	}

	private void reloadFromRootElementMap(final Map<String, GenericElement> rootElementMap) 
			throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		if (this.basePrimaryElement != null) {
			GenericElement ge = rootElementMap.get(this.basePrimaryElement.getId());
			this.primaryElement = (ge instanceof AbstractRelationableInformationElement) 
					? (AbstractRelationableInformationElement) ge : this.primaryElement;
			super.addInnerElementDelegate(this.primaryElement);
		}
		if (this.baseSecondaryElement != null) {
			GenericElement ge = rootElementMap.get(this.baseSecondaryElement.getId());
			this.secondaryElement = (ge instanceof AbstractRelationableInformationElement) 
					? (AbstractRelationableInformationElement) ge : this.secondaryElement;
			super.addInnerElementDelegate(this.secondaryElement);
		}
	}


	@Override
	public BaseRelation cloneElement(final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				String type;
				if (value != null && value.getClass().equals(BaseRelation.class) 
						&& ((type = value.getType()) == null || type.equals(TYPE_NAME))) {
					return (BaseRelation) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		BaseRelation clonedElement = (BaseRelation) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public BaseRelation cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		return (!(clonedElement instanceof BaseRelation) 
						|| !((ce = super.cloneToElement(clonedElement)) instanceof BaseRelation)) 
				? null : cloneToElement((BaseRelation) ce, null);
	}

	private BaseRelation cloneToElement(final BaseRelation clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.basePrimaryElement = (this.basePrimaryElement != null) 
				? this.basePrimaryElement.cloneElement(clonedElementMap) 
				: clonedElement.basePrimaryElement;
		clonedElement.primaryElement = (this.primaryElement != null) 
				? this.primaryElement.cloneElement(clonedElementMap) 
				: clonedElement.primaryElement;

		clonedElement.baseSecondaryElement = (this.baseSecondaryElement != null) 
				? this.baseSecondaryElement.cloneElement(clonedElementMap) 
				: clonedElement.baseSecondaryElement;
		clonedElement.secondaryElement = (this.secondaryElement != null) 
				? this.secondaryElement.cloneElement(clonedElementMap) 
				: clonedElement.secondaryElement;

		return clonedElement;
	}


	/* static methods */

	protected static void registerSubPojoClassForInit(final Class<? extends BaseElement> clazz) {
		if (BaseRelation.class.isAssignableFrom(clazz)) {
			SUB_POJO_CLAZZ_COLLECTION.add(clazz);
		}
	}

}
