class Solution {
    fun maxRunTime(n: Int, batteries: IntArray): Long {
         var totalEnergy = 0L
        for (b in batteries) totalEnergy += b

        batteries.sort()
        var m = n
        var energy = totalEnergy

        for (i in batteries.size - 1 downTo 0) {
            if (batteries[i].toLong() > energy / m) {
                energy -= batteries[i]
                m--
            } else break
        }

        return energy / m
    }
}