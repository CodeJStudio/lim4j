package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.doubt.Doubt;
import com.codejstudio.lim.pojo.doubt.Explanation;

/**
 * DoubtNExplanationRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class DoubtNExplanationRelation extends BaseRelation {

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public DoubtNExplanationRelation() throws LIMException {
		super();
	}

	public DoubtNExplanationRelation(boolean ifInitId) throws LIMException {
		super(ifInitId, true);
	}

	public DoubtNExplanationRelation(boolean ifInitId, Doubt doubt, Explanation explanation) throws LIMException {
		super(ifInitId, true, doubt, explanation);
	}


}
