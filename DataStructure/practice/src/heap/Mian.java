package heap;

import Tree.util.printer.BinaryTrees;

import java.util.Comparator;

public class Mian {
    public static void main(String[] args) {
        Integer[] data = {88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37};
        BinaryHeap_<Integer> heap = new BinaryHeap_<>(data, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        BinaryTrees.println(heap);
    }
}
