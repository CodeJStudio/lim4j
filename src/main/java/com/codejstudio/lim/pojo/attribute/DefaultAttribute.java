package com.codejstudio.lim.pojo.attribute;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CaseFormatUtil.WordSeparator;
import com.codejstudio.lim.pojo.AbstractElement;

/**
 * DefaultAttribute.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class DefaultAttribute extends Attribute {

	/* constants */

	public static final String DEFAULT_KEY = "default";

	
	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
    public DefaultAttribute() throws LIMException {
		super();
	}

    public DefaultAttribute(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public DefaultAttribute(boolean ifInitId, boolean ifInitType, String discription) throws LIMException {
		super(ifInitId, ifInitType, discription);
	}

	public DefaultAttribute(boolean ifInitId, boolean ifInitType, AbstractElement value) throws LIMException {
		super(ifInitId, ifInitType);
		if(value == null) {
			throw new LIMException(new NullPointerException());
		}
		super.init((DEFAULT_KEY + WordSeparator.UNDERSCORE.getSeparator() + value.getId()), value);
	}

	/**
	 * required = true
	 */
	public DefaultAttribute(AbstractElement value) throws LIMException {
		this(true, true, value);
	}

}
