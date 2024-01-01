fun main() {
    fun part1(input: List<String>): Int {
        val values = input.map { it.toInt() }

        values.forEach { n1 ->
            values.forEach { n2 ->
                if (n1 + n2 == 2020) {
                    return n1 * n2
                }
            }
        }

        return 0
    }

    fun part2(input: List<String>): Int {
        val values = input.map { it.toInt() }

        values.forEach { n1 ->
            values.forEach { n2 ->
                values.forEach { n3 ->
                    if (n1 + n2 + n3 == 2020) {
                        return n1 * n2 * n3
                    }
                }
            }
        }

        return 0
    }

    val input = readInput("inputs/Day01")
    part1(input).println()
    part2(input).println()
}

