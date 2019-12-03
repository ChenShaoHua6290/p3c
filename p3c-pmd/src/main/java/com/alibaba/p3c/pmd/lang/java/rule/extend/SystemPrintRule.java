package com.alibaba.p3c.pmd.lang.java.rule.extend;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTStatementExpression;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;
import org.jaxen.JaxenException;

import java.util.List;
import java.util.Objects;

/**
 * @author ChenSH
 * @date 2019/11/29 17:56
 */
public class SystemPrintRule extends AbstractAliRule {
    @Override
    public Object visit(ASTStatementExpression node, Object data) {
        String prefix = "//PrimaryExpression/PrimaryPrefix[1]/Name[@Image='System.out.println'] | " +
                "//PrimaryExpression/PrimaryPrefix[1]/Name[@Image='System.out.print']";
        try {
            List<Node> nodesWithPath = node.findChildNodesWithXPath(prefix);
            if (Objects.nonNull(nodesWithPath) && nodesWithPath.size()>0) {
                addViolationWithMessage(data,nodesWithPath.get(0),"java.extend.SystemPrintRule.rule.msg");
            }
        } catch (JaxenException e) {
            e.printStackTrace();
        }
        return super.visit(node, data);
    }
}
