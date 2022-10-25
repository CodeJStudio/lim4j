package com.codejstudio.lim.pojo.comment;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
import com.codejstudio.lim.pojo.OwnableInformationElement;
import com.codejstudio.lim.pojo.i.IIntegratable;

/**
 * Comment.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = Comment.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
    "baseElement",
})
public class Comment extends AbstractElement {
	
	/* constants */
	
	public static final String TYPE_NAME = "comment";


	/* variables */
	
	@XmlElement(name = "element", required = true)
	protected BaseElement baseElement;
	
	protected OwnableInformationElement element;

	
	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Comment() throws LIMException {
		super();
	}
	
	public Comment(Comment comment) throws LIMException {
		super(comment);
		load(comment);
	}

	public Comment(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public Comment(boolean ifInitId, boolean ifInitType, OwnableInformationElement element) throws LIMException {
		super(ifInitId, ifInitType);
		setElement(element);
	}
	

	public Comment(OwnableInformationElement element) throws LIMException {
		this(true, false, element);
	}
	

	/* getters & setters */

	public BaseElement getBaseElement() {
		return baseElement;
	}

	@XmlTransient
	public OwnableInformationElement getElement() {
		return element;
	}

	/**
	 * required = true
	 */
	public void setElement(OwnableInformationElement element) throws LIMException {
		if(element == null) {
			throw new LIMException(new NullPointerException());
		}
		if(ObjectUtil.checkEquals(this.element, element)) {
			return;
		}
		
		if(this.element != null) {
			this.baseElement = null;
			super.removeInnerElementDelegate(this.element);
			this.element = null;
		}
		this.element = element;
		super.addInnerElementDelegate(element);
		this.baseElement = new BaseElement(element);
	}


	/* overridden methods */

	@Override
	public AbstractElement getXmlElement() throws LIMException {
		if(this.xmlElement == null) {
			if(this.getClass().equals(Comment.class)) {
				this.xmlElement = this;
			}else {
				this.xmlElement = new Comment(this);
			}
		}
		return this.xmlElement;
	}

	@Override
	public AbstractElement getPojoElement(Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (this.pojoElement == null) {
			if (StringUtils.isEmpty(this.getType())  
					|| !this.getClass().equals(Comment.class)) {
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
		if (element instanceof Comment) {
			if (super.reload(element, rootElementMap) == null) {
				return null;
			}
			load((Comment) element);
			reloadFromRootElementMap(rootElementMap);
			return (IIntegratable) this;
		} else {
			return null;
		}
	}
	
	private void load(Comment element) {
		if(element != null) {
			this.baseElement = element.baseElement;
			this.element = element.element;
		}
	}

	private void reloadFromRootElementMap(Map<String, AbstractElement> rootElementMap) {
		if(CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		if(this.baseElement != null && this.baseElement.getId() != null) {
			AbstractElement element = rootElementMap.get(this.baseElement.getId());
			this.element = (element instanceof OwnableInformationElement) 
					? (OwnableInformationElement) element : this.element;
		}
	}


	@Override
	public Comment cloneElement() throws LIMException {
		Comment cloneElement = (Comment) super.cloneElement();
		
		cloneElement.baseElement = (this.baseElement != null) 
				? (BaseElement) this.baseElement.cloneElement() : cloneElement.baseElement;
		cloneElement.element = (this.element != null) 
				? (OwnableInformationElement) this.element.cloneElement() : cloneElement.element;

		return cloneElement;
	}

}
