package com.codejstudio.lim.samples;

import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.argument.Argument;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.condition.QuantifiersCondition;
import com.codejstudio.lim.pojo.condition.QuantifiersCondition.QuantifiersType;
import com.codejstudio.lim.pojo.statement.JudgedStatement;
import com.codejstudio.lim.pojo.statement.Proposition;

/**
 * sample: Example09_1 
 * in "Theory of Logical Information Model & Logical Information Network"
 * from https://github.com/jhjiang/lim_lin
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.Root
 * @see     com.codejstudio.lim.pojo.argument.Argument
 * @see     com.codejstudio.lim.pojo.condition.Condition
 * @see     com.codejstudio.lim.pojo.condition.QuantifiersCondition
 * @see     com.codejstudio.lim.pojo.statement.JudgedStatement
 * @see     com.codejstudio.lim.pojo.statement.Proposition
 * @since   lim4j_v1.0.0
 */
public class Example09_1 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode();

		doXmlMarshal();
//		HelloWorld.doXmlUnmarshal(Example09_1.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		Argument a1 = new Argument("All mammals have lungs. All whales are mammals. Therefore all whales have lungs.");

		JudgedStatement js1 = new Proposition("All mammals have lungs.");
		JudgedStatement js2 = new Proposition("All whales are mammals.");
		JudgedStatement js3 = new Proposition("All whales have lungs.");

		Concept c1 = new Concept("all");

		Condition cd1 = new QuantifiersCondition(c1, QuantifiersType.UNIVERSAL);

		js1.addCondition(cd1);
		js2.addCondition(cd1);
		js3.addCondition(cd1);
		a1.addCondition(cd1);
		a1.addJudgedStatement(js1, js2, js3);



		Root root = new Root(a1);
		System.out.println();
		root.marshalToXml(System.out);
	}

}
