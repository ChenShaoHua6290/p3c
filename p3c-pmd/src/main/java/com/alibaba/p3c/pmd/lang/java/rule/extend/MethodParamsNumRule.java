package com.alibaba.p3c.pmd.lang.java.rule.extend;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.java.ast.ASTFormalParameters;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * @author ChenSH
 * @date 2019/11/29 17:56
 */
public class MethodParamsNumRule extends AbstractAliRule {

    private static final int PARAMSNUM = 5;

    @Override
    public Object visit(ASTFormalParameters node, Object data) {
        if (node.jjtGetNumChildren()>PARAMSNUM) {
            addViolationWithMessage(data,node,"java.extend.MethodParamsNumRule.rule.msg");
        };
        return super.visit(node, data);
    }

}
