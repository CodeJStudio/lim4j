package com.codejstudio.lim.pojo.statement;

import com.codejstudio.lim.common.exception.LIMException;

/**
 * Narration.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class Narration extends JudgedStatement {

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Narration() throws LIMException {
		super();
	}

	public Narration(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public Narration(boolean ifInitId, boolean ifInitType, String discription) throws LIMException {
		super(ifInitId, ifInitType, discription);
	}
	

	public Narration(String discription) throws LIMException {
		super(true, true, discription);
	}

}
