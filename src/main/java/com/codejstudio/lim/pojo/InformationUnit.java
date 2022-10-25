package com.codejstudio.lim.pojo;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.apache.commons.lang3.StringUtils;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.argument.Argument;
import com.codejstudio.lim.pojo.argument.Validity;
import com.codejstudio.lim.pojo.attribute.Attribute;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.statement.Statement;
import com.codejstudio.lim.pojo.statement.Truth;

/**
 * InformationUnit.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = InformationUnit.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlSeeAlso({
    Argument.class,
    Attribute.class,
    Concept.class,
    Condition.class,
    Statement.class,
    Truth.class,
    Validity.class,
})
public class InformationUnit extends InformationSection {
	
	/* constants */
	
	public static final String TYPE_NAME = "information-unit";
	

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public InformationUnit() throws LIMException {
		super();
	}

	public InformationUnit(InformationUnit element) throws LIMException {
		super(element);
	}

	public InformationUnit(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public InformationUnit(boolean ifInitId, boolean ifInitType, String discription) throws LIMException {
		super(ifInitId, ifInitType, discription);
	}


	/* overridden methods */

	@Override
	public AbstractElement getXmlElement() throws LIMException {
		if(this.xmlElement == null) {
			if(this.getClass().equals(InformationUnit.class)) {
				this.xmlElement = this;
			}else {
				this.xmlElement = new InformationUnit(this);
			}
		}
		return this.xmlElement;
	}

	@Override
	public AbstractElement getPojoElement(Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (this.pojoElement == null) {
			if (StringUtils.isEmpty(this.getType())  
					|| !this.getClass().equals(InformationUnit.class)) {
				this.pojoElement = this;
			} else {
				this.pojoElement = super.generatePojoElementDelegate(rootElementMap);
			}
		}
		this.pojoElement.reload(this, rootElementMap);
		return this.pojoElement;
	}

}
