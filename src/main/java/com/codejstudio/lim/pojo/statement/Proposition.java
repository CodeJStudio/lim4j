package com.codejstudio.lim.pojo.statement;

import com.codejstudio.lim.common.exception.LIMException;

/**
 * Proposition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class Proposition extends JudgedStatement {

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Proposition() throws LIMException {
		super();
	}

	public Proposition(Proposition proposition) throws LIMException {
		super(proposition);
	}

	public Proposition(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public Proposition(boolean ifInitId, boolean ifInitType, String discription) throws LIMException {
		super(ifInitId, ifInitType, discription);
	}
	

	public Proposition(String discription) throws LIMException {
		super(true, true, discription);
	}


	public Proposition(Statement statement) throws LIMException {
		super(statement);
	}

}
