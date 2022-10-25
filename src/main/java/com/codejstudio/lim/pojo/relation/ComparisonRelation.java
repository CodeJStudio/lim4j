package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;

/**
 * ComparisonRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class ComparisonRelation extends MappingRelation {

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public ComparisonRelation() throws LIMException {
		super();
	}

	public ComparisonRelation(boolean ifInitId) throws LIMException {
		super(ifInitId);
	}

	public ComparisonRelation(boolean ifInitId, AbstractRelationableInformationElement primaryElement, AbstractRelationableInformationElement secondaryElement) throws LIMException {
		super(ifInitId, primaryElement, secondaryElement);
	}

}
