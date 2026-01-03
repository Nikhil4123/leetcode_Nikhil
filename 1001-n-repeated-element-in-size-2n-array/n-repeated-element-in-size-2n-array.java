class Solution {
    public int repeatedNTimes(int[] nums) {
        int a=nums.length/2;
        HashMap<Integer , Integer> m = new HashMap<>();
        for(int i : nums){
            m.put(i,m.getOrDefault(i,0)+1);
        }
        for(Map.Entry<Integer , Integer> r : m.entrySet()){
            if(r.getValue()==a){
                return r.getKey();
            }
        }
        return -1;
    }
}