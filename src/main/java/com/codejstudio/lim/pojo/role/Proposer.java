package com.codejstudio.lim.pojo.role;

import java.util.Collection;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.OwnableInformationElement;
import com.codejstudio.lim.pojo.comment.Comment;
import com.codejstudio.lim.pojo.entity.Entity;

/**
 * Proposer.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class Proposer extends BaseRole {

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Proposer() throws LIMException {
		super();
	}

	public Proposer(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public Proposer(boolean ifInitId, boolean ifInitType, Entity entity) throws LIMException {
		super(ifInitId, ifInitType, entity);
	}

	public Proposer(boolean ifInitId, boolean ifInitType, Entity entity, Comment... comments) throws LIMException {
		super(ifInitId, ifInitType, entity, comments);
	}


	public Proposer(Entity entity) throws LIMException {
		super(true, true, entity);
	}

	public Proposer(Entity entity, Comment... comments) throws LIMException {
		super(true, true, entity, comments);
	}


	/* CRUD for collections, maps, sub-groups: proposed elements */

	public boolean containProposedElement(OwnableInformationElement element) throws LIMException{
		return this.entity.containProposedElement(element);
	}

	public boolean addProposedElement(OwnableInformationElement... elements) throws LIMException {
		return this.entity.addProposedElement(elements);
	}

	public boolean addProposedElement(Collection<OwnableInformationElement> elements) throws LIMException {
		return this.entity.addProposedElement(elements);
	}

	public boolean removeProposedElement(OwnableInformationElement... elements) throws LIMException {
		return this.entity.removeProposedElement(elements);
	}

	public boolean removeProposedElement(Collection<OwnableInformationElement> elements) throws LIMException {
		return this.entity.removeProposedElement(elements);
	}
	
}
