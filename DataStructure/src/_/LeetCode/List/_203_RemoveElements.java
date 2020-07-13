package _.LeetCode.List;

/**
 * 203.移除链表元素
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 */
public class _203_RemoveElements {
    public ListNode removeElements(ListNode head, int val){
        ListNode first = new ListNode(0);
        first.next = head;
        ListNode prev=first,cur=head;
        while (cur != null){
            if(cur.val == val)
                prev.next = cur.next;
            else
                prev = cur;
            cur = cur.next;
        }
        return first.next;
    }
}
