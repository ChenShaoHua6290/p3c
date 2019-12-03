package com.alibaba.p3c.pmd.lang.java.rule.extend;

import net.sourceforge.pmd.testframework.SimpleAggregatorTst;

/**
 * @Auther: ChenSH
 * @Date: 2019/11/27 16:43
 * @Description:
 */
public class EntityNullRuleTest  extends SimpleAggregatorTst {

    // 加载CLASSPATH下的rulesets/java/ali-oop.xml
    private static final String RULESET = "java-ali-extend";

    @Override
    public void setUp() {
        addRule(RULESET, "EntityNullRule");
//        addRule(RULESET, "CatchNullExceptionRule");
//        addRule(RULESET, "CollectionAndMapInitRule");
//        addRule(RULESET, "ImportPackageAllRule");
//        addRule(RULESET, "MethodParamsNumRule");
//        addRule(RULESET, "StringSplitRule");
//        addRule(RULESET, "SystemPrintRule");
    }

}
