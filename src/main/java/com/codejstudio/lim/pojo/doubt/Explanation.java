package com.codejstudio.lim.pojo.doubt;

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
import com.codejstudio.lim.pojo.InformationElement;
import com.codejstudio.lim.pojo.i.IIntegratable;

/**
 * Explanation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = Explanation.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
    "baseTargetDoubt",
    "baseResponse",
})
public class Explanation extends InformationElement {
	
	/* constants */
	
	public static final String TYPE_NAME = "explanation";


	/* variables */
	
	@XmlElement(name = "target-doubt")
	protected BaseElement baseTargetDoubt;
	
	protected Doubt targetDoubt;

	@XmlElement(name = "response")
    protected BaseElement baseResponse;

	protected AbstractElement response;

	
	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Explanation() throws LIMException {
		super();
	}
	
	public Explanation(Explanation explanation) throws LIMException {
		super(explanation);
		load(explanation);
	}

	public Explanation(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public Explanation(boolean ifInitId, boolean ifInitType, String discription) throws LIMException {
		super(ifInitId, ifInitType, discription);
	}

	public Explanation(boolean ifInitId, boolean ifInitType, Doubt targetDoubt) throws LIMException {
		super(ifInitId, ifInitType);
		setTargetDoubt(targetDoubt);
	}

	public Explanation(boolean ifInitId, boolean ifInitType, Doubt targetDoubt, AbstractElement response) throws LIMException {
		super(ifInitId, ifInitType);
		setTargetDoubt(targetDoubt);
		setResponse(response);
	}


	public Explanation(Doubt targetDoubt) throws LIMException {
		this(true, false, targetDoubt);
	}

	public Explanation(Doubt targetDoubt, AbstractElement response) throws LIMException {
		this(true, false, targetDoubt, response);
	}


	/* getters & setters */

	public BaseElement getBaseTargetDoubt() {
		return baseTargetDoubt;
	}

	@XmlTransient
	public Doubt getTargetDoubt() {
		return targetDoubt;
	}

	public void setTargetDoubt(Doubt targetDoubt) throws LIMException {
		if(ObjectUtil.checkEquals(this.targetDoubt, targetDoubt)) {
			return;
		}
		
		if(this.targetDoubt != null) {
			this.baseTargetDoubt = null;
			if(this.targetDoubt.containExplanation(this)) {
				this.targetDoubt.removeExplanation(this);
			}
			super.removeInnerElementDelegate(this.targetDoubt);
			this.targetDoubt = null;
		}
		if(targetDoubt != null) {
			this.targetDoubt = targetDoubt;
			super.addInnerElementDelegate(targetDoubt);
			if(!this.targetDoubt.containExplanation(this)) {
				this.targetDoubt.addExplanation(this);
			}
			this.baseTargetDoubt = new BaseElement(targetDoubt);
		}
	}

	public BaseElement getBaseResponse() {
		return baseResponse;
	}

	@XmlTransient
	public AbstractElement getResponse() {
		return response;
	}

	public void setResponse(AbstractElement response) throws LIMException {
		if(ObjectUtil.checkEquals(this.response, response)) {
			return;
		}
		
		if(this.response != null) {
			this.baseResponse = null;
			super.removeInnerElementDelegate(this.response);
			this.response = null;
		}
		if(response != null) {
			this.response = response;
			super.addInnerElementDelegate(response);
			this.baseResponse = new BaseElement(response);
		}
	}


	/* overridden methods */

	@Override
	public AbstractElement getXmlElement() throws LIMException {
		if(this.xmlElement == null) {
			if(this.getClass().equals(Explanation.class)) {
				this.xmlElement = this;
			}else {
				this.xmlElement = new Explanation(this);
			}
		}
		return this.xmlElement;
	}

	@Override
	public AbstractElement getPojoElement(Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (this.pojoElement == null) {
			if (StringUtils.isEmpty(this.getType())  
					|| !this.getClass().equals(Explanation.class)) {
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
		if (element instanceof Explanation) {
			if (super.reload(element, rootElementMap) == null) {
				return null;
			}
			load((Explanation) element);
			reloadFromRootElementMap(rootElementMap);
			return (IIntegratable) this;
		} else {
			return null;
		}
	}
	
	private void load(Explanation element) {
		if(element != null) {
			this.baseTargetDoubt = element.baseTargetDoubt;
			this.targetDoubt = element.targetDoubt;
			this.baseResponse = element.baseResponse;
			this.response = element.response;
		}
	}

	private void reloadFromRootElementMap(Map<String, AbstractElement> rootElementMap) {
		if(CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		if(this.baseTargetDoubt != null && this.baseTargetDoubt.getId() != null) {
			AbstractElement element = rootElementMap.get(this.baseTargetDoubt.getId());
			this.targetDoubt = (element instanceof Doubt) 
					? (Doubt) element : this.targetDoubt;
		}
		if(this.baseResponse != null && this.baseResponse.getId() != null) {
			AbstractElement element = rootElementMap.get(this.baseResponse.getId());
			this.response = (element != null) ? element : this.response;
		}
	}


	@Override
	public Explanation cloneElement() throws LIMException {
		Explanation cloneElement = (Explanation) super.cloneElement();
		
		cloneElement.baseTargetDoubt = (this.baseTargetDoubt != null) 
				? (BaseElement) this.baseTargetDoubt.cloneElement() : cloneElement.baseTargetDoubt;
		cloneElement.targetDoubt = (this.targetDoubt != null) 
				? this.targetDoubt.cloneElement() : cloneElement.targetDoubt;
		
		cloneElement.baseResponse = (this.baseResponse != null) 
				? (BaseElement) this.baseResponse.cloneElement() : cloneElement.baseResponse;
		cloneElement.response = (this.response != null) 
				? this.response.cloneElement() : cloneElement.response;
		
		return cloneElement;
	}

}
