package com.codejstudio.lim.common.util;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.PropertiesLoader.PropertiesFile;

/**
 * <code>CollectionUtil</code> is written to generate new objects of Collection, List or Map interface.<br>
 * The implemented class types can be configured in "common.properties".<br>
 * The default types are "<code>java.util.HashSet</code>", "<code>java.util.LinkedList</code>" and "<code>java.util.HashMap</code>".
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public final class CollectionUtil {

	/* constants */
	
	private static final String DEFAULT_NEW_COLLECTION = "java.util.HashSet";
	
	private static final String DEFAULT_NEW_LIST = "java.util.LinkedList";
	
	private static final String DEFAULT_NEW_MAP = "java.util.HashMap";
	
	
	private static final String NEW_COLLECTION_TYPE_KEY = "newCollectionType";
	
	private static final String NEW_LIST_TYPE_KEY = "newListType";
	
	private static final String NEW_MAP_TYPE_KEY = "newMapType";


	/* static methods */

	@SuppressWarnings("unchecked")
	public static final <V> Collection<V> getNewCollection() throws LIMException {
		try {
			String className = PropertiesLoader.getProperty(PropertiesFile.COMMON, NEW_COLLECTION_TYPE_KEY);
			if(className == null) {
				return (Collection<V>) Class.forName(className).newInstance();
			} else {
				return getDefaultNewCollection();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new LIMException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected static final <V> Collection<V> getDefaultNewCollection() throws LIMException {
		try {
			return (Collection<V>) Class.forName(DEFAULT_NEW_COLLECTION).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new LIMException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static final <V> List<V> getNewList() throws LIMException {
		try {
			String className = PropertiesLoader.getProperty(PropertiesFile.COMMON, NEW_LIST_TYPE_KEY);
			if(className == null) {
				return (List<V>) Class.forName(className).newInstance();
			} else {
				return getDefaultNewList();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new LIMException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected static final <V> List<V> getDefaultNewList() throws LIMException {
		try {
			return (List<V>) Class.forName(DEFAULT_NEW_LIST).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new LIMException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <K,V> Map<K,V> getNewMap() throws LIMException {
		try {
			String className = PropertiesLoader.getProperty(PropertiesFile.COMMON, NEW_MAP_TYPE_KEY);
			if(className == null) {
				return (Map<K,V>) Class.forName(className).newInstance();
			} else {
				return getDefaultNewMap();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new LIMException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected static final <K,V> Map<K,V> getDefaultNewMap() throws LIMException {
		try {
			return (Map<K,V>) Class.forName(DEFAULT_NEW_MAP).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new LIMException(e);
		}
	}
	

	
	@SuppressWarnings("unchecked")
	public static final <V> Collection<V> generateCollection(V... elements) throws LIMException {
		if(checkNullOrEmpty(elements)) {
			return null;
		}
		
		Collection<V> c = getNewCollection();
		Collections.addAll(c, elements);
		return c;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static final <V> boolean checkNullOrEmpty(V... elements) {
		if(elements == null) {
			return true;
		}
		for (V element : elements) {
			if(element != null) {
				return false;
			}
		}
		return true;
	}
	
	public static final <V> boolean checkNullOrEmpty(Collection<V> elements) {
		if(elements == null || elements.size() == 0) {
			return true;
		}
		for (V element : elements) {
			if(element != null) {
				return false;
			}
		}
		return true;
	}
	
	public static final <K,V> boolean checkNullOrEmpty(Map<K,V> map) {
		if(map == null || map.size() == 0) {
			return true;
		}
		for (K key : map.keySet()) {
			if(key != null && map.get(key) != null) {
				return false;
			}
		}
		return true;
	}
	
	
	@SuppressWarnings("unchecked")
	public static final <V> boolean onlyContains(Collection<V> collection, V... elements) {
		if(!checkNullOrEmpty(collection) && !checkNullOrEmpty(elements) 
				&& collection.size() == elements.length) {
			for (V element : elements) {
				if(!collection.contains(element)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	
	@SuppressWarnings("unchecked")
	public static final <V> Collection<V> removeElements(Collection<V> collection, V... elements) throws LIMException {
		try {
			Collection<V> c = collection;
			if(!checkNullOrEmpty(collection) && !checkNullOrEmpty(elements)) {
				c = (Collection<V>) collection.getClass().newInstance();
				c.addAll(collection);
				for (V element : elements) {
					c.remove(element);
				}
			}
			return c;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new LIMException(e);
		}
	}

}
