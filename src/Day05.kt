import kotlin.math.ceil
import kotlin.math.floor

fun main() {

    fun elaborate(seat: String): Pair<Int, Int> {
        val row = seat.take(7).fold(0..127) { acc, c ->
            val middle = (acc.first + acc.last) / 2.0
            when (c) {
                'F' -> acc.first..floor(middle).toInt()
                'B' -> ceil(middle).toInt()..acc.last
                else -> error("wrong seat $c")
            }
        }

        val column = seat.takeLast(3).fold(0..7) { acc, c ->
            val middle = (acc.first + acc.last) / 2.0
            when (c) {
                'L' -> acc.first..floor(middle).toInt()
                'R' -> ceil(middle).toInt()..acc.last
                else -> error("wrong seat $c")
            }
        }

        return row.first to column.first
    }

    fun part1(input: List<String>) =
        input.maxOfOrNull {
            val (row, column) = elaborate(it)
            row * 8 + column
        }

    fun part2(input: List<String>): Int {
        val seats = input.map {
            val (row, column) = elaborate(it)
            row * 8 + column
        }.sorted()

        val index = seats.zipWithNext { a, b -> b - a }.indexOf(2)
        return seats[index] + 1
    }

    val input = readInput("inputs/Day05")
    part1(input).println()
    part2(input).println()
}

