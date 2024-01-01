import utils.Direction.EAST
import utils.Direction.SOUTH
import utils.Point

fun main() {

    fun countTrees(input: List<String>, right: Int, down: Int): Long {
        val grid = mutableMapOf<Point, Char>()
        val repetitions = (input.size * right / input[0].length) + 1

        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                repeat (repetitions ) {
                    grid[Point((it * line.length) + x, y)] = c
                }
            }
        }

        var current = Point(0, 0)
        var counterTree = 0L

        while (current.y < input.size) {
            current = current.move(EAST, right).move(SOUTH, down)
            if (grid[current] == '#') {
                counterTree++
            }
        }

        return counterTree
    }

    fun part1(input: List<String>) =
        countTrees(input, 3, 1)

    fun part2(input: List<String>) =
        countTrees(input, 1, 1) *
        countTrees(input, 3, 1) *
        countTrees(input, 5, 1) *
        countTrees(input, 7, 1) *
        countTrees(input, 1, 2)

    val input = readInput("inputs/Day03")
    part1(input).println()
    part2(input).println()
}

