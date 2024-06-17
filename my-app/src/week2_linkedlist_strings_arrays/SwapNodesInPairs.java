package week2_linkedlist_strings_arrays;

public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode start = new ListNode(-1);
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

    public ListNode swapPairs2(ListNode head) {
        ListNode d = new ListNode(-1);
        d.next = head;
        // initialize  one temp node and mark it as prev node
        ListNode prev = d;
        while(head != null && head.next!=null){
            // get the current node and next node
            ListNode a = head;
            ListNode b = head.next;
            // now previous node's next node becomes b
            prev.next = b;
            //  connect b nodes next to a nodes next to preserve chaining
            a.next = b.next;
            // to make a node comes after b,
            b.next = a;
            // now a node becomes previous
            prev = a;
            // iterate to next pair of nodes
            head= a.next;
        }
        return d.next;
    }
}
