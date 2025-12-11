class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        int ans = 0;
        int[] maxR = new int[n + 1];
        int[] minR = new int[n + 1];
        int[] maxC = new int[n + 1];
        int[] minC = new int[n + 1];

        Arrays.fill(minR, n+1);
        Arrays.fill(minC, n+1);


        for(int[] p : buildings){
            int x = p[0];
            int y = p[1];
            maxR[y] = Math.max(maxR[y], x);
            minR[y] = Math.min(minR[y], x);
            maxC[x] = Math.max(maxC[x], y);
            minC[x] = Math.min(minC[x], y);

        }

        for(int[] i: buildings){
            int x = i[0];
            int y = i[1];
            if(x > minR[y] && x < maxR[y] && y > minC[x] && y < maxC[x])
                ans++;
        }

        return ans;        
    }
}