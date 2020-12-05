package main.kotlin.day4

import main.kotlin.utils.Utils

fun main(args: Array<String>) {
    val puzzle = Utils.readFromFs("/home/amadejk/Desktop/input.txt")

    println("Part 1: ${solve(puzzle)}")
    //println("Part 2: $result")
}

fun solve(puzzle: String): Int {
    var result = 0

    var temp = ""
    for (line in puzzle.split('\n')) {
        if (line.isBlank()) {
            if (Passport.fromString(temp.trim()).isValid()) {
                result++
            }
            temp = ""
        } else {
            temp += "$line "
        }
    }

    return result
}
