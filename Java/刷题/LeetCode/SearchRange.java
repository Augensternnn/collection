public class SearchRange{
	// 34.在排序数组中查找元素的第一个和最后一个位置
    public static int[] searchRange(int[] nums, int target) {
        ArrayList list = new ArrayList();
        int[] arr = new int[2];
        for(int i = 0; i < nums.length; i++)
            list.add(nums[i]);
        if(list.indexOf(target)==-1){
            arr[0] = -1;
            arr[1] = -1;
            return arr;
        }else {
            arr[0] = list.indexOf(target);
            arr[1] = list.lastIndexOf(target);
            return arr;
        }
    }
}