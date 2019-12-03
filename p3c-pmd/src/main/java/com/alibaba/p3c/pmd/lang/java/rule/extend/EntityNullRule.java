package com.alibaba.p3c.pmd.lang.java.rule.extend;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTBlockStatement;
import net.sourceforge.pmd.lang.java.ast.ASTEqualityExpression;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author ChenSH
 * @date 2019/11/29 17:56
 */
public class EntityNullRule extends AbstractAliRule {

    /**
     * private static final String METHOD_RETURN_OBJECT_XPATH = "Statement/IfStatement/Expression/EqualityExpression/PrimaryExpression/PrimaryPrefix/Literal/NullLiteral";
     */
    private static final String EQUALS_TO="==";
    private static final String NOT_EQUALS_TO="!=";
    private static final String NULL_DEFINITION="PrimaryExpression/PrimaryPrefix/Literal/NullLiteral";
    @Override
    public Object visit(ASTEqualityExpression node, Object data) {
        try {
            String image = node.getImage();
            if (EQUALS_TO.equals(image) || NOT_EQUALS_TO.equals(image)){
                List<Node> path = node.findChildNodesWithXPath(NULL_DEFINITION);
                if (path!=null && path.size()>0){
                    addViolationWithMessage(data,node,"java.extend.EntityNullRule.rule.msg");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.visit(node,data);
    }


}
