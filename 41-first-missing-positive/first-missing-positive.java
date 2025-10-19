class Solution {
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);

        // Initialize the smallest positive number to check
        int smallestMissing = 1;

        for (int num : nums) {
            if (num == smallestMissing) {
                smallestMissing++; // Increment to check the next positive number
            }
        }

        // Return the first missing positive number
        return smallestMissing;
    }
}