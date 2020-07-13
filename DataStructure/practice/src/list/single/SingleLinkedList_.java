package list.single;

import list.AbstractList_;

/**
 * 单向链表
 */
public class SingleLinkedList_<E> extends AbstractList_<E> {
    private Node<E> first;

    private  static class Node<E> {
        E element;
        Node next;

        public Node(E element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    public void clear() {
        size = 0;
        first = null;
    }

    public E get(int index) {
        return node(index).element;
    }

    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if(index == 0){
            first = new Node<E>(element,first);
        }else {
            Node<E> prev = node(index-1);
            prev.next = new Node<E>(element,prev.next);
        }
        size++;
    }

    /**
     * 获取index位置对应节点对象
     */
    private Node<E> node(int index){
        rangeCheck(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = first;
        if(index == 0){
            first = first.next;
        }else {
            Node<E> prev = node(index-1);
            node = prev.next;
            prev.next = node.next;
        }
        size--;
        return node.element;
    }

    public int indexOf(E element) {
        Node<E> node = first;
        if(element == null){
            for (int i = 0; i < size; i++) {
                if(node.element == null)
                    return i;
                node = node.next;
            }
        }else {
            for (int i = 0; i < size; i++) {
                if(element.equals(node.element))
                    return i;
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if(i != 0){
                string.append(", ");
            }
            string.append(node.element);
            node = node.next;
        }
        /*while (node != null){
            node = node.next;
        }*/
        string.append("]");
        return string.toString();
    }
}
