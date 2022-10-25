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
 * HypotheticalCondition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class HypotheticalCondition extends Condition {

	/* constants */
	
	protected static final String ANTECEDENT = "antecedent";
	

	/* variables */
	
	protected Statement antecedent;

	
	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public HypotheticalCondition() throws LIMException {
		super();
	}

	public HypotheticalCondition(boolean ifInitId, boolean ifInitType, Statement antecedent) throws LIMException {
		super(ifInitId, ifInitType);
		setAntecedent(antecedent);
	}


	public HypotheticalCondition(Statement antecedent) throws LIMException {
		this(true, true, antecedent);
	}

	public HypotheticalCondition(Statement antecedent, Boolean implicit) throws LIMException {
		this(true, true, antecedent);
		setImplicit(implicit);
	}


	/* getters & setters */

	public Statement getAntecedent() {
		return antecedent;
	}

	public void setAntecedent(Statement antecedent) throws LIMException {
		if(ObjectUtil.checkEquals(this.antecedent, antecedent)) {
			return;
		}
		
		if(this.antecedent != null) {
			super.removeInnerElementDelegate(this.antecedent);
			super.removeIntegratedElementDelegate(ANTECEDENT);
			this.antecedent = null;
		}
		if(antecedent != null) {
			this.antecedent = antecedent;
			super.addInnerElementDelegate(antecedent);
			super.putIntegratedElementDelegate(ANTECEDENT, new BaseElement(antecedent));
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

		BaseElement antecedent = map.get(ANTECEDENT);
		if(antecedent != null && antecedent.getId() != null) {
			AbstractElement element = rootElementMap.get(antecedent.getId());
			this.antecedent = (element instanceof Statement) 
					? (Statement) element : this.antecedent;
		}
	}


	@Override
	public HypotheticalCondition cloneElement() throws LIMException {
		HypotheticalCondition cloneElement = (HypotheticalCondition) super.cloneElement();
		cloneElement.antecedent = (this.antecedent != null) 
				? (Statement) this.antecedent.cloneElement() : cloneElement.antecedent;
		return cloneElement;
	}

}
