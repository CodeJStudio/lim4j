package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.attribute.DefaultAttribute;
import com.codejstudio.lim.pojo.concept.Concept;

/**
 * SynonymyRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class SynonymyRelation extends DefiningRelation {

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public SynonymyRelation() throws LIMException {
		super();
	}

	public SynonymyRelation(boolean ifInitId) throws LIMException {
		super(ifInitId);
	}

	public SynonymyRelation(boolean ifInitId, Concept primaryDefiniendum, Concept secondaryDefiniendum) throws LIMException {
		super(ifInitId, primaryDefiniendum, secondaryDefiniendum);
		secondaryDefiniendum.addAttribute(new DefaultAttribute(primaryDefiniendum));
		super.addInnerElementDelegate(new EquivalenceRelation(ifInitId, primaryDefiniendum, secondaryDefiniendum));
	}

}
