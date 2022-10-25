package com.codejstudio.lim.pojo.i;

import java.util.Collection;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.AbstractElement;

/**
 * IGroupable.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public interface IGroupable<T extends AbstractElement> {

	/* constants */

	public static final String GROUP_KEY = "group";
	

	/* getters & setters */

	/**
	 * The implemented classes should also contain:<br/>
	 * private Collection<T> innerGroupCollection;<br/>
	 * private void initInnerGroupCollection();<br/>
	 * private void destroyInnerGroupCollection();
	 */
	public Collection<T> getInnerGroupCollection();
	
	public int size();


	/* CRUD for collections, maps, sub-groups: group elements */

	public boolean containGroupElement(T element) throws LIMException;

	@SuppressWarnings("unchecked")
	public boolean addGroupElement(T... elements) throws LIMException;
	
	public boolean addGroupElement(Collection<T> elements) throws LIMException;

	@SuppressWarnings("unchecked")
	public boolean removeGroupElement(T... elements) throws LIMException;

	public boolean removeGroupElement(Collection<T> elements) throws LIMException;


	/* static methods */
	
	public static boolean checkNullOrEmpty(IGroupable<?> group) {
		if(group == null || group.size() == 0) {
			return true;
		}
		return false;
	}

}
