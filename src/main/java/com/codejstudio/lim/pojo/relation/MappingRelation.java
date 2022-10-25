package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;

/**
 * MappingRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class MappingRelation extends BaseRelation {

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public MappingRelation() throws LIMException {
		super();
	}

	public MappingRelation(boolean ifInitId) throws LIMException {
		super(ifInitId, true);
	}

	public MappingRelation(boolean ifInitId, AbstractRelationableInformationElement primaryElement, AbstractRelationableInformationElement secondaryElement) throws LIMException {
		super(ifInitId, true, primaryElement, secondaryElement);
	}


	public MappingRelation(AbstractRelationableInformationElement primaryElement, AbstractRelationableInformationElement secondaryElement) throws LIMException {
		super(true, true, primaryElement, secondaryElement);
	}

}
