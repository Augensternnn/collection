package _.LeetCode.Queue;


import java.util.Stack;

/**
 * 232.用栈实现队列
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 */
public class _232_StackRealizeQueue {
    private Stack<Integer> inStack = new Stack();
    private Stack<Integer> outStack = new Stack();

    public _232_StackRealizeQueue() {

    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        checkOutStack();
        return outStack.pop();
    }

    public int peek() {
        checkOutStack();
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private void checkOutStack(){
        if(outStack.isEmpty()){
            while (!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
        }
    }
}
