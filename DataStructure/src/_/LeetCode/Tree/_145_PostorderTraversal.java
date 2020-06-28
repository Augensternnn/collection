package _.LeetCode.Tree;


import java.util.LinkedList;
import java.util.List;

/**
 * 145.二叉树的后序遍历
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 */
public class _145_PostorderTraversal {
    /**
     * 递归
     */
    List<Integer> list = new LinkedList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root==null)
            return list;
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        list.add(root.val);
        return list;
    }

    /**
     * 非递归
     */
    public List<Integer> postorderTraversal_(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if(root==null)
            return output;
        stack.add(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pollLast();
            output.addFirst(node.val);
            if(node.left!=null)
                stack.add(node.left);
            if(node.right!=null)
                stack.add(node.right);
        }
        return output;
    }
}
