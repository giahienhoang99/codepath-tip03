package week2_linkedlist_strings_arrays;

public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        ListNode cur = head;
        int length = 1;
        // traverse til cur == last node in list
        while (cur.next != null) {
            cur = cur.next;
            length++;
        }
        // cur at this point is the last node
        // make it a circular list
        cur.next = head;

        // find new head and tail
        k = k % length; //in case k >= length;
        int stepsFromHead = length - k;
        while (stepsFromHead > 0) {
            head = head.next;
            cur = cur.next;
            stepsFromHead--;
        }
        // break the circular linkedlist
        cur.next = null;
        return head;
    }
}
