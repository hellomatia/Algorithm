class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head; // base case, nothing to swap

        ListNode dummy = new ListNode(-1); // dummy node to handle edge cases
        dummy.next = head;

        ListNode prevNode = dummy;  // This will track the node before the pair being swapped
        ListNode firstNode = head;

        // Loop until there are at least two nodes to swap
        while (firstNode != null && firstNode.next != null) {
            ListNode secondNode = firstNode.next; // second node in the pair
            ListNode nextPair = secondNode.next;  // Next pair starts after the second node

            // Swap the two nodes
            secondNode.next = firstNode;
            firstNode.next = nextPair;
            prevNode.next = secondNode;

            // Move the pointers forward to the next pair
            prevNode = firstNode;
            firstNode = nextPair;
        }

        return dummy.next; // Return the new head
    }
}
