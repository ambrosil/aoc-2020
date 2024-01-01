fun main() {

    fun part1(input: List<String>): Int {
        val jolts = input.map { it.toInt() }
        val end = jolts.max()
        var current = 0
        val counters = mutableMapOf(1 to 0, 3 to 0)

        while (current != end) {
            counters.keys.find { current + it in jolts }
                ?.let {
                    current += it
                    counters[it] = counters.getValue(it) + 1
                }
        }

        return counters.getValue(1) * (counters.getValue(3) + 1)
    }

    fun part2(input: List<String>): Long {
        val pathsByJolt: MutableMap<Int,Long> = mutableMapOf(0 to 1L)
        val jolts = input.map { it.toInt() }.plus(0).toMutableList().apply { this += max() + 3 }.sorted()

        jolts.drop(1).forEach { jolt ->
            pathsByJolt[jolt] = (1..3).sumOf { pathsByJolt[jolt - it] ?: 0 }
        }

        return pathsByJolt.getValue(jolts.last())
    }

    val input = readInput("inputs/Day10")
    part1(input).println()
    part2(input).println()
}

