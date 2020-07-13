package list;

public class ArrayList_<E> extends AbstractList_<E> {
     /**
      * 所有的元素
      */
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList_(int capaCity) {
        capaCity = (capaCity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capaCity;
        elements = (E[]) new Object[capaCity];
    }
    public ArrayList_(){
        this(DEFAULT_CAPACITY);
    }

     /**
      * 清除所有元素
      */
     public void clear() {
         for (int i = 0; i < size; i++) {
             elements[i] = null;
         }
         size = 0;
         //缩容（大小、是否缩容等可自定义）
         if(elements != null && elements.length > DEFAULT_CAPACITY)
             elements = (E[])new Object[DEFAULT_CAPACITY];
     }

    /**
     * 返回index位置对应的元素
     * @param index
     * @return
     */
    public E get(int index){
        rangeCheck(index);
        return elements[index];
    }

    /**
     * 设置index位置的元素
     * @param index
     * @param element
     * @return 原来的元素
     */
    public E set(int index,E element){
        rangeCheck(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 往index位置添加元素
     * @param index
     * @param element
     */
    public void add(int index,E element){
        rangeCheckForAdd(index);
        ensureCapacity(size+1);
        for(int i = size; i > index; i--){
            elements[i] = elements[i-1];
        }
        elements[index] = element;
        size++;
    }

    /**
     * 删除index位置对应的元素
     * @param index
     * @return
     */
    public E remove(int index){
        rangeCheck(index);
        E old = elements[index];
        for(int i = index+1; i < size; i++){
            elements[i-1] = elements[i];
        }
        elements[--size] = null;
        trim();//动态缩容
        return old;
    }

    //删除某元素
    public void remove(E element){
        remove(indexOf(element));
    }

    /**
     * 查看元素的位置
     * @param element
     * @return
     */
    public int indexOf(E element){
        //null调用方法会报空指针异常
        if(element == null){//1
            for(int i = 0; i < size; i++){
                if(elements[i] == null)
                    return i;
            }
        }else {
            for(int i = 0; i < size; i++){
                if(element.equals(elements[i]))
                    return i;//n
            }
        }
        return ELEMENT_NOT_FOUND;
    }

     /*public int indexOf2(E element) {
		for (int i = 0; i < size; i++) {
			if (valEquals(element, elements[i])) return i; // 2n
		}
		return ELEMENT_NOT_FOUND;
	}
     private boolean valEquals(Object v1, Object v2) {
		return v1 == null ? v2 == null : v1.equals(v2);
	}*/

     /**
      * 保证要有capacity的容量（非线程安全）
      * @param capacity
      */
    private void ensureCapacity(int capacity){
        int oldCapacity = elements.length;
        if(oldCapacity >= capacity)
            return;
        int newCapacity = oldCapacity + (oldCapacity >> 1);//左移1：乘2，右移1：除2
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

     /**
      * 动态缩容操作
      * 发现剩余空间还有一半就缩容
      */
     private void trim(){
         int capacity = elements.length;
         int newCapacity = capacity >> 1;
         if(size >= newCapacity || capacity <= DEFAULT_CAPACITY)//剩余空间小于一半 或 小于默认容量
             return;
         //缩容
         E[] newElements = (E[]) new Object[newCapacity];
         for (int i = 0; i < size; i++) {
             newElements[i] = elements[i];
         }
         elements = newElements;
     }

    @Override
    public String toString() {
        //size=3, [99, 88, 77]
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        //法一：
        /*for (int i = 0; i < size; i++) {
            string.append(elements[i]);
            if(i != size-1){
                string.append(", ");
            }
        }*/
        //法二：
        for (int i = 0; i < size; i++) {
            if(i != 0){
                string.append(", ");
            }
            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }
}
