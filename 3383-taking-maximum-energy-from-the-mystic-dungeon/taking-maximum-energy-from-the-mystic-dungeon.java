class Solution {
    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        int ans = Integer.MIN_VALUE;

        // Start from the end and move backwards
        for (int i = n - 1; i >= 0; i--) {
            if (i + k < n) 
                energy[i] += energy[i + k];  // reuse energy[] as dp[]
            ans = Math.max(ans, energy[i]);
        }

        return ans;
    }
}
