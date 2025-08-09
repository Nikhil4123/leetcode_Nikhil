class Solution {
    public boolean canJump(int[] nums) {
        int p=0;
        for(int i=0;i<nums.length;i++){
            if(i>p) return false;
            p=Math.max(p,i+nums[i]);
        }
        return true;
    }
}