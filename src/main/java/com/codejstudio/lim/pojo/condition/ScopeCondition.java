package com.codejstudio.lim.pojo.condition;

import com.codejstudio.lim.common.exception.LIMException;

/**
 * ScopeCondition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class ScopeCondition extends Condition {

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public ScopeCondition() throws LIMException {
		super();
	}

	public ScopeCondition(boolean ifInitId, boolean ifInitType, String discription) throws LIMException {
		super(ifInitId, ifInitType, discription);
	}


	public ScopeCondition(String discription) throws LIMException {
		super(true, true, discription);
	}

	public ScopeCondition(String discription, Boolean implicit) throws LIMException {
		super(true, true, discription);
		setImplicit(implicit);
	}

}
