class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}

public class AddTwoNumbers{
	// 2.两数相加
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newList = new ListNode(0);
        ListNode cur = newList;
        int tmp = 0;
        while (l1 != null || l2 != null){
            int x =(l1 != null) ? l1.val : 0;
            int y =(l2 != null) ? l2.val : 0;
            int sum = x + y + tmp;
            tmp = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if(l1 != null)
                l1 = l1.next;
            else
                l1 = null;
            if(l2 != null)
                l2 = l2.next;
            else
                l2 = null;
        }
        if(tmp > 0)
            cur.next = new ListNode(tmp);
        return newList.next;
    }
}