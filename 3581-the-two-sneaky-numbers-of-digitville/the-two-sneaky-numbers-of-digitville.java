class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int[] ar=new int [nums.length];
        int[] ans= new int [2];
        int t=0;
        for(int i : nums){
            ar[i]+=1;
        }
        for(int i=0;i<ar.length;i++){
            if(ar[i]>1) 
            {ans[t]=i;
            t++;}
        }
        return ans;
    }
}