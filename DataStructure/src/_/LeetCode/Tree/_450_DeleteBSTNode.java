package _.LeetCode.Tree;

/**
 * 450.删除二叉搜索树中的节点
 * https://leetcode-cn.com/problems/delete-node-in-a-bst/
 */
public class _450_DeleteBSTNode {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null)
            return null;
        if(key > root.val)
            root.right = deleteNode(root.right,key);
        else if(key < root.val)
            root.left = deleteNode(root.left,key);
        else {
            // the node is a leaf
            if(root.left == null && root.right == null)
                root = null;
            else if(root.right != null){
                // the node is not a leaf and has a right child
                root.val = successor(root);
                root.right = deleteNode(root.right,root.val);
            }else {
                // the node is not a leaf, has no right child, and has a left child
                root.val = predecessor(root);
                root.left = deleteNode(root.left,root.val);
            }
        }
        return root;
    }
    //后继节点
    private int successor(TreeNode node){
        node = node.right;
        while (node.left != null)
            node = node.left;
        return node.val;
    }
    //前驱节点
    private int predecessor(TreeNode node){
        node = node.left;
        while (node.right != null)
            node = node.right;
        return node.val;
    }
}
