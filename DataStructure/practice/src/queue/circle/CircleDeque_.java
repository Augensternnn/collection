package queue.circle;



/**
 * 循环双端队列
 */
public class CircleDeque_<E> {
    private int front;
    private int size;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public CircleDeque_() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 从尾部入队
     */
    public void enQueueRear(E element) {
        ensureCapacity(size + 1);
        elements[index(size)] = element;
        size++;
    }

    /**
     * 从头部入队
     */
    public void enQueueFront(E element) {
        ensureCapacity(size + 1);
        front = index(-1);
        elements[front] = element;
        size++;
    }

    /**
     * 从尾部出队
     */
    public E deQueueRear() {
        int rearIndex = index(size - 1);
        E rearElement = elements[rearIndex];
        elements[rearIndex] = null;
        size--;
        return rearElement;
    }

    /**
     * 从头部出队
     */
    public E deQueueFront() {
        E frontElement = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return frontElement;
    }

    /**
     * 获取头部
     */
    public E front() {
        return elements[front];
    }

    /**
     * 获取尾部
     */
    public E rear() {
        return elements[index(size - 1)];
    }

    /**
     * 清空
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        size = 0;
        front = 0;
    }

    /**
     * 动态扩容
     * @param capacity 保证要有capacity的容量
     */
    private void ensureCapacity(int capacity){
        int oldCapacity = elements.length;
        if(oldCapacity >= capacity)
            return;
        int newCapacity = oldCapacity + (oldCapacity >> 1);//新容量为旧容量1.5倍
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[index(i)];
        }
        elements = newElements;
        //重置front
        front = 0;
    }

    /**
     * 索引映射
     */
    private int index(int index){
        index += front;
        if(index < 0)
            return index + elements.length;
        return index - (elements.length > index ? 0 : elements.length);
    }
}
