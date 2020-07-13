package _.LeetCode.List;

/**
 * 83.删除排序链表中的重复元素
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 */
public class _83_DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null)
            return null;
        int val = head.val;
        ListNode ans = new ListNode(-2^31);
        ListNode node = ans;
        ListNode cur = head;
        while (cur != null){
            val = cur.val;
            if(node.val != val) {
                node.next = new ListNode(val);
                node = node.next;
            }
            cur = cur.next;
        }
        return ans.next;
    }

    public ListNode deleteDuplicates_(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null){
            if(cur.next.val == cur.val)
                cur.next = cur.next.next;
            else
                cur = cur.next;
        }
        return head;
    }
}
