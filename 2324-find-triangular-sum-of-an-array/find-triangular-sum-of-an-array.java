class Solution {
    public int triangularSum(int[] nums) {
        int c=nums.length;
        while(c>1){
            for(int i=0;i<c-1;i++){
                nums[i]=(nums[i+1]+nums[i])%10;
            }
            c--;
        }
        return nums[0];
    }
}