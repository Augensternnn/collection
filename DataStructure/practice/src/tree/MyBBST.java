package tree;

import java.util.Comparator;

/**
 * 平衡二叉搜索树
 */
public class MyBBST<E> extends MyBST<E> {
    public MyBBST() {
    }

    public MyBBST(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     *左旋转
     */
    protected void rotateLeft(Node<E> grand){
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand,parent,child);
    }

    /**
     *右旋转
     */
    protected void rotateRight(Node<E> grand){
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand,parent,child);
    }

    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child){
        //1.更新grand、parent、child的parent属性
        //让parent称为子树根节点
        parent.parent = grand.parent;
        if(grand.isLeftChild()){
            grand.parent.left= parent;
        }else if(grand.isRightChild()){
            grand.parent.right = parent;
        }else {//grand是root节点
            root = parent;
        }
        //更新child的parent
        if(child != null)
            child.parent = grand;
        //更新grand的parent
        grand.parent = parent;
    }

    /**
     * 统一所有旋转操作
     */
    protected void rotate(
        Node<E> r,//子树根节点
        Node<E> a, Node<E> b, Node<E> c,
        Node<E> d,
        Node<E> e, Node<E> f, Node<E> g){
        // 让d成为子树根节点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }

        //a-b-c
        b.left = a;
        b.right = c;
        if(a != null)
            a.parent = b;
        if(c != null)
            c.parent = b;

        //e-f-g
        f.left = e;
        f.right = g;
        if(e != null)
            e.parent = f;
        if(g != null)
            g.parent = f;

        //b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
    }
}
