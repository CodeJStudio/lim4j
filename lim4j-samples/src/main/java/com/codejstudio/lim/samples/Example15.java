package com.codejstudio.lim.samples;

import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.InformationSection;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.comment.Comment;
import com.codejstudio.lim.pojo.entity.Entity;
import com.codejstudio.lim.pojo.role.Observer;
import com.codejstudio.lim.pojo.role.Proposer;
import com.codejstudio.lim.pojo.statement.JudgedStatement;
import com.codejstudio.lim.pojo.statement.Proposition;

/**
 * sample: Example15 
 * in "Theory of Logical Information Model & Logical Information Network"
 * from https://github.com/jhjiang/lim_lin
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.Root
 * @see     com.codejstudio.lim.pojo.InformationSection
 * @see     com.codejstudio.lim.pojo.comment.Comment
 * @see     com.codejstudio.lim.pojo.entity.Entity
 * @see     com.codejstudio.lim.pojo.role.Observer
 * @see     com.codejstudio.lim.pojo.role.Proposer
 * @see     com.codejstudio.lim.pojo.statement.JudgedStatement
 * @see     com.codejstudio.lim.pojo.statement.Proposition
 * @since   lim4j_v1.0.0
 */
public class Example15 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode();

		doXmlMarshal();
//		HelloWorld.doXmlUnmarshal(Example15.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		InformationSection is1 = new InformationSection("Einstein and Bohr were two giants in the field of quantum mechanics, and several academic debates between them had great influence in the history of science. "
				+ "As early as 1924, Bohr co-authored a paper entitled 'Über die Quantentheorie der Strahlung' (About the Quantum Theory of Radiation), which proposed a highly controversial point of view that "
				+ "energy and momentum need not be conserved in a single microscopic interaction process, but only in a statistical sense. "
				+ "In this regard, Einstein wrote in a letter to Born, another physicist: 'Bohr's ideas about radiation are very interesting. "
				+ "But ... I find it utterly unacceptable the idea that when an electron is irradiated by radiation, not only the moment of its jump, but also the direction in which it jumps, are chosen by its own free will.'");

		InformationSection is2 = new InformationSection("@paper: 'Über die Quantentheorie der Strahlung'");
		InformationSection is3 = new InformationSection("Bohr's ideas about radiation are very interesting. "
				+ "But ... I find it utterly unacceptable the idea that when an electron is irradiated by radiation, not only the moment of its jump, but also the direction in which it jumps, are chosen by its own free will.");

		JudgedStatement js1 = new Proposition("Energy and momentum need not be conserved in a single microscopic interaction process, but only in a statistical sense.");

		Entity e1 = new Entity("Bohr");
		Entity e2 = new Entity("Einstein");

		Proposer p1 = new Proposer(e1);

		e2.propose(is3);

		Comment cm1 = new Comment(is3);

		Observer o1 = new Observer(e2, cm1);

		is2.setProposer(p1);
		is2.addObserver(o1);
		is2.addSubInformationElement(js1);

		is1.addSubInformationElement(is2);



		Root root = new Root(is1);
		System.out.println();
		root.marshalToXml(System.out);
	}

}
