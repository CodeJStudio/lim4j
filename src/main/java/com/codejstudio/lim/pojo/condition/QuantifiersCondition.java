package com.codejstudio.lim.pojo.condition;

import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.ObjectUtil;
import com.codejstudio.lim.pojo.AbstractElement;
import com.codejstudio.lim.pojo.i.IIntegratable;

/**
 * QuantifiersCondition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class QuantifiersCondition extends ScopeCondition {

	/* enumeration */
	
	public enum QuantifiersType{
		UNIVERSAL,
		PARTICULAR,
		SINGULAR,
		;
	}


	/* constants */
	
	protected static final String QUANTIFIERS_TYPE = "quantifiers-type";

	
	/* variables */
	
	protected QuantifiersType quantifiersType;

	
	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public QuantifiersCondition() throws LIMException {
		super();
	}

	public QuantifiersCondition(boolean ifInitId, boolean ifInitType, String discription, QuantifiersType quantifiersType) throws LIMException {
		super(ifInitId, ifInitType, discription);
		setQuantifiersType(quantifiersType);
	}


	public QuantifiersCondition(String discription, QuantifiersType quantifiersType) throws LIMException {
		this(true, true, discription, quantifiersType);
	}

	public QuantifiersCondition(String discription, QuantifiersType quantifiersType, Boolean implicit) throws LIMException {
		this(true, true, discription, quantifiersType);
		setImplicit(implicit);
	}


	/* getters & setters */

	public QuantifiersType getQuantifiersType() {
		return quantifiersType;
	}

	public void setQuantifiersType(QuantifiersType quantifiersType) throws LIMException {
		if(ObjectUtil.checkEquals(this.quantifiersType, quantifiersType)) {
			return;
		}
		
		if(this.quantifiersType != null) {
			super.removeIntegratedAttributeDelegate(QUANTIFIERS_TYPE);
			this.quantifiersType = null;
		}
		if(quantifiersType != null) {
			this.quantifiersType = quantifiersType;
			super.putIntegratedAttributeDelegate(QUANTIFIERS_TYPE, quantifiersType.name());	
		}
	}


	/* overridden methods */

	@Override
	public IIntegratable reload(IIntegratable element, Map<String, AbstractElement> rootElementMap) throws LIMException {
		if(super.reload(element, rootElementMap) != null) {
			load();
			return (IIntegratable) this;
		} else {
			return null;
		}
	}
	
	private void load() throws LIMException {
		String typeName = super.getIntegratedAttributeDelegate(QUANTIFIERS_TYPE);
		this.quantifiersType = QuantifiersType.valueOf(typeName);
	}


	@Override
	public QuantifiersCondition cloneElement() throws LIMException {
		QuantifiersCondition cloneElement = (QuantifiersCondition) super.cloneElement();
		cloneElement.quantifiersType = this.quantifiersType;
		return cloneElement;
	}

}
