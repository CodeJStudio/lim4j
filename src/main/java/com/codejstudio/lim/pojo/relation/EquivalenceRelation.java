package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;

/**
 * EquivalenceRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class EquivalenceRelation extends MappingRelation {

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public EquivalenceRelation() throws LIMException {
		super();
	}

	public EquivalenceRelation(boolean ifInitId) throws LIMException {
		super(ifInitId);
	}

	public EquivalenceRelation(boolean ifInitId, AbstractRelationableInformationElement primaryElement, AbstractRelationableInformationElement secondaryElement) throws LIMException {
		super(ifInitId, primaryElement, secondaryElement);
	}


	public EquivalenceRelation(AbstractRelationableInformationElement primaryElement, AbstractRelationableInformationElement secondaryElement) throws LIMException {
		super(true, primaryElement, secondaryElement);
	}

}
