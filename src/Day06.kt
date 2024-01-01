fun main() {

    fun part1(input: List<String>): Int {
        return input.joinToString("\r\n")
            .split("\r\n\r\n")
            .map { it.split("\r\n").joinToString("") }
            .sumOf { it.toSet().size }
    }

    fun part2(input: List<String>) =
        input.joinToString("\r\n")
            .split("\r\n\r\n")
            .map { it.split("\r\n").map { it.toSet() } }
            .sumOf {
                it.drop(1).fold(it.first()) { acc, item ->
                    acc.intersect(item)
                }.size
            }

    val input = readInput("inputs/Day06")
    part1(input).println()
    part2(input).println()
}

