class Solution {
    public int maxSumDivThree(int[] nums) {
        int[] dp = new int[]{0, -1000000000, -1000000000};

        for (int x : nums) {
            int[] curr = dp.clone();

            for (int r = 0; r < 3; r++) {
                int nr = (r + x) % 3;
                dp[nr] = Math.max(dp[nr], curr[r] + x);
            }
        }

        return dp[0];
    }
}