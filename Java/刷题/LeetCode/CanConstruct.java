public class CanConstruct{
	// 383.赎金信
    public static boolean canConstruct(String ransomNote, String magazine) {
        if(ransomNote.length() > magazine.length())
            return false;
        StringBuilder mag = new StringBuilder(magazine);
        for(int i = 0; i < ransomNote.length(); i++){
            if(mag.indexOf(ransomNote.charAt(i)+"") == -1){
                return false;
            }else {
                mag.deleteCharAt(mag.indexOf(ransomNote.charAt(i)+""));
            }
        }
        if (mag.length() < 0){
            return false;
        }
        return true;
    }
}