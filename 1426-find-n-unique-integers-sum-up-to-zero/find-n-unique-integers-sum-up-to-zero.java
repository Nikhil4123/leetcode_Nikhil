class Solution {
    public int[] sumZero(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = ((n % 2 == 1) && i == n / 2) ? 0 : (i < n / 2 ? i + 1 : -(n - i));
        }
        return a;
    }
}