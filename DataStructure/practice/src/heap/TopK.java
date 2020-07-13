package heap;

import Tree.util.printer.BinaryTrees;

import java.util.Comparator;

/**
 * Top K问题：从 n 个整数中找出 最大/最小 的前 K 个数(k 远远小于 n)
 * 1.进行全排序 -- 时间复杂度为 O(nlogn)
 * 2.使用二叉堆 -- 时间复杂度为 O(nlogk)
 *
 * 【找最大的 k 个数】
 * （1）建一个小顶堆
 * （2）扫描 n 个整数
 *      先将遍历到的前 k 个数放入堆中
 *      从第 k+1 个数开始，若大于堆顶元素，就使用replace操作(删除堆顶元素，将第 k+1 个数添加到堆中)
 * （3）扫描完毕：堆中剩下的就是最大的前 k 个数
 *
 * 【找最小的 k 个数】
 * 使用大顶堆；若小于堆顶元素，就使用replace操作
 */
public class TopK {
    public static void main(String[] args) {
        //新建一个小顶堆
        BinaryHeap_<Integer> heap = new BinaryHeap_<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        // 找出最大的前k个数
        int k = 3;
        Integer[] data = {51, 30, 39, 92, 74, 25, 16, 93,
                91, 19, 54, 47, 73, 62, 76, 63, 35, 18,
                90, 6, 65, 49, 3, 26, 61, 21, 48};
        for (int i = 0; i < data.length; i++) {
            if(heap.size < k)   // 前k个数添加到小顶堆
                heap.add(data[i] );
            else if(data[i] > heap.get())   // 如果是第k + 1个数，并且大于堆顶元素
                heap.replace(data[i]);
        }
        BinaryTrees.println(heap);
    }
}
