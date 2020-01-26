public class LengthOfLongestSubstring{
	// 3.无重复字符的最长子串
	public static int lengthOfLongestSubstring(String s) {
        if(s.length() == 0)
            return 0;
        String longestStr = "";
        int i = 0, j = i + 1;
        while (i < j) {
            if (i < s.length() && j < s.length()) {
               // String str = s.substring(i, j);
                StringBuilder str = new StringBuilder(s.substring(i, j));
                String ss = str.toString();
                if (!ss.contains(s.subSequence(j,j+1))) {
                   // String strr = str + s.charAt(j);
                    str.append(s.charAt(j));
                    if ((j - i + 1) > longestStr.length())
                        longestStr = str.toString();
                       // longestStr = strr;
                    j+=1;
                }else {
                    i++;
                    j = i + 1;
                }
            }else
                break;
        }
        if(longestStr.length() == 0 && s.length() != 0)
            longestStr += s.charAt(0);
        return longestStr.length();
    }
}