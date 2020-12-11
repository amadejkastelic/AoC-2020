package main.kotlin.day11

import main.kotlin.utils.Utils

fun main(args: Array<String>) {
    val puzzle = Utils.readFromFs("/opt/projects/AoC-2020/inputs/day11/input.txt").split('\n')

    val layout = SeatLayout(ArrayList(puzzle.map { it.toCharArray() }))
    //println("Part 1: ${layout.occupiedSeats1()}")
    println("Part 2: ${layout.occupiedSeats2()}")
}
