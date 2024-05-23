package com.codejstudio.lim.pojo.role;

import java.util.Collection;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.OwnableInformationElement;
import com.codejstudio.lim.pojo.comment.Comment;
import com.codejstudio.lim.pojo.entity.Entity;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * Proposer.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class Proposer extends BaseRole {

	/* constants */

	private static final long serialVersionUID = -502068808206259261L;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Proposer() {
		super();
	}

	public Proposer(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public Proposer(boolean initIdFlag, boolean initTypeFlag, Entity entity) throws LIMException {
		super(initIdFlag, initTypeFlag, entity);
	}

	public Proposer(boolean initIdFlag, boolean initTypeFlag, Entity entity, Comment... comments) 
			throws LIMException {
		super(initIdFlag, initTypeFlag, entity, comments);
	}


	public Proposer(Entity entity) throws LIMException {
		super(true, true, entity);
	}

	public Proposer(Entity entity, Comment... comments) throws LIMException {
		super(true, true, entity, comments);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(Proposer.class);
		BaseRole.registerSubPojoClassForInit(Proposer.class);
	}


	/* CRUD for arrays, collections, maps, groups: proposed elements */

	public boolean containProposedElement(final OwnableInformationElement element) {
		return (this.entity == null) ? false : this.entity.containProposedElement(element);
	}

	public boolean addProposedElement(final OwnableInformationElement... elements) throws LIMException {
		return (this.entity == null) ? false : this.entity.addProposedElement(elements);
	}

	public boolean addProposedElement(final Collection<OwnableInformationElement> elementCollection) 
			throws LIMException {
		return (this.entity == null) ? false : this.entity.addProposedElement(elementCollection);
	}

	public boolean removeProposedElement(final OwnableInformationElement... elements) throws LIMException {
		return (this.entity == null) ? false : this.entity.removeProposedElement(elements);
	}

	public boolean removeProposedElement(final Collection<OwnableInformationElement> elementCollection) 
	throws LIMException {
		return (this.entity == null) ? false : this.entity.removeProposedElement(elementCollection);
	}

}
