public class IsLongPressedName{
	// 925.长按键入
    public boolean isLongPressedName(String name, String typed) {
        if(name.length() > typed.length())
            return false;
        int i=0, j=0;
        while (i<name.length() && j<typed.length()){
            if(name.charAt(i) == typed.charAt(j)){
                i++;
                j++;
            }else if(j>0 && typed.charAt(j) == typed.charAt(j-1)){
                j++;
            }else {
                return false;
            }
        }
        return i==name.length();
    }
}