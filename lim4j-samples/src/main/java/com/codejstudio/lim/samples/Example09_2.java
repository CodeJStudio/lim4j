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
 * sample: Example09_2 
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
public class Example09_2 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode();

		doXmlMarshal();
//		HelloWorld.doXmlUnmarshal(Example09_2.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		Argument a1 = new Argument("All soldiers who want to be generals are not cowards. "
				+ "Some soldiers are cowards. Therefore some soldiers don't want to be generals.");

		JudgedStatement js1 = new Proposition("All soldiers who want to be generals are not cowards.");
		JudgedStatement js2 = new Proposition("Some soldiers are cowards.");
		JudgedStatement js3 = new Proposition("Some soldiers don't want to be generals.");

		Concept c1 = new Concept("all");
		Concept c2 = new Concept("some");

		Condition cd1 = new QuantifiersCondition(c1, QuantifiersType.UNIVERSAL);
		Condition cd2 = new QuantifiersCondition(c2, QuantifiersType.PARTICULAR);

		js1.addCondition(cd1);
		js2.addCondition(cd2);
		js3.addCondition(cd2);
		a1.addCondition(cd1, cd2);
		a1.addJudgedStatement(js1, js2, js3);



		Root root = new Root(a1);
		System.out.println();
		root.marshalToXml(System.out);
	}

}
