package _.LeetCode.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 104.二叉树的最大深度
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 */
public class _104_MaxDepth {
    /**
     * 递归
     */
    public int maxDepth(TreeNode root) {
        return height(root);
    }
    private int height(TreeNode node){
        if(node==null)
            return 0;
        return 1 + Math.max(height(node.left),height(node.right));
    }

    /**
     * 非递归
     */
    public int maxDepth_(TreeNode root) {
        if(root == null)
            return 0;
        int height = 0;
        int levelSize = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            levelSize--;
            if(node.left!=null)
                queue.offer(node.left);
            if(node.right!=null)
                queue.offer(node.right);
            if(levelSize==0){
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }
}
