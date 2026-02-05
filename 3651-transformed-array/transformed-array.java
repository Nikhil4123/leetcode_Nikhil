class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int idx = (i + nums[i]) % n;
            if (idx < 0) idx += n;
            ans[i] = nums[idx];
        }
        return ans;
    }
}
