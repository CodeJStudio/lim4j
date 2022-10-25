package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;

/**
 * CustomizedRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class CustomizedRelation extends BaseRelation {

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public CustomizedRelation() throws LIMException {
		super();
	}

	public CustomizedRelation(boolean ifInitId) throws LIMException {
		super(ifInitId, true);
	}

	public CustomizedRelation(boolean ifInitId, AbstractRelationableInformationElement primaryElement, AbstractRelationableInformationElement secondaryElement) throws LIMException {
		super(ifInitId, true, primaryElement, secondaryElement);
	}

}
