package com.codejstudio.lim.pojo.condition;

import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.ObjectUtil;
import com.codejstudio.lim.pojo.AbstractElement;
import com.codejstudio.lim.pojo.i.IIntegratable;

/**
 * NegativesCondition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class NegativesCondition extends ScopeCondition {

	/* enumeration */
	
	public enum NegativesType{
		AFFIRMATIVE,
		NEGATIVE,
		;
	}


	/* constants */
	
	protected static final String NEGATIVES_TYPE = "negatives-type";

	
	/* variables */
	
	protected NegativesType negativesType;

	
	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public NegativesCondition() throws LIMException {
		super();
	}

	public NegativesCondition(boolean ifInitId, boolean ifInitType, String discription, NegativesType negativesType) throws LIMException {
		super(ifInitId, ifInitType, discription);
		setNegativesType(negativesType);
	}


	public NegativesCondition(String discription, NegativesType negativesType) throws LIMException {
		this(true, true, discription, negativesType);
	}

	public NegativesCondition(String discription, NegativesType negativesType, Boolean implicit) throws LIMException {
		this(true, true, discription, negativesType);
		setImplicit(implicit);
	}


	/* getters & setters */

	public NegativesType getNegativesType() {
		return negativesType;
	}

	public void setNegativesType(NegativesType negativesType) throws LIMException {
		if(ObjectUtil.checkEquals(this.negativesType, negativesType)) {
			return;
		}
		
		if(this.negativesType != null) {
			super.removeIntegratedAttributeDelegate(NEGATIVES_TYPE);
			this.negativesType = null;
		}
		if(negativesType != null) {
			this.negativesType = negativesType;
			super.putIntegratedAttributeDelegate(NEGATIVES_TYPE, negativesType.name());	
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
		String typeName = super.getIntegratedAttributeDelegate(NEGATIVES_TYPE);
		this.negativesType = NegativesType.valueOf(typeName);
	}


	@Override
	public NegativesCondition cloneElement() throws LIMException {
		NegativesCondition cloneElement = (NegativesCondition) super.cloneElement();
		cloneElement.negativesType = this.negativesType;
		return cloneElement;
	}

}
