package com.codejstudio.lim.pojo.argument;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.statement.HypotheticalProposition;

/**
 * PureHypotheticalSyllogism.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class PureHypotheticalSyllogism extends HypotheticalSyllogism {

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public PureHypotheticalSyllogism() throws LIMException {
		super();
	}

	public PureHypotheticalSyllogism(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public PureHypotheticalSyllogism(boolean ifInitId, boolean ifInitType, String discription) throws LIMException {
		super(ifInitId, ifInitType, discription);
	}
	

	public PureHypotheticalSyllogism(String discription) throws LIMException {
		super(true, true, discription);
	}


	/* getters & setters */

	public HypotheticalProposition getMajorPremise() {
		return (majorPremise instanceof HypotheticalProposition) ? (HypotheticalProposition) majorPremise : null;
	}

	public void setMajorPremise(HypotheticalProposition majorPremise) throws LIMException {
		super.setMajorPremise(majorPremise);
	}

	public HypotheticalProposition getMinorPremise() {
		return (minorPremise instanceof HypotheticalProposition) ? (HypotheticalProposition) minorPremise : null;
	}

	public void setMinorPremise(HypotheticalProposition minorPremise) throws LIMException {
		super.setMinorPremise(minorPremise);
	}

	public HypotheticalProposition getConclusion() {
		return (conclusion instanceof HypotheticalProposition) ? (HypotheticalProposition) conclusion : null;
	}

	public void setConclusion(HypotheticalProposition conclusion) throws LIMException {
		super.setConclusion(conclusion);
	}


	public void setElementsOfSyllogism(HypotheticalProposition majorPremise, HypotheticalProposition minorPremise, HypotheticalProposition conclusion) throws LIMException {
		super.setElementsOfSyllogism(majorPremise, minorPremise, conclusion);
	}

}
