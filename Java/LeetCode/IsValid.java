public class IsValid{
	// 20.有效的括号
	public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i)=='('||s.charAt(i)=='{'||s.charAt(i)=='[')
                stack.push(s.charAt(i));
            if(stack.isEmpty())
                return false;
            if(s.charAt(i)==')'&&stack.pop()!='(')
                return false;
             if(s.charAt(i)=='}'&&stack.pop()!='{')
                 return false;
             if(s.charAt(i)==']'&&stack.pop()!='[')
                 return false;
        }
        return stack.isEmpty();
    }
}