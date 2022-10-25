package com.codejstudio.lim.pojo;

import java.util.Collection;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.StringUtils;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.doubt.Doubt;
import com.codejstudio.lim.pojo.doubt.Explanation;
import com.codejstudio.lim.pojo.i.IIntegratable;

/**
 * InformationElement.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = InformationElement.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
    "discription",
    "baseSubInformationElementGroup",
})
@XmlSeeAlso({
    Doubt.class,
    Explanation.class,
    InformationSection.class,
    InformationUnit.class,
})
public class InformationElement extends AbstractRelationableInformationElement {
	
	/* constants */
	
	public static final String TYPE_NAME = "information-element";


	/* variables */
	
	@XmlElement
	protected String discription;

	
	/* variables: collections, maps, sub-groups */

	@XmlElement(name = "sub-information-element-group")
	private BaseElement baseSubInformationElementGroup;

	private InformationElementGroup subInformationElementGroup;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public InformationElement() throws LIMException {
		super();
	}

	public InformationElement(InformationElement element) throws LIMException {
		super(element);
		load(element);
	}

	public InformationElement(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public InformationElement(boolean ifInitId, boolean ifInitType, String discription) throws LIMException {
		super(ifInitId, ifInitType);
		this.discription = discription;
	}
	

	/* initializers */

	private void initSubInformationElementGroup() throws LIMException {
		if(this.subInformationElementGroup == null) {
			this.subInformationElementGroup = new InformationElementGroup(true);
			super.addInnerElementDelegate(this.subInformationElementGroup);
			this.baseSubInformationElementGroup = new BaseElement(subInformationElementGroup);
		}
	}
	

	/* destroyers */

	private void destroySubInformationElementGroup() throws LIMException {
		if(this.subInformationElementGroup != null && this.subInformationElementGroup.size() == 0) {
			this.baseSubInformationElementGroup = null;
			super.removeInnerElementDelegate(this.subInformationElementGroup);
			this.subInformationElementGroup = null;
		}
	}


	/* getters & setters */

	@XmlTransient
	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public InformationElementGroup getSubInformationElementGroup() {
		return subInformationElementGroup;
	}
	

	/* CRUD for collections, maps, sub-groups: sub-information-elements */

	public boolean containSubInformationElement(InformationElement element) throws LIMException{
		return (this.subInformationElementGroup != null) ? this.subInformationElementGroup.containGroupElement(element) : false;
	}

	public boolean addSubInformationElement(InformationElement... elements) throws LIMException {
		return addSubInformationElement(CollectionUtil.generateCollection(elements));
	}

	public boolean addSubInformationElement(Collection<InformationElement> elements) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(elements)) {
			return false;
		}
		
		initSubInformationElementGroup();
		boolean flag = true;
		flag &= super.addInnerElementDelegate(elements) 
				& this.subInformationElementGroup.addGroupElement(elements);
		destroySubInformationElementGroup();
		return flag;
	}
	
	public boolean removeSubInformationElement(InformationElement... elements) throws LIMException {
		return removeSubInformationElement(CollectionUtil.generateCollection(elements));
	}

	public boolean removeSubInformationElement(Collection<InformationElement> elements) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(elements) || this.subInformationElementGroup == null) {
			return false;
		}
		
		boolean flag = true;
		flag &= super.removeInnerElementDelegate(elements) 
				& this.subInformationElementGroup.removeGroupElement(elements);
		destroySubInformationElementGroup();
		return flag;
	}


	/* overridden methods */

	@Override
	public AbstractElement getXmlElement() throws LIMException {
		if(this.xmlElement == null) {
			if(this.getClass().equals(InformationElement.class)) {
				this.xmlElement = this;
			}else {
				this.xmlElement = new InformationElement(this);
			}
		}
		return this.xmlElement;
	}

	@Override
	public AbstractElement getPojoElement(Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (this.pojoElement == null) {
			if (StringUtils.isEmpty(this.getType())  
					|| !this.getClass().equals(InformationElement.class)) {
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
		if (element instanceof InformationElement) {
			if (super.reload(element, rootElementMap) == null) {
				return null;
			}
			load((InformationElement) element);
			reloadFromRootElementMap(rootElementMap);
			return (IIntegratable) this;
		} else {
			return null;
		}
	}
	
	private void load(InformationElement element) {
		if(element != null) {
			this.discription = element.discription;
			this.baseSubInformationElementGroup = element.baseSubInformationElementGroup;
			this.subInformationElementGroup = element.subInformationElementGroup;
		}
	}

	private void reloadFromRootElementMap(Map<String, AbstractElement> rootElementMap) {
		if(CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}
		
		if(this.baseSubInformationElementGroup != null && this.baseSubInformationElementGroup.getId() != null) {
			AbstractElement element = rootElementMap.get(this.baseSubInformationElementGroup.getId());
			this.subInformationElementGroup = (element instanceof InformationElementGroup) 
					? (InformationElementGroup) element : this.subInformationElementGroup;
		}
	}


	@Override
	public InformationElement cloneElement() throws LIMException {
		InformationElement cloneElement = (InformationElement) super.cloneElement();
		cloneElement.discription = this.discription;
		
		cloneElement.baseSubInformationElementGroup = (this.baseSubInformationElementGroup != null) 
				? (BaseElement) this.baseSubInformationElementGroup.cloneElement() : cloneElement.baseSubInformationElementGroup;
		cloneElement.subInformationElementGroup = (this.subInformationElementGroup != null) 
				? (InformationElementGroup) this.subInformationElementGroup.cloneElement() : cloneElement.subInformationElementGroup;

		return cloneElement;
	}

}
