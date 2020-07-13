package set;

import list.LinkedList_;
import list.Lists;

public class ListSet_<E> implements Set_<E> {
    private Lists<E> list = new LinkedList_<E>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {
//         if(list.contains(element))
//             return;
//         list.add(element);
         int index = list.indexOf(element);
         if(index != list.ELEMENT_NOT_FOUND)
             list.set(index,element);
         else
             list.add(element);
    }

    @Override
    public void remove(E element) {
        int index = list.indexOf(element);
        if(index != list.ELEMENT_NOT_FOUND)
            list.remove(index);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        if(visitor == null)
            return;
        for (int i = 0; i < list.size(); i++) {
            if(visitor.visit(list.get(i)))
                return;
        }
    }


}
