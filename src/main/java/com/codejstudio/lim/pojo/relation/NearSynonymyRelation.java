package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.concept.Concept;

/**
 * NearSynonymyRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class NearSynonymyRelation extends SynonymyRelation {

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public NearSynonymyRelation() throws LIMException {
		super();
	}

	public NearSynonymyRelation(boolean ifInitId) throws LIMException {
		super(ifInitId);
	}

	public NearSynonymyRelation(boolean ifInitId, Concept primaryDefiniendum, Concept secondaryDefiniendum) throws LIMException {
		super(ifInitId, primaryDefiniendum, secondaryDefiniendum);
	}

}
