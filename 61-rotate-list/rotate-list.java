class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0) return head;

        ListNode cur = head;
        int len = 1;

        while(cur.next != null){
            cur = cur.next;
            len++;
        }

        k = k % len;
        if(k == 0) return head;

        cur.next = head;

        int steps = len - k;
        ListNode newTail = head;

        for(int i = 1; i < steps; i++){
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}
