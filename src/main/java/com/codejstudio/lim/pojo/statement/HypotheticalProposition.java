package com.codejstudio.lim.pojo.statement;

import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.ObjectUtil;
import com.codejstudio.lim.pojo.AbstractElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.i.IIntegratable;

/**
 * HypotheticalProposition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class HypotheticalProposition extends Proposition {

	/* constants */
	
	protected static final String ANTECEDENT = "antecedent";
	
	protected static final String CONSEQUENT = "consequent";
	

	/* variables */
	
	protected Condition antecedent;

	protected JudgedStatement consequent;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public HypotheticalProposition() throws LIMException {
		super();
	}

	public HypotheticalProposition(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public HypotheticalProposition(boolean ifInitId, boolean ifInitType, String discription) throws LIMException {
		super(ifInitId, ifInitType, discription);
	}
	

	public HypotheticalProposition(String discription) throws LIMException {
		super(true, true, discription);
	}


	/* getters & setters */

	public Condition getAntecedent() {
		return antecedent;
	}

	public void setAntecedent(Condition antecedent) throws LIMException {
		if(ObjectUtil.checkEquals(this.antecedent, antecedent)) {
			return;
		}
		
		if(this.antecedent != null) {
			super.removeIntegratedElementDelegate(ANTECEDENT);
			super.removeInnerElementDelegate(this.antecedent);
			this.antecedent = null;
		}
		if(antecedent != null) {
			this.antecedent = antecedent;
			super.addInnerElementDelegate(antecedent);
			super.putIntegratedElementDelegate(ANTECEDENT, new BaseElement(antecedent));
		}
	}

	public JudgedStatement getConsequent() {
		return consequent;
	}

	public void setConsequent(JudgedStatement consequent) throws LIMException {
		if(ObjectUtil.checkEquals(this.consequent, consequent)) {
			return;
		}
		
		if(this.consequent != null) {
			super.removeIntegratedElementDelegate(CONSEQUENT);
			super.removeInnerElementDelegate(this.consequent);
			this.consequent = null;
		}
		if(consequent != null) {
			this.consequent = consequent;
			super.addInnerElementDelegate(consequent);
			super.putIntegratedElementDelegate(CONSEQUENT, new BaseElement(consequent));	
		}
	}


	public void setAntecedentAndConsequent(Condition antecedent, JudgedStatement consequent) throws LIMException {
		setAntecedent(antecedent);
		setConsequent(consequent);
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
			this.antecedent = (element instanceof Condition) 
					? (Condition) element : this.antecedent;
		}
		BaseElement consequent = map.get(CONSEQUENT);
		if(consequent != null && consequent.getId() != null) {
			AbstractElement element = rootElementMap.get(consequent.getId());
			this.consequent = (element instanceof JudgedStatement) 
					? (JudgedStatement) element : this.consequent;
		}
	}


	@Override
	public HypotheticalProposition cloneElement() throws LIMException {
		HypotheticalProposition cloneElement = (HypotheticalProposition) super.cloneElement();
		
		cloneElement.antecedent = (this.antecedent != null) 
				? (Condition) this.antecedent.cloneElement() : cloneElement.antecedent;
		cloneElement.consequent = (this.consequent != null) 
				? (JudgedStatement) this.consequent.cloneElement() : cloneElement.consequent;
		
		return cloneElement;
	}

}
