fun main() {

    data class Bag(val name: String, val n: Int = 1)

    fun Map<String, List<Bag>>.find(needed: List<String>, seen: MutableSet<String>) {
        needed.forEach { need ->
            val containingBags = this.filter { it.value.map { it.name }.contains(need) }.map { it.key }
            containingBags.forEach { seen += it }

            if (containingBags.isNotEmpty()) {
                this.find(containingBags, seen)
            }
        }
    }

    fun parseInput(input: List<String>) = input
        .map {
            it.replace("bags", "")
                .replace("bag", "")
                .replace(".", "")
        }
        .filter { "no other" !in it }
        .associate { line ->
            val source = line.substringBefore(" contain ").trim()
            val bags = line.substringAfter(" contain ")
                .split(", ")
                .map { it.trim() }
                .map {
                    val n = it.substringBefore(" ").toInt()
                    val name = it.substringAfter(" ")
                    Bag(name, n)
                }

            source to bags
        }

    fun part1(input: List<String>): Int {
        val bags = parseInput(input)
        val seen = mutableSetOf<String>()
        bags.find(listOf("shiny gold"), seen)
        return seen.size
    }

    fun Map<String, List<Bag>>.count(needed: Bag): Int {
        var count = 0

        if (needed.name in this) {
            count += getValue(needed.name).sumOf { it.n + count(it) }
        }

        return count * needed.n
    }

    fun part2(input: List<String>): Int {
        val bags = parseInput(input)
        return bags.count(Bag( "shiny gold"))
    }

    val input = readInput("inputs/Day07")
    part1(input).println()
    part2(input).println()
}


