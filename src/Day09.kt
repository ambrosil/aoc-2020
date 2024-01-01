fun main() {

    fun isValid(entry: Map.Entry<Long, List<Long>>): Boolean {
        val n = entry.key
        val list = entry.value

        list.forEach { n1 ->
            list.forEach { n2 ->
                if (n1 + n2 == n) {
                    return true
                }
            }
        }

        return false
    }

    fun List<Long>.findInvalid(preamble: Int = 25): Long {
        return this.windowed(preamble + 1)
            .associate { it.last() to it.dropLast(1) }
            .filterNot { isValid(it) }
            .keys
            .first()
    }

    fun part1(input: List<String>): Long =
        input.map { it.toLong() }.findInvalid()

    fun part2(input: List<String>): Long {
        val numbers = input.map { it.toLong() }
        val invalid = numbers.findInvalid()
        var windowSize = 2

        while (true) {
            numbers
                .windowed(windowSize++)
                .find { it.sum() == invalid }
                ?.apply {
                    return min() + max()
                }
        }
    }

    val input = readInput("inputs/Day09")
    part1(input).println()
    part2(input).println()
}

