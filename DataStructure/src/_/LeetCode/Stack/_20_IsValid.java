package _.LeetCode.Stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * 20.有效的括号
 * https://leetcode-cn.com/problems/valid-parentheses/
 */
public class _20_IsValid {
    public boolean isValid(String s) {
        if(s == "")
            return true;
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            if(stack.isEmpty()) {
                stack.push(check(s.charAt(i)));
            }else {
                int str = check(s.charAt(i));
                int c = stack.peek();
                if((c-str) == 1)
                    stack.pop();
                else
                    stack.push(str);
            }
        }
        return stack.isEmpty();
    }
    private int check(char c){
        switch (c){
            case '(':return 22;
            case ')':return 21;
            case '{':return 12;
            case '}':return 11;
            case '[':return 2;
            case ']':return 1;
        }
        return -1;
    }

    /**
     * 方法二
     */
    private HashMap<Character,Character> mappings;
    public _20_IsValid() {
        this.mappings = new HashMap<Character, Character>();
        this.mappings.put(')','(');
        this.mappings.put('}','{');
        this.mappings.put(']','[');
    }
    public boolean isValid_1(String s){
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(this.mappings.containsKey(c)){
                char topElement = stack.isEmpty() ? '#' : stack.pop();
                if(topElement != this.mappings.get(c))
                    return false;
            }else
                stack.push(c);
        }
        return stack.isEmpty();
    }

    /**
     * 方法三
     */
    public boolean isValid_2(String s) {
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else { // 右括号
                if (stack.isEmpty()) return false;
                char left = stack.pop();
                if (left == '(' && c != ')') return false;
                if (left == '{' && c != '}') return false;
                if (left == '[' && c != ']') return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 方法四
     */
    public boolean isValid_3(String s){
        while (s.contains("{}") || s.contains("[]") || s.contains("()")){
            s = s.replace("{}","");
            s = s.replace("[]","");
            s =s.replace("()","");
        }
        return s.isEmpty();
    }
}

