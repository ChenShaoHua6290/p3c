package com.alibaba.p3c.pmd.lang.java.rule.extend;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTStatementExpression;
import net.sourceforge.pmd.lang.java.ast.ASTVariableDeclarator;
import net.sourceforge.pmd.lang.java.ast.ASTVariableInitializer;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;
import org.jaxen.JaxenException;

import java.util.List;
import java.util.Objects;

/**
 * @author ChenSH
 * @date 2019/11/29 17:56
 */
public class StringSplitRule extends AbstractAliRule {
    @Override
    public Object visit(ASTVariableInitializer node, Object data) {
        /* 获取方法前缀   s.split()  | "adb".split() */
        String prefix = "Expression/PrimaryExpression/PrimaryPrefix[1]/Name[ends-with(@Image,'.split')] | " +
                "Expression/PrimaryExpression/PrimarySuffix[1][@Image='split'] ";
        //方法后缀
        String suffix = "//PrimarySuffix/Arguments/ArgumentList/Expression/PrimaryExpression" +
                "/PrimaryPrefix/Literal[@Image='\".\"'] " +
                "| //PrimarySuffix/Arguments/ArgumentList/Expression/PrimaryExpression/PrimaryPrefix/Literal[@Image='\"|\"'] ";
        try {
            List<Node> nodesWithPath = node.findChildNodesWithXPath(prefix);
            if (Objects.nonNull(nodesWithPath) && nodesWithPath.size() == 1) {
                List<Node> childNodesWithPath = node.findChildNodesWithXPath(suffix);
                if (Objects.nonNull(childNodesWithPath) && childNodesWithPath.size() == 1) {
                    addViolationWithMessage(data, node, "java.extend.StringSplitRule.rule.msg");
                }
            }
        } catch (JaxenException e) {
            e.printStackTrace();
        }
        return super.visit(node, data);
    }
}
