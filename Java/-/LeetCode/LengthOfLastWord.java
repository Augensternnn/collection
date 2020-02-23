public class LengthOfLastWord{
	// 58.最后一个单词的长度
    public static int lengthOfLastWord(String s) {
        String[] str = s.split(" ");
        if(str.length == 0) {
            return 0;
        }else {
            String lastWord = str[str.length-1];
            return lastWord.length();
        }
    }
}