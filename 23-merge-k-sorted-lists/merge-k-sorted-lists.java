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
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length ==0) return null;
        ListNode head = lists[0];
        for(int i=1;i<lists.length;i++){
            head=mearge2(head,lists[i]);
        }
        return head;
    }
    public ListNode mearge2(ListNode l1 , ListNode l2){
        ListNode dummy = new ListNode(-1);
        ListNode tl=dummy;
        ListNode l1h=l1 , l2h=l2;
        while(l1h!=null && l2h!=null){
            if(l1h.val<l2h.val){
                tl.next=l1h;
                l1h=l1h.next;
            }else{
                tl.next=l2h;
                l2h=l2h.next; 
            }
            tl=tl.next;
        }
        if(l1h!=null) tl.next=l1h;
        if(l2h!=null) tl.next=l2h;
        return dummy.next;
    }
}