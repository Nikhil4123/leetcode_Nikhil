class Solution {
    public int totalMoney(int n) {
        int s = 28; // sum of 1..7
        int r = n % 7;
        int w = n / 7; // full weeks
        int ans = 0;

        // Add full weeks
        for (int i = 0; i < w; i++) {
            ans += s + (i * 7); // each next week adds +7
        }

        // Add remaining days (your loop logic fixed)
        int m = 1 + w; // starting money for remaining days
        for (int i = 0; i < r; i++) {
            ans += m;
            m++;
        }

        return ans;
    }
}
