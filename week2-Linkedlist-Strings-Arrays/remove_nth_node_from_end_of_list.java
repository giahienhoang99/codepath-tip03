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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // dummy 1 moves n+1 steps ahead of dummy 2
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }
        // move both until dummy 1 is the last node
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        // then dummy 2 is right before the to-be-removed node
        ListNode next = second.next;
        second.next = next.next;

        return dummy.next;
    }
}