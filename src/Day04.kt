fun main() {

    fun parseInput(input: List<String>) = input
        .joinToString("\r\n")
        .split("\r\n\r\n")
        .map { it.split("\r\n").joinToString(" ") }
        .map { it.split(" ").map { it.split(":") } }
        .map { it.associate { (key, value) -> key to value } }

    fun part1(input: List<String>): Int {
        val needed = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
        return parseInput(input).count { it.keys.containsAll(needed) }
    }

    fun isValid(entry: Map.Entry<String, String>): Boolean {
        val key = entry.key
        val value = entry.value
        return when (key) {
            "byr" -> value.toInt() in 1920..2002
            "iyr" -> value.toInt() in 2010..2020
            "eyr" -> value.toInt() in 2020..2030
            "pid" -> value.length == 9 && value.contains("[0-9]".toRegex())
            "ecl" -> value in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
            "hcl" -> value.first() == '#' && value.drop(1).contains("[0-9]|[a-f]".toRegex())
            "cid" -> true
            "hgt" -> {
                val n = value.dropLast(2)
                val unit = value.takeLast(2)
                when (unit) {
                    "cm" -> n.toInt() in 150..193
                    "in" -> n.toInt() in 59..76
                    else -> false
                }
            }

            else -> error("no such field $key")
        }
    }

    fun part2(input: List<String>): Int {
        val needed = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
        return parseInput(input)
            .filter { it.keys.containsAll(needed) }
            .filter { it.all(::isValid) }
            .size
    }

    val input = readInput("inputs/Day04")
    part1(input).println()
    part2(input).println()
}


