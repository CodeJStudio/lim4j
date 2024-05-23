package com.codejstudio.lim.samples;

import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.argument.Argument;
import com.codejstudio.lim.pojo.argument.Validity;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.relation.BaseRelation;
import com.codejstudio.lim.pojo.relation.EquivalenceRelation;
import com.codejstudio.lim.pojo.statement.JudgedStatement;
import com.codejstudio.lim.pojo.statement.JudgedStatementGroup;
import com.codejstudio.lim.pojo.statement.Proposition;
import com.codejstudio.lim.pojo.statement.Truth;

/**
 * sample: Example20_1 (Example9_1 in Figure 42) 
 * in "Theory of Logical Information Model & Logical Information Network" 
 * from https://github.com/jhjiang/lim_lin
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.Root
 * @see     com.codejstudio.lim.pojo.argument.Argument
 * @see     com.codejstudio.lim.pojo.argument.Validity
 * @see     com.codejstudio.lim.pojo.attribute.DefaultAttribute
 * @see     com.codejstudio.lim.pojo.concept.Concept
 * @see     com.codejstudio.lim.pojo.relation.BaseRelation
 * @see     com.codejstudio.lim.pojo.relation.EquivalenceRelation
 * @see     com.codejstudio.lim.pojo.statement.JudgedStatement
 * @see     com.codejstudio.lim.pojo.statement.JudgedStatementGroup
 * @see     com.codejstudio.lim.pojo.statement.Proposition
 * @see     com.codejstudio.lim.pojo.statement.Truth
 * @since   lim4j_v1.0.0
 */
public class Example20_1 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode();

		doXmlMarshal();
//		HelloWorld.doXmlUnmarshal(Example20_1.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		Argument a1 = new Argument("All mammals have lungs. All whales are mammals. Therefore all whales have lungs.");
		a1.setValidity(new Validity(1));
		a1.addDefaultAttribute(new Proposition("This is a syllogism in the form of 'all M is P, all S is M, so all S is P'."));

		JudgedStatement js1 = new Proposition("All mammals have lungs.");
		JudgedStatement js2 = new Proposition("All whales are mammals.");
		JudgedStatement js3 = new Proposition("All whales have lungs.");

		js1.setTruth(new Truth(1));
		js2.setTruth(new Truth(1));
		js3.setTruth(new Truth(1));

		JudgedStatementGroup jsg1 = new JudgedStatementGroup(js1, js2, js3);

		Concept c1 = new Concept("All");
		Concept c2 = new Concept("have lungs");
		Concept c3 = new Concept("lungs");
		Concept c4 = new Concept("whales");
		Concept c5 = new Concept("mammals");
		Concept c6 = new Concept("have");
		Concept c7 = new Concept("are");

		js1.addConceptWithPosition(c1, c5, c2);
		js2.addConceptWithPosition(c1, c4, c7, c5);
		js3.addConceptWithPosition(c1, c4, c2);
		c2.addSubConceptWithPosition(c6, c3);

		BaseRelation br1 = new EquivalenceRelation(a1, jsg1);



		Root root = new Root(a1, jsg1, br1);
		System.out.println();
		root.marshalToXml(System.out);
	}

}
