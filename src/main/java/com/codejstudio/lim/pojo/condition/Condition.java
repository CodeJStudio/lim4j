package com.codejstudio.lim.pojo.condition;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.StringUtils;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.ObjectUtil;
import com.codejstudio.lim.pojo.AbstractElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.InformationUnit;
import com.codejstudio.lim.pojo.i.IIntegratable;

/**
 * Condition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = Condition.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
    "baseDiscriptionElement",
})
public class Condition extends InformationUnit {
	
	/* constants */
	
	public static final String TYPE_NAME = "condition";


	/* variables */

	@XmlAttribute(name = "implicit")
	protected Boolean implicit;

	@XmlElement(name = "discription-element")
	protected BaseElement baseDiscriptionElement;

	protected InformationElement discriptionElement;

	
	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Condition() throws LIMException {
		super();
	}

	public Condition(Condition condition) throws LIMException {
		super(condition);
		load(condition);
	}

	public Condition(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public Condition(boolean ifInitId, boolean ifInitType, String discription) throws LIMException {
		super(ifInitId, ifInitType, discription);
	}

	public Condition(boolean ifInitId, boolean ifInitType, InformationElement discriptionElement) throws LIMException {
		super(ifInitId, ifInitType);
		setDiscriptionElement(discriptionElement);
	}
	

	public Condition(String discription) throws LIMException {
		super(true, false, discription);
	}

	public Condition(String discription, Boolean implicit) throws LIMException {
		super(true, false, discription);
		setImplicit(implicit);
	}

	public Condition(InformationElement discriptionElement) throws LIMException {
		this(true, false, discriptionElement);
	}

	public Condition(InformationElement discriptionElement, Boolean implicit) throws LIMException {
		this(true, false, discriptionElement);
		setImplicit(implicit);
	}


	/* getters & setters */


	@XmlTransient
	public Boolean getImplicit() {
		return implicit;
	}

	public void setImplicit(Boolean implicit) {
		if(implicit != null && implicit) {
			this.implicit = implicit;
		}else {
			this.implicit = null;
		}
	}

	public BaseElement getBaseDiscriptionElement() {
		return baseDiscriptionElement;
	}

	@XmlTransient
	public InformationElement getDiscriptionElement() {
		return discriptionElement;
	}

	public void setDiscriptionElement(InformationElement discriptionElement) throws LIMException {
		if(ObjectUtil.checkEquals(this.discriptionElement, discriptionElement)) {
			return;
		}
		
		if(this.discriptionElement != null) {
			this.baseDiscriptionElement = null;
			super.removeInnerElementDelegate(this.discriptionElement);
			this.discriptionElement = null;
		}
		this.discriptionElement = discriptionElement;
		if(discriptionElement != null) {
			super.addInnerElementDelegate(discriptionElement);
			this.baseDiscriptionElement = new BaseElement(discriptionElement);
		}
	}


	/* overridden methods */

	@Override
	public AbstractElement getXmlElement() throws LIMException {
		if(this.xmlElement == null) {
			if(this.getClass().equals(Condition.class)) {
				this.xmlElement = this;
			}else {
				this.xmlElement = new Condition(this);
			}
		}
		return this.xmlElement;
	}

	@Override
	public AbstractElement getPojoElement(Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (this.pojoElement == null) {
			if (StringUtils.isEmpty(this.getType())  
					|| !this.getClass().equals(Condition.class)) {
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
		if (element instanceof Condition) {
			if (super.reload(element, rootElementMap) == null) {
				return null;
			}
			load((Condition) element);
			reloadFromRootElementMap(rootElementMap);
			return (IIntegratable) this;
		} else {
			return null;
		}
	}
	
	private void load(Condition element) {
		if(element != null) {
			this.implicit = element.implicit;
			this.baseDiscriptionElement = element.baseDiscriptionElement;
			this.discriptionElement = element.discriptionElement;
		}
	}

	private void reloadFromRootElementMap(Map<String, AbstractElement> rootElementMap) {
		if(CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		if(this.baseDiscriptionElement != null && this.baseDiscriptionElement.getId() != null) {
			AbstractElement element = rootElementMap.get(this.baseDiscriptionElement.getId());
			this.discriptionElement = (element instanceof InformationElement) 
					? (InformationElement) element : this.discriptionElement;
		}
	}


	@Override
	public Condition cloneElement() throws LIMException {
		Condition cloneElement = (Condition) super.cloneElement();
		cloneElement.implicit = this.implicit;
		
		cloneElement.baseDiscriptionElement = (this.baseDiscriptionElement != null) 
				? (BaseElement) this.baseDiscriptionElement.cloneElement() : cloneElement.baseDiscriptionElement;
		cloneElement.discriptionElement = (this.discriptionElement != null) 
				? (InformationElement) this.discriptionElement.cloneElement() : cloneElement.discriptionElement;
		
		return cloneElement;
	}

}
