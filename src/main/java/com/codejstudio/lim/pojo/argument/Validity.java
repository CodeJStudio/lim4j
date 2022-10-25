package com.codejstudio.lim.pojo.argument;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.StringUtils;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.AbstractElement;
import com.codejstudio.lim.pojo.InformationUnit;
import com.codejstudio.lim.pojo.i.IIntegratable;

/**
 * Validity.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = Validity.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Validity extends InformationUnit {

	/* enumeration */
	
	public enum ValidityType{
		VALID(1),
		INVALID(0),
		HALF_VALID(0.5),
		ABSOLUTELY_VALID(Long.MAX_VALUE),
		ABSOLUTELY_INVALID(Long.MIN_VALUE),
		;
		
		private double validityValue;

		ValidityType(double validityValue) {
			this.validityValue = validityValue;
		}

		public double getValidityValue() {
			return validityValue;
		}
		
		public static String getDiscription(double validityValue) {
			for(ValidityType type : ValidityType.values()) {
				if(type.validityValue == validityValue) {
					return type.name();
				}
			}
			return null;
		}
		
	}


	/* constants */
	
	public static final String TYPE_NAME = "validity";


	/* variables */
	
	@XmlAttribute(required = true)
	private double validityValue;

	
	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Validity() throws LIMException {
		super();
	}
	
	public Validity(Validity validity) throws LIMException {
		super(validity);
		load(validity);
	}

	public Validity(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public Validity(boolean ifInitId, boolean ifInitType, double validityValue, String discription) throws LIMException {
		super(ifInitId, ifInitType, discription);
		this.validityValue = validityValue;
	}

	public Validity(boolean ifInitId, boolean ifInitType, double validityValue) throws LIMException {
		this(ifInitId, ifInitType, validityValue, ValidityType.getDiscription(validityValue));
	}


	public Validity(double validityValue, String discription) throws LIMException {
		super(true, false, discription);
		this.validityValue = validityValue;
	}

	public Validity(double validityValue) throws LIMException {
		this(true, false, validityValue, ValidityType.getDiscription(validityValue));
	}


	/* getters & setters */

	@XmlTransient
	public double getValidityValue() {
		return validityValue;
	}

	public void setValidityValue(double validityValue) {
		setValidityValue(validityValue, ValidityType.getDiscription(validityValue));
	}

	public void setValidityValue(double validityValue, String discription) {
		this.validityValue = validityValue;
		this.discription = discription;
	}


	/* overridden methods */

	@Override
	public AbstractElement getXmlElement() throws LIMException {
		if(this.xmlElement == null) {
			if(this.getClass().equals(Validity.class)) {
				this.xmlElement = this;
			}else {
				this.xmlElement = new Validity(this);
			}
		}
		return this.xmlElement;
	}

	@Override
	public AbstractElement getPojoElement(Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (this.pojoElement == null) {
			if (StringUtils.isEmpty(this.getType())  
					|| !this.getClass().equals(Validity.class)) {
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
		if (element instanceof Validity) {
			if (super.reload(element, rootElementMap) == null) {
				return null;
			}
			load((Validity) element);
			return (IIntegratable) this;
		} else {
			return null;
		}
	}
	
	private void load(Validity element) {
		if(element != null) {
			this.validityValue = element.validityValue;
		}
	}


	@Override
	public Validity cloneElement() throws LIMException {
		Validity cloneElement = (Validity) super.cloneElement();
		cloneElement.validityValue = this.validityValue;
		return cloneElement;
	}
	
}
