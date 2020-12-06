package main.kotlin.day6

import main.kotlin.utils.Utils

fun main(args: Array<String>) {
    val puzzle = Utils.readFromFs("/opt/projects/advent_of_code_2020/inputs/day6/input.txt")

    val groups = puzzle.split("\n\n")
    var result = 0L
    groups.forEach { result += it
        .replace(" ", "")
        .replace("\n", "")
        .chars().distinct().count() }

    println("Part 1: $result")

    result = 0L
    groups.forEach {
        val temp = it.trim().split('\n')
        val common = stringToSet(temp[0])
        temp.stream().skip(1).forEach {
            common.retainAll(stringToSet(it))
        }
        result += common.size
    }

    println("Part 2: $result")
}

fun stringToSet(string: String): HashSet<Char> {
    val result = HashSet<Char>()
    string.forEach { result.add(it) }
    return result
}
