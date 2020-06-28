package tree;

import java.util.Comparator;

public class MyRBTree<E> extends MyBBST<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public MyRBTree() {
    }

    public MyRBTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 删除之后的处理
     * @param node 被删除的节点 或 取代被删除节点的子节点
     */
    @Override
    protected void afterRemove(Node<E> node) {
        // 删除的节点是红色 或 用以取代删除节点的子节点是红色
        if(isRed(node)){
            black(node);
            return;
        }

        Node<E> parent = node.parent;
        // 删除的是根节点
        if(parent == null)
            return;

        // 删除黑色叶子节点【下溢】
        boolean left = parent.left==null || node.isLeftChild();// 判断被删除的node是左还是右
        Node<E> sibling = left ? parent.left : parent.right;
        if(left){// 被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                colour(sibling, colourOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }

        }else {// 被删除的节点在右边，兄弟节点在左边
            if(isRed(sibling)){// 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }

            // 兄弟节点必然是黑色
            if(isBlack(sibling.left) && isBlack(sibling.right)){// 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if(parentBlack)
                    afterRemove(parent);
            }else {// 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if(isBlack(sibling.left)){
                    rotateLeft(sibling);
                    sibling = parent.left;
                }
                colour(sibling,colourOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }

    }

    /*@Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        //删除的节点是红色
        if(isRed(node))
            return;

        //删除的节点是黑色
        // 用以取代node的子节点是红色
        if(isRed(replacement)){
            black(replacement);
            return;
        }

        Node<E> parent = node.parent;
        //删除的是根节点
        if(parent == null)
            return;

        // 删除黑色叶子节点【下溢】
        boolean left = parent.left==null || node.isLeftChild();//判断被删除的node是左还是右
        Node<E> sibling = left ? parent.right : parent.left;
        if(left){//被删除的节点在左边，兄弟节点在右边
            if(isRed(sibling)){//兄弟节点是红色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                //更换兄弟
                sibling = parent.right;
            }

            //兄弟节点是黑色
            if(isBlack(sibling.left) && isBlack(sibling.right)){//兄弟没有1个红色子节点--父节点向下和兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if(parentBlack)
                    afterRemove(parent,null);
            }else {//兄弟节点至少有1个红色子节点--向兄弟节点借元素
                //兄弟节点左边是黑色，兄弟节点要先旋转
                if(isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }
                colour(sibling,colourOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }

        }else {//被删除的节点在右边，兄弟节点在左边

            if(isRed(sibling)){//兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                //更换兄弟
                sibling = parent.left;
            }

            //兄弟节点是黑色
            if(isBlack(sibling.left) && isBlack(sibling.right)){//兄弟没有1个红色子节点--父节点向下和兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if(parentBlack)
                    afterRemove(parent,null);
            }else {//兄弟节点至少有1个红色子节点--向兄弟节点借元素
                //兄弟节点左边是黑色，兄弟节点要先旋转
                if(isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }
                colour(sibling,colourOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
    }*/

    /**
     * 添加之后的处理
     */
    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;

        //添加的是根节点 或者 上溢到达了根节点
        if(parent == null){
            black(node);
            return;
        }

        // 父节点是黑色：直接返回
        if(isBlack(parent))
            return;

        Node<E> uncle = parent.sibling();// 叔父节点
        Node<E> grand = red(parent.parent);//祖父节点
        //叔父节点是红色【B树节点上溢】
        if(isRed(uncle)){
            black(parent);
            black(uncle);
            // 将祖父节点当做是新添加的节点
            afterAdd(grand);
            return;
        }
        //叔父节点不是红色
        if(parent.isLeftChild()){//L
            if(node.isLeftChild()){//LL
                black(parent);
            }else {//LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        }else {//R
            if(node.isRightChild()){//RR
                black(parent);
            }else {//RL
                black(node);
                rotateRight(parent);
            }
            rotateLeft(grand);
        }
    }

    /**
     * 给节点染色
     */
   private Node<E> colour(Node<E> node,boolean colour){
        if(node == null)
            return node;
       ((RBNode<E>)node).colour = colour;
       return node;
   }

    /**
     * 将节点染成红色
     */
    private Node<E> red(Node<E> node){
        return colour(node,RED);
    }

    /**
     * 将节点染成黑色
     */
    private Node<E> black(Node<E> node){
        return colour(node,BLACK);
    }

    /**
     * 判断节点是什么颜色
     */
    private boolean colourOf(Node<E> node){
        return node==null ? BLACK : ((RBNode<E>)node).colour;
    }

    /**
     * 节点是否为黑色
     */
    private boolean isBlack(Node<E> node){
        return colourOf(node) == BLACK;
    }

    /**
     * 节点是否为红色
     */
    private boolean isRed(Node<E> node){
        return colourOf(node) == RED;
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element,parent);
    }

    private static class RBNode<E> extends Node<E>{
        boolean colour = RED;
        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String str = "";
            if (colour == RED) {
                str = "R_";
            }
            return str + element.toString();
        }
    }
}