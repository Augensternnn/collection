package _.LeetCode.Tree;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 230.二叉搜索树中第K小的元素
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
 */
public class _230_KthSmallest {
    /**
     *前序遍历
     */
    public int kthSmallest(TreeNode root, int k) {
        if(root == null)
            return 0;
        List<Integer> arr = preOrder(root);
        Collections.sort(arr);
        return arr.get(k-1);
    }
    List<Integer> list = new LinkedList<>();
    private List<Integer> preOrder(TreeNode node){
        if(node == null)
            return list;
        list.add(node.val);
        preOrder(node.left);
        preOrder(node.right);
        return list;
    }

    /**
     *中序遍历
     * 二叉搜索树的中序遍历是升序序列
     */
    public int kthSmallest_1(TreeNode root, int k) {
        if(root == null)
            return 0;
        List<Integer> arr = inOrder(root);
        return arr.get(k-1);
    }
    List<Integer> list1 = new LinkedList<>();
    private List<Integer> inOrder(TreeNode node){
        if(node == null)
            return list1;
        inOrder(node.left);
        list1.add(node.val);
        inOrder(node.right);
        return list1;
    }
    /*public ArrayList<Integer> inorder(TreeNode root, ArrayList<Integer> arr) {
        if (root == null) return arr;
        inorder(root.left, arr);
        arr.add(root.val);
        inorder(root.right, arr);
        return arr;
    }
    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> nums = inorder(root, new ArrayList<Integer>());
        return nums.get(k - 1);
    }*/

    /**
     * 迭代
     */
    public int kthSmallest_2(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

        while (true) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();
            if (--k == 0) return root.val;
            root = root.right;
        }
    }
}
