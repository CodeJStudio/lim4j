package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;

/**
 * AnalogyRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class AnalogyRelation extends MappingRelation {

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public AnalogyRelation() throws LIMException {
		super();
	}

	public AnalogyRelation(boolean ifInitId) throws LIMException {
		super(ifInitId);
	}

	public AnalogyRelation(boolean ifInitId, AbstractRelationableInformationElement primaryElement, AbstractRelationableInformationElement secondaryElement) throws LIMException {
		super(ifInitId, primaryElement, secondaryElement);
	}


	public AnalogyRelation(AbstractRelationableInformationElement primaryElement, AbstractRelationableInformationElement secondaryElement) throws LIMException {
		this(true, primaryElement, secondaryElement);
	}

}
