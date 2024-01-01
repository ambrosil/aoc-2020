fun main() {

    data class Cmd(val name: String, val n: Int)

    fun parseInput(input: List<String>): List<Cmd> =
        input.map {
            val (name, n) = it.split(" ")
            Cmd(name, n.toInt())
        }

    fun execute(commands: List<Cmd>, loop: (acc: Int) -> Int = { it }): Int {
        var acc = 0
        var ip = 0
        val seen = mutableListOf<Int>()

        while (true) {
            if (ip in seen) return loop(acc)
            seen += ip

            val current = commands[ip]
            when (current.name) {
                "nop" -> ip++
                "jmp" -> ip += current.n
                "acc" -> {
                    acc += current.n
                    ip++
                }
            }

            if (ip == commands.size) return acc
        }
    }

    fun Cmd.flipOrNull(): Cmd? =
        when (name) {
            "jmp" -> Cmd("nop", n)
            "nop" -> Cmd("jmp", n)
            else -> null
        }

    fun List<Cmd>.flipIndexOrNull(index: Int): List<Cmd>? =
        this[index].flipOrNull()?.let { flipped ->
            this.toMutableList().apply { this[index] = flipped }
        }

    fun part1(input: List<String>) =
        execute(parseInput(input))

    fun part2(input: List<String>): Int {
        val commands = input.map {
            val (name, n) = it.split(" ")
            Cmd(name, n.toInt())
        }

        return commands
            .indices
            .asSequence()
            .mapNotNull { index -> commands.flipIndexOrNull(index) }
            .map { execute(it) { -1 } }
            .filter { it >= 0 }
            .first()
    }

    val input = readInput("inputs/Day08")
    part1(input).println()
    part2(input).println()
}

