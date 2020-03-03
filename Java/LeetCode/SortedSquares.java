public class SortedSquares{
	// 977.有序数组的平方
    public static int[] sortedSquares(int[] A) {
        int[] res = new int[A.length];
        int i = 0;
        for(int temp : A){
            res[i++] = temp*temp;
        }
        Arrays.sort(res);
        return res;
    }
}