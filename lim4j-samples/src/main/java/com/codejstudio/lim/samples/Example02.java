package com.codejstudio.lim.samples;

import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.argument.Argument;
import com.codejstudio.lim.pojo.statement.JudgedStatement;
import com.codejstudio.lim.pojo.statement.Proposition;

/**
 * sample: Example02 
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
public class Example02 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode();

		doXmlMarshal();
//		HelloWorld.doXmlUnmarshal(Example02.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		Argument a1 = new Argument("① Because the greatest mitochondrial variations occurred in African people, "
				+ "② scientists concluded that they had the longest evolutionary history, "
				+ "③ indicating a probable African origin for modern humans.");

		Argument a2 = new Argument("① Because the greatest mitochondrial variations occurred in African people, "
				+ "② scientists concluded that they had the longest evolutionary history,");

		Argument a3 = new Argument("② (scientists concluded that )they had the longest evolutionary history, "
				+ "③ indicating a probable African origin for modern humans.");

		JudgedStatement js1 = new Proposition("the greatest mitochondrial variations occurred in African people,");
		JudgedStatement js2 = new Proposition("scientists concluded that they had the longest evolutionary history,");
		JudgedStatement js3 = new Proposition("(there is )a probable African origin for modern humans.");

		a2.addConclusionAndEvidence(js2, js1);
		a3.addConclusionAndEvidence(js3, js2);
		a1.addSubArgument(a2, a3);



		Root root = new Root(a1);
		System.out.println();
		root.marshalToXml(System.out);
	}

}
