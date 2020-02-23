public class SortArrayByParity{
	// 905.按奇偶排序数组
    public static int[] sortArrayByParity(int[] A) {
        // 偶数在前，奇数在后
        int i = 0, j = A.length-1;
        while (i < j){
            if(A[i]%2==0){
                i++;
            }else {
                while (A[j]%2 != 0 && j > i) {
                    j--;
                }
                int t = A[i];
                A[i] = A[j];
                A[j] = t;
                j--;
                i++;
            }
        }
        return A;
    }
}