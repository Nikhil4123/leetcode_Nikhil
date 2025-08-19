class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] ar = new int[nums1.length + nums2.length];
        int l = 0, r = 0, i = 0;

        // merge both arrays
        while (l < nums1.length && r < nums2.length) {
            if (nums1[l] < nums2[r]) {
                ar[i++] = nums1[l++];
            } else {
                ar[i++] = nums2[r++];
            }
        }
        while (l < nums1.length) {
            ar[i++] = nums1[l++];
        }
        while (r < nums2.length) {
            ar[i++] = nums2[r++];
        }

        int n = ar.length;
        if (n % 2 == 0) {
            return ((double) ar[n/2 - 1] + (double) ar[n/2]) / 2.0;
        } else {
            return ar[n/2];
        }
    }
}
