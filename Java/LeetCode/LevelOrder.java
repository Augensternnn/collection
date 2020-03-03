class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

public class LevelOrder{
	// 102.二叉树的层序遍历
	public static class Element{
        int level;
        TreeNode node;
        Element(TreeNode node,int level){
            this.level = level;
            this.node = node;
        }
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> retList = new ArrayList<>();
        if(root == null){
            return retList;
        }
        Queue<Element> queue = new LinkedList<>();
        Element e = new Element(root,0);
        queue.add(e);
        while(!queue.isEmpty()){
            Element front = queue.poll();
            if(front.level == retList.size()){
                retList.add(new ArrayList<>());
            }
            retList.get(front.level).add(front.node.val);
            if(front.node.left != null){
                queue.add(new Element(front.node.left,front.level + 1));
            }
            if(front.node.right != null){
                queue.add(new Element(front.node.right,front.level + 1));
            }
        }
        return retList;
    }
}