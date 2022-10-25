package com.codejstudio.lim.pojo.i;

import java.util.Collection;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.relation.BaseRelation;
import com.codejstudio.lim.pojo.relation.RelationGroup;

/**
 * IRelationable.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public interface IRelationable {

	/* getters & setters */

	/**
	 * The implemented classes should also contain:<br/>
	 * RelationGroup<?> relationGroup;<br/>
	 * initRelationGroup();
	 */
	public RelationGroup getRelationGroup();


	/* CRUD for collections, maps, sub-groups: relations */

	public boolean containRelation(BaseRelation relation) throws LIMException;

	public boolean addRelation(BaseRelation... relations) throws LIMException;

	public boolean addRelation(Collection<BaseRelation> relations) throws LIMException;
	
	public boolean removeRelation(BaseRelation... relations) throws LIMException;
	
	public boolean removeRelation(Collection<BaseRelation> relations) throws LIMException;


	/* other methods */

	public boolean equivalents(IRelationable obj) throws LIMException;

}
