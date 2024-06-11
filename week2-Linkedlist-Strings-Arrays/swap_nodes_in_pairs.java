/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode start = new ListNode(0);
        start.next = head;

        ListNode prev = start;
        ListNode cur = head;

        while (cur != null && cur.next != null) {
            ListNode next = cur.next;
            // swapping
            prev.next = next;
            cur.next = next.next;
            next.next = cur;
            // move to da next node
            prev = cur;
            cur = cur.next;
        }

        return start.next;
    }
}