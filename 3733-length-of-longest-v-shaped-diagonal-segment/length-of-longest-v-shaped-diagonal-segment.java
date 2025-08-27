class Solution {
    public int lenOfVDiagonal(int[][] grid) {
        int res = 0;
        res = Math.max(res, dpRtoL(grid));
        res = Math.max(res, dpLtoR(grid));
        res = Math.max(res, dpTtoB(grid));
        res = Math.max(res, dpBtoT(grid));
        return res;
    }

    private static int dpRtoL(int [][]grid){
        int R = grid.length;
        int C = grid[0].length;
        int [][]dp = new int [R][3];
        int res = 0;

        for(int c = C-1; c>=0; c--){
            int prev = 0;
            for(int r = R-1; r >=0; r--){
                int currPrev = dp[r][2];
                int val = grid[r][c];
                if(val == 0){
                    dp[r][0] = 1;
                    dp[r][1] = 1;
                    dp[r][2] = 1;
                    if(r > 0 && c < (C-1) && grid[r-1][c+1] == 2){
                        dp[r][0] = Math.max(dp[r][0], dp[r-1][0]+1);
                        dp[r][1] = Math.max(dp[r][1], dp[r-1][1]+1);
                    }
                    if(r < (R-1) && c < (C-1) && grid[r+1][c+1] == 2){
                        dp[r][1] = Math.max(dp[r][1], prev+1);
                        dp[r][2] = Math.max(dp[r][2], prev+1);
                    }
                }
                else if(val == 2){
                    dp[r][0] = 1;
                    dp[r][1] = 1;
                        dp[r][2] = 1;
                    if(r > 0 && c < (C-1) && grid[r-1][c+1] == 0){
                        dp[r][0] = Math.max(dp[r][0], dp[r-1][0]+1);
                        dp[r][1] = Math.max(dp[r][1], dp[r-1][1]+1);
                    }
                    if(r < (R-1) && c < (C-1) && grid[r+1][c+1] == 0){
                        dp[r][1] = Math.max(dp[r][1], prev+1);
                        dp[r][2] = Math.max(dp[r][2], prev+1);
                    }
                }
                else{
                    dp[r][0] = 0;
                    dp[r][1] = 0;
                    dp[r][2] = 0;
                    res = Math.max(res, 1);
                    if(r > 0 && c < (C-1) && grid[r-1][c+1] == 2){
                        res = Math.max(res, dp[r-1][1]+1);
                    }
                }
                prev = currPrev;
            }
        }
        return res;
    }

    private static int dpLtoR(int [][]grid){
        int R = grid.length;
        int C = grid[0].length;
        int [][]dp = new int [R][3];
        int res = 0;

        for(int c = 0; c<C; c++){
            int prev = 0;
            for(int r = 0; r < R; r++){
                int currPrev = dp[r][2];
                int val = grid[r][c];
    // [0,2,0,2,1,2,2,2,0,2,0]
    // [0,2,1,2,0,2,0,2,0,2,0]
    // [0,2,0,2,0,2,0,2,1,2,0]
                if(val == 0){
                    dp[r][0] = 1;
                    dp[r][1] = 1;
                    dp[r][2] = 1;
                    if(r < (R-1) && c > 0 && grid[r+1][c-1] == 2){
                        dp[r][0] = Math.max(dp[r][0], dp[r+1][0]+1);
                        dp[r][1] = Math.max(dp[r][1], dp[r+1][1]+1);
                    }
                    if(r > 0 && c > 0 && grid[r-1][c-1] == 2){
                        dp[r][1] = Math.max(dp[r][1], prev+1);
                        dp[r][2] = Math.max(dp[r][2], prev+1);
                    }
                }
               else if(val == 2){
                    dp[r][0] = 1;
                    dp[r][1] = 1;
                    dp[r][2] = 1;
                    if(r < (R-1) && c > 0 && grid[r+1][c-1] == 0){
                        dp[r][0] = Math.max(dp[r][0], dp[r+1][0]+1);
                        dp[r][1] = Math.max(dp[r][1], dp[r+1][1]+1);
                    }
                    if(r > 0 && c > 0 && grid[r-1][c-1] == 0){
                        dp[r][1] = Math.max(dp[r][1], prev+1);
                        dp[r][2] = Math.max(dp[r][2], prev+1);
                    }
                }
                else{
                    dp[r][0] = 0;
                    dp[r][1] = 0;
                    dp[r][2] = 0;
                    res = Math.max(res, 1);
                    if(r < (R-1) && c > 0 && grid[r+1][c-1] == 2){
                        res = Math.max(res, dp[r+1][1]+1);
                    }
                }
                prev = currPrev;
            }
            // print(dp);
        }
        return res;
    }

    private static int dpTtoB(int [][]grid){
        int R = grid.length;
        int C = grid[0].length;
        int [][]dp = new int [C][3];
        int res = 0;

        for(int r = 0; r < R; r++){
            int prev = 0;
            for(int c = C-1; c >= 0; c--){
                int currPrev = dp[c][2];
                int val = grid[r][c];
                if(val == 0){
                    dp[c][0] = 1;
                    dp[c][1] = 1;
                    dp[c][2] = 1;
                    if(r > 0 && c > 0 && grid[r-1][c-1] == 2){
                        dp[c][0] = Math.max(dp[c][0], dp[c-1][0]+1);
                        dp[c][1] = Math.max(dp[c][1], dp[c-1][1]+1);
                    }
                    if(r > 0 && c < (C-1) && grid[r-1][c+1] == 2){
                        dp[c][1] = Math.max(dp[c][1], prev+1);
                        dp[c][2] = Math.max(dp[c][2], prev+1);
                    }
                }
                else if(val == 2){
                    dp[c][0] = 1;
                    dp[c][1] = 1;
                    dp[c][2] = 1;
                    if(r > 0 && c > 0 && grid[r-1][c-1] == 0){
                        dp[c][0] = Math.max(dp[c][0], dp[c-1][0]+1);
                        dp[c][1] = Math.max(dp[c][1], dp[c-1][1]+1);
                    }
                    if(r > 0 && c < (C-1) && grid[r-1][c+1] == 0){
                        dp[c][1] = Math.max(dp[c][1], prev+1);
                        dp[c][2] = Math.max(dp[c][2], prev+1);
                    }
                }
                else{
                    dp[c][0] = 0;
                    dp[c][1] = 0;
                    dp[c][2] = 0;
                    res = Math.max(res, 1);
                    if(r > 0 && c > 0 && grid[r-1][c-1] == 2){
                        res = Math.max(res, dp[c-1][1]+1);
                    }
                }
                prev = currPrev;
            }
        }
        return res;
    }

    private static int dpBtoT(int [][]grid){
        int R = grid.length;
        int C = grid[0].length;
        int [][]dp = new int [C][3];
        int res = 0;

        for(int r = R-1; r >= 0; r--){
            int prev = 0;
            for(int c = 0; c < C; c++){
                int currPrev = dp[c][2];
                int val = grid[r][c];
                if(val == 0){
                    dp[c][0] = 1;
                    dp[c][1] = 1;
                    dp[c][2] = 1;
                    if(r < (R-1) && c < (C-1) && grid[r+1][c+1] == 2){
                        dp[c][0] = Math.max(dp[c][0], dp[c+1][0]+1);
                        dp[c][1] = Math.max(dp[c][1], dp[c+1][1]+1);
                    }
                    if(r < (R-1) && c > 0 && grid[r+1][c-1] == 2){
                        dp[c][1] = Math.max(dp[c][1], prev+1);
                        dp[c][2] = Math.max(dp[c][2], prev+1);
                    }
                }
                else if(val == 2){
                    dp[c][0] = 1;
                    dp[c][1] = 1;
                    dp[c][2] = 1;
                    if(r < (R-1) && c < (C-1) && grid[r+1][c+1] == 0){
                        dp[c][0] = Math.max(dp[c][0], dp[c+1][0]+1);
                        dp[c][1] = Math.max(dp[c][1], dp[c+1][1]+1);
                    }
                    if(r < (R-1) && c > 0 && grid[r+1][c-1] == 0){
                        dp[c][1] = Math.max(dp[c][1], prev+1);
                        dp[c][2] = Math.max(dp[c][2], prev+1);
                    }
                }
                else{
                    dp[c][0] = 0;
                    dp[c][1] = 0;
                    dp[c][2] = 0;
                    res = Math.max(res, 1);
                    if(r < (R-1) && c < (C-1) && grid[r+1][c+1] == 2){
                        res = Math.max(res, dp[c+1][1]+1);
                    }
                }
                prev = currPrev;
            }
        }
        return res;
    }

    private static void print(int [][]dp){
        for(int []d :dp){
            System.out.print(Arrays.toString(d));
        }
        System.out.println();
    }



}