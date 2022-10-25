package com.codejstudio.lim.pojo.statement;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.StringUtils;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.ObjectUtil;
import com.codejstudio.lim.pojo.AbstractElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.attribute.Attribute;
import com.codejstudio.lim.pojo.i.IIntegratable;

/**
 * JudgedStatement.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = JudgedStatement.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = { 
	"baseTruth", 
})
public class JudgedStatement extends Statement {

	/* enumeration */
	
	public enum StatementAttributeType {
		TRUTH,
	}

	
	/* constants */
	
	public static final String TYPE_NAME = "judged-statement";


	/* variables */

	@XmlElement(name = "truth")
	protected BaseElement baseTruth;

	
	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public JudgedStatement() throws LIMException {
		super();
	}
	
	public JudgedStatement(JudgedStatement statement) throws LIMException {
		super(statement);
		load(statement);
	}

	public JudgedStatement(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public JudgedStatement(boolean ifInitId, boolean ifInitType, String discription) throws LIMException {
		super(ifInitId, ifInitType, discription);
	}
	

	public JudgedStatement(String discription) throws LIMException {
		super(true, false, discription);
	}

	public JudgedStatement(Statement statement) throws LIMException {
		super(statement);
	}
	

	/* getters & setters */

	public BaseElement getBaseTruth() {
		return baseTruth;
	}

	@XmlTransient
	public Truth getTruth() {
		Attribute attribute = super.getAttribute(StatementAttributeType.TRUTH.name());
		return (attribute != null) ? (Truth) attribute.getValue() : null;
	}

	public void setTruth(Truth truth) throws LIMException {
		Truth t = getTruth();
		if(ObjectUtil.checkEquals(t, truth)) {
			return;
		}

		if(t != null) {
			this.baseTruth = null;
			super.removeInnerElementDelegate(t);
			super.removeAttribute(StatementAttributeType.TRUTH.name());
		}
		if(truth != null) {
			super.addAttribute(new Attribute(StatementAttributeType.TRUTH.name(), truth));
			super.addInnerElementDelegate(truth);
			this.baseTruth = new BaseElement(truth);
		}
	}
	

	/* overridden methods */

	@Override
	public AbstractElement getXmlElement() throws LIMException {
		if(this.xmlElement == null) {
			if(this.getClass().equals(JudgedStatement.class)) {
				this.xmlElement = this;
			}else {
				this.xmlElement = new JudgedStatement(this);
			}
		}
		return this.xmlElement;
	}

	@Override
	public AbstractElement getPojoElement(Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (this.pojoElement == null) {
			if (StringUtils.isEmpty(this.getType())  
					|| !this.getClass().equals(JudgedStatement.class)) {
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
		if (element instanceof JudgedStatement) {
			if (super.reload(element, rootElementMap) == null) {
				return null;
			}
			load((JudgedStatement) element);
			return (IIntegratable) this;
		} else {
			return null;
		}
	}
	
	private void load(JudgedStatement element) {
		if(element != null) {
			this.baseTruth = element.baseTruth;
		}
	}


	@Override
	public JudgedStatement cloneElement() throws LIMException {
		JudgedStatement cloneElement = (JudgedStatement) super.cloneElement();
		
		cloneElement.baseTruth = (this.baseTruth != null) 
				? (BaseElement) this.baseTruth.cloneElement() : cloneElement.baseTruth;
		
		return cloneElement;
	}

}
