package queue;

import heap.BinaryHeap_;

import java.util.Comparator;

public class PriorityQueue_<E> {
    private BinaryHeap_<E> heap;

    public PriorityQueue_(Comparator<E> comparator) {
        heap = new BinaryHeap_<>(comparator);
    }

    public PriorityQueue_() {
        this(null);
    }

    public int size(){
        return heap.size();
    }

    public boolean isEmpty(){
        return heap.isEmpty();
    }

    public void clear(){
        heap.clear();
    }

    /**
     * 入队
     */
    public void enQueue(E element){
        heap.add(element);
    }

    /**
     * 优先级最高的元素出队
     */
    public E deQueue(){
        return heap.remove();
    }

    /**
     * 获取队列头元素
     */
    public E front(){
        return heap.get();
    }


}
