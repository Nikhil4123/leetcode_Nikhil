class Solution {
    public long subArrayRanges(int[] nums) {
        long ans = 0;

        for (int l = 0; l < nums.length; l++) {
            int minc = nums[l];
            int maxc = nums[l];

            for (int r = l; r < nums.length; r++) {
                minc = Math.min(minc, nums[r]);
                maxc = Math.max(maxc, nums[r]);

                ans += maxc - minc;
            }
        }

        return ans;
    }
}
