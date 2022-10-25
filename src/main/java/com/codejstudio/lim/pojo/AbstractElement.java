package com.codejstudio.lim.pojo;

import java.util.Collection;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.adapter.XmlAttributeMapAdapter;
import com.codejstudio.lim.pojo.adapter.XmlElementMapAdapter;
import com.codejstudio.lim.pojo.comment.Comment;
import com.codejstudio.lim.pojo.entity.Entity;
import com.codejstudio.lim.pojo.i.IIntegratable;
import com.codejstudio.lim.pojo.relation.BaseRelation;
import com.codejstudio.lim.pojo.role.BaseRole;

/**
 * AbstractElement.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = AbstractElement.TYPE_NAME, propOrder = {
	"integratedAttribute",
	"integratedElement",
})
@XmlSeeAlso({
    BaseRole.class,
    Comment.class,
    Entity.class,
    OwnableInformationElement.class,
})
public abstract class AbstractElement extends BaseElement implements IIntegratable {

	/* constants */
	
	public static final String TYPE_NAME = "abstract-element";


	/* variables */
	
	protected AbstractElement xmlElement;

	protected AbstractElement pojoElement;


	/* variables: collections, maps, sub-groups */

	/**
	 * the collection to contain all sub-elements and this element itself<br/>
	 * only for JAXB usage in <code>Root</code> class
	 */
	private Collection<AbstractElement> innerElementCollection;
	
	private Map<String, String> integratedAttributeMap;
	
	private Map<String, BaseElement> integratedElementMap;

	
	/* constructors */

	/**
	 * for JAXB usage of unmarshalling
	 */
	public AbstractElement() throws LIMException {
		super();
		addInnerElementDelegate(this);
	}

	public AbstractElement(AbstractElement element) throws LIMException {
		super(element, false);
		load(element);
	}

	public AbstractElement(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
		addInnerElementDelegate(this);
	}
	

	/* initializers */

	private void initInnerElementCollection() throws LIMException {
		if(this.innerElementCollection == null) {
			this.innerElementCollection = CollectionUtil.getNewCollection();
		}
	}

	private void initIntegratedAttributeMap() throws LIMException {
		if (this.integratedAttributeMap == null) {
			this.integratedAttributeMap = CollectionUtil.getNewMap();
		}
	}

	private void initIntegratedElementMap() throws LIMException {
		if (this.integratedElementMap == null) {
			this.integratedElementMap = CollectionUtil.getNewMap();
		}
	}

	
	/* destroyers */
	
	private void destroyInnerElementCollection() throws LIMException {
		if(this.innerElementCollection != null && this.innerElementCollection.size() == 0) {
			this.innerElementCollection = null;
		}
	}

	private void destroyIntegratedAttributeMap() throws LIMException {
		if(this.integratedAttributeMap != null && this.integratedAttributeMap.size() == 0) {
			this.integratedAttributeMap = null;
		}
	}

	private void destroyIntegratedElementMap() throws LIMException {
		if(this.integratedElementMap != null && this.integratedElementMap.size() == 0) {
			this.integratedElementMap = null;
		}
	}


	/* getters & setters */

	/**
	 * only for JAXB usage in <code>Root</code> class
	 */
	public Collection<AbstractElement> getInnerElementCollection() {
		return innerElementCollection;
	}

	/**
	 * for JAXB usage of unmarshalling
	 */
	@Override
	@XmlElement(name = IIntegratable.INTEGRATED_ATTACHMENT_XMLATTRIBUTE_NAME)
	@XmlJavaTypeAdapter(value = XmlAttributeMapAdapter.class)
	public Map<String, String> getIntegratedAttribute() {
		return integratedAttributeMap;
	}

	/**
	 * for JAXB usage of unmarshalling
	 */
	@Override
	public void setIntegratedAttribute(Map<String, String> attributeMap) {
		this.integratedAttributeMap = attributeMap;
	}


	/**
	 * for JAXB usage of unmarshalling
	 */
	@Override
	@XmlElement(name = IIntegratable.INTEGRATED_ATTACHMENT_XMLELEMENT_NAME)
	@XmlJavaTypeAdapter(value = XmlElementMapAdapter.class)
	public Map<String, BaseElement> getIntegratedElement() {
		return integratedElementMap;
	}

	/**
	 * for JAXB usage of unmarshalling
	 */
	@Override
	public void setIntegratedElement(Map<String, BaseElement> elementMap) {
		this.integratedElementMap = elementMap;
	}


	/* CRUD for collections, maps, sub-groups: inner elements */

	protected boolean containInnerElementDelegate(AbstractElement element) throws LIMException{
		return (this.innerElementCollection != null) ? this.innerElementCollection.contains(element) : false;
	}

	protected boolean addInnerElementDelegate(AbstractElement... elements) throws LIMException {
		return addInnerElementDelegate(CollectionUtil.generateCollection(elements));
	}

	protected <T extends AbstractElement> boolean addInnerElementDelegate(Collection<T> elements) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(elements)) {
			return false;
		}
		
		initInnerElementCollection();
		boolean flag = true;
		for (T element : elements) {
			if(element != null && !this.innerElementCollection.contains(element)) {
				flag &= this.innerElementCollection.add(element);	
			}
		}
		return flag;
	}

	protected boolean removeInnerElementDelegate(AbstractElement... elements) throws LIMException {
		return removeInnerElementDelegate(CollectionUtil.generateCollection(elements));
	}

	protected <T extends AbstractElement> boolean removeInnerElementDelegate(Collection<T> elements) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(elements) 
				|| CollectionUtil.checkNullOrEmpty(this.innerElementCollection)) {
			return false;
		}
		
		boolean flag = this.innerElementCollection.removeAll(elements);
		destroyInnerElementCollection();
		return flag;
	}


	/* CRUD for collections, maps, sub-groups: integrated XmlAttribute attachments */

	protected String getIntegratedAttributeDelegate(String key) throws LIMException {
		if(key == null || CollectionUtil.checkNullOrEmpty(this.integratedAttributeMap)) {
			return null;
		}
		return this.integratedAttributeMap.get(key);
	}

	protected boolean putIntegratedAttributeDelegate(String key, String value) throws LIMException {
		if(key == null || value == null) {
			return false;
		}
		initIntegratedAttributeMap();
		this.integratedAttributeMap.put(key, value);
		return true;
	}

	protected String removeIntegratedAttributeDelegate(String key) throws LIMException {
		if(key == null || CollectionUtil.checkNullOrEmpty(this.integratedAttributeMap)) {
			return null;
		}
		String value = this.integratedAttributeMap.remove(key);
		destroyIntegratedAttributeMap();
		return value;
	}


	/* CRUD for collections, maps, sub-groups: integrated XmlElement attachments */

	protected BaseElement getIntegratedElementDelegate(String key) throws LIMException {
		if(key == null || CollectionUtil.checkNullOrEmpty(this.integratedElementMap)) {
			return null;
		}
		return this.integratedElementMap.get(key);
	}

	protected boolean putIntegratedElementDelegate(String key, BaseElement value) throws LIMException {
		if(key == null || value == null) {
			return false;
		}
		initIntegratedElementMap();
		this.integratedElementMap.put(key, value);
		return true;
	}

	protected BaseElement removeIntegratedElementDelegate(String key) throws LIMException {
		if(key == null || CollectionUtil.checkNullOrEmpty(this.integratedElementMap)) {
			return null;
		}
		BaseElement value = this.integratedElementMap.remove(key);
		destroyIntegratedElementMap();
		return value;
	}


	/* delegate methods */

	protected AbstractElement generatePojoElementDelegate(Map<String, AbstractElement> rootElementMap) throws LIMException {
		try {
			String className = generateClassNameFromType(this.getType());
			String packageName = this.getClass().getPackage().getName();
			AbstractElement pojoElement = (AbstractElement) Class.forName(packageName + "." + className).newInstance();
			return pojoElement;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new LIMException(e);
		}
	}


	protected <T extends BaseRelation> boolean removeRelationInInnerElementDelegate(AbstractElement primaryElement, AbstractElement secondaryElement, Class<T> clazz) throws LIMException {
		if(primaryElement == null || secondaryElement == null || clazz == null 
				|| CollectionUtil.checkNullOrEmpty(this.innerElementCollection)) {
			return false;
		}

		Collection<AbstractElement> c = CollectionUtil.getNewCollection();
		for (AbstractElement element : this.innerElementCollection) {
			if(element.getClass().equals(clazz)) {
				BaseRelation r  = (BaseRelation) element;
				if(primaryElement.equals(r.getPrimaryElement()) && secondaryElement.equals(r.getSecondaryElement())) {
					c.add(r);
				}
			}
		}
		
		boolean flag = this.innerElementCollection.removeAll(c);
		destroyInnerElementCollection();
		return flag;
	}


	public AbstractElement getXmlElement() throws LIMException {
		if(this.xmlElement == null) {
			this.xmlElement = this;
		}
		return this.xmlElement;
	}

	public AbstractElement getPojoElement(Map<String, AbstractElement> rootElementMap) throws LIMException {
		if(this.pojoElement == null) {
			this.pojoElement = this;
		}
		return this.pojoElement;
	}


	/* overridden methods */

	@Override
	public IIntegratable reload(IIntegratable element, Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (this.absoluteEquals(element)) {
			return element;
		} else if (element instanceof AbstractElement) {
			if(super.reload(element) == null) {
				return null;
			}
			load((AbstractElement) element);
			return (IIntegratable) this;
		} else {
			return null;
		}
	}
	
	private void load(AbstractElement element) {
		if(element != null) {
			this.innerElementCollection = element.innerElementCollection;
			this.integratedAttributeMap = element.integratedAttributeMap;
			this.integratedElementMap = element.integratedElementMap;
		}
	}


	@Override
	public AbstractElement cloneElement() throws LIMException {
		AbstractElement cloneElement = (AbstractElement) super.cloneElement();
		
		if(!CollectionUtil.checkNullOrEmpty(this.innerElementCollection)) {
			cloneElement.initInnerElementCollection();
			for (AbstractElement element : this.innerElementCollection) {
				if(element != null && !this.absoluteEquals(element)) {
					cloneElement.innerElementCollection.add(element.cloneElement());
				}
			}
		}
		if(!CollectionUtil.checkNullOrEmpty(this.integratedAttributeMap)) {
			cloneElement.initIntegratedAttributeMap();
			for (String key : this.integratedAttributeMap.keySet()) {
				String value = this.integratedAttributeMap.get(key);
				cloneElement.integratedAttributeMap.put(key, value);
			}
		}
		if(!CollectionUtil.checkNullOrEmpty(this.integratedElementMap)) {
			cloneElement.initIntegratedElementMap();
			for (String key : this.integratedElementMap.keySet()) {
				BaseElement value = this.integratedElementMap.get(key);
				if(value != null) {
					cloneElement.integratedElementMap.put(key, value.cloneElement());
				}
			}
		}
		
		return cloneElement;
	}

}
