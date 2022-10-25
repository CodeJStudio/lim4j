package com.codejstudio.lim.pojo.argument;

import com.codejstudio.lim.common.exception.LIMException;

/**
 * HypotheticalSyllogism.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class HypotheticalSyllogism extends Syllogism {

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public HypotheticalSyllogism() throws LIMException {
		super();
	}

	public HypotheticalSyllogism(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public HypotheticalSyllogism(boolean ifInitId, boolean ifInitType, String discription) throws LIMException {
		super(ifInitId, ifInitType, discription);
	}
	

	public HypotheticalSyllogism(String discription) throws LIMException {
		super(true, true, discription);
	}

}
