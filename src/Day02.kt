fun main() {
    val day = "Day02"

    fun toRanges(s: String): List<LongRange> {
        return s.split(",")
            .map {
                val p = it.split("-")
                p[0].toLong()..p[1].toLong()
            }
    }

    fun part1(input: List<String>): Long {
        fun isValid(n: Long): Boolean {
            val s = n.toString()
            val l = s.substring(0, s.length.div(2))
            val r = s.substring(s.length.div(2), s.length)
            return l != r
        }

        val ranges = toRanges(input[0])
        var total = 0L
        for (range in ranges) {
            for (n in range) {
                if (!isValid(n)) {
                    total += n
                }
            }
        }
        return total
    }

    fun part2(input: List<String>): Long {
        fun isValid(n: Long): Boolean {
            val s = n.toString()

            fun repeats(l: String, r: String): Boolean {
                if (r.length.rem(l.length) != 0) {
                    return false
                }
                val repeated = l.repeat(r.length.div(l.length))
                return repeated == r
            }

            for (i in 1..s.length.div(2)) {
                val l = s.substring(0, i)
                val r = s.substring(i, s.length)
                if (repeats(l, r)) {
                    return false
                }
            }

            return true
        }

        val ranges = toRanges(input[0])
        var total = 0L
        for (range in ranges) {
            for (n in range) {
                if (!isValid(n)) {
                    total += n
                }
            }
        }
        return total
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("${day}_test")
    check(part1(testInput) == 1227775554L)
    check(part2(testInput) == 4174379265L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("${day}")
    part1(input).println()
    part2(input).println()
}
