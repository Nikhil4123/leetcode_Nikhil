class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long zeroc=0;
        long consz=0;
        for(int n : nums){
            if(n==0){
                consz++;
                zeroc+=consz;
            }else{
                consz=0;
            }
        }
        return zeroc;
    }
}