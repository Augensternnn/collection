package heap;

import Tree.util.printer.BinaryTreeInfo;

import java.util.Comparator;

/**
 * 二叉堆（大顶堆）
 */
public class BinaryHeap_<E> extends AbstractHeap_<E> implements BinaryTreeInfo {
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap_(E[] elements, Comparator<E> comparator){
        super(comparator);
        if(elements == null || elements.length == 0)
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        else {
            size = elements.length;
            int capacity = Math.max(elements.length,DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[capacity];
            for (int i = 0; i < elements.length; i++) {
                this.elements[i] = elements[i];
            }
            heapify();
        }
    }

    public BinaryHeap_(E[] elements){
        this(elements,null);
    }

    public BinaryHeap_(Comparator<E> comparator) {
        this(null,comparator);
    }

    public BinaryHeap_() {
        this(null,null);
    }

    /**
     * 批量建堆
     */
    private void heapify(){
        //自上而下的上滤 -- 效率较低
        /*for (int i = 1; i < size; i++) {
            siftUp(i);
        }*/

        //自下而上的下滤
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);
        elements[size++] = element;
        siftUp(size - 1);
    }
    /**
     * index位置元素上滤
     */
    private void siftUp(int index){
        E element = elements[index];
        while (index > 0){
            int parentIndex = (index - 1) >> 1;//默认向下取整
            E parent = elements[parentIndex];
            if (compare(parent,element) >= 0)
                break;
            elements[index] = parent;
            index = parentIndex;
        }
        elements[index] = element;
    }

    /**
     * 获取堆顶元素
     */
    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    /**
     * 删除堆顶元素
     */
    @Override
    public E remove() {
        emptyCheck();
        E first = elements[0];
        int lastIndex = --size;
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;
        siftDown(0);
        return first;
    }
    /**
     * index位置元素下滤
     */
    private void siftDown(int index) {
        E element = elements[index];
        int half = size >> 1;
        while(index < half){//index必须是非叶子节点
            //默认用左子节点进行比较
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];

            int rightIndex = childIndex + 1;
            //右子节点比左子节点大
            if(rightIndex < size && compare(elements[rightIndex],child) > 0)
                child = elements[childIndex = rightIndex];
            //大于等于子节点
            if(compare(element,child) >= 0)
                break;
            elements[index] = child;
            index = childIndex;
        }
        elements[index] = element;
    }

    /**
     * 删除堆顶元素，同时插入一个新元素
     * 返回被删除的堆顶元素
     */
    @Override
    public E replace(E element) {
        E top = null;
        if(size == 0)
            elements[size++] = element;
        else {
            top = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return top;
    }

    /**
     * 扩容
     * 保证要有capacity的容量
     */
    private void ensureCapacity(int capacity){
        int oldCapacity = elements.length;
        if(oldCapacity >= capacity)
            return;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    private void emptyCheck(){
        if(size() == 0)
            throw new IndexOutOfBoundsException("Heap is empty！");
    }

    private void elementNotNullCheck(E element){
        if(element == null)
            throw new IllegalArgumentException("element must not be null！");
    }

    /**
     **************************实现以下方法目的：树的形式打印堆**************************
     */
    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int index = ((int) node << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        int index = ((int) node << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        return elements[(int) node];
    }
    /**
     ***********************************************************************************
     */
}
