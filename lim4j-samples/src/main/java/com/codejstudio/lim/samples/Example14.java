package com.codejstudio.lim.samples;

import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.InformationSection;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.doubt.Doubt;
import com.codejstudio.lim.pojo.doubt.Doubt.DoubtType;
import com.codejstudio.lim.pojo.doubt.Explanation;
import com.codejstudio.lim.pojo.entity.Entity;
import com.codejstudio.lim.pojo.role.Proposer;
import com.codejstudio.lim.pojo.statement.HypotheticalProposition;
import com.codejstudio.lim.pojo.statement.JudgedStatement;
import com.codejstudio.lim.pojo.statement.Statement;

/**
 * sample: Example14 
 * in "Theory of Logical Information Model & Logical Information Network"
 * from https://github.com/jhjiang/lim_lin
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.Root
 * @see     com.codejstudio.lim.pojo.InformationSection
 * @see     com.codejstudio.lim.pojo.doubt.Doubt
 * @see     com.codejstudio.lim.pojo.doubt.Explanation
 * @see     com.codejstudio.lim.pojo.entity.Entity
 * @see     com.codejstudio.lim.pojo.role.Proposer
 * @see     com.codejstudio.lim.pojo.statement.HypotheticalProposition
 * @see     com.codejstudio.lim.pojo.statement.JudgedStatement
 * @see     com.codejstudio.lim.pojo.statement.Statement
 * @since   lim4j_v1.0.0
 */
public class Example14 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode();

		doXmlMarshal();
//		HelloWorld.doXmlUnmarshal(Example14.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		InformationSection is1 = new InformationSection("Salviati: \"... How far may one go without geometry for a good philosophical exploration of the natural world?\"\n"
				+ "Sagredo: \"... (And it must be admitted that) trying to deal with physical problems without geometry is attempting the impossible.\"");

		Statement s1 = new HypotheticalProposition("One may go far without geometry for a good philosophical exploration of the natural world.");

		Doubt d1 = new Doubt("How far may one go without geometry for a good philosophical exploration of the natural world?");

		d1.setTarget(s1);
		d1.setDoubtType(DoubtType.HOW_FAR);

		JudgedStatement js1 = new HypotheticalProposition("Trying to deal with physical problems without geometry is attempting the impossible.");

		Explanation ex1 = new Explanation(d1, js1);

		Entity e1 = new Entity("Salviati");
		Entity e2 = new Entity("Sagredo");

		Proposer p1 = new Proposer(e1);
		Proposer p2 = new Proposer(e2);

		d1.setProposer(p1);
		ex1.setProposer(p2);

		is1.addSubInformationElement(d1, ex1);



		Root root = new Root(is1);
		System.out.println();
		root.marshalToXml(System.out);
	}

}
