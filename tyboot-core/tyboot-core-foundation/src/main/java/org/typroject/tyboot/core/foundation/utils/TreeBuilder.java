package org.typroject.tyboot.core.foundation.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by magintursh on 2017/12/3.
 */
public class TreeBuilder {
    public TreeBuilder() {
    }

    public static <N extends TreeNode> Collection<N> bulid(Collection<N> treeNodes, Object root) {
        Collection<N> trees = new TreeSet<>();
        Iterator var3 = treeNodes.iterator();

        while (var3.hasNext()) {
            N treeNode = (N) var3.next();
            if (root.equals(treeNode.getMyParentId())) {
                trees.add(treeNode);
            }
            Iterator var5 = treeNodes.iterator();
            while (var5.hasNext()) {
                N it = (N) var5.next();
                if (it.getMyParentId().equals(treeNode.getMyId())) {
                    if (ValidationUtil.isEmpty(treeNode.getChilds())) {
                        treeNode.setChilds(new TreeSet<>());
                    }

                    treeNode.getChilds().add(it);
                }
            }
        }

        return trees;
    }

    public static <N extends TreeNode> Collection<N> buildByRecursive(Collection<N> treeNodes, Object root) {
        Collection<N> trees = new  TreeSet<>();
        Iterator var3 = treeNodes.iterator();

        while (var3.hasNext()) {
            N treeNode = (N) var3.next();
            if (root.equals(treeNode.getMyParentId())) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }

        return trees;
    }

    private static <N extends TreeNode> N findChildren(N node, Collection<N> treeNodes) {
        Iterator var2 = treeNodes.iterator();

        while (var2.hasNext()) {
            N it = (N) var2.next();
            if (node.getMyId().equals(it.getMyParentId())) {
                if (ValidationUtil.isEmpty(node.getChilds())) {
                    node.setChilds(new TreeSet());
                }
                node.getChilds().add(findChildren(it, treeNodes));
            }
        }
        return node;
    }
}