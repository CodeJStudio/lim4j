package com.codejstudio.lim.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;

/**
 * <code>PropertiesLoader</code> is written to load local property files.
 * <br>
 * eg. "common.properties"
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class PropertiesLoader {

	/* enumeration */
	
	public enum PropertiesFile{
		COMMON,
	}

	
	/* constants */
	
	private static final String PROPERTIES_PATH_PREFIX = "/properties/";

	private static final String PROPERTIES_PATH_SUFFIX = ".properties";


	/* variables: collections, maps, sub-groups */
	
	private static Map<String,Properties> propsMap = null;


	/* initializers */

	private static void init(PropertiesFile file) throws LIMException {
		InputStream inStream = null;
		try {
			if(propsMap == null) {
				propsMap = CollectionUtil.getDefaultNewMap();
			}
			Properties props = new Properties();
			for (PropertiesFile pf : PropertiesFile.values()) {
				if (pf.equals(file) && propsMap.get(file.name()) == null) {
					String filePath = PROPERTIES_PATH_PREFIX + file.name().toLowerCase() + PROPERTIES_PATH_SUFFIX;
					inStream = PropertiesLoader.class.getResourceAsStream(filePath);
					props.load(inStream);
					propsMap.put(file.name(), props);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new LIMException(e);
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	/* getters & setters */

	public static String getProperty(PropertiesFile file, String key) throws LIMException {
		if (propsMap == null || propsMap.get(file.name()) == null) {
			init(file);
		}
		return propsMap.get(file.name()).getProperty(key);
	}

	public static Map<String, String> getProperties(PropertiesFile file) throws LIMException {
		if (propsMap == null || propsMap.get(file.name()) == null) {
			init(file);
		}
		return propsMap.get(file.name());
	}

}
