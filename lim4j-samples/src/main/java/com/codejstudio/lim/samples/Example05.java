package com.codejstudio.lim.samples;

import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.statement.Definition;

/**
 * sample: Example05 
 * in "Theory of Logical Information Model & Logical Information Network"
 * from https://github.com/jhjiang/lim_lin
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.Root
 * @see     com.codejstudio.lim.pojo.concept.Concept
 * @see     com.codejstudio.lim.pojo.statement.Definition
 * @since   lim4j_v1.0.0
 */
public class Example05 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode();

		doXmlMarshal();
//		HelloWorld.doXmlUnmarshal(Example05.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		Definition df1 = new Definition("Bird is a kind of warm-blooded vertebrate with feathers.");

		Concept c1 = new Concept("bird");
		Concept c2 = new Concept("is (be)");
		Concept c3 = new Concept("a kind of warm-blooded vertebrate with feathers");

		df1.setDefiniendumAndDefiniens(c1, c3, c2);



		Root root = new Root(df1);
		System.out.println();
		root.marshalToXml(System.out);
	}

}
