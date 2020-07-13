package set;

public interface Set_<E> {
    int size();

    boolean isEmpty();

    void clear();

    boolean contains(E element);

    void add(E element);

    void remove(E element);

    void traversal(Visitor<E> visitor);

    abstract class Visitor<E> {
        boolean stop;
        public abstract boolean visit(E element);
    }
}
