package main.kotlin.day8

import main.kotlin.utils.Utils

fun main(args: Array<String>) {
    val puzzle = Utils.readFromFs("/opt/projects/AoC-2020/inputs/day8/input.txt")

    val input = puzzle.split('\n')

    val console = Console()
    console.execute(input)
    println("Part 1: ${console.accumulator}")

    var ok = console.executeFix(input)
    while (!ok) {
        println("--------------------")
        ok = console.executeFix(input)
    }
    println("Part 2: ${console.accumulator}")
}
