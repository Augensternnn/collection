package _.LeetCode.Tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 144.二叉树的前序遍历
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 */
public class _144_PreorderTraversal {
    /**
     * 递归
     */
    List<Integer> list = new LinkedList();
    public List<Integer> preorderTraversal_(TreeNode root) {
        if(root==null)
            return list;
        list.add(root.val);
        preorderTraversal_(root.left);
        preorderTraversal_(root.right);
        return list;
    }

    /**
     * 非递归
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            output.add(node.val);
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
        return output;
    }
}
