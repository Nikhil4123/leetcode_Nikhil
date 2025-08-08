class Solution {
    public int maxProfit(int[] prices) {
        int l = 0;
        int r = 1;
        int maxp = 0;
        while (r < prices.length ) {
            if (prices[l] < prices[r]) {
                int p = prices[r] - prices[l];
                maxp = Math.max(p, maxp);
            } else {
                l = r;
            }
            r++;
        }
        return maxp;
    }
}