class Solution {
    public int findFinalValue(int[] nums, int original) {
        HashSet<Integer> sr=new HashSet<>();
        for(int i:nums){
            sr.add(i);
        }
        int v=original;
        while(sr.contains(v)){
            v*=2;
        }
        return v;
    }
}