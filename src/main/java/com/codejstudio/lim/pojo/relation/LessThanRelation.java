package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;

/**
 * LessThanRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class LessThanRelation extends ComparisonRelation {

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public LessThanRelation() throws LIMException {
		super();
	}

	public LessThanRelation(boolean ifInitId) throws LIMException {
		super(ifInitId);
	}

	public LessThanRelation(boolean ifInitId, AbstractRelationableInformationElement primaryElement, AbstractRelationableInformationElement secondaryElement) throws LIMException {
		super(ifInitId, primaryElement, secondaryElement);
	}

}
