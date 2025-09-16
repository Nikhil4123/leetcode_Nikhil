class Solution {
    fun moveZeroes(nums: IntArray) {
        var l = 0
        var r = 1
        while (r < nums.size) {
            if (nums[l] == 0) {
                if (nums[r] != 0) {
                    val t = nums[l]
                    nums[l] = nums[r]
                    nums[r] = t
                    l++
                }
                r++
            } else {
                l++
                r++
            }
        }
    }
}
