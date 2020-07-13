package hashTable.set;

import hashTable.map.HashMap_;
import map.Map_;
import set.Set_;

/**
 * HashSet理论上效率会高于TreeSet
 * 但是遍历是无序的
 */
public class HashSet_<E> implements Set_<E> {
    private HashMap_<E,Object> map = new HashMap_<>();

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean contains(E element) {
        return map.containsKey(element);
    }

    @Override
    public void add(E element) {
        map.put(element, null);
    }

    @Override
    public void remove(E element) {
        map.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        map.traversal(new Map_.Visitor<E, Object>() {
            public boolean visit(E key, Object value) {
                return visitor.visit(key);
            }
        });
    }
}
