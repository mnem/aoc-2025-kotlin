import kotlin.math.absoluteValue

fun main() {
    fun toShifts(input: List<String>): List<Int> {
        return input.map {
            when (val movement = Movement.fromString(it)) {
                is Movement.Left -> -movement.distance
                is Movement.Right -> movement.distance
            }
        }
    }

    fun part1(input: List<String>): Int {
        var dial = 50
        var zeroes = 0
        for (shift in toShifts(input)) {
            dial = (dial + shift).mod(100)
            if (dial == 0) {
                zeroes += 1
            }
        }
        return zeroes
    }

    fun part2(input: List<String>): Int {
        var dial = 50
        var zeroes = 1
        for (shift in toShifts(input)) {
            dial = (dial + shift).mod(100)
            val zeroPasses = (dial + shift.absoluteValue).floorDiv(100)
            zeroes += zeroPasses
        }
        return zeroes
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 3)
    check(part2(testInput) == 6)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

sealed class Movement {
    data class Left(val distance: Int) : Movement()
    data class Right(val distance: Int) : Movement()

    companion object {
        fun fromString(input: String): Movement {
            if (input.isEmpty()) throw IllegalStateException("Input should not be empty")

            val direction = input.first()
            val distance = input.drop(1).toIntOrNull() ?: throw IllegalStateException("Associated value isn't an integer")

            return when (direction) {
                'L' -> Left(distance)
                'R' -> Right(distance)
                else -> throw IllegalArgumentException("Invalid direction: $direction")
            }
        }
    }
}
