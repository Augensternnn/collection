package _.LeetCode.Tree;

import java.util.ArrayList;
import java.util.Stack;

public class _938_RangeSumBST {
    public int rangeSumBST(TreeNode root, int L, int R) {
        ArrayList<Integer> list = inorder(root, new ArrayList<Integer>());
        int first = list.indexOf(L);
        int end = list.indexOf(R);
        int count = 0;
        for (int i = first; i <= end; i++) {
            count += list.get(i);
        }
        return count;
    }
    private ArrayList<Integer> inorder(TreeNode node, ArrayList<Integer> arr){
        if(node == null)
            return arr;
        inorder(node.left,arr);
        arr.add(node.val);
        inorder(node.right,arr);
        return arr;
    }

    /**
     * 深度优先搜索
     */
    //迭代
    public int rangeSumBST_1(TreeNode root, int L, int R) {
        int ans = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            if (node != null) {
                if (L <= node.val && node.val <= R)
                    ans += node.val;
                if (L < node.val)
                    dfs(node.left, L, R);
                if (node.val < R)
                    dfs(node.right, L, R);
            }
        }
        return ans;
    }

    //递归
    int ans;
    public int rangeSumBST_2(TreeNode root, int L, int R) {
        ans = 0;
        dfs(root, L, R);
        return ans;
    }
    private void dfs(TreeNode node, int L, int R){
        if (node != null) {
            if (L <= node.val && node.val <= R)
                ans += node.val;
            if (L < node.val)
                dfs(node.left, L, R);
            if (node.val < R)
                dfs(node.right, L, R);
        }
    }
}
