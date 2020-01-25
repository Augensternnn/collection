public class ToLowerCase{
	// 709.将字符串中的大写字母转换成小写字母
    public static String toLowerCase(String str) {
        String s = "";
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i)>='A' && str.charAt(i)<='Z')
                s += (char)(str.charAt(i)+32);
            else
                s += str.charAt(i);
        }
        return s;
    }
}