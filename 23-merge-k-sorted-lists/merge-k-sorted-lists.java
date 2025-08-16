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
        if (lists == null || lists.length == 0) return null;
        ListNode head=lists[0];
        for(int i=1;i<lists.length;i++){
            head=mearge2lists(head,lists[i]);
        }
        return head;
    }
    public ListNode mearge2lists(ListNode l1 , ListNode l2){
        ListNode dum=new ListNode(0);
        ListNode hd=dum;
        while(l1!=null && l2!=null){
            if(l1.val<l2.val){
                hd.next=l1;
                l1=l1.next;
            }else{
                hd.next=l2;
                l2=l2.next;
            }
            hd=hd.next;
        }
        if(l1!=null) hd.next=l1;
        if(l2!=null) hd.next=l2;
        return dum.next;
    }
}