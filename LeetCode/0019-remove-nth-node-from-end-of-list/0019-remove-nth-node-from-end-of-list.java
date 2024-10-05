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
        
        int totalSize = 0;
        ListNode nowNode = head;
        while (nowNode != null) {
            totalSize++;
            nowNode = nowNode.next;
        }

        if (n == totalSize) {
            return head.next;
        }

        ListNode removePreNode = head;
        for (int i = 0; i < totalSize - n - 1; i++) {
            removePreNode = removePreNode.next;
        }

        ListNode removeNode = removePreNode.next;
        removePreNode.next = removeNode.next;

        return head;
    }
}