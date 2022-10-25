package com.codejstudio.lim.pojo.role;

import java.util.Collection;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.OwnableInformationElement;
import com.codejstudio.lim.pojo.comment.Comment;
import com.codejstudio.lim.pojo.entity.Entity;

/**
 * Observer.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class Observer extends BaseRole {

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Observer() throws LIMException {
		super();
	}

	public Observer(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public Observer(boolean ifInitId, boolean ifInitType, Entity entity) throws LIMException {
		super(ifInitId, ifInitType, entity);
	}

	public Observer(boolean ifInitId, boolean ifInitType, Entity entity, Comment... comments) throws LIMException {
		super(ifInitId, ifInitType, entity, comments);
	}


	public Observer(Entity entity) throws LIMException {
		super(true, true, entity);
	}

	public Observer(Entity entity, Comment... comments) throws LIMException {
		super(true, true, entity, comments);
	}


	/* CRUD for collections, maps, sub-groups: observed elements */

	public boolean containObservedElement(OwnableInformationElement element) throws LIMException{
		return this.entity.containObservedElement(element);
	}

	public boolean addObservedElement(OwnableInformationElement... elements) throws LIMException {
		return this.entity.addObservedElement(elements);
	}

	public boolean addObservedElement(Collection<OwnableInformationElement> elements) throws LIMException {
		return this.entity.addObservedElement(elements);
	}

	public boolean removeObservedElement(OwnableInformationElement... elements) throws LIMException {
		return this.entity.removeObservedElement(elements);
	}

	public boolean removeObservedElement(Collection<OwnableInformationElement> elements) throws LIMException {
		return this.entity.removeObservedElement(elements);
	}

}
