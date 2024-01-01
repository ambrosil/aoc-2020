fun main() {
    fun part1(input: List<String>) =
        input.filter { line ->
            val (minmax, letter, pwd) = line.replace(":", "").split(" ")
            val (min, max) = minmax.split("-").map { it.toInt() }
            val count = pwd.count { it == letter.first() }
            count in min..max
        }.size

    fun part2(input: List<String>): Int {
        return input.filter { line ->
            val (minmax, letter, pwd) = line.replace(":", "").split(" ")
            val (pos1, pos2) = minmax.split("-").map { it.toInt() }
            (pwd[pos1 - 1] == letter.first()) xor (pwd[pos2 - 1] == letter.first())
        }.size
    }

    val input = readInput("inputs/Day02")
    part1(input).println()
    part2(input).println()
}

