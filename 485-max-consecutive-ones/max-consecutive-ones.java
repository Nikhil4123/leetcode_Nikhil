class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int m=0;
        int t=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==1 ){
                t++;
            }else{
                t=0;
            }
            m=Math.max(m,t);
        }
        return m;
    }
}