package com.codejstudio.lim.samples;

import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.concept.ConceptGroup;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.condition.QuantifiersCondition;
import com.codejstudio.lim.pojo.condition.QuantifiersCondition.QuantifiersType;
import com.codejstudio.lim.pojo.statement.Definition;

/**
 * sample: Example13 
 * in "Theory of Logical Information Model & Logical Information Network"
 * from https://github.com/jhjiang/lim_lin
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.Root
 * @see     com.codejstudio.lim.pojo.attribute.Attribute
 * @see     com.codejstudio.lim.pojo.attribute.DefaultAttribute
 * @see     com.codejstudio.lim.pojo.concept.Concept
 * @see     com.codejstudio.lim.pojo.concept.ConceptGroup
 * @see     com.codejstudio.lim.pojo.condition.Condition
 * @see     com.codejstudio.lim.pojo.condition.QuantifiersCondition
 * @see     com.codejstudio.lim.pojo.statement.Definition
 * @since   lim4j_v1.0.0
 */
public class Example13 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode();

		doXmlMarshal();
//		HelloWorld.doXmlUnmarshal(Example13.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		Definition df1 = new Definition("Lion is a (kind of) Felidae animal.");
		Definition df2 = new Definition("Lu Xun was a litterateur.");
		Definition df3 = new Definition("A motor vehicle is a vehicle driven on the road and powered by the movement of machines.");
		Definition df4 = new Definition("The first requirement for a planet in a celestial system is to orbit a star or stellar debris.");

		Concept c1 = new Concept("lion");
		Concept c2 = new Concept("Felidae animal");
		Concept c3 = new Concept("Lu Xun");
		Concept c4 = new Concept("litterateur");
		Concept c5 = new Concept("motor vehicle");

		Concept c6 = new Concept("vehicle driven on the road and powered by the movement of machines");
		Concept c7 = new Concept("the first requirement for a planet");
		Concept c8 = new Concept("to orbit a star or stellar debris");

		Concept c9 = new Concept("vehicle");
		Concept c10 = new Concept("driven on the road");
		Concept c11 = new Concept("powered by the movement of machines");
		Concept c12 = new ConceptGroup(c9, c10, c11);

		Concept c13 = new Concept("the first requirement");
		Concept c14 = new Concept("planet");
		Concept c15 = new ConceptGroup(c13, c14);

		Concept c16 = new Concept("orbit");
		Concept c17 = new Concept("star");
		Concept c18 = new Concept("stellar debris");
		Concept c19 = new ConceptGroup(c16, c17, c18);

		Condition cd1 = new QuantifiersCondition(null, QuantifiersType.SINGULAR, true);
		Condition cd2 = new Condition(c10);
		Condition cd3 = new Condition(c11);

		c3.addCondition(cd1);
		c9.addCondition(cd2, cd3);
		c14.addDefaultAttribute(c13);

		c6.addSubConcept(c12);
		c7.addSubConcept(c15);
		c8.addSubConcept(c19);

		df1.setDefiniendumAndDefiniens(c1, c2);
		df2.setDefiniendumAndDefiniens(c3, c4);
		df3.setDefiniendumAndDefiniens(c5, c6);
		df4.setDefiniendumAndDefiniens(c7, c8);



		Root root = new Root(df1, df2, df3, df4);
		System.out.println();
		root.marshalToXml(System.out);
	}

}
