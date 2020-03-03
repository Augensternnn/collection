class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}

public class RemoveElements{
	// 203.移除链表元素
	public ListNode removeElements(ListNode head, int val) {
        if(head == null)
            return null;
        ListNode prev = head;
        ListNode cur = head.next;
        while(cur != null){
            if(cur.val == val)
                prev.next = cur.next;
            else
                prev = cur;
            cur = cur.next;
        }
        if(head.val == val)
            head = head.next;
        return head;
    }
}