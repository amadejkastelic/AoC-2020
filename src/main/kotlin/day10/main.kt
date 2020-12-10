package main.kotlin.day10

import main.kotlin.utils.Utils

fun main(args: Array<String>) {
    var puzzle = Utils.readFromFs("/opt/projects/AoC-2020/inputs/day10/input.txt")
    val numbers = parse(puzzle)

    println("Part 1: ${joltDifferences(numbers)}")
    println("Part 2: ${arrangements(numbers)}")
}

fun parse(input: String): List<Long> = input.split('\n').map { it.toLong() }

fun joltDifferences(numbers: List<Long>): Int {
    val jolts = numbers.sorted().plus(0)
    var diff1 = 0
    var diff3 = 1
    for (i in 1 until jolts.size) {
        if (jolts[i]-1 == jolts[i-1]) {
            diff1++
        } else if (jolts[i]-3 == jolts[i-1]) {
            diff3++
        }
    }
    return diff1*diff3
}

fun arrangements(numbers: List<Long>): Long {
    val cache = HashMap<Long, Long>()

    val numbersDesc = numbers.sortedDescending().plus(0)
    cache[numbersDesc.first()] = 1

    numbersDesc.drop(1).forEach {
        var arr = 0L
        for (i in it+1..it+3) {
            arr += cache[i] ?: 0
        }
        cache[it] = arr
    }

    return cache[0] ?: -1
}
