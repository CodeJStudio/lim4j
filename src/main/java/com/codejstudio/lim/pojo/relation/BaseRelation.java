package com.codejstudio.lim.pojo.relation;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.StringUtils;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.ObjectUtil;
import com.codejstudio.lim.pojo.AbstractElement;
import com.codejstudio.lim.pojo.OwnableInformationElement;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.i.IIntegratable;
import com.codejstudio.lim.pojo.i.IRelationable;

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
	
	public static final String TYPE_NAME = "relation";


	/* variables */

	@XmlElement(name = "primary")
	protected BaseElement basePrimaryElement;
	
	protected AbstractRelationableInformationElement primaryElement;

	@XmlElement(name = "secondary")
	protected BaseElement baseSecondaryElement;

	protected AbstractRelationableInformationElement secondaryElement;

	
	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public BaseRelation() throws LIMException {
		super();
	}
	
	public BaseRelation(BaseRelation relation) throws LIMException {
		super(relation);
		load(relation);
	}

	public BaseRelation(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public BaseRelation(boolean ifInitId, boolean ifInitType, AbstractRelationableInformationElement primaryElement, AbstractRelationableInformationElement secondaryElement) throws LIMException {
		super(ifInitId, ifInitType);
		setPrimaryElement(primaryElement, true);
		setSecondaryElement(secondaryElement, true);
	}
	

	/* getters & setters */

	public BaseElement getBasePrimaryElement() {
		return basePrimaryElement;
	}

	public AbstractRelationableInformationElement getPrimaryElement() {
		return primaryElement;
	}

	protected void setPrimaryElement(AbstractRelationableInformationElement primaryElement, boolean baseElementFlag) throws LIMException {
		if(ObjectUtil.checkEquals(this.primaryElement, primaryElement)) {
			return;
		}

		if(this.primaryElement != null) {
			this.basePrimaryElement = baseElementFlag ? null : this.basePrimaryElement;
			this.primaryElement.removeRelation(this);
			super.removeInnerElementDelegate(this.primaryElement);
			this.primaryElement = null;
		}
		if(primaryElement != null) {
			this.primaryElement = primaryElement;
			super.addInnerElementDelegate(primaryElement);
			this.primaryElement.addRelation(this);
			this.basePrimaryElement = baseElementFlag ? new BaseElement(primaryElement) : this.basePrimaryElement;
		}
	}

	public BaseElement getBaseSecondaryElement() {
		return baseSecondaryElement;
	}

	public AbstractRelationableInformationElement getSecondaryElement() {
		return secondaryElement;
	}

	protected void setSecondaryElement(AbstractRelationableInformationElement secondaryElement, boolean baseElementFlag) throws LIMException {
		if(ObjectUtil.checkEquals(this.secondaryElement, secondaryElement)) {
			return;
		}
		
		if(this.secondaryElement != null) {
			this.baseSecondaryElement = baseElementFlag ? null : this.baseSecondaryElement;
			this.secondaryElement.removeRelation(this);
			super.removeInnerElementDelegate(this.secondaryElement);
			this.secondaryElement = null;
		}
		if(secondaryElement != null) {
			this.secondaryElement = secondaryElement;
			super.addInnerElementDelegate(secondaryElement);
			this.secondaryElement.addRelation(this);
			this.baseSecondaryElement = baseElementFlag ? new BaseElement(secondaryElement) : this.baseSecondaryElement;
		}
	}


	public IRelationable getAnotherElement(IRelationable element) {
		if (primaryElement.absoluteEquals(element)) {
			return secondaryElement;
		} else if (secondaryElement.absoluteEquals(element)) {
			return primaryElement;
		} else {
			return null;
		}
	}

	
	/* overridden methods */

	@Override
	public AbstractElement getXmlElement() throws LIMException {
		if(this.xmlElement == null) {
			if(this.getClass().equals(BaseRelation.class)) {
				this.xmlElement = this;
			}else {
				this.xmlElement = new BaseRelation(this);
			}
		}
		return this.xmlElement;
	}

	@Override
	public AbstractElement getPojoElement(Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (this.pojoElement == null) {
			if (StringUtils.isEmpty(this.getType())  
					|| !this.getClass().equals(BaseRelation.class)) {
				this.pojoElement = this;
			} else {
				this.pojoElement = super.generatePojoElementDelegate(rootElementMap);
			}
		}
		this.pojoElement.reload(this, rootElementMap);
		return this.pojoElement;
	}


	@Override
	public IIntegratable reload(IIntegratable element, Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (element instanceof BaseRelation) {
			if (super.reload(element, rootElementMap) == null) {
				return null;
			}
			load((BaseRelation) element);
			reloadFromRootElementMap(rootElementMap);
			return (IIntegratable) this;
		} else {
			return null;
		}
	}
	
	private void load(BaseRelation element) {
		if(element != null) {
			this.basePrimaryElement = element.basePrimaryElement;
			this.baseSecondaryElement = element.baseSecondaryElement;
			this.primaryElement = element.primaryElement;
			this.secondaryElement = element.secondaryElement;
		}
	}

	private void reloadFromRootElementMap(Map<String, AbstractElement> rootElementMap) {
		if(CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		if(this.basePrimaryElement != null) {
			AbstractElement element1 = rootElementMap.get(this.basePrimaryElement.getId());
			this.primaryElement = (element1 instanceof AbstractRelationableInformationElement) 
					? (AbstractRelationableInformationElement) element1 : this.primaryElement;
		}
		if(this.baseSecondaryElement != null) {
			AbstractElement element2 = rootElementMap.get(this.baseSecondaryElement.getId());
			this.secondaryElement = (element2 instanceof AbstractRelationableInformationElement) 
					? (AbstractRelationableInformationElement) element2 : this.secondaryElement;
		}
	}


	@Override
	public BaseRelation cloneElement() throws LIMException {
		BaseRelation cloneElement = (BaseRelation) super.cloneElement();
		
		cloneElement.basePrimaryElement = (this.basePrimaryElement != null) 
				? this.basePrimaryElement.cloneElement() : cloneElement.basePrimaryElement;
		cloneElement.primaryElement = (this.primaryElement != null) 
				? this.primaryElement.cloneElement() : cloneElement.primaryElement;
		
		cloneElement.baseSecondaryElement = (this.baseSecondaryElement != null) 
				? this.baseSecondaryElement.cloneElement() : cloneElement.baseSecondaryElement;
		cloneElement.secondaryElement = (this.secondaryElement != null) 
				? this.secondaryElement.cloneElement() : cloneElement.secondaryElement;

		return cloneElement;
	}

}
