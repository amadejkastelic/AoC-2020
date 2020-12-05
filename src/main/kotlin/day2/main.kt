package main.kotlin.day2

fun main(args: Array<String>) {
    var line = readLine()!!
    val puzzle = ArrayList<String>()
    while (line.isNotEmpty()) {
        puzzle.add(line)
        line = readLine()!!
    }

    println("Part 1: ${solve(puzzle)}")
    println("Part 2: ${solve2(puzzle)}")
}

fun solve(puzzle: ArrayList<String>): Int {
    var count = 0
    for (input in puzzle) {
        val password = Password.parse(input)
        if (password.isLegal()) {
            count++
        }
    }
    return count
}

fun solve2(puzzle: ArrayList<String>): Int {
    var count = 0
    for (input in puzzle) {
        val password = Password.parse(input)
        if (password.isLegal2()) {
            count++
        }
    }
    return count
}
