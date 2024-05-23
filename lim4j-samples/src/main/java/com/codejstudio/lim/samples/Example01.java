package com.codejstudio.lim.samples;

import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.argument.Argument;
import com.codejstudio.lim.pojo.statement.JudgedStatement;
import com.codejstudio.lim.pojo.statement.Proposition;

/**
 * sample: Example01 
 * in "Theory of Logical Information Model & Logical Information Network"
 * from https://github.com/jhjiang/lim_lin
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.Root
 * @see     com.codejstudio.lim.pojo.argument.Argument
 * @see     com.codejstudio.lim.pojo.statement.JudgedStatement
 * @see     com.codejstudio.lim.pojo.statement.Proposition
 * @since   lim4j_v1.0.0
 */
public class Example01 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode();

		doXmlMarshal();
//		HelloWorld.doXmlUnmarshal(Example01.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		Argument a1 = new Argument("① All mammals are warm-blooded animals. "
				+ "② No lizards are warm-blooded animals. "
				+ "③ Therefore all lizards are nonmammals.");

		JudgedStatement js1 = new Proposition("All mammals are warm-blooded animals.");
		JudgedStatement js2 = new Proposition("No lizards are warm-blooded animals.");
		JudgedStatement js3 = new Proposition("All lizards are nonmammals.");

		a1.addConclusionAndEvidence(js3, js1, js2);



		Root root = new Root(a1);
		System.out.println();
		root.marshalToXml(System.out);
	}

}
