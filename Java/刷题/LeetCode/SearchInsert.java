public class SearchInsert{
	// 35.搜索插入位置
	// 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
    // 数组中无重复元素
    public static int searchInsert(int[] nums, int target) {
        if(nums.length == 0){
            return 0;
        }else if(nums[nums.length-1] < target){
            return nums.length;
        }else {
            int i = 0;
            for(i = 0; i < nums.length; i++) {
                if (nums[i] == target || nums[i] > target)
                    break;
                else
                    continue;
            }
            return i;
        }
    }
}