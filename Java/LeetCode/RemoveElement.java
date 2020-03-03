public class RemoveElement{
	// 27.移除元素
	// 给定一个数组 nums 和一个值 val，原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
    // 不要使用额外的数组空间，元素的顺序可以改变
    public static int removeElement(int[] nums, int val) {
        if(nums.length == 0){
            return 0;
        }else {
            int i = 0, j = nums.length-1;
            while (i < j){
                if(nums[i] == val){
                    while (i<j && nums[j] == val){
                        j--;
                    }
                    nums[i] = nums[j];
                    nums[j] = val;
                    i++;
                    j--;
                }else {
                    i++;
                }
            }
            int n = 0;
            for(int l = 0; l < nums.length; l++){
                if(nums[l] != val){
                    n++;
                }else {
                    break;
                }
            }
            return n;
        }
    }
}