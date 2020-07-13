package list.single;

import list.AbstractList_;

/**
 * 增加一个虚拟头结点
 * 【不推荐】
 */
public class SingleLinkedList_2<E> extends AbstractList_<E> {
    private Node<E> first;

    public SingleLinkedList_2() {
        first = new Node<>(null,null);
    }

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
        Node<E> prev = index==0 ? first : node(index-1);
        prev.next = new Node<E>(element,prev.next);
        size++;
    }

    public E remove(int index) {
        rangeCheck(index);
        Node<E> prev = index==0 ? first : node(index-1);
        Node<E> node = prev.next;
        prev.next = node.next;
        size--;
        return node.element;
    }

    /**
     * 获取index位置对应节点对象
     */
    private Node<E> node(int index){
        rangeCheck(index);
        Node<E> node = first.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
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
        Node<E> node = first.next;
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
