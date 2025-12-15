class Solution {
    public long getDescentPeriods(int[] prices) {
        long count = 1;   
        long len = 1;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i - 1] - prices[i] == 1) {
                len++;
            } else {
                len = 1;
            }
            count += len;
        }
        return count;
    }
}
