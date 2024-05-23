package com.codejstudio.lim.samples;

import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.argument.Argument;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.condition.PremiseCondition;
import com.codejstudio.lim.pojo.statement.Statement;

/**
 * sample: Example11 
 * in "Theory of Logical Information Model & Logical Information Network"
 * from https://github.com/jhjiang/lim_lin
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.Root
 * @see     com.codejstudio.lim.pojo.argument.Argument
 * @see     com.codejstudio.lim.pojo.condition.Condition
 * @see     com.codejstudio.lim.pojo.condition.PremiseCondition
 * @see     com.codejstudio.lim.pojo.statement.Statement
 * @since   lim4j_v1.0.0
 */
public class Example11 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode();

		doXmlMarshal();
//		HelloWorld.doXmlUnmarshal(Example11.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		Argument a1 = new Argument("If the opponent of the death penalty is incorrect in his belief that the death penalty doesn't deter (homicide), "
				+ "he is responsible for the murder of innocent individuals who would not have been murdered if the death penalty had been invoked.");

		Statement s1 = new Statement("If the opponent of the death penalty is incorrect in his belief that the death penalty doesn't deter (homicide),");
		Statement s2 = new Statement("Protecting the lives of innocent individuals from murder justifies the execution of murderers "
				+ "if other murderers are then deterred by the fear of execution.");

		Condition cd1 = new PremiseCondition(s1);
		Condition cd2 = new PremiseCondition(s2, true);

		a1.addCondition(cd1, cd2);



		Root root = new Root(a1);
		System.out.println();
		root.marshalToXml(System.out);
	}

}
