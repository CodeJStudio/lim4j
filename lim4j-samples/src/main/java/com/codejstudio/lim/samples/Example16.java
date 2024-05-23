package com.codejstudio.lim.samples;

import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.argument.Argument;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.relation.BaseRelation;
import com.codejstudio.lim.pojo.relation.MappingRelation;
import com.codejstudio.lim.pojo.relation.PredicateMappingRelation;
import com.codejstudio.lim.pojo.relation.RelationGroup;
import com.codejstudio.lim.pojo.statement.JudgedStatement;
import com.codejstudio.lim.pojo.statement.JudgedStatementGroup;
import com.codejstudio.lim.pojo.statement.Proposition;
import com.codejstudio.lim.pojo.statement.Statement;

/**
 * sample: Example16 
 * in "Theory of Logical Information Model & Logical Information Network"
 * from https://github.com/jhjiang/lim_lin
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.Root
 * @see     com.codejstudio.lim.pojo.argument.Argument
 * @see     com.codejstudio.lim.pojo.concept.Concept
 * @see     com.codejstudio.lim.pojo.relation.BaseRelation
 * @see     com.codejstudio.lim.pojo.relation.MappingRelation
 * @see     com.codejstudio.lim.pojo.relation.PredicateMappingRelation
 * @see     com.codejstudio.lim.pojo.relation.RelationGroup
 * @see     com.codejstudio.lim.pojo.statement.JudgedStatement
 * @see     com.codejstudio.lim.pojo.statement.JudgedStatementGroup
 * @see     com.codejstudio.lim.pojo.statement.Proposition
 * @see     com.codejstudio.lim.pojo.statement.Statement
 * @since   lim4j_v1.0.0
 */
public class Example16 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode();

		doXmlMarshal();
//		HelloWorld.doXmlUnmarshal(Example16.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		Argument a1 = new Argument("This earth which we inhabit, and the other planets, Saturn, Jupiter, Mars, Venus, and Mercury .... "
				+ "They all revolve around the sun, ... "
				+ "they borrow all their light from the sun, ... "
				+ "they are all subject to the same law of gravitation .... "
				+ "From all this similitude, it is not unreasonable to think that those planets may, like our earth, be the habitation of various orders of living creatures.");

		Statement s1 = new Statement("This earth which we inhabit, and the other planets, Saturn, Jupiter, Mars, Venus, and Mercury");

		JudgedStatement js1 = new Proposition("They all revolve around the sun,");
		JudgedStatement js2 = new Proposition("they borrow all their light from the sun,");
		JudgedStatement js3 = new Proposition("they are all subject to the same law of gravitation");
		JudgedStatement js4 = new Proposition("From all this similitude, it is not unreasonable to think that "
				+ "those planets may, like our earth, be the habitation of various orders of living creatures.");
		JudgedStatement js5 = new JudgedStatementGroup(js1, js2, js3);

		a1.addSubInformationElement(s1);
		a1.addConclusionAndEvidence(js4, js5);


		Concept c1 = new Concept("the earth");
		Concept c2 = new Concept("other planets, Saturn, Jupiter, Mars, Venus, and Mercury");
		Concept c3 = new Concept("other planets");
		Concept c4 = new Concept("Saturn");
		Concept c5 = new Concept("Jupiter");
		Concept c6 = new Concept("Mars");
		Concept c7 = new Concept("Venus");
		Concept c8 = new Concept("Mercury");
		Concept c9 = new Concept("revolve around the sun");
		Concept c10 = new Concept("borrow all light from the sun");
		Concept c11 = new Concept("be subject to the same law of gravitation");
		Concept c12 = new Concept("be the habitation of various orders of living creatures");

		c2.addSubConcept(c3, c4, c5, c6, c7, c8);

		BaseRelation br1 = new MappingRelation(c1, c2);
		BaseRelation br2 = new PredicateMappingRelation(c1, c2, c9);
		BaseRelation br3 = new PredicateMappingRelation(c1, c2, c10);
		BaseRelation br4 = new PredicateMappingRelation(c1, c2, c11);
		BaseRelation br5 = new PredicateMappingRelation(c1, c2, c12);
		BaseRelation br6 = new RelationGroup(br2, br3, br4, br5);



		Root root = new Root(a1, br1, br6);
		System.out.println();
		root.marshalToXml(System.out);
	}

}
