class Solution {
    public int[] twoSum(int[] nums, int target) {
        int l=0,r=1;
        while(l<nums.length){
            if(nums[l]+nums[r]==target){
                return new int[] {l,r};
            }else if(r==nums.length-1){
                l++;
                r=l+1;
            }else{
                r++;
            }
        }
        return new int[] {-1,-1};
    }
}