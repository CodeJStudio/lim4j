package com.codejstudio.lim.pojo;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;

import com.codejstudio.lim.common.exception.LIMException;

/**
 * InformationSection.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = InformationSection.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
public class InformationSection extends InformationElement {
	
	/* constants */
	
	public static final String TYPE_NAME = "information-section";
	

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public InformationSection() throws LIMException {
		super();
	}

	public InformationSection(InformationSection element) throws LIMException {
		super(element);
	}

	public InformationSection(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public InformationSection(boolean ifInitId, boolean ifInitType, String discription) throws LIMException {
		super(ifInitId, ifInitType, discription);
	}
	

	public InformationSection(String discription) throws LIMException {
		super(true, false, discription);
	}


	/* overridden methods */

	@Override
	public AbstractElement getXmlElement() throws LIMException {
		if(this.xmlElement == null) {
			if(this.getClass().equals(InformationSection.class)) {
				this.xmlElement = this;
			}else {
				this.xmlElement = new InformationSection(this);
			}
		}
		return this.xmlElement;
	}

	@Override
	public AbstractElement getPojoElement(Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (this.pojoElement == null) {
			if (StringUtils.isEmpty(this.getType())  
					|| !this.getClass().equals(InformationSection.class)) {
				this.pojoElement = this;
			} else {
				this.pojoElement = super.generatePojoElementDelegate(rootElementMap);
			}
		}
		this.pojoElement.reload(this, rootElementMap);
		return this.pojoElement;
	}

}
