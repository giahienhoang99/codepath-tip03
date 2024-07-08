package week4_review;

public class PartitionList {
    public static ListNode partition(ListNode head, int x) {
        // Edge cases: if the list is empty or has only one element, or x is out of range
        if (head == null || head.next == null || x < -100 || x > 100) {
            return head;
        }

        // Dummy nodes to act as placeholders for the new lists
        ListNode lessHead = new ListNode(0); // List for nodes less than x
        ListNode greaterHead = new ListNode(0); // List for nodes greater than or equal to x
        ListNode less = lessHead, greater = greaterHead;

        // Traverse the original list and partition it into two lists
        while (head != null) {
            if (head.val < x) {
                less.next = head;
                less = less.next;
            } else {
                greater.next = head;
                greater = greater.next;
            }
            head = head.next;
        }

        // Connect the two lists
        greater.next = null; // End the greater list to avoid cycles
        less.next = greaterHead.next; // Connect the less list with the greater list

        return lessHead.next;
    }

    // Helper method to create a linked list from an array of integers
    public static ListNode createList(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }
        return dummy.next;
    }

    // Helper method to print a linked list
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        // Test case 1
        ListNode head1 = createList(new int[]{1, 4, 3, 2, 5, 2});
        int x1 = 3;
        ListNode result1 = partition(head1, x1);
        System.out.print("Test case 1 result for x = " + x1 + ": ");
        printList(result1); // Expected output: [1, 2, 2, 4, 3, 5]

        // Test case 2
        ListNode head2 = createList(new int[]{2, 1});
        int x2 = 2;
        ListNode result2 = partition(head2, x2);
        System.out.print("Test case 2 result for x = " + x2 + ": ");
        printList(result2); // Expected output: [1, 2]
    }
}
