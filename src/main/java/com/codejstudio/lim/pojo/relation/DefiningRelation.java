package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.attribute.DefaultAttribute;
import com.codejstudio.lim.pojo.concept.Concept;

/**
 * DefiningRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class DefiningRelation extends BaseRelation {

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public DefiningRelation() throws LIMException {
		super();
	}

	public DefiningRelation(boolean ifInitId) throws LIMException {
		super(ifInitId, true);
	}

	public DefiningRelation(boolean ifInitId, Concept definiendum, Concept definiens) throws LIMException {
		super(ifInitId, true, definiendum, definiens);
		definiendum.addAttribute(new DefaultAttribute(definiens));
		super.addInnerElementDelegate(new MappingRelation(ifInitId, definiendum, definiens));
	}

}
