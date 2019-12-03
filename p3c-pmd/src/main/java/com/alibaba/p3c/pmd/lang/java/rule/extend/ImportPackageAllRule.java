package com.alibaba.p3c.pmd.lang.java.rule.extend;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.java.ast.ASTImportDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;


/**
 * @author ChenSH
 * @date 2019/11/29 17:56
 */
public class ImportPackageAllRule extends AbstractAliRule {

    private static final String PARSER_CONFIG_IMPORT_NAME = "*";

    @Override
    public Object visit(ASTImportDeclaration node, Object data) {
       /*判断导入的节点名称是否存在* 号*/
        if (node.getImportedName().contains(PARSER_CONFIG_IMPORT_NAME)) {
            addViolationWithMessage(data,node," import不要使用通配符 * ");
        }
        return super.visit(node, data);
    }
}
