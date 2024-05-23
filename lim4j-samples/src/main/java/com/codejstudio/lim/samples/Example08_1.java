package com.codejstudio.lim.samples;

import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.statement.JudgedStatement;
import com.codejstudio.lim.pojo.statement.Proposition;

/**
 * sample: Example08_1 
 * in "Theory of Logical Information Model & Logical Information Network"
 * from https://github.com/jhjiang/lim_lin
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.Root
 * @see     com.codejstudio.lim.pojo.condition.Condition
 * @see     com.codejstudio.lim.pojo.condition.ConditionGroup
 * @see     com.codejstudio.lim.pojo.statement.JudgedStatement
 * @see     com.codejstudio.lim.pojo.statement.Proposition
 * @since   lim4j_v1.0.0
 */
public class Example08_1 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode();

		doXmlMarshal();
//		HelloWorld.doXmlUnmarshal(Example08_1.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		JudgedStatement js1 = new Proposition("The total amount of domestic oil consumption in this year has increased 10% from last year.");

		Concept c1 = new Concept("in this year");
		Concept c2 = new Concept("from last year");
		Concept c3 = new Concept("domestic");

		Condition cd1 = new Condition(c1);
		Condition cd2 = new Condition(c2);
		Condition cd3 = new Condition(c3);
		Condition cd4 = new ConditionGroup(cd1, cd2, cd3);

		js1.addCondition(cd4);



		Root root = new Root(js1);
		System.out.println();
		root.marshalToXml(System.out);
	}

}
