package com.codejstudio.lim.pojo.statement;

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
 * Truth.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = Truth.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Truth extends InformationUnit {

	/* enumeration */
	
	public enum TruthType{
		TRUE(1),
		FALSE(0),
		HALF_TRUE_HALF_FALSE(0.5),
		ABSOLUTELY_TRUE(Long.MAX_VALUE),
		ABSOLUTELY_FALSE(Long.MIN_VALUE),
		TRUE_FALSE_GAP(-2),
		NO_DETERMINED(-3),
		MEANINGLESS(-4),
		;
		
		private double truthValue;

		private TruthType(double truthValue) {
			this.truthValue = truthValue;
		}

		public double getTruthValue() {
			return truthValue;
		}
		
		public static String getDiscription(double truthValue) {
			for(TruthType type : TruthType.values()) {
				if(type.truthValue == truthValue) {
					return type.name();
				}
			}
			return null;
		}
		
	}
	

	/* constants */
	
	public static final String TYPE_NAME = "truth";


	/* variables */
	
	@XmlAttribute(required = true)
	protected double truthValue;

	
	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
    public Truth() throws LIMException {
		super();
	}
	
	public Truth(Truth truth) throws LIMException {
		super(truth);
		load(truth);
	}

    public Truth(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public Truth(boolean ifInitId, boolean ifInitType, double truthValue, String discription) throws LIMException {
		super(ifInitId, ifInitType, discription);
		this.truthValue = truthValue;
	}

	public Truth(boolean ifInitId, boolean ifInitType, double truthValue) throws LIMException {
		this(ifInitId, ifInitType, truthValue, TruthType.getDiscription(truthValue));
	}


	public Truth(double truthValue, String discription) throws LIMException {
		super(true, false, discription);
		this.truthValue = truthValue;
	}

	public Truth(double truthValue) throws LIMException {
		this(true, false, truthValue, TruthType.getDiscription(truthValue));
	}


	/* getters & setters */

	@XmlTransient
	public double getTruthValue() {
		return truthValue;
	}

	public void setTruthValue(double truthValue) {
		setTruthValue(truthValue, TruthType.getDiscription(truthValue));
	}

	public void setTruthValue(double truthValue, String discription) {
		this.truthValue = truthValue;
		this.discription = discription;
	}


	/* overridden methods */

	@Override
	public AbstractElement getXmlElement() throws LIMException {
		if(this.xmlElement == null) {
			if(this.getClass().equals(Truth.class)) {
				this.xmlElement = this;
			}else {
				this.xmlElement = new Truth(this);
			}
		}
		return this.xmlElement;
	}

	@Override
	public AbstractElement getPojoElement(Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (this.pojoElement == null) {
			if (StringUtils.isEmpty(this.getType())  
					|| !this.getClass().equals(Truth.class)) {
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
		if (element instanceof Truth) {
			if (super.reload(element, rootElementMap) == null) {
				return null;
			}
			load((Truth) element);
			return (IIntegratable) this;
		} else {
			return null;
		}
	}
	
	private void load(Truth element) {
		if(element != null) {
			this.truthValue = element.truthValue;
		}
	}


	@Override
	public Truth cloneElement() throws LIMException {
		Truth cloneElement = (Truth) super.cloneElement();
		cloneElement.truthValue = this.truthValue;
		return cloneElement;
	}
	
}
