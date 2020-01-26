class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}

public class ReverseList{
	// 206.反转链表
	public ListNode reverseList(ListNode head) {
        ListNode newList = null;
        ListNode cur = head;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = newList;
            newList = cur;
            cur = next;
        }
        return newList;
    }
}