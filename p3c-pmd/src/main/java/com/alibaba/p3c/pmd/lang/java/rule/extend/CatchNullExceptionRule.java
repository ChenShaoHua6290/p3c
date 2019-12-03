package com.alibaba.p3c.pmd.lang.java.rule.extend;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

import java.util.List;

/**
 * @author ChenSH
 * @date 2019/11/29 17:56
 */

public class CatchNullExceptionRule extends AbstractAliRule {

    private static final String NULL_EXCEPTHION = "//CatchStatement/FormalParameter/Type/ReferenceType/ClassOrInterfaceType[@Image='NullPointerException']";

    @Override
    public Object visit(ASTMethodDeclaration node, Object data) {
        try {
            List<Node> astPrimitiveTypeList = node.findChildNodesWithXPath(NULL_EXCEPTHION);
            if (astPrimitiveTypeList != null && astPrimitiveTypeList.size() > 0) {
                addViolationWithMessage(data, astPrimitiveTypeList.get(0), "java.extend.CatchNullExceptionRule.rule.msg");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.visit(node, data);
    }
}
