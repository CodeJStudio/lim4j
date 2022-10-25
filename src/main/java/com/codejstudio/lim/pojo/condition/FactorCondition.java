package com.codejstudio.lim.pojo.condition;

import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.ObjectUtil;
import com.codejstudio.lim.pojo.AbstractElement;
import com.codejstudio.lim.pojo.i.IIntegratable;

/**
 * FactorCondition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class FactorCondition extends Condition {

	/* constants */
	
	protected static final String FACTOR_NAME = "factor-name";
	
	public static final String TIME = "time";

	public static final String PLACE = "place";
	

	/* variables */
	
	protected String factorName;

	
	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public FactorCondition() throws LIMException {
		super();
	}

	public FactorCondition(boolean ifInitId, boolean ifInitType, String discription, String factorName) throws LIMException {
		super(ifInitId, ifInitType, discription);
		setFactorName(factorName);
	}


	public FactorCondition(String discription, String factorName) throws LIMException {
		this(true, true, discription, factorName);
	}

	public FactorCondition(String discription, String factorName, Boolean implicit) throws LIMException {
		this(true, true, discription, factorName);
		setImplicit(implicit);
	}


	/* getters & setters */

	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) throws LIMException {
		if(ObjectUtil.checkEquals(this.factorName, factorName)) {
			return;
		}
		
		if(this.factorName != null) {
			super.removeIntegratedAttributeDelegate(FACTOR_NAME);
			this.factorName = null;
		}
		if(factorName != null) {
			this.factorName = factorName;
			super.putIntegratedAttributeDelegate(FACTOR_NAME, factorName);	
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
		this.factorName = super.getIntegratedAttributeDelegate(FACTOR_NAME);
	}


	@Override
	public FactorCondition cloneElement() throws LIMException {
		FactorCondition cloneElement = (FactorCondition) super.cloneElement();
		cloneElement.factorName = this.factorName;
		return cloneElement;
	}

}
