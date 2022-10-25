package com.codejstudio.lim.pojo.attribute;

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
import com.codejstudio.lim.pojo.InformationUnit;
import com.codejstudio.lim.pojo.i.IIntegratable;

/**
 * Attribute.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = Attribute.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
	"baseValue",
})
public class Attribute extends InformationUnit {

	/* constants */

	public static final String TYPE_NAME = "attribute";

	
	/* variables */
	
    @XmlAttribute(required = true)
    protected String key;

    @XmlElement(name = "value", required = true)
    protected BaseElement baseValue;

    protected AbstractElement value;

	
	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
    public Attribute() throws LIMException {
		super();
	}
	
	public Attribute(Attribute attribute) throws LIMException {
		super(attribute);
		load(attribute);
	}

    public Attribute(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public Attribute(boolean ifInitId, boolean ifInitType, String discription) throws LIMException {
		super(ifInitId, ifInitType, discription);
	}

	public Attribute(boolean ifInitId, boolean ifInitType, String key, AbstractElement value) throws LIMException {
		super(ifInitId, ifInitType);
		init(key, value);
	}

	public Attribute(String key, AbstractElement value) throws LIMException {
		this(true, false, key, value);
	}

	
	/* initializers */
	
	protected void init(String key, AbstractElement value) throws LIMException {
		setKey(key);
		setValue(value);
	}


	/* getters & setters */

	@XmlTransient
	public String getKey() {
		return key;
	}

	/**
	 * required = true
	 */
	public void setKey(String key) throws LIMException {
		if(key == null) {
			throw new LIMException(new NullPointerException());
		}
		this.key = key;
	}

	public BaseElement getBaseValue() {
		return baseValue;
	}

	@XmlTransient
	public AbstractElement getValue() {
		return value;
	}

	/**
	 * required = true
	 */
	public void setValue(AbstractElement value) throws LIMException {
		if(value == null) {
			throw new LIMException(new NullPointerException());
		}
		if(ObjectUtil.checkEquals(this.value, value)) {
			return;
		}
		
		if(this.value != null) {
			this.baseValue = null;
			super.removeInnerElementDelegate(this.value);
			this.value = null;
		}
		this.value = value;
		super.addInnerElementDelegate(value);
		this.baseValue = new BaseElement(value);
	}


	/* overridden methods */

	@Override
	public AbstractElement getXmlElement() throws LIMException {
		if(this.xmlElement == null) {
			if(this.getClass().equals(Attribute.class)) {
				this.xmlElement = this;
			}else {
				this.xmlElement = new Attribute(this);
			}
		}
		return this.xmlElement;
	}

	@Override
	public AbstractElement getPojoElement(Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (this.pojoElement == null) {
			if (StringUtils.isEmpty(this.getType())  
					|| !this.getClass().equals(Attribute.class)) {
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
		if (element instanceof Attribute) {
			if (super.reload(element, rootElementMap) == null) {
				return null;
			}
			load((Attribute) element);
			reloadFromRootElementMap(rootElementMap);
			return (IIntegratable) this;
		} else {
			return null;
		}
	}
	
	private void load(Attribute element) {
		if(element != null) {
			this.key = element.key;
			this.baseValue = element.baseValue;
			this.value = element.value;
		}
	}

	private void reloadFromRootElementMap(Map<String, AbstractElement> rootElementMap) {
		if(CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		if(this.baseValue != null && this.baseValue.getId() != null) {
			AbstractElement element = rootElementMap.get(this.baseValue.getId());
			this.value = (element != null) ? element : this.value;
		}
	}


	@Override
	public Attribute cloneElement() throws LIMException {
		Attribute cloneElement = (Attribute) super.cloneElement();
		cloneElement.key = this.key;
		
		cloneElement.baseValue = (this.baseValue != null) 
				? (BaseElement) this.baseValue.cloneElement() : cloneElement.baseValue;
		cloneElement.value = (this.value != null) 
				? (AbstractElement) this.value.cloneElement() : cloneElement.value;

		return cloneElement;
	}

}
