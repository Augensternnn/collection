package _.LeetCode.Tree.N;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 590.N叉树的后序遍历
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 */
public class _590_Postorder {
    public List<Integer> postorder(Node root) {
        LinkedList<Node> list = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if(root == null)
            return output;
        list.add(root);
        while (!list.isEmpty()){
            Node cur = list.removeLast();
            output.addFirst(cur.val);
            for (Node child : cur.children) {
                list.add(child);
            }
        }
        return output;
    }

    /**
     * 递归
     */
    public List<Integer> postorder_(Node root) {
        List<Integer> res = new ArrayList<>();
        if(root == null)
            return res;
        helper(root,res);
        return res;
    }
    private void helper(Node root, List<Integer> res){
        if(root == null)
            return;
        for (Node child : root.children) {
            helper(child,res);
        }
        res.add(root.val);
    }
}
