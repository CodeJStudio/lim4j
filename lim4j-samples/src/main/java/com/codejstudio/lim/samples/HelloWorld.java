package com.codejstudio.lim.samples;

import java.net.URL;

import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.relation.BaseRelation;
import com.codejstudio.lim.pojo.relation.EquivalenceRelation;
import com.codejstudio.lim.pojo.statement.Statement;

/**
 * sample: HelloWorld
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.Root
 * @see     com.codejstudio.lim.pojo.concept.Concept
 * @see     com.codejstudio.lim.pojo.relation.BaseRelation
 * @see     com.codejstudio.lim.pojo.relation.EquivalenceRelation
 * @see     com.codejstudio.lim.pojo.statement.Statement
 * @since   lim4j_v1.0.0
 */
public class HelloWorld {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode();

		doXmlMarshal();
//		doXmlUnmarshal(HelloWorld.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		Statement s1 = new Statement("Hello, world!");

		Concept c1 = new Concept("hello");
		Concept c2 = new Concept(",");
		Concept c3 = new Concept("world");
		Concept c4 = new Concept("!");
		Concept c5 = new Concept("H");
		Concept c6 = new Concept("h");
		Concept c7 = new Concept("e");
		Concept c8 = new Concept("l");
		Concept c9 = new Concept("o");
		Concept c10 = new Concept("w");
		Concept c11 = new Concept("r");
		Concept c12 = new Concept("d");
		Concept c13 = null;

		s1.addConcept(c1, c2, c3, c4, c5); //No position of sub-concepts sample
		s1.addConcept(c13); //NullPointerException tolerance sample
		c1.addSubConceptWithPosition(c6, c7, c8, c9); //Auto positions of sub-concepts sample
		c1.addSubConcept(); //NullPointerException tolerance sample
		c3.addSubConcept(new Integer[] {7, 8, 9, 10, 11}, c10, c9, c11, c8, c12); //Manual positions of sub-concepts sample

		c5.addIncompatibleAttribute("case", new Concept("Uppercase"));
		c5.addCompatibleAttribute("case", new Concept("大写"));

		BaseRelation br1 = new EquivalenceRelation(c5, c6);



		//print out all the elements in XML mode
		Root root = new Root(s1, br1, null); //NullPointerException tolerance sample
//		root.removeElement(br1);
		System.out.println();
		root.marshalToXml(System.out);

		//set OUTPUT_XML_FILE_PATH first, before opening below comment code
//		String OUTPUT_XML_FILE_PATH = "";//e.g. "d:/" + HelloWorld.class.getSimpleName() + ".xml"
//		root.marshalToXml(true, new BufferedOutputStream(new FileOutputStream(OUTPUT_XML_FILE_PATH)));
	}

	static void doXmlUnmarshal(final String clazzName) throws Exception {
		String inputXmlFilePath = "samples/" + clazzName + ".xml";
		URL url = Thread.currentThread().getContextClassLoader().getResource(inputXmlFilePath);
		Root root = Root.unmarshalFromXml(url);
		root.redecorate();
		System.out.println();
		root.marshalToXml(System.out);
	}

}
