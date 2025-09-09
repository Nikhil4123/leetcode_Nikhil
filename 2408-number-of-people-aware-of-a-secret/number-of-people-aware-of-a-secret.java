class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        final int MOD = 1_000_000_007; // to keep numbers small

        // dp[i] = number of people who first learn the secret on day i
        long[] dp = new long[n + 1];
        dp[1] = 1; // on day 1, exactly 1 person knows the secret

        long window = 0; // keeps track of "active sharers"

        for (int i = 2; i <= n; i++) {
            int startSharing = i - delay;  // people who can start telling today
            int stopSharing  = i - forget; // people who forgot today

            // add new sharers
            if (startSharing >= 1) 
                window = (window + dp[startSharing]) % MOD;

            // remove people who forgot
            if (stopSharing >= 1) 
                window = (window - dp[stopSharing] + MOD) % MOD;

            // total new people learning today
            dp[i] = window;
        }

        // at the end, sum people who still remember the secret
        long ans = 0;
        for (int i = n - forget + 1; i <= n; i++) {
            if (i >= 1) ans = (ans + dp[i]) % MOD;
        }

        return (int) ans;
    }
}
