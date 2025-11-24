class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> ans = new ArrayList<>();
        int cur = 0;

        for (int bit : nums) {
            cur = (cur * 2 + bit) % 5;
            ans.add(cur == 0);
        }

        return ans;
    }
}
