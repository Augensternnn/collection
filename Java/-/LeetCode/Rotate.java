import java.util.Arrays;

public class Rotate{
	// 189.将数组中的元素向右移动 k 个位置，其中 k 是非负数
    public static void rotate(int[] nums, int k) {
        int[] arr = new int[nums.length];
        int j = 0;
        if(k < 0){
            return;
        }else if((k>=0 && k<nums.length) || (k>nums.length)){
            if(k > nums.length){
                k = k - nums.length;
            }
            for(int i = nums.length-k; i < nums.length; i++){
                arr[j++] = nums[i];
            }
            for(int i = 0; i < nums.length-k; i++){
                arr[j++] = nums[i];
            }
        }else if(k == nums.length){
            arr = nums;
        }
        for(int i = 0; i < nums.length; i++){
            nums[i] = arr[i];
        }
        System.out.println(Arrays.toString(nums));
    }
}