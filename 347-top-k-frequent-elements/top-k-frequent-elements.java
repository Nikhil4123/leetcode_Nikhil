class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer , Integer> m=new HashMap<>();
        for(int i : nums){
            m.put(i,m.getOrDefault(i,0)+1);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(m.entrySet());
        list.sort((a,b)-> b.getValue() - a.getValue());
        int[]ans = new int[k];
        for(int i=0;i<k;i++){
            ans[i]=list.get(i).getKey();
        }
        return ans;
    }
}