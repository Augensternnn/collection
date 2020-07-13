package map.set_map;

import map.Map_;
import map.TreeMap_;

/**
 * 使用Map实现
 */
public class TreeSet_1<E> implements Set_1<E> {
    Map_<E,Object> map = new TreeMap_<>();

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void clear() {
        map.clear();
    }

    public boolean contains(E element) {
        return map.containsKey(element);
    }

    public void add(E element) {
        map.put(element,null);
    }

    public void remove(E element) {
        map.remove(element);
    }

    public void traversal(Visitor<E> visitor) {
        map.traversal(new Map_.Visitor<E, Object>() {
            @Override
            public boolean visit(E key, Object value) {
                return visitor.visit(key);
            }
        });
    }
}
