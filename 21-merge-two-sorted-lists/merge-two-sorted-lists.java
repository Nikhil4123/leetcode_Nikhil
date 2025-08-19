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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1==null&&list2==null) return list1;
        ListNode cl1=list1;
        ListNode cl2=list2;
        ListNode dummy = new ListNode(-1);
        ListNode dum=dummy;
        while(cl1!=null && cl2!=null){
            if(cl1.val<cl2.val){
                dum.next=cl1;
                cl1=cl1.next;
            }else{
                dum.next=cl2;
                cl2=cl2.next;
            }
            dum=dum.next;
        }
        if(cl1!=null) dum.next=cl1;
        if(cl2!=null) dum.next=cl2;
        return dummy.next;
    }
}