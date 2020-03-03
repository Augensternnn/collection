public class ReverseOnlyLetters{
	// 917.仅仅反转字母
    public static String reverseOnlyLetters(String S) {
        // 法一：栈
//        Stack<Character> letters = new Stack<>();
//        for(char c : S.toCharArray()){
//            if(Character.isLetter(c))
//                letters.push(c);
//        }
//        String res = "";
//        for(char c : S.toCharArray()){
//            if(Character.isLetter(c))
//                res += letters.pop();
//            else
//                res += c;
//        }
//        return res;

        // 法二：双指针
        String res = "";
        int j = S.length()-1;
        for(int i = 0; i < S.length(); i++){
            if(Character.isLetter(S.charAt(i))){
                while (!Character.isLetter(S.charAt(j)))
                    j--;
                res += S.charAt(j--);
            }else {
                res += S.charAt(i);
            }
        }
        return res;
    }
}