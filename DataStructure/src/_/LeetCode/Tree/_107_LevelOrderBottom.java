package _.LeetCode.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 107.二叉树的层次遍历二
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
 */
public class _107_LevelOrderBottom {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> arr = new LinkedList<>();
        if(root == null)
            return arr;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> list = new LinkedList<>();
            int size = queue.size();
            while (size-- != 0){
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left!=null)
                    queue.add(node.left);
                if(node.right!=null)
                    queue.add(node.right);
            }
            arr.add(0,list);
        }
        return arr;
    }
}
