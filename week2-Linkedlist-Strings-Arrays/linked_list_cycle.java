/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    public boolean hasCycleBruteForce(ListNode head) {
        // edge cases
        if (head == null || head.next == null) {
            return false;
        }

        // create set for containing visited nodes
        Set<ListNode> visited = new HashSet<>();

        // create a ListNode
        ListNode current = head;

        // traverse linkedlist
        while (current != null) {
            if (!visited.contains(current)) {
                // track visited nodes by adding to the set
                visited.add(current);
                // shift the current node forward
                current = (current.next != null) ? current.next : null;
            } else {
                return true;
            }
        }

        return false;
    }
}