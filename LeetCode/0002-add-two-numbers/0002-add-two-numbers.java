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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int bonusNum = 0;
        ListNode result = new ListNode();
        ListNode now = result;

        while (l1 != null || l2 != null || bonusNum != 0) {
            int num = bonusNum;

            if (l1 != null) {
                num += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                num += l2.val;
                l2 = l2.next;            
            }

            if (num >= 10) {
                num = num % 10;
                bonusNum = 1;
            } else {
                bonusNum = 0;
            }
            
            now.val = num;

            if (l1 != null || l2 != null || bonusNum != 0) {
                ListNode newListNode = new ListNode();
                now.next = newListNode;
                now = now.next;
            }
        }

        return result;
    }
}