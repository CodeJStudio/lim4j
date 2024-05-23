# Logical Information Model for Java (LIM4J) v2.0.0

Refer to: 

[Theory of Logical Information Model & Logical Information Network / 《逻辑信息模型与逻辑信息网络》](https://github.com/jhjiang/lim_lin)

[From Logical Information Model, to Logical Information Network, to the realization of Artificial General Intelligence (AGI)](https://www.reddit.com/user/JeffreyJiang/comments/upcloh/from_logical_information_model_to_logical/)

[《从逻辑信息模型，到逻辑信息网络，直至实现通用人工智能》](https://zhuanlan.zhihu.com/p/497443483)

---------

# LIM4J Samples

The code of "Hello World" is as follows:

```java
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
	}
```


Output：

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<lim-elements version="1.0">
    <statement id="4">
        <description>Hello, world!</description>
        <concept-group id="17" type="concept-group"/>
    </statement>
    <concept id="5">
        <description>hello</description>
        <sub-element-positions>
            <item key="0,1" value="10"/>
            <item key="1,1" value="11"/>
            <item key="2,1" value="12"/>
            <item key="3,1" value="12"/>
            <item key="4,1" value="13"/>
        </sub-element-positions>
        <sub-concept-group id="18" type="concept-group"/>
    </concept>
    <concept id="6">
        <description>,</description>
    </concept>
    <concept id="7">
        <description>world</description>
        <sub-element-positions>
            <item key="10,1" value="12"/>
            <item key="11,1" value="16"/>
            <item key="7,1" value="14"/>
            <item key="8,1" value="13"/>
            <item key="9,1" value="15"/>
        </sub-element-positions>
        <sub-concept-group id="19" type="concept-group"/>
    </concept>
    <concept id="8">
        <description>!</description>
    </concept>
    <concept id="9">
        <relation-group id="25" type="relation-group"/>
        <description>H</description>
        <attribute-group id="23" type="attribute-group"/>
    </concept>
    <concept id="10">
        <relation-group id="29" type="relation-group"/>
        <description>h</description>
    </concept>
    <concept id="11">
        <description>e</description>
    </concept>
    <concept id="12">
        <description>l</description>
    </concept>
    <concept id="13">
        <description>o</description>
    </concept>
    <concept id="14">
        <description>w</description>
    </concept>
    <concept id="15">
        <description>r</description>
    </concept>
    <concept id="16">
        <description>d</description>
    </concept>
    <concept id="17" type="concept-group">
        <itx-elements>
            <item key="group_9" id="9" type="concept"/>
            <item key="group_8" id="8" type="concept"/>
            <item key="group_5" id="5" type="concept"/>
            <item key="group_7" id="7" type="concept"/>
            <item key="group_6" id="6" type="concept"/>
        </itx-elements>
    </concept>
    <concept id="18" type="concept-group">
        <itx-elements>
            <item key="group_10" id="10" type="concept"/>
            <item key="group_13" id="13" type="concept"/>
            <item key="group_11" id="11" type="concept"/>
            <item key="group_12" id="12" type="concept"/>
        </itx-elements>
    </concept>
    <concept id="19" type="concept-group">
        <itx-elements>
            <item key="group_15" id="15" type="concept"/>
            <item key="group_16" id="16" type="concept"/>
            <item key="group_13" id="13" type="concept"/>
            <item key="group_14" id="14" type="concept"/>
            <item key="group_12" id="12" type="concept"/>
        </itx-elements>
    </concept>
    <concept id="20">
        <description>Uppercase</description>
    </concept>
    <attribute compatible="true" key="case" id="21">
        <relation-group id="26" type="relation-group"/>
        <value-group id="22" type="generic-element-group"/>
    </attribute>
    <element id="22" type="generic-element-group">
        <itx-elements>
            <item key="group_20" id="20" type="concept"/>
            <item key="group_27" id="27" type="concept"/>
        </itx-elements>
    </element>
    <attribute id="23" type="attribute-group">
        <itx-elements>
            <item key="group_21" id="21" type="attribute"/>
        </itx-elements>
    </attribute>
    <relation id="24" type="affiliation-relation">
        <itx-elements>
            <item key="slave" id="21" type="attribute"/>
            <item key="master" id="9" type="concept"/>
        </itx-elements>
        <primary id="9" type="concept"/>
        <secondary id="21" type="attribute"/>
    </relation>
    <relation id="25" type="relation-group">
        <itx-elements>
            <item key="group_28" id="28" type="equivalence-relation"/>
            <item key="group_24" id="24" type="affiliation-relation"/>
        </itx-elements>
    </relation>
    <relation id="26" type="relation-group">
        <itx-elements>
            <item key="group_24" id="24" type="affiliation-relation"/>
        </itx-elements>
    </relation>
    <concept id="27">
        <description>大写</description>
    </concept>
    <relation id="28" type="equivalence-relation">
        <primary id="9" type="concept"/>
        <secondary id="10" type="concept"/>
    </relation>
    <relation id="29" type="relation-group">
        <itx-elements>
            <item key="group_28" id="28" type="equivalence-relation"/>
        </itx-elements>
    </relation>
</lim-elements>
```
