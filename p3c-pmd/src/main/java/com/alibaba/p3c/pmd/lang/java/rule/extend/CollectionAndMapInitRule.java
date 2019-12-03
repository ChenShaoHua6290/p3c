/*
 * Copyright 1999-2017 Alibaba Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.p3c.pmd.lang.java.rule.extend;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;
import org.jaxen.JaxenException;

import java.util.Arrays;
import java.util.List;

/**
 * @author ChenSH
 * @date 2019/11/29 17:56
 */
public class CollectionAndMapInitRule extends AbstractAliRule {


    private final static List<String> COLLECTION_MAP_LIST = Arrays.asList("HashMap", "ConcurrentHashMap", "LinkedHashMap", "EnumMap", "TreeMap", "LinkedList", "ArrayList");


    @Override
    public Object visit(ASTClassOrInterfaceDeclaration node, Object data) {
        try {
            // 找到方法中集合初始化的地方
            for (String collectionType : COLLECTION_MAP_LIST) {
                String collectionArgXpath =
                        "//AllocationExpression/ClassOrInterfaceType[@Image='" + collectionType + "']";
                List<Node> argumentsNodes = node.findChildNodesWithXPath(collectionArgXpath);
                for (Node argNode : argumentsNodes) {
                    if (argNode.getFirstParentOfType(ASTMethodDeclaration.class) == null) {
                        continue;
                    }
                    addViolationWithMessage(data, argNode, "java.extend.CollectionAndMapInitRule.rule.msg",new Object[]{collectionType});
                }
            }
        } catch (JaxenException e) {
            e.printStackTrace();
        }
        return super.visit(node, data);
    }

}
