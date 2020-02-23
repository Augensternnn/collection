public class PivotIndex{
	// 724.寻找数组的中心索引
    public static int pivotIndex(int[] nums) {
        int sum = 0, leftsum = 0;
        for(int x : nums)
            sum += x;
        for(int i = 0; i < nums.length; i++){
            if(leftsum == sum - nums[i] -leftsum)
                return i;
            leftsum += nums[i];
        }
        return -1;
    }
}