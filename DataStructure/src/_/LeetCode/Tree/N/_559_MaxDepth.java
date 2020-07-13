package _.LeetCode.Tree.N;

import _.LeetCode.List.ListNode;
import javafx.util.Pair;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 559.N叉树的最大深度
 * https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/
 */
public class _559_MaxDepth {
    /**
     * 递归
     */
    public int maxDepth(Node root) {
        if(root == null) {
            return 0;
        }else if(root.children.isEmpty()){
            return 1;
        }else {
            List<Integer> heights = new LinkedList<>();
            for (Node child : root.children) {
                heights.add(maxDepth(child));
            }
            return Collections.max(heights)+1;
        }
    }

    /**
     * 非递归
     */
    public int maxDepth_(Node root) {
        Queue<Pair<Node, Integer>> stack = new LinkedList<>();
        if (root != null) {
            stack.add(new Pair(root, 1));
        }
        int depth = 0;
        while (!stack.isEmpty()) {
            Pair<Node, Integer> current = stack.poll();
            root = current.getKey();
            int current_depth = current.getValue();
            if (root != null) {
                depth = Math.max(depth, current_depth);
                for (Node c : root.children) {
                    stack.add(new Pair(c, current_depth + 1));
                }
            }
        }
        return depth;
    }
}
