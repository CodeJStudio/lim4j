package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.concept.Concept;

/**
 * AntonymyRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class AntonymyRelation extends SynonymyRelation {

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public AntonymyRelation() throws LIMException {
		super();
	}

	public AntonymyRelation(boolean ifInitId) throws LIMException {
		super(ifInitId);
	}

	public AntonymyRelation(boolean ifInitId, Concept primaryDefiniendum, Concept secondaryDefiniendum) throws LIMException {
		super(ifInitId, primaryDefiniendum, secondaryDefiniendum);
	}

}
