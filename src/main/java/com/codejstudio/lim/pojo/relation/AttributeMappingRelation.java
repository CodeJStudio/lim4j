package com.codejstudio.lim.pojo.relation;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.attribute.Attribute;

/**
 * AttributeMappingRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class AttributeMappingRelation extends MappingRelation {

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public AttributeMappingRelation() throws LIMException {
		super();
	}

	public AttributeMappingRelation(boolean ifInitId) throws LIMException {
		super(ifInitId);
	}

	public AttributeMappingRelation(boolean ifInitId, Attribute primaryAttribute, Attribute secondaryAttribute) throws LIMException {
		super(ifInitId, primaryAttribute, secondaryAttribute);
	}

}
