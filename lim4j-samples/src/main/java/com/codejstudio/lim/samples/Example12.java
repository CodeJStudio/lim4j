package com.codejstudio.lim.samples;

import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.argument.Argument;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.condition.HypotheticalCondition;
import com.codejstudio.lim.pojo.condition.PremiseCondition;
import com.codejstudio.lim.pojo.statement.Statement;

/**
 * sample: Example12 
 * in "Theory of Logical Information Model & Logical Information Network"
 * from https://github.com/jhjiang/lim_lin
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.Root
 * @see     com.codejstudio.lim.pojo.argument.Argument
 * @see     com.codejstudio.lim.pojo.condition.Condition
 * @see     com.codejstudio.lim.pojo.condition.HypotheticalCondition
 * @see     com.codejstudio.lim.pojo.condition.PremiseCondition
 * @see     com.codejstudio.lim.pojo.statement.Statement
 * @since   lim4j_v1.0.0
 */
public class Example12 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode();

		doXmlMarshal();
//		HelloWorld.doXmlUnmarshal(Example12.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		Argument a1 = new Argument("Love looks not with the eyes, but with the mind; And therefore is wing'd Cupid painted blind.");

		Statement s1 = new Statement("if there is a wing'd Cupid");
		Statement s2 = new Statement("Cupid is a symbol (or synonymous) of love");
		Statement s3 = new Statement("blindness is to see with the mind rather than with the eyes");

		Condition cd1 = new HypotheticalCondition(s1, true);
		Condition cd2 = new PremiseCondition(s2, true);
		Condition cd3 = new PremiseCondition(s3, true);

		a1.addCondition(cd1, cd2, cd3);



		Root root = new Root(a1);
		System.out.println();
		root.marshalToXml(System.out);
	}

}
