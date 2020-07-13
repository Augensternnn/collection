package _.LeetCode.Set;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 349.两个数组的交集
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/
 */
public class _349_Intersection {
    /**
     * 使用Set
     */
    //直接掉API -- retainALl()
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int n1 : nums1)
            set1.add(n1);
        for (int n2 : nums2)
            set2.add(n2);
        set1.retainAll(set2);
        int[] output = new int[set1.size()];
        int index = 0;
        for (Integer i : set1)
            output[index++] = i;
        return output;
    }

    public int[] intersection_1(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int n1 : nums1)
            set1.add(n1);
        for (int n2 : nums2)
            set2.add(n2);
        if(set1.size() < set2.size())
            return all(set1,set2);
        else
            return all(set2,set1);
    }
    private int[] all(Set<Integer> set1, Set<Integer> set2){
        int[] output = new int[set1.size()];
        int index = 0;
        for (Integer i : set1)
            if(set2.contains(i))
                output[index++] = i;
        return Arrays.copyOf(output,index);
    }

    public int[] intersection_2(int[] nums1, int[] nums2) {
        if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0)
            return new int[0];
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int num1 : nums1)
            set1.add(num1);
        for (int num2 : nums2) {
            if (set1.contains(num2))
                set2.add(num2);
        }
        int[] ans = new int[set2.size()];
        int n = 0;
        for (Integer val : set2) {
            ans[n++] = val;
        }
        return ans;
    }

    /**
     * 双指针
     */
    public int[] intersection_3(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length){
            if(nums1[i] == nums2[j]){
                set.add(nums1[i]);
                i++;j++;
            }else if(nums1[i] < nums2[j]){
                i++;
            }else if(nums1[i] > nums2[j]){
                j++;
            }
        }
        int[] ans = new int[set.size()];
        int n = 0;
        for (int val : set) {
            ans[n++] = val;
        }
        return ans;
    }

    /**
     * 二分查找
     */
    public int[] intersection_4(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums2);
        for (int target : nums1) {
            if(binarySearch(nums2,target))
                set.add(target);
        }
        int n = 0;
        int[] ans = new int[set.size()];
        for (int num : set) {
            ans[n++] = num;
        }
        return ans;
    }
    private boolean binarySearch(int[] nums, int target){
        int left = 0, right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target)
                return true;
            else if(nums[mid] > target)
                right = mid - 1;
            else if(nums[mid] < target)
                left = mid + 1;
        }
        return false;
    }
}
