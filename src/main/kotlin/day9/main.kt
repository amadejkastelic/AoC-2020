package main.kotlin.day9

import main.kotlin.utils.Utils

fun main(args: Array<String>) {
    val puzzle = Utils.readFromFs("/opt/projects/AoC-2020/inputs/day9/input.txt")

    val numbers = parse(puzzle)

    val firstInvalid = solve(25, numbers)
    println("Part 1: $firstInvalid")

    println("Part 2: ${findContiguous(firstInvalid, numbers)}")
}

fun parse(input: String): List<Long> {
    return input.split('\n').map { it.toLong() }
}

fun buildPreamble(from: Int, to: Int, numbers: List<Long>): HashSet<Long> {
    val result = HashSet<Long>()
    for (i in from until to) {
        for (j in from until to) {
            if (j != i) {
                result.add(numbers[i] + numbers[j])
            }
        }
    }
    return result
}

fun solve(start: Int, input: List<Long>): Long {
    for (i in start until input.size) {
        val preamble = buildPreamble(i-start, i, input)
        if (!preamble.contains(input[i])) {
            return input[i]
        }
    }
    return -1
}

fun findContiguous(number: Long, numbers: List<Long>): Long {
    var first = 0
    var last = 0
    var sum = 0L
    var i = 0
    while (i < numbers.size) {
        sum += numbers[i]
        if (sum == number) {
            return numbers.subList(first+1, last).min()!! + numbers.subList(first+1, last).max()!!
        } else if (sum > number) {
            first++
            i = first
            last = first
            sum = 0
        }
        last++
        i++
    }
    return -1
}
