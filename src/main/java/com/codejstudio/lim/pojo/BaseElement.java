package com.codejstudio.lim.pojo;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.StringUtils;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CaseFormatUtil;
import com.codejstudio.lim.common.util.CaseFormatUtil.WordSeparator;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.IDUtil;
import com.codejstudio.lim.pojo.i.IIntegratable;

/**
 * BaseElement.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = BaseElement.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlSeeAlso({
    AbstractElement.class,
})
public class BaseElement implements Cloneable, Comparable<BaseElement> {

	/* constants */
	
	public static final String TYPE_NAME = "element";


	/* variables */
	
    @XmlAttribute
	protected String id;
	
    @XmlAttribute
	protected String type;

	
	/* constructors */

	/**
	 * only for JAXB usage of unmarshalling
	 */
	public BaseElement() {
		super();
	}

	public BaseElement(BaseElement element, boolean ifVerifyType) throws LIMException {
		super();
		load(element);
		if(ifVerifyType && this.type == null) {
			initType(element);
		}
	}

	public BaseElement(BaseElement element) throws LIMException {
		this(element, true);
	}

	public BaseElement(String id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public BaseElement(boolean ifInitId, boolean ifInitType) throws LIMException {
		super();
		if(ifInitId) {
			initId();
		}
		if(ifInitType) {
			initType(this);
		}
	}

	
	/* initializers */
	
	private void initId() throws LIMException {
		this.id = IDUtil.generateID();
	}
	
	private void initType(BaseElement element) throws LIMException {
		if(element != null) {
			this.type = generateTypeFromClass(element.getClass());
		}
	}


	/* getters & setters */

	@XmlTransient
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	@XmlTransient
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	protected IIntegratable reload(IIntegratable element) {
		if(this instanceof IIntegratable && element != null) {
			load((BaseElement) element);
			return (IIntegratable) this;
		}else {
			return null;
		}
	}
	
	private void load(BaseElement element) {
		if(element != null) {
			this.id = element.id;
			this.type = element.type;
		}
	}

	public BaseElement cloneElement() throws LIMException {
		try {
			BaseElement cloneElement = (BaseElement) Class.forName(this.getClass().getCanonicalName()).newInstance();
			cloneElement.id = this.id;
			cloneElement.type = this.type;
			return cloneElement;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new LIMException(e);
		}
	}


	/* overridden methods */

	/**
	 * The method "<code>equals</code>" in class <code>Object</code> is rewritten 
	 * to fit the LIM elements comparison requirement.<br/>
	 * To use Object.equals(Object obj), please invoke "absoluteEquals(Object obj)".
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof BaseElement)) {
			return false;
		}
		BaseElement element = (BaseElement) obj;
		if(this.id == element.id && this.type == element.type) {//this.getClass().equals(element.getClass())
			return true;
		}
		return super.equals(obj);
	}
	
	public boolean absoluteEquals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int compareTo(BaseElement element) {
		if(StringUtils.isNumeric(this.id) && StringUtils.isNumeric(element.id)) {
			return Long.valueOf(this.id).compareTo(Long.valueOf(element.id));
		}else {
			return this.id.compareTo(element.id);	
		}
	}
	

	/* static methods */

	protected static final String generateTypeFromClass(Class<? extends BaseElement> clazz) {
		return (clazz != null) ? CaseFormatUtil.camelToSeparated(clazz.getSimpleName(), WordSeparator.HYPHEN, false) : null;
	}
	
	protected static final String generateClassNameFromType(String type) {
		return (type != null) ? CaseFormatUtil.separatedToCamel(type, WordSeparator.HYPHEN, true) : null;
	}

	
	@SuppressWarnings("unchecked")
	protected static final <T extends BaseElement> boolean removeBaseElements(Collection<BaseElement> collection, T... elements) throws LIMException {
		return removeBaseElements(collection, CollectionUtil.generateCollection(elements));
	}
	
	protected static final <T extends BaseElement> boolean removeBaseElements(Collection<BaseElement> collection, Collection<T> elements) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(collection) 
				|| CollectionUtil.checkNullOrEmpty(elements)) {
			return false;
		}
		
		Collection<BaseElement> c = CollectionUtil.getNewCollection();
		for (BaseElement element : elements) {
			for (BaseElement be : collection) {
				if(be.id.equals(element.id)) {
					c.add(be);
					break;
				}
			}
		}
		return collection.removeAll(c);
	}

}
