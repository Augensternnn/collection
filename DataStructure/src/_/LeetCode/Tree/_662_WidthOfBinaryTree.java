package _.LeetCode.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 662.二叉树最大高度
 * https://leetcode-cn.com/problems/maximum-width-of-binary-tree/
 */
public class _662_WidthOfBinaryTree {
    private class Node{
        TreeNode node;
        int level,pos;

        public Node(TreeNode node, int level, int position) {
            this.node = node;
            this.level = level;
            this.pos = position;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(root,0,0));
        int curDepth=0,left=0,ans=0;
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            if(cur.node != null){
                queue.offer(new Node(cur.node.left,cur.level+1,2*cur.pos));
                queue.offer(new Node(cur.node.right,cur.level+1,2*cur.pos+1));
                if(curDepth != cur.level){
                    curDepth = cur.level;
                    left = cur.pos;
                }
                ans = Math.max(ans,cur.pos-left+1);
            }
        }
        return ans;
    }
}
