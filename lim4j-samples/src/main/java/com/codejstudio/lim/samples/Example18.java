package com.codejstudio.lim.samples;

import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.argument.Argument;
import com.codejstudio.lim.pojo.argument.Validity;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.statement.Definition;
import com.codejstudio.lim.pojo.statement.HypotheticalProposition;
import com.codejstudio.lim.pojo.statement.JudgedStatement;
import com.codejstudio.lim.pojo.statement.Statement;

/**
 * sample: Example18 
 * in "Theory of Logical Information Model & Logical Information Network" 
 * from https://github.com/jhjiang/lim_lin
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.Root
 * @see     com.codejstudio.lim.pojo.argument.Argument
 * @see     com.codejstudio.lim.pojo.argument.Validity
 * @see     com.codejstudio.lim.pojo.condition.Condition
 * @see     com.codejstudio.lim.pojo.statement.Definition
 * @see     com.codejstudio.lim.pojo.statement.HypotheticalProposition
 * @see     com.codejstudio.lim.pojo.statement.JudgedStatement
 * @since   lim4j_v1.0.0
 */
public class Example18 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode();

		doXmlMarshal();
//		HelloWorld.doXmlUnmarshal(Example18.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		Argument a1 = new Argument("∵ax²+bx+c=0, ∴x=(-b±√(b²-4ac))/2a");
		Argument a2 = new Argument("∵ax²+bx+c=0, ∴x²+(b/a)x+(c/a)=0");
		Argument a3 = new Argument("∵x²+(b/a)x+(c/a)=0, ∴x²+2(b/2a)x+(b/2a)²=-(c/a)+(b/2a)²");
		Argument a4 = new Argument("∵x²+2(b/2a)x+(b/2a)²=-(c/a)+(b/2a)², ∴(x+(b/2a))²=(b²-4ac)/4a²");
		Argument a5 = new Argument("∵(x+(b/2a))²=(b²-4ac)/4a², ∴x+(b/2a)=√(b²-4ac)/2a");
		Argument a6 = new Argument("∵x+(b/2a)=√(b²-4ac)/2a, ∴x=(-b±√(b²-4ac))/2a");

		JudgedStatement js1 = new Definition("ax²+bx+c=0");
		JudgedStatement js2 = new Definition("x=(-b±√(b²-4ac))/2a");
		JudgedStatement js3 = new Definition("x²+(b/a)x+(c/a)=0");
		JudgedStatement js4 = new Definition("x²+2(b/2a)x+(b/2a)²=-(c/a)+(b/2a)²");
		JudgedStatement js5 = new Definition("(x+(b/2a))²=(b²-4ac)/4a²");
		JudgedStatement js6 = new Definition("x+(b/2a)=±√(b²-4ac)/2a");
		JudgedStatement js7 = new HypotheticalProposition("If ac=bc, and c≠0, then a=b. (cancellation law of multiplication)");
		JudgedStatement js8 = new HypotheticalProposition("If a+c=b+c, then a=b. (cancellation law of addition)");
		JudgedStatement js9 = new Definition("x²+2kx+k²=(x+k)², k∈R");
		JudgedStatement js10 = new HypotheticalProposition("If a²=b², then a=±b.");

		Statement s1 = new Statement("a,b,c∈R, a≠0");

		Condition cd1 = new Condition(s1);

		a2.addConclusionAndEvidence(js3, js1, js7);
		a2.addCondition(cd1);
		a2.setValidity(new Validity(1));
		a3.addConclusionAndEvidence(js4, js3, js8);
		a3.addCondition(cd1);
		a3.setValidity(new Validity(1));
		a4.addConclusionAndEvidence(js5, js4, js9);
		a4.addCondition(cd1);
		a4.setValidity(new Validity(1));
		a5.addConclusionAndEvidence(js6, js5, js10);
		a5.addCondition(cd1);
		a5.setValidity(new Validity(1));
		a6.addConclusionAndEvidence(js2, js6, js8);
		a6.addCondition(cd1);
		a6.setValidity(new Validity(1));

		a1.addConclusionAndEvidence(js2, js1);
		a1.addCondition(cd1);
		a1.addSubArgument(a2, a3, a4, a5, a6);
		a1.setValidity(new Validity(1));



		Root root = new Root(a1);
		System.out.println();
		root.marshalToXml(System.out);
	}

}
