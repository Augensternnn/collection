package list;

import list.single.SingleLinkedList_;

/**
 * 双向链表
 */
public class LinkedList_<E> extends AbstractList_<E> {
    private Node<E> first;
    private Node<E> last;

    private  static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if(prev != null)
                sb.append(prev.element);
            else
                sb.append("null");
            sb.append("_").append(element).append("_");
            if(next != null)
                sb.append(next.element);
            else
                sb.append("null");
            return sb.toString();
        }
    }

    public void clear() {
        size = 0;
        first = null;
        last = null;
        //节点之间的线虽然没有断掉，但是他们没有被gc root对象(LinkedList对象)指用(因为first、last断掉了)，故会被销毁
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
        if(index == size){//往最后面添加元素
            Node<E> oldLast = last;
            last = new Node<>(oldLast,element,null);
            if(oldLast == null)
                first = last;
            else
                oldLast.next = last;
        } else {
            Node<E> next = node(index);//是新添加节点的下一个
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(prev,element,next);
            next.prev = node;
            if(prev == null)//index == 0
                first = node;
            else
                prev.next = node;
        }
        size++;
    }

    /**
     * 获取index位置对应节点对象
     */
    private Node<E> node(int index){
        rangeCheck(index);

        if(index < (size >> 1)){//索引小于size的一半
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }else {
            Node<E> node = last;
            for (int i = size-1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;
        if(prev == null)//index == 0
            first = next;
        else
            prev.next = next;
        if(next == null)//index == size-1
            last = prev;
        else
            next.prev = prev;
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
            string.append(node);
            node = node.next;
        }
        string.append("]");
        return string.toString();
    }
}
