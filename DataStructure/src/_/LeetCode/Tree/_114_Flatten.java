package _.LeetCode.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 114.二叉树展开为链表
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 */
public class _114_Flatten {
    public void flatten(TreeNode root) {
        if(root == null)
            return;
        LinkedList<Integer> arr = preOrder(root);
        arr.removeFirst();
        TreeNode cur = root;
        for (Integer i : arr) {
            cur.right = new TreeNode(i);
            cur.left = null;
            cur = cur.right;
        }
    }
    LinkedList<Integer> list = new LinkedList();
    private LinkedList<Integer> preOrder(TreeNode node){
        if (node == null)
            return list;
        list.add(node.val);
        preOrder(node.left);
        preOrder(node.right);
        return list;
    }

    /**
     * 法二
     */
    public void flatten_1(TreeNode root) {
        while (root != null){
            if(root.left == null){
                //左子树为null：直接考虑下一节点
                root = root.right;
            }else {
                //找出子树最右边的节点
                TreeNode pre = root.left;
                while (pre.right != null)
                    pre = pre.right;
                //将原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                //将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null;
                //考虑下一节点
                root = root.right;
            }
        }
    }

    /**
     * 法三
     */
    public void flatten_2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root, pre = null;
        while (cur != null || !stack.isEmpty()){
            while (cur != null){
                stack.push(cur);
                cur = cur.right;
            }
            cur = stack.peek();
            // 在不存在左节点或者右节点已经访问过的情况下，访问根节点
            if(cur.left == null || cur.left == pre){
                stack.pop();
                cur.right = pre;
                cur.left = null;
                pre = cur;
                cur = null;
            }else {
                // 左节点还没有访问过就先访问左节点
                cur = cur.left;
            }
        }
    }

    /**
     * 法四
     */
    public void flatten_3(TreeNode root) {

    }
}
