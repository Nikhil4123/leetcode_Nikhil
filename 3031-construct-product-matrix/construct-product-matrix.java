class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        final int MOD = 12345;
        int rows = grid.length, cols = grid[0].length;
        int[][] res = new int[rows][cols];
        long suf = 1;
        // Backward pass: suffix products
        for (int i = rows - 1; i >= 0; --i) {
            for (int j = cols - 1; j >= 0; --j) {
                res[i][j] = (int) suf;
                suf = suf * grid[i][j] % MOD;
            }
        }
        long pre = 1;
        // Forward pass: multiply by prefix
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                res[i][j] = (int) (res[i][j] * pre % MOD);
                pre = pre * grid[i][j] % MOD;
            }
        }
        return res;
    }
}
