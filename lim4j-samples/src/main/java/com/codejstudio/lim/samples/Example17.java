package com.codejstudio.lim.samples;

import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.argument.Argument;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.relation.BaseRelation;
import com.codejstudio.lim.pojo.relation.EquivalenceRelation;
import com.codejstudio.lim.pojo.statement.JudgedStatement;
import com.codejstudio.lim.pojo.statement.Proposition;
import com.codejstudio.lim.pojo.statement.Statement;

/**
 * sample: Example17 
 * in "Theory of Logical Information Model & Logical Information Network"
 * from https://github.com/jhjiang/lim_lin
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.Root
 * @see     com.codejstudio.lim.pojo.argument.Argument
 * @see     com.codejstudio.lim.pojo.condition.Condition
 * @see     com.codejstudio.lim.pojo.relation.BaseRelation
 * @see     com.codejstudio.lim.pojo.relation.EquivalenceRelation
 * @see     com.codejstudio.lim.pojo.statement.JudgedStatement
 * @see     com.codejstudio.lim.pojo.statement.Proposition
 * @see     com.codejstudio.lim.pojo.statement.Statement
 * @since   lim4j_v1.0.0
 */
public class Example17 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode();

		doXmlMarshal();
//		HelloWorld.doXmlUnmarshal(Example17.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		Argument a1 = new Argument("At standard atmospheric pressure, when the temperature drops to 0 ℃, water will gradually condense, so then water will go from liquid to solid.");
		Argument a2 = new Argument("Because water will gradually condense when it is at standard atmospheric pressure and its temperature drops to 0 ℃, water will go from liquid to solid.");

		Statement s1 = new Statement("At standard atmospheric pressure");
		Statement s2 = new Statement("the temperature drops to 0 ℃");
		Statement s3 = new Statement("water will gradually condense");
		Statement s4 = new Statement("water will go from liquid to solid");

		JudgedStatement js1 = new Proposition("at standard atmospheric pressure, when the temperature drops to 0 ℃, water will gradually condense");
		JudgedStatement js2 = new Proposition(s3);
		JudgedStatement js3 = new Proposition(s4);

		Condition cd1 = new Condition(s1);
		Condition cd2 = new Condition(s2);
		Condition cd3 = new Condition(cd1, true);
		Condition cd4 = new Condition(cd2, true);

		js1.addCondition(cd1, cd2);
		js3.addCondition(cd3, cd4);

		a1.addSubInformationElement(s1, s2, s3, s4);
		a1.addConclusionAndEvidence(js3, js1);

		a2.addSubInformationElement(s3, s1, s2, s4);
		a2.addConclusionAndEvidence(js3, js2);
		a2.addCondition(cd1, cd2);

		BaseRelation br1 = new EquivalenceRelation(a1, a2);



		Root root = new Root(br1);
		System.out.println();
		root.marshalToXml(System.out);
	}

}
