package com.codejstudio.lim.pojo.i;

import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.AbstractElement;
import com.codejstudio.lim.pojo.BaseElement;

/**
 * IIntegratable.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public interface IIntegratable {

	/* constants */
	
	public static final String INTEGRATED_ATTACHMENT_XMLATTRIBUTE_NAME = "itx-attributes";
	
	public static final String INTEGRATED_ATTACHMENT_XMLELEMENT_NAME = "itx-elements";


	/* getters & setters */

	/**
	 * for JAXB usage of unmarshalling<p>
	 * The implemented classes should also contain:<br/>
	 * private Map<String, String> integratedAttributeMap;<br/>
	 * private void initIntegratedAttributeMap();<br/>
	 * private void destroyIntegratedAttributeMap();<br/>
	 * protected String getIntegratedAttributeDelegate(String key);<br/>
	 * protected boolean putIntegratedAttributeDelegate(String key, String value);<br/>
	 * protected String removeIntegratedAttributeDelegate(String key);
	 */
	public Map<String, String> getIntegratedAttribute();

	/**
	 * for JAXB usage of unmarshalling<p>
	 * private Map<String, String> integratedAttributeMap;<br/>
	 * private void initIntegratedAttributeMap();<br/>
	 * private void destroyIntegratedAttributeMap();<br/>
	 * protected String getIntegratedAttributeDelegate(String key);<br/>
	 * protected boolean putIntegratedAttributeDelegate(String key, String value);<br/>
	 * protected String removeIntegratedAttributeDelegate(String key);
	 */
	public void setIntegratedAttribute(Map<String, String> attributeMap);

	/**
	 * for JAXB usage of unmarshalling<p>
	 * The implemented classes should also contain:<br/>
	 * private Map<String, BaseElement> integratedElementMap;<br/>
	 * private void initIntegratedElementMap();<br/>
	 * private void destroyIntegratedElementMap();<br/>
	 * protected BaseElement getIntegratedElementDelegate(String key);<br/>
	 * protected boolean putIntegratedElementDelegate(String key, BaseElement value);<br/>
	 * protected BaseElement removeIntegratedElementDelegate(String key);
	 */
	public Map<String, BaseElement> getIntegratedElement();

	/**
	 * for JAXB usage of unmarshalling<p>
	 * The implemented classes should also contain:<br/>
	 * private Map<String, BaseElement> integratedElementMap;<br/>
	 * private void initIntegratedElementMap();<br/>
	 * private void destroyIntegratedElementMap();<br/>
	 * protected BaseElement getIntegratedElementDelegate(String key);<br/>
	 * protected boolean putIntegratedElementDelegate(String key, BaseElement value);<br/>
	 * protected BaseElement removeIntegratedElementDelegate(String key);
	 */
	public void setIntegratedElement(Map<String, BaseElement> elementMap);


	/* other methods */

	/**
	 * The implemented classes should also contain:<br/>
	 * public T(T element);<br/>
	 * And rewrite:<br/>
	 * public AbstractElement getXmlElement();<br/>
	 * public AbstractElement getPojoElement(Map<String, AbstractElement> rootElementMap);
	 */
	public IIntegratable reload(IIntegratable element, Map<String, AbstractElement> rootElementMap) throws LIMException;

}
