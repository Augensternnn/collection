package hashTable.set;

import hashTable.map.LinkedHashMap_;
import map.Map_;
import set.Set_;

public class LinkedHashSet_<E> implements Set_<E> {
    private LinkedHashMap_<E,Object> map = new LinkedHashMap_<>();

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
