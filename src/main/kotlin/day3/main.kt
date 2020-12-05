package main.kotlin.day3

fun main(args: Array<String>) {
    var line = readLine()!!
    val puzzle = ArrayList<String>()
    while (line.isNotEmpty()) {
        puzzle.add(line)
        line = readLine()!!
    }

    val result = solve(puzzle, 1, 1) * solve(puzzle, 3, 1) *
            solve(puzzle, 5, 1) * solve(puzzle, 7, 1) * solve(puzzle, 1, 2)

    println("Part 1: ${solve(puzzle, 3, 1)}")
    println("Part 2: $result")
}

fun solve(puzzle: ArrayList<String>, right: Int, down: Int): Int {
    var result = 0
    val width = puzzle[0].length
    var step = right
    for (i in down until puzzle.size step down) {
        if (step >= width) {
            step -= width
        }
        if (puzzle[i][step] == '#') {
            result++
        }
        step += right
    }
    return result
}
