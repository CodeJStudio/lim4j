package com.codejstudio.lim.pojo.role;

import java.util.Collection;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.StringUtils;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.ObjectUtil;
import com.codejstudio.lim.pojo.AbstractElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.comment.Comment;
import com.codejstudio.lim.pojo.comment.CommentGroup;
import com.codejstudio.lim.pojo.entity.Entity;
import com.codejstudio.lim.pojo.i.IIntegratable;

/**
 * BaseRole.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
@XmlRootElement(name = BaseRole.TYPE_NAME)
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
    "baseEntity",
    "baseCommentGroup",
})
public class BaseRole extends AbstractElement {

	/* constants */
	
	public static final String TYPE_NAME = "role";
	

	/* variables */

	@XmlElement(name = "entity", required = true)
    protected BaseElement baseEntity;
	
	protected Entity entity;


	/* variables: collections, maps, sub-groups */
	
	@XmlElement(name = "comment-group")
	private BaseElement baseCommentGroup;
	
	private CommentGroup commentGroup;

	
	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public BaseRole() throws LIMException {
		super();
	}

	public BaseRole(BaseRole role) throws LIMException {
		super(role);
		load(role);
	}

	public BaseRole(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public BaseRole(boolean ifInitId, boolean ifInitType, Entity entity) throws LIMException {
		super(ifInitId, ifInitType);
		setEntity(entity);
	}

	public BaseRole(boolean ifInitId, boolean ifInitType, Entity entity, Comment... comments) throws LIMException {
		super(ifInitId, ifInitType);
		setEntity(entity);
		addComment(comments);
	}


	public BaseRole(Entity entity) throws LIMException {
		this(true, false, entity);
	}

	public BaseRole(Entity entity, Comment... comments) throws LIMException {
		this(true, false, entity, comments);
	}
	

	/* initializers */

	private void initCommentGroup() throws LIMException {
		if(this.commentGroup == null) {
			this.commentGroup = new CommentGroup(true);
			super.addInnerElementDelegate(this.commentGroup);
			this.baseCommentGroup = new BaseElement(commentGroup);
		}
	}
	

	/* destroyers */

	private void destroyCommentGroup() throws LIMException {
		if(this.commentGroup != null && this.commentGroup.size() == 0) {
			this.baseCommentGroup = null;
			super.removeInnerElementDelegate(this.commentGroup);
			this.commentGroup = null;
		}
	}
	

	/* getters & setters */

	public BaseElement getBaseEntity() {
		return baseEntity;
	}

	@XmlTransient
	public Entity getEntity() {
		return entity;
	}

	/**
	 * required = true
	 */
	public void setEntity(Entity entity) throws LIMException {
		if(entity == null) {
			throw new LIMException(new NullPointerException());
		}
		if(ObjectUtil.checkEquals(this.entity, entity)) {
			return;
		}
		
		if(this.entity != null) {
			this.baseEntity = null;
			super.removeInnerElementDelegate(this.entity);
			this.entity = null;
		}
		this.entity = entity;
		super.addInnerElementDelegate(entity);
		this.baseEntity = new BaseElement(entity);
	}

	public CommentGroup getCommentGroup() {
		return commentGroup;
	}


	/* CRUD for collections, maps, sub-groups: comments */

	public boolean containComment(Comment element) throws LIMException{
		return (this.commentGroup != null) 
				? this.commentGroup.containGroupElement(element) : false;
	}

	public boolean addComment(Comment... elements) throws LIMException {
		return addComment(CollectionUtil.generateCollection(elements));
	}

	public boolean addComment(Collection<Comment> elements) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(elements)) {
			return false;
		}
		
		initCommentGroup();
		boolean flag = true;
		flag &= super.addInnerElementDelegate(elements) 
				& this.commentGroup.addGroupElement(elements);
		destroyCommentGroup();
		return flag;
	}

	public boolean removeComment(Comment... elements) throws LIMException {
		return removeComment(CollectionUtil.generateCollection(elements));
	}

	public boolean removeComment(Collection<Comment> elements) throws LIMException {
		if(CollectionUtil.checkNullOrEmpty(elements) || this.commentGroup == null) {
			return false;
		}
		
		boolean flag = true;
		flag &= super.removeInnerElementDelegate(elements) 
				& this.commentGroup.removeGroupElement(elements);
		destroyCommentGroup();
		return flag;
	}


	/* overridden methods */

	@Override
	public AbstractElement getXmlElement() throws LIMException {
		if(this.xmlElement == null) {
			if(this.getClass().equals(BaseRole.class)) {
				this.xmlElement = this;
			}else {
				this.xmlElement = new BaseRole(this);
			}
		}
		return this.xmlElement;
	}

	@Override
	public AbstractElement getPojoElement(Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (this.pojoElement == null) {
			if (StringUtils.isEmpty(this.getType())  
					|| !this.getClass().equals(BaseRole.class)) {
				this.pojoElement = this;
			} else {
				this.pojoElement = super.generatePojoElementDelegate(rootElementMap);
			}
		}
		this.pojoElement.reload(this, rootElementMap);
		return this.pojoElement;
	}


	@Override
	public IIntegratable reload(IIntegratable element, Map<String, AbstractElement> rootElementMap) throws LIMException {
		if (element instanceof BaseRole) {
			if (super.reload(element, rootElementMap) == null) {
				return null;
			}
			load((BaseRole) element);
			reloadFromRootElementMap(rootElementMap);
			return (IIntegratable) this;
		} else {
			return null;
		}
	}
	
	private void load(BaseRole element) {
		if(element != null) {
			this.baseEntity = element.baseEntity;
			this.entity = element.entity;
			this.baseCommentGroup = element.baseCommentGroup;
			this.commentGroup = element.commentGroup;
		}
	}

	private void reloadFromRootElementMap(Map<String, AbstractElement> rootElementMap) {
		if(CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		if(this.baseEntity != null && this.baseEntity.getId() != null) {
			AbstractElement element = rootElementMap.get(this.baseEntity.getId());
			this.entity = (element instanceof Entity) ? (Entity) element : this.entity;
		}
		if(this.baseCommentGroup != null && this.baseCommentGroup.getId() != null) {
			AbstractElement element = rootElementMap.get(this.baseCommentGroup.getId());
			this.commentGroup = (element instanceof CommentGroup) 
					? (CommentGroup) element : this.commentGroup;
		}
	}


	@Override
	public BaseRole cloneElement() throws LIMException {
		BaseRole cloneElement = (BaseRole) super.cloneElement();
		
		cloneElement.baseEntity = (this.baseEntity != null) 
				? (BaseElement) this.baseEntity.cloneElement() : cloneElement.baseEntity;
		cloneElement.entity = (this.entity != null) 
				? (Entity) this.entity.cloneElement() : cloneElement.entity;
		
		cloneElement.baseCommentGroup = (this.baseCommentGroup != null) 
				? (BaseElement) this.baseCommentGroup.cloneElement() : cloneElement.baseCommentGroup;
		cloneElement.commentGroup = (this.commentGroup != null) 
				? (CommentGroup) this.commentGroup.cloneElement() : cloneElement.commentGroup;
		
		return cloneElement;
	}

}
