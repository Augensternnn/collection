package set;

import tree.MyBinaryTree;
import tree.MyRBTree;

import java.util.Comparator;

/**
 * 使用红黑树实现
 */
public class TreeSet_<E> implements Set_<E> {
    private MyRBTree<E> tree;

    public TreeSet_() {
        this(null);
    }

    public TreeSet_(Comparator<E> comparator) {
        tree = new MyRBTree<>(comparator);
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean contains(E element) {
        return tree.contains(element);
    }

    @Override
    public void add(E element) {
        tree.add(element);
    }

    @Override
    public void remove(E element) {
        tree.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        tree.inorder(new MyBinaryTree.Visitor<E>() {
            @Override
            public boolean visit(E element) {
                return visitor.visit(element);
            }
        });
    }
}
