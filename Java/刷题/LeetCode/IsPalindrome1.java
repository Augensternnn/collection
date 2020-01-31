public class IsPalindrome{
	// 9.判断是不是回文数
    public boolean isPalindrome1(int x) {
        String s = String.valueOf(x);
        int i = 0, j = s.length()-1;
        while (i < j){
            if(s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}