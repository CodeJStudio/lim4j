package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;

/**
 * GreaterThanRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class GreaterThanRelation extends ComparisonRelation {

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public GreaterThanRelation() throws LIMException {
		super();
	}

	public GreaterThanRelation(boolean ifInitId) throws LIMException {
		super(ifInitId);
	}

	public GreaterThanRelation(boolean ifInitId, AbstractRelationableInformationElement primaryElement, AbstractRelationableInformationElement secondaryElement) throws LIMException {
		super(ifInitId, primaryElement, secondaryElement);
	}

}
