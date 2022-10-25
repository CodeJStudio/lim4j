package com.codejstudio.lim.common.util;

import java.util.UUID;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.PropertiesLoader.PropertiesFile;

/**
 * <code>IDUtil</code> is written to generate ID in String type.<br>
 * Which type of generation can be configured in "common.properties".<br>
 * eg. "increment" or "uuid".<br>
 * The default type is "increment".
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class IDUtil {

	/* enumeration */
	
	public enum IdGenerationType{
		UUID,
		INCREMENT,
	}

	
	/* constants */
	
	private static final String ID_GENERATION_KEY = "idGeneration";


	/* static methods */

	/**
	 * The entrance of ID generation.
	 */
	public static String generateID() throws LIMException {
		String idGenerationType = PropertiesLoader.getProperty(PropertiesFile.COMMON, ID_GENERATION_KEY);
		switch(IdGenerationType.valueOf(idGenerationType.toUpperCase())) {
			case UUID:
				return generateUUID();
			case INCREMENT:
				return generateIncrementID();
		}
		// default type:
		return generateIncrementID();
	}



	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}
	
	
	private static long incrementId = 0;

	public static String generateIncrementID() {
		return incrementId++ + "";
	}
	
}
