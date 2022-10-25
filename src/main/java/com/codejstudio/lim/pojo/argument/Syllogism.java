package com.codejstudio.lim.pojo.argument;

import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.ObjectUtil;
import com.codejstudio.lim.pojo.AbstractElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.i.IIntegratable;
import com.codejstudio.lim.pojo.statement.JudgedStatement;

/**
 * Syllogism.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class Syllogism extends Argument {

	/* constants */
	
	protected static final String MAJOR_PREMISE = "major-premise";
	
	protected static final String MINOR_PREMISE = "minor-premise";
	
	protected static final String CONCLUSION = "conclusion";
	

	/* variables */
	
	protected JudgedStatement majorPremise;

	protected JudgedStatement minorPremise;

	protected JudgedStatement conclusion;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Syllogism() throws LIMException {
		super();
	}

	public Syllogism(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public Syllogism(boolean ifInitId, boolean ifInitType, String discription) throws LIMException {
		super(ifInitId, ifInitType, discription);
	}
	

	public Syllogism(String discription) throws LIMException {
		super(true, true, discription);
	}

	public Syllogism(JudgedStatement majorPremise, JudgedStatement minorPremise, JudgedStatement conclusion) throws LIMException {
		super(true, true);
		setMajorPremise(majorPremise);
		setMinorPremise(minorPremise);
		setConclusion(conclusion);
	}


	/* getters & setters */

	public JudgedStatement getMajorPremise() {
		return majorPremise;
	}

	public void setMajorPremise(JudgedStatement majorPremise) throws LIMException {
		if(ObjectUtil.checkEquals(this.majorPremise, majorPremise)) {
			return;
		}
		
		if(this.majorPremise != null) {
			super.removeInnerElementDelegate(this.majorPremise);
			super.removeJudgedStatement(this.majorPremise);
			super.removeEvidence(this.majorPremise);
			super.removeIntegratedElementDelegate(MAJOR_PREMISE);
			this.majorPremise = null;
		}
		if(majorPremise != null) {
			this.majorPremise = majorPremise;
			super.addInnerElementDelegate(majorPremise);
			super.addJudgedStatement(majorPremise);
			super.addEvidence(majorPremise);
			super.putIntegratedElementDelegate(MAJOR_PREMISE, new BaseElement(majorPremise));
		}
	}

	public JudgedStatement getMinorPremise() {
		return minorPremise;
	}

	public void setMinorPremise(JudgedStatement minorPremise) throws LIMException {
		if(ObjectUtil.checkEquals(this.minorPremise, minorPremise)) {
			return;
		}
		
		if(this.minorPremise != null) {
			super.removeInnerElementDelegate(this.minorPremise);
			super.removeJudgedStatement(this.minorPremise);
			super.removeEvidence(this.minorPremise);
			super.removeIntegratedElementDelegate(MINOR_PREMISE);
			this.minorPremise = null;
		}
		if(minorPremise != null) {
			this.minorPremise = minorPremise;
			super.addInnerElementDelegate(minorPremise);
			super.addJudgedStatement(minorPremise);
			super.addEvidence(minorPremise);
			super.putIntegratedElementDelegate(MINOR_PREMISE, new BaseElement(minorPremise));	
		}
	}

	public JudgedStatement getConclusion() {
		return conclusion;
	}

	public void setConclusion(JudgedStatement conclusion) throws LIMException {
		if(ObjectUtil.checkEquals(this.conclusion, conclusion)) {
			return;
		}
		
		if(this.conclusion != null) {
			super.removeInnerElementDelegate(this.conclusion);
			super.removeJudgedStatement(this.conclusion);
			super.removeConclusion(this.conclusion);
			super.removeIntegratedElementDelegate(CONCLUSION);
			this.conclusion = null;
		}
		if(conclusion != null) {
			this.conclusion = conclusion;
			super.addInnerElementDelegate(conclusion);
			super.addJudgedStatement(conclusion);
			super.addConclusion(conclusion);
			super.putIntegratedElementDelegate(CONCLUSION, new BaseElement(conclusion));	
		}
	}


	public void setElementsOfSyllogism(JudgedStatement majorPremise, JudgedStatement minorPremise, JudgedStatement conclusion) throws LIMException {
		setMajorPremise(majorPremise);
		setMinorPremise(minorPremise);
		setConclusion(conclusion);
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

		BaseElement majorPremise = map.get(MAJOR_PREMISE);
		if(majorPremise != null && majorPremise.getId() != null) {
			AbstractElement element = rootElementMap.get(majorPremise.getId());
			this.majorPremise = (element instanceof JudgedStatement) 
					? (JudgedStatement) element : this.majorPremise;
		}
		BaseElement minorPremise = map.get(MINOR_PREMISE);
		if(minorPremise != null && minorPremise.getId() != null) {
			AbstractElement element = rootElementMap.get(minorPremise.getId());
			this.minorPremise = (element instanceof JudgedStatement) 
					? (JudgedStatement) element : this.minorPremise;
		}
		BaseElement conclusion = map.get(CONCLUSION);
		if(conclusion != null && conclusion.getId() != null) {
			AbstractElement element = rootElementMap.get(conclusion.getId());
			this.conclusion = (element instanceof JudgedStatement) 
					? (JudgedStatement) element : this.conclusion;
		}
	}


	@Override
	public Syllogism cloneElement() throws LIMException {
		Syllogism cloneElement = (Syllogism) super.cloneElement();
		
		cloneElement.majorPremise = (this.majorPremise != null) 
				? (JudgedStatement) this.majorPremise.cloneElement() : cloneElement.majorPremise;
		cloneElement.minorPremise = (this.minorPremise != null) 
				? (JudgedStatement) this.minorPremise.cloneElement() : cloneElement.minorPremise;
		cloneElement.conclusion = (this.conclusion != null) 
				? (JudgedStatement) this.conclusion.cloneElement() : cloneElement.conclusion;
		
		return cloneElement;
	}

}
