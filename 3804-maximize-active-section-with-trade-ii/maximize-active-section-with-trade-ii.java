class Solution {

    /** {}{}{} N S }
     * For each query [L, R], returns the maximum number of "active" (1) positions
     * achievable if we're allowed to flip one 0-segment to 1s inside that range,
     * where the flipped segment must sit between two 1-segments (a 0-1-0 -> 1-1-1 style trade).
     *
     * Approach:
     * 1. Compress the string into contiguous segments (runs of same character).
     * 2. Map every original index to the segment that contains it (O(1) lookup).
     * 3. For every 1-segment (except the first/last), precompute the "gain" you'd get
     *    if you could merge its left and right 0-neighbors into it
     *    (gain = length of left 0-run + length of right 0-run).
     * 4. Build a Sparse Table over these gains so any range-max query can be
     *    answered in O(1) after O(n log n) preprocessing.
     * 5. For each query, find the max gain among segments strictly inside the range,
     *    handling the two boundary segments specially since they may be partially
     *    cut off by L/R, then add that gain to the total count of 1s in the string.
     */
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();

        // Count of all '1's in the original string — this is our baseline answer
        // before any trade/gain is applied.
        int totalOnes = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                totalOnes++;
            }
        }

        // ---- Step 1: Run-length encode the string into segments ----
        // Each segment records: its type (0 or 1), start index, and end index.
        List<Integer> typeList = new ArrayList<>();
        List<Integer> startList = new ArrayList<>();
        List<Integer> endList = new ArrayList<>();

        for (int i = 0; i < n; ) {
            int j = i;
            // Extend j while characters match the current run's character
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            typeList.add(s.charAt(i) - '0'); // 0 or 1
            startList.add(i);
            endList.add(j - 1);
            i = j; // jump to the start of the next run
        }

        // Convert to arrays for fast indexed access during preprocessing/queries.
        int N = typeList.size();
        int[] type = new int[N];
        int[] start = new int[N];
        int[] endIdx = new int[N];
        for (int i = 0; i < N; i++) {
            type[i] = typeList.get(i);
            start[i] = startList.get(i);
            endIdx[i] = endList.get(i);
        }

        // ---- Step 2: Map every original string index to its segment index ----
        // Lets us convert a query's [L, R] positions into segment indices in O(1) per position.
        int[] posToSeg = new int[n];
        for (int i = 0; i < N; i++) {
            for (int j = start[i]; j <= endIdx[i]; j++) {
                posToSeg[j] = i;
            }
        }

        // ---- Step 3: Precompute the gain for every valid 1-segment ----
        // A 1-segment sandwiched between two 0-segments can "absorb" both neighbors
        // if flipped, since neighbors are 0s becoming part of one continuous 1-run.
        // gain = length of left 0-segment + length of right 0-segment.
        // Skip the very first and last segment since they don't have both neighbors.
        int[] ans = new int[N];
        for (int i = 1; i < N - 1; i++) {
            if (type[i] == 1) {
                ans[i] = (endIdx[i - 1] - start[i - 1] + 1) + (endIdx[i + 1] - start[i + 1] + 1);
            }
        }

        // ---- Step 4: Build a Sparse Table over `ans[]` for O(1) range-max queries ----

        // logTable[x] = floor(log2(x)), used to quickly find the largest power of 2
        // that fits within any given range length.
        int[] logTable = new int[N + 1];
        for (int i = 2; i <= N; i++) {
            logTable[i] = logTable[i / 2] + 1;
        }

        // K = number of levels needed in the sparse table (log2(N) + 1)
        int K = logTable[N] + 1;

        // st[j][i] = max value of ans[] in the range starting at i with length 2^j
        int[][] st = new int[K][N];

        // Base case: ranges of length 1 (2^0) are just the value itself
        for (int i = 0; i < N; i++) {
            st[0][i] = ans[i];
        }

        // Build up: each level combines two overlapping half-ranges from the level below
        for (int j = 1; j < K; j++) {
            for (int i = 0; i + (1 << j) <= N; i++) {
                st[j][i] = Math.max(st[j - 1][i], st[j - 1][i + (1 << (j - 1))]);
            }
        }

        // ---- Step 5: Answer each query using the segments + sparse table ----
        List<Integer> res = new ArrayList<>();

        for (int[] q : queries) {
            int L = q[0];
            int R = q[1];

            // Convert query positions into segment indices
            int segL = posToSeg[L];
            int segR = posToSeg[R];

            // If the range doesn't span at least 3 segments, there's no room for a
            // 0-1-0 pattern to exploit, so no gain is possible — return the baseline.
            if (segR - segL < 2) {
                res.add(totalOnes);
                continue;
            }

            int maxGain = 0;

            // The segment right after segL and right before segR may be partially
            // cut off by L or R, so they need special handling (can't just use
            // their full precomputed segment lengths).
            maxGain = Math.max(maxGain, evaluateEdge(segL + 1, L, R, segL, segR, type, start, endIdx));
            maxGain = Math.max(maxGain, evaluateEdge(segR - 1, L, R, segL, segR, type, start, endIdx));

            // Any segments strictly between these two edge segments are fully
            // contained in the query range, so we can safely use the sparse table
            // to get their max precomputed gain in O(1).
            if (segL + 2 <= segR - 2) {
                int L_idx = segL + 2;
                int R_idx = segR - 2;
                int j = logTable[R_idx - L_idx + 1];
                // Combine two overlapping blocks of size 2^j to cover [L_idx, R_idx]
                int rmqVal = Math.max(st[j][L_idx], st[j][R_idx - (1 << j) + 1]);
                maxGain = Math.max(maxGain, rmqVal);
            }

            // Final answer for this query = original 1-count + best gain found
            res.add(totalOnes + maxGain);
        }

        return res;
    }

    /**
     * Computes the gain for a boundary segment `i` (the segment immediately next to
     * segL or segR), accounting for the fact that its neighboring segments might be
     * cut short by the query's L or R boundary rather than using their full length.
     *
     * Returns 0 if:
     *  - i is out of valid bounds (equal to or past segL/segR), or
     *  - segment i is not a 1-segment (only 1-segments can be "gained into").
     */
    private int evaluateEdge(int i, int L, int R, int segL, int segR, int[] type, int[] start, int[] endIdx) {
        if (i <= segL || i >= segR) return 0;
        if (type[i] == 0) return 0;

        // Length of the left neighbor segment (i - 1).
        // If that neighbor is exactly segL (the segment containing L), it may be
        // partially outside the query range, so clip it to start at L instead of
        // its true start.
        int leftLen = 0;
        if (i - 1 == segL) leftLen = Math.max(0, endIdx[i - 1] - L + 1);
        else leftLen = endIdx[i - 1] - start[i - 1] + 1;

        // Length of the right neighbor segment (i + 1).
        // Same clipping logic applies if that neighbor is segR (the segment containing R).
        int rightLen = 0;
        if (i + 1 == segR) rightLen = Math.max(0, R - start[i + 1] + 1);
        else rightLen = endIdx[i + 1] - start[i + 1] + 1;

        return leftLen + rightLen;
    }
}