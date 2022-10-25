package com.codejstudio.lim.pojo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.xml.sax.InputSource;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.PropertiesLoader;
import com.codejstudio.lim.common.util.PropertiesLoader.PropertiesFile;
import com.codejstudio.lim.pojo.argument.Argument;
import com.codejstudio.lim.pojo.argument.Validity;
import com.codejstudio.lim.pojo.attribute.Attribute;
import com.codejstudio.lim.pojo.comment.Comment;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.doubt.Doubt;
import com.codejstudio.lim.pojo.doubt.Explanation;
import com.codejstudio.lim.pojo.entity.Entity;
import com.codejstudio.lim.pojo.relation.BaseRelation;
import com.codejstudio.lim.pojo.role.BaseRole;
import com.codejstudio.lim.pojo.statement.JudgedStatement;
import com.codejstudio.lim.pojo.statement.Statement;
import com.codejstudio.lim.pojo.statement.Truth;

/**
 * Root.class

 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.BaseElement
 * @see     com.codejstudio.lim.pojo.AbstractElement#getXmlElement()
 * @see     com.codejstudio.lim.pojo.AbstractElement#getPojoElement(Map)
 * @see     com.codejstudio.lim.pojo.AbstractElement#cloneElement()
 * @see     com.codejstudio.lim.pojo.AbstractElement#absoluteEquals(Object)
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = "lim-elements")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlSeeAlso({
    BaseElement.class,
})
public final class Root implements Serializable {

	/* constants */
	
	private static final long serialVersionUID = -6816612054593041686L;

    @XmlAttribute(name = "version")
	public static final String VERSION = "1.0";


	/* variables: collections, maps, sub-groups */

	/**
	 * only for JAXB usage
	 */
	private List<AbstractElement> xmlElementList;
	
	private List<AbstractElement> pojoElementList;
	
	private Map<String, AbstractElement> pojoElementMap;

	
	/* constructors */

	/**
	 * for JAXB auto unmarshalling usage
	 */
	public Root() {
		super();
	}

	public Root(AbstractElement... elements) throws LIMException {
		super();
		addElement(elements);
	}

	public Root(Collection<AbstractElement> elements) throws LIMException {
		super();
		addElement(elements);
	}

	
	/* initializers */

	private void initXmlElementList() throws LIMException {
        if (this.xmlElementList == null) {
            this.xmlElementList = CollectionUtil.getNewList();
        }
	}
	
	private void initPojoElementList() throws LIMException {
        if (this.pojoElementList == null) {
            this.pojoElementList = CollectionUtil.getNewList();
        }
	}
	
	private boolean initPojoElementMap() throws LIMException {
        if (this.pojoElementMap == null) {
            this.pojoElementMap = CollectionUtil.getNewMap();
            return true;
        }
        return false;
	}
	

	/* getters & setters */

	/**
	 * only for JAXB usage of unmarshalling
	 */
	@XmlMixed
	@XmlElementRefs(value = { 
			@XmlElementRef(name = Argument.TYPE_NAME, type = Argument.class), 
			@XmlElementRef(name = Concept.TYPE_NAME, type = Concept.class), 
			@XmlElementRef(name = JudgedStatement.TYPE_NAME, type = JudgedStatement.class), 
			@XmlElementRef(name = Statement.TYPE_NAME, type = Statement.class), 
			
			@XmlElementRef(name = Attribute.TYPE_NAME, type = Attribute.class), 
			@XmlElementRef(name = Condition.TYPE_NAME, type = Condition.class), 
			@XmlElementRef(name = Truth.TYPE_NAME, type = Truth.class), 
			@XmlElementRef(name = Validity.TYPE_NAME, type = Validity.class), 

			@XmlElementRef(name = OwnableInformationElement.TYPE_NAME, type = OwnableInformationElement.class), 
			@XmlElementRef(name = InformationElement.TYPE_NAME, type = InformationElement.class), 
			@XmlElementRef(name = InformationSection.TYPE_NAME, type = InformationSection.class), 
			@XmlElementRef(name = InformationUnit.TYPE_NAME, type = InformationUnit.class), 

			@XmlElementRef(name = Doubt.TYPE_NAME, type = Doubt.class), 
			@XmlElementRef(name = Explanation.TYPE_NAME, type = Explanation.class), 

			@XmlElementRef(name = BaseRelation.TYPE_NAME, type = BaseRelation.class), 
			@XmlElementRef(name = BaseRole.TYPE_NAME, type = BaseRole.class), 
			@XmlElementRef(name = Comment.TYPE_NAME, type = Comment.class), 
			@XmlElementRef(name = Entity.TYPE_NAME, type = Entity.class), 
			})
	public List<AbstractElement> getXmlElement() throws LIMException {
		initXmlElementList();
		return xmlElementList;
	}

	private Map<String, AbstractElement> getPojoElementMap() throws LIMException {
		generatePojoElementMap();
		return this.pojoElementMap;
	}

	private void generatePojoElementMap() throws LIMException {
		if(initPojoElementMap()) {
			for (Object obj : this.pojoElementList) {
				if(obj instanceof AbstractElement) {
					AbstractElement element = (AbstractElement) obj;
					this.pojoElementMap.put(element.id, element);
				}
			}
		}
	}

	private void regeneratePojoElementMap() throws LIMException {
		this.pojoElementMap = null;
		generatePojoElementMap();
	}


	/* CRUD for collections, maps, sub-groups: elements */
	
	public AbstractElement getElement(String elementId) throws LIMException {
		if(elementId == null 
				|| (CollectionUtil.checkNullOrEmpty(this.pojoElementList) 
						&& CollectionUtil.checkNullOrEmpty(this.pojoElementMap))) {
			return null;
		}
		return this.getPojoElementMap().get(elementId);
	}

	public boolean containElement(AbstractElement element) throws LIMException{
		return (this.pojoElementList != null) ? this.pojoElementList.contains(element) : false;
	}
	
	
	public boolean addElement(AbstractElement... elements) throws LIMException {
		return addElement(true, elements);
	}
	
	public boolean addElement(Collection<AbstractElement> elements) throws LIMException {
		return addElement(true, elements);
	}
	
	public boolean addElement(boolean subElementsFlag, AbstractElement... elements) throws LIMException {
		return addElement(subElementsFlag, CollectionUtil.generateCollection(elements));
	}
	
	public boolean addElement(boolean subElementsFlag, Collection<AbstractElement> elements) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(elements)) {
			return false;
		}

    	initPojoElementList();
		initXmlElementList();
		initPojoElementMap();
		boolean flag = true;
		for (AbstractElement element : elements) {
	    	if(subElementsFlag) {
	    		flag &= addSubElements(element);
	    	}else {
	    		flag &= this.pojoElementList.add(element) & this.xmlElementList.add(element.getXmlElement());
				this.pojoElementMap.put(element.id, element);
	    	}
		}
		return flag;
	}
	
	private boolean addSubElements(AbstractElement element) throws LIMException {
		boolean flag = true;
		if(element != null && !this.pojoElementList.contains(element)) {
	    	flag &= this.pojoElementList.add(element) & this.xmlElementList.add(element.getXmlElement());
			this.pojoElementMap.put(element.id, element);
	    	
	    	if(element.getInnerElementCollection() != null && element.getInnerElementCollection().size() > 1) {
	    		for (AbstractElement subElement : element.getInnerElementCollection()) {
	    			flag &= addSubElements(subElement);
	    		}
	    	}
		}
		return flag;
	}
	
	
	public boolean removeElement(AbstractElement... elements) throws LIMException {
		throw new LIMException(new UnsupportedOperationException());
	}


	/* redecorate methods, for unmarshalling process */

	public void redecorate() throws LIMException {
		cleanXmlElementList();
		cloneListFromXmlToPojo();
		generatePojoElementMap();
		doRedecorate();
		copyListFromPojoToXml();
	}

	private void cleanXmlElementList() throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(this.xmlElementList)) {
			return;
		}
		
		Collection<Object> removeCollection = CollectionUtil.getNewCollection();
		for (Object obj : this.xmlElementList) {
			if(!(obj instanceof AbstractElement)) {
				removeCollection.add(obj);
				continue;
			}
		}
		this.xmlElementList.removeAll(removeCollection);
	}

	private void cloneListFromXmlToPojo() throws LIMException {
		this.pojoElementList = null;
		if(CollectionUtil.checkNullOrEmpty(this.xmlElementList)) {
			return;
		}
		
    	initPojoElementList();
		for (AbstractElement element : this.xmlElementList) {
			this.pojoElementList.add(element.cloneElement());
		}
	}

	private void doRedecorate() throws LIMException {
		List<AbstractElement> tempList = CollectionUtil.getNewList();
		boolean pojoFlag = false;
		for (AbstractElement element : this.pojoElementList) {
			if(element != null) {
				AbstractElement pojoElement = element.getPojoElement(this.pojoElementMap);
				pojoFlag |= (!element.absoluteEquals(pojoElement)) ? true : false;
				tempList.add(pojoElement);
			}
		}
		this.pojoElementList = tempList;
		regeneratePojoElementMap();
		
		if(pojoFlag) {
			doRedecorate();
		}
	}

	private void copyListFromPojoToXml() throws LIMException {
		this.xmlElementList = null;
		if(CollectionUtil.checkNullOrEmpty(this.pojoElementList)) {
			return;
		}
		
    	initXmlElementList();
		for (AbstractElement element : this.pojoElementList) {
			this.xmlElementList.add(element.getXmlElement());
		}
	}
	

	/* JAXB methods, for marshalling & unmarshalling processes */

	public void marshalToXml(boolean ifSortById, OutputStream os) throws LIMException{
		if(ifSortById) {
			xmlSort();
		}
		
		try {
			JAXBContext jc = JAXBContext.newInstance(Root.class);
			Marshaller ma = jc.createMarshaller();

			String jaxbEncoding = PropertiesLoader.getProperty(PropertiesFile.COMMON, Marshaller.JAXB_ENCODING);
			if(jaxbEncoding != null) {
				ma.setProperty(Marshaller.JAXB_ENCODING, jaxbEncoding);
			}
			
			String jaxbFormattedOutput = PropertiesLoader.getProperty(PropertiesFile.COMMON, Marshaller.JAXB_FORMATTED_OUTPUT);
			if(jaxbFormattedOutput != null) {
				ma.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.valueOf(jaxbFormattedOutput));
			}
			
			String jaxbSchemaLocation = PropertiesLoader.getProperty(PropertiesFile.COMMON, Marshaller.JAXB_SCHEMA_LOCATION);
			if(jaxbSchemaLocation != null) {
				ma.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, jaxbSchemaLocation);
			}
			
			String jaxbNoNamespaceSchemaLocation = PropertiesLoader.getProperty(PropertiesFile.COMMON, Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION);
			if(jaxbNoNamespaceSchemaLocation != null) {
				ma.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, jaxbNoNamespaceSchemaLocation);
			}
			
			String jaxbFragment = PropertiesLoader.getProperty(PropertiesFile.COMMON, Marshaller.JAXB_FRAGMENT);
			if(jaxbFragment != null) {
				ma.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.valueOf(jaxbFragment));
			}

			ma.marshal(this, os);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new LIMException(e);
		}
	}
	
	/**
	 * Refer to BaseElement.compareTo(BaseElement element);
	 */
	private void xmlSort() {
		Collections.sort(this.xmlElementList);		
	}
	
	
	public static Root unmarshalFromXml(File file) throws LIMException{
		try {
			return unmarshalFromXml(new BufferedInputStream(new FileInputStream(file)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new LIMException(e);
		}
	}
	
	public static Root unmarshalFromXml(InputStream is) throws LIMException{
		return unmarshalFromXml(new InputSource(is));
	}
	
	public static Root unmarshalFromXml(Reader reader) throws LIMException{
		return unmarshalFromXml(new InputSource(reader));
	}
	
	public static Root unmarshalFromXml(URL url) throws LIMException{
		return unmarshalFromXml(new InputSource(url.toExternalForm()));
	}

	public static Root unmarshalFromXml(InputSource source) throws LIMException{
		try {
			JAXBContext jc = JAXBContext.newInstance(Root.class);
			Unmarshaller um = jc.createUnmarshaller();
			return (Root) um.unmarshal(source);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new LIMException(e);
		}
	}
	
}
