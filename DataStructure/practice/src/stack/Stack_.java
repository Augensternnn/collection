package stack;

import list.ArrayList_;
import list.Lists;

public class Stack_<E> {

    private Lists<E> list = new ArrayList_<>();

    public void clear() {
        list.clear();
    }

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void push(E element){
        list.add(element);
    }

    public E pop(){
        return (E) list.remove(list.size() - 1);
    }

    public E top(){
        return (E) list.get(list.size() - 1);
    }
}
