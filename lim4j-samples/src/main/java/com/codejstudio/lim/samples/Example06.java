package com.codejstudio.lim.samples;

import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.InformationSection;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.argument.Argument;
import com.codejstudio.lim.pojo.statement.Definition;
import com.codejstudio.lim.pojo.statement.JudgedStatement;
import com.codejstudio.lim.pojo.statement.Narration;
import com.codejstudio.lim.pojo.statement.Proposition;

/**
 * sample: Example06 
 * in "Theory of Logical Information Model & Logical Information Network"
 * from https://github.com/jhjiang/lim_lin
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.Root
 * @see     com.codejstudio.lim.pojo.argument.Argument
 * @see     com.codejstudio.lim.pojo.statement.Definition
 * @see     com.codejstudio.lim.pojo.statement.JudgedStatement
 * @see     com.codejstudio.lim.pojo.statement.Narration
 * @see     com.codejstudio.lim.pojo.statement.Proposition
 * @since   lim4j_v1.0.0
 */
public class Example06 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode();

		doXmlMarshal();
//		HelloWorld.doXmlUnmarshal(Example06.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		InformationSection is1 = new InformationSection("How should we define the word 'planet'? "
				+ "① Planets are simply bodies in orbit around the sun and that there are nine planets in the solar system -- of which the smallest is Pluto, made of unusual stuff, with an unusual orbit, and most distant from the sun. "
				+ "② But other bodies, larger than Pluto and oddly shaped, have been recently discovered orbiting the sun. Are they also planets? Why not? "
				+ "③ Older definitions had become conceptually inadequate. "
				+ "④ An intense controversy within the International Astronomical Union (IAU), still not fully resolved, has recently resulted in a new definition of 'planet' .... "
				+ "⑤ Needed were definitions that would accommodate new discoveries as well as old, while maintaining a consistent and fully intelligible account of the entire system. "
				+ "⑥ Such definitions were adopted by the IAU in 2006. "
				+ "A planet is a celestial body that, within the Solar System, (1) is in orbit around the Sun; and (2) has sufficient mass for its self-gravity to overcome rigid body forces so that it assumes a hydrostatic equilibrium (nearly round) shape; and (3) has cleared the neighborhood around its orbit. "
				+ "In a system other than our solar system, the new definition requires that the body (1) be in orbit around a star or stellar remnant; and (2) have a mass below the limiting mass for thermonuclear fusion of deuterium; and (3) be above the minimum mass/size requirement for planetary status in the solar system.");

		Argument a1 = new Argument("① Planets are simply bodies in orbit around the sun and that there are nine planets in the solar system -- of which the smallest is Pluto, made of unusual stuff, with an unusual orbit, and most distant from the sun. "
				+ "② But other bodies, larger than Pluto and oddly shaped, have been recently discovered orbiting the sun.... "
				+ "③ Older definitions had become conceptually inadequate.");

		Argument a2 = new Argument("③ Older definitions had become conceptually inadequate.... "
				+ "⑤ Needed were definitions that would accommodate new discoveries as well as old, while maintaining a consistent and fully intelligible account of the entire system. "
				+ "⑥ Such definitions were adopted by the IAU in 2006. "
				+ "A planet is a celestial body that, within the Solar System, (1) is in orbit around the Sun; and (2) has sufficient mass for its self-gravity to overcome rigid body forces so that it assumes a hydrostatic equilibrium (nearly round) shape; and (3) has cleared the neighborhood around its orbit. "
				+ "In a system other than our solar system, the new definition requires that the body (1) be in orbit around a star or stellar remnant; and (2) have a mass below the limiting mass for thermonuclear fusion of deuterium; and (3) be above the minimum mass/size requirement for planetary status in the solar system.");

		JudgedStatement js1 = new Definition("Planets are simply bodies in orbit around the sun and that there are nine planets in the solar system -- of which the smallest is Pluto, made of unusual stuff, with an unusual orbit, and most distant from the sun.");
		JudgedStatement js2 = new Proposition("Other bodies, larger than Pluto and oddly shaped, have been recently discovered orbiting the sun.");
		JudgedStatement js3 = new Proposition("Older definitions(①) had become conceptually inadequate.");
		JudgedStatement js4 = new Narration("An intense controversy within the International Astronomical Union (IAU), still not fully resolved, has recently resulted in a new definition of 'planet'.");
		JudgedStatement js5 = new Proposition("Needed were definitions that would accommodate new discoveries as well as old, while maintaining a consistent and fully intelligible account of the entire system.");
		JudgedStatement js6 = new Definition("Such definitions were adopted by the IAU in 2006. "
				+ "A planet is a celestial body that, within the Solar System, (1) is in orbit around the Sun; and (2) has sufficient mass for its self-gravity to overcome rigid body forces so that it assumes a hydrostatic equilibrium (nearly round) shape; and (3) has cleared the neighborhood around its orbit. "
				+ "In a system other than our solar system, the new definition requires that the body (1) be in orbit around a star or stellar remnant; and (2) have a mass below the limiting mass for thermonuclear fusion of deuterium; and (3) be above the minimum mass/size requirement for planetary status in the solar system.");

		a1.addConclusionAndEvidence(js3, js1, js2);
		a2.addConclusionAndEvidence(js6, js3, js5);

		is1.addSubInformationElement(a1, a2, js1, js2, js3, js4, js5, js6);



		Root root = new Root(is1);
		System.out.println();
		root.marshalToXml(System.out);
	}

}
