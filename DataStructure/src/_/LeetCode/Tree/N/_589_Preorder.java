package _.LeetCode.Tree.N;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 589.N叉树的前序遍历
 * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
 */
public class _589_Preorder {
    public List<Integer> preorder(Node root) {
        LinkedList<Node> list = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if(root == null)
            return output;
        list.add(root);
        while (!list.isEmpty()){
            Node node = list.removeLast();
            output.add(node.val);
            Collections.reverse(node.children);
            for (Node child : node.children) {
                list.add(child);
            }
        }
        return output;
    }
}
