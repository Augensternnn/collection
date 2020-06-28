package tree;

import java.util.Comparator;

public class AVL<E> extends MyBBST<E>{

    public AVL() {
        this(null);
    }

    public AVL(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 删除node之后的调整
     */
    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null){
            if(isBalance(node)){
                updateHeight(node);
            }else {
                rebalance(node);
            }
        }
    }

    /**
     * 添加node之后的调整
     */
    @Override
    protected void afterAdd(Node node) {
        while ((node=node.parent)!=null){
            if(isBalance(node)){
                //平衡：更新高度
                updateHeight(node);
            }else {
                //不平衡：恢复平衡
                rebalance(node);
                //整棵树恢复平衡
                break;
            }
        }
    }

    /**
     * 节点是否平衡
     * 平衡：平衡因子绝对值小于等于1
     */
    private boolean isBalance(Node<E> node){
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }

    /**
     * 更新某一节点的高度
     */
    private void updateHeight(Node<E> node){
        ((AVLNode<E>)node).updateHeight();
    }

    /**
     * 恢复平衡
     * @param grand 高度最低的不平衡节点
     */
    private void rebalance(Node<E> grand){
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();
        Node<E> node = ((AVLNode<E>)parent).tallerChild();
        if(parent.isLeftChild()){//L
            if(node.isLeftChild()) {//LL
                rotateRight(grand);
            }else {//LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        }else {//R
            if(node.isRightChild()){//RR
                rotateLeft(grand);
            }else {//RL
                rotateRight(parent);
                rotateLeft(grand);
            }
        }
    }

    /**
     * 恢复平衡--统一所有的旋转
     */
    private void rebalance_1(Node<E> grand){
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();
        Node<E> node = ((AVLNode<E>)parent).tallerChild();
        if(parent.isLeftChild()){//L
            if(node.isLeftChild()){//LL
                rotate(grand,node.left,node,node.right,parent,parent.right,grand,grand.right);
            }else {//LR
                rotate(grand,parent.left,parent,node.left,node,node.right,grand,grand.right);
            }
        }else {//R
            if(node.isRightChild()){//RR
                rotate(grand,grand.left,grand,parent.left,parent,node.left,node,node.right);
            }else {//RL
                rotate(grand,grand.left,grand,node.left,node,node.right,parent,parent.right);
            }
        }
    }

    @Override
    protected void afterRotate(MyBinaryTree.Node<E> grand, MyBinaryTree.Node<E> parent, MyBinaryTree.Node<E> child) {
        super.afterRotate(grand, parent, child);
        //2.先后更新grand、parent的高度
        updateHeight(grand);
        updateHeight(parent);
    }

    @Override
    protected void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
        super.rotate(r, a, b, c, d, e, f, g);
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    @Override
    protected Node createNode(Object element, Node parent) {
        return new AVLNode(element,parent);
    }

    private static class AVLNode<E> extends Node<E> {
        int height = 1;//因为刚添加到AVL树的都是叶子节点，高度为1

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        //获取这个节点的平衡因子
        public int balanceFactor(){
            int leftHeight = left==null?0:((AVLNode<E>)left).height;
            int rightHeight = right==null?0:((AVLNode<E>)right).height;
            return leftHeight-rightHeight;
        }

        //更新自己的高度（递归更新的话，性能太差）
        public void updateHeight(){
            int leftHeight = left==null?0:((AVLNode<E>)left).height;
            int rightHeight = right==null?0:((AVLNode<E>)right).height;
            height = 1 + Math.max(leftHeight,rightHeight);
        }

        /**
         * 高度最高的子节点
         */
        public Node<E> tallerChild(){
            int leftHeight = left==null?0:((AVLNode<E>)left).height;
            int rightHeight = right==null?0:((AVLNode<E>)right).height;
            if(leftHeight > rightHeight)
                return left;
            if(leftHeight < rightHeight)
                return right;
            return isLeftChild() ? left : right;
        }

        @Override
        public String toString(){
            String parentString = "null";
            if(parent != null)
                parentString = parent.element.toString();
            return element + "_p（" + parentString + "）_h（" + height + "）";
        }
    }
}
