class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int k = 1; k <= n; k++) {

            if (n % k != 0)
                continue;

            boolean possible = true;
            int previousMax = Integer.MIN_VALUE;

            for (int start = 0; start < n; start += k) {

                int end = start + k;
                int drops = 0;
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;

                for (int i = start; i < end; i++) {
                    min = Math.min(min, nums[i]);
                    max = Math.max(max, nums[i]);

                    if (i < end - 1 && nums[i] > nums[i + 1]) {
                        drops++;
                    }
                }

                // Circular check
                if (nums[end - 1] > nums[start]) {
                    drops++;
                }

                if (drops > 1) {
                    possible = false;
                    break;
                }

                // Check order between blocks
                if (start > 0 && previousMax > min) {
                    possible = false;
                    break;
                }

                previousMax = max;
            }

            if (possible) {
                ans += k;
            }
        }

        return ans;
    }
}