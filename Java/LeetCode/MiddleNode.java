class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}

public class MiddleNode{
	// 876.链表的中间结点
	public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null){
            fast = fast.next;
            if(fast == null)
                break;
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}