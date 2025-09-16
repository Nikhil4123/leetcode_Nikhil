class Solution {
    fun replaceNonCoprimes(nums: IntArray): List<Int> {
        val result = mutableListOf<Int>()
        for (num in nums) {
            result.add(num)
            while (result.size > 1) {
                val a = result[result.size - 1]
                val b = result[result.size - 2]
                val g = gcd(a, b)
                if (g > 1) {
                    result.removeAt(result.size - 1)
                    result.removeAt(result.size - 1)
                    val lcm = a.toLong() / g * b
                    result.add(lcm.toInt())
                } else {
                    break
                }
            }
        }
        return result
    }

    private fun gcd(a: Int, b: Int): Int {
        var x = a
        var y = b
        while (y != 0) {
            val tmp = y
            y = x % y
            x = tmp
        }
        return x
    }
}