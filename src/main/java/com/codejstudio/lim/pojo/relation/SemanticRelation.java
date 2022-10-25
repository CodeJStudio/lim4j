package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;

/**
 * SemanticRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class SemanticRelation extends BaseRelation {

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public SemanticRelation() throws LIMException {
		super();
	}

	public SemanticRelation(boolean ifInitId) throws LIMException {
		super(ifInitId, true);
	}

	public SemanticRelation(boolean ifInitId, AbstractRelationableInformationElement primaryElement, AbstractRelationableInformationElement secondaryElement) throws LIMException {
		super(ifInitId, true, primaryElement, secondaryElement);
	}

}
