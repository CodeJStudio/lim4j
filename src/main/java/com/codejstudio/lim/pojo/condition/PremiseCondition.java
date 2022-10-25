package com.codejstudio.lim.pojo.condition;

import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.ObjectUtil;
import com.codejstudio.lim.pojo.AbstractElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.i.IIntegratable;
import com.codejstudio.lim.pojo.statement.Statement;

/**
 * PremiseCondition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class PremiseCondition extends Condition {

	/* constants */
	
	protected static final String PREMISE = "premise";
	

	/* variables */
	
	protected Statement premise;

	
	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public PremiseCondition() throws LIMException {
		super();
	}

	public PremiseCondition(boolean ifInitId, boolean ifInitType, Statement premise) throws LIMException {
		super(ifInitId, ifInitType);
		setPremise(premise);
	}


	public PremiseCondition(Statement premise) throws LIMException {
		this(true, true, premise);
	}

	public PremiseCondition(Statement premise, Boolean implicit) throws LIMException {
		this(true, true, premise);
		setImplicit(implicit);
	}


	/* getters & setters */

	public Statement getPremise() {
		return premise;
	}

	public void setPremise(Statement premise) throws LIMException {
		if(ObjectUtil.checkEquals(this.premise, premise)) {
			return;
		}
		
		if(this.premise != null) {
			super.removeInnerElementDelegate(this.premise);
			super.removeIntegratedElementDelegate(PREMISE);
			this.premise = null;
		}
		if(premise != null) {
			this.premise = premise;
			super.addInnerElementDelegate(premise);
			super.putIntegratedElementDelegate(PREMISE, new BaseElement(premise));
		}
	}


	/* overridden methods */

	@Override
	public IIntegratable reload(IIntegratable element, Map<String, AbstractElement> rootElementMap) throws LIMException {
		if(super.reload(element, rootElementMap) != null) {
			reloadFromRootElementMap(rootElementMap);
			return (IIntegratable) this;
		} else {
			return null;
		}
	}

	private void reloadFromRootElementMap(Map<String, AbstractElement> rootElementMap) {
		Map<String, BaseElement> map = getIntegratedElement();
		if(CollectionUtil.checkNullOrEmpty(map) 
				|| CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		BaseElement premise = map.get(PREMISE);
		if(premise != null && premise.getId() != null) {
			AbstractElement element = rootElementMap.get(premise.getId());
			this.premise = (element instanceof Statement) 
					? (Statement) element : this.premise;
		}
	}


	@Override
	public PremiseCondition cloneElement() throws LIMException {
		PremiseCondition cloneElement = (PremiseCondition) super.cloneElement();
		cloneElement.premise = (this.premise != null) 
				? (Statement) this.premise.cloneElement() : cloneElement.premise;
		return cloneElement;
	}

}
