class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] result = new int[m * n];
        int idx = 0;

        // total diagonals = m + n - 1
        for (int s = 0; s < m + n - 1; s++) {
            if (s % 2 == 0) {
                // move upward
                int i = Math.min(s, m - 1);
                int j = s - i;
                while (i >= 0 && j < n) {
                    result[idx++] = mat[i][j];
                    i--;
                    j++;
                }
            } else {
                // move downward
                int j = Math.min(s, n - 1);
                int i = s - j;
                while (j >= 0 && i < m) {
                    result[idx++] = mat[i][j];
                    i++;
                    j--;
                }
            }
        }

        return result;
    }
}
