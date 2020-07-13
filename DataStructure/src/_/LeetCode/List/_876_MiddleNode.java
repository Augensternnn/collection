package _.LeetCode.List;

/**
 * 链表的中间节点
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 */
public class _876_MiddleNode {
    public ListNode middleNode(ListNode head) {
        if(head == null || head.next == null)
            return head;
        int sum = 0,middle;
        ListNode cur = head;
        while (cur != null){
            sum++;
            cur = cur.next;
        }
        cur = head;
        if(sum % 2 == 0){
            middle = sum / 2 + 1;
            while (--middle > 1){
                cur = cur.next;
            }
        }else {
            middle = (int)Math.ceil(sum / 2);
            while (--middle > 0){
                cur = cur.next;
            }
        }
        head = cur.next;
        cur = null;
        return head;
    }

    //数组
    public ListNode middleNode_1(ListNode head) {
        ListNode[] arr = new ListNode[100];
        int t = 0;
        while (head != null){
            arr[t++] = head;
            head = head.next;
        }
        return arr[Math.round(t/2)];
    }

    //单指针法
    public ListNode middleNode_2(ListNode head) {
        int sum = 0, k = 0;
        ListNode cur = head;
        while (cur != null){
            ++sum;
            cur = cur.next;
        }
        cur = head;
        while (k < sum / 2){
            ++k;
            cur = cur.next;
        }
        return cur;
    }

    //快慢指针
    public ListNode middleNode_3(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode middleNode_4(ListNode head) {
        return null;
    }

    public ListNode middleNode_5(ListNode head) {
        return null;
    }
}
