package com.codejstudio.lim.common.util;

/**
 * ObjectUtil.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public final class ObjectUtil {

	/* static methods */
	
	public static final boolean checkEquals(Object obj1, Object obj2) {
		if((obj1 == null && obj2 == null) 
				|| (obj1 != null && obj1.equals(obj2))) {
			return true;
		}else {
			return false;
		}
	}

}
