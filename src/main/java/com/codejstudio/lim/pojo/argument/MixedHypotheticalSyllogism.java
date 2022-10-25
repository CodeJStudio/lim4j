package com.codejstudio.lim.pojo.argument;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.statement.HypotheticalProposition;
import com.codejstudio.lim.pojo.statement.JudgedStatement;

/**
 * MixedHypotheticalSyllogism.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class MixedHypotheticalSyllogism extends HypotheticalSyllogism {

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public MixedHypotheticalSyllogism() throws LIMException {
		super();
	}

	public MixedHypotheticalSyllogism(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public MixedHypotheticalSyllogism(boolean ifInitId, boolean ifInitType, String discription) throws LIMException {
		super(ifInitId, ifInitType, discription);
	}
	

	public MixedHypotheticalSyllogism(String discription) throws LIMException {
		super(true, true, discription);
	}


	/* getters & setters */

	public HypotheticalProposition getMajorPremise() {
		return (majorPremise instanceof HypotheticalProposition) ? (HypotheticalProposition) majorPremise : null;
	}

	public void setMajorPremise(HypotheticalProposition majorPremise) throws LIMException {
		super.setMajorPremise(majorPremise);
	}


	public void setElementsOfSyllogism(HypotheticalProposition majorPremise, JudgedStatement minorPremise, JudgedStatement conclusion) throws LIMException {
		super.setElementsOfSyllogism(majorPremise, minorPremise, conclusion);
	}

}
