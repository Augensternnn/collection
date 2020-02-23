class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; } 
}
	
public class MergeTwoLists{
	// 21.合并两个有序链表
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode newList = null;
        ListNode last = null;
        while (cur1 != null && cur2 != null){
            if(cur1.val <= cur2.val){
                ListNode next = cur1.next;
                if(newList == null){
                    cur1.next = null;
                    newList = cur1;
                }else {
                    cur1.next = null;
                    last.next = cur1;
                }
                last = cur1;
                cur1 = next;
            }else {
                ListNode next = cur2.next;
                if(newList == null){
                    cur2.next = null;
                    newList = cur2;
                }else {
                    cur2.next = null;
                    last.next = cur2;
                }
                last = cur2;
                cur2 = next;
            }
        }
        if(cur1 != null){
            last.next = cur1;
        }else {
            last.next = cur2;
        }
        return newList;
    }
}