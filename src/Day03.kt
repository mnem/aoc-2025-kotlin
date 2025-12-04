fun main() {
    val day = "Day03"

    fun maxJoltage(s: String, slots: Int): Long {
        fun maxWithReserved(s: String, reserve: Int): Pair<Char, String> {
            val window = 0..(s.length - reserve - 1)
            var highestIndex = 0
            for (i in window) {
                if (s[i].toString().toInt() > s[highestIndex].toString().toInt()) {
                    highestIndex = i
                }
            }
            val remaining = if (highestIndex < s.length - 1) { s.substring(highestIndex + 1) } else  { "" }
            return Pair(s[highestIndex], remaining)
        }

        val selected = mutableListOf<Char>()
        var search = s
        while (selected.size < slots) {
            val (joltage, remaining) = maxWithReserved(search, slots - selected.size - 1)
            selected.add(joltage)
            search = remaining
        }
        return selected.joinToString("").toLong()
    }

    fun part1(input: List<String>): Long {
        fun maxJoltage(s: String): Long {
            return maxJoltage(s, 2)
        }

        return input.sumOf(::maxJoltage)
    }

    fun part2(input: List<String>): Long {
        fun maxJoltage(s: String): Long {
            return maxJoltage(s, 12)
        }

        return input.sumOf(::maxJoltage)
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("${day}_test")
    check(part1(testInput) == 357L)
    check(part2(testInput) == 3121910778619L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput(day)
    part1(input).println()
    part2(input).println()
}
