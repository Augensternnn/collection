package _.LeetCode.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 101.对称二叉树
 * https://leetcode-cn.com/problems/symmetric-tree/
 */
public class _101_IsSymmetric {
    /**
     * 递归
     */
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root,root);
    }
    private boolean isMirror(TreeNode t1, TreeNode t2){
        if(t1==null && t2==null)
            return true;
        if(t1==null || t2==null)
            return false;
        return (t1.val == t2.val) && isMirror(t1.left,t2.right) && isMirror(t1.right,t2.left);
    }

    /**
     * 非递归
     */
    public boolean isSymmetric_(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            if(t1==null && t2==null)
                continue;
            if(t1==null || t2==null)
                return false;
            if(t1.val != t2.val)
                return false;
            queue.add(t1.left);
            queue.add(t2.right);

            queue.add(t1.right);
            queue.add(t2.left);
        }
        return true;
    }
}
