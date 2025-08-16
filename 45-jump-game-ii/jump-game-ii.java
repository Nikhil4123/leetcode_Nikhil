import java.util.*;

class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int mx = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0}); // index = 0, jumps = 0

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int i = curr[0], d = curr[1];

            if (i == n - 1) return d; // reached last index

            int h = Math.min(n - 1, i + nums[i]); // max jump from i
            int l = Math.max(i + 1, mx + 1);      // start from new unexplored index

            for (int k = l; k <= h; k++) {
                q.add(new int[]{k, d + 1});
                mx = k; // mark as visited up to k
            }
        }
        return -1;
    }
}
