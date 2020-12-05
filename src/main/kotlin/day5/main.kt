package main.kotlin.day5

import main.kotlin.utils.Utils
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    val puzzle = Utils.readFromFs("/opt/projects/advent_of_code_2020/inputs/day5/input.txt")

    val takenSeats = ArrayList<Int>()

    var result = 0
    for (line in puzzle.split('\n')) {
        val row = getRow(line.substring(0, 7))
        val id = getSeatId(row, getColumn(line.substring(7)))
        if (id > result) {
            result = id
        }
        if (row != 0) {
            takenSeats.add(id)
        }
    }

    println("Part 1: $result")

    takenSeats.sort()
    var previous = takenSeats[0]
    for (i in 1 until takenSeats.size) {
        if (previous+1 != takenSeats[i]) {
            println("Part 2: ${previous+1}")
            break
        }
        previous = takenSeats[i]
    }
}

fun getRow(input: String) =
    Integer.parseInt(input.replace('F', '0').replace('B', '1'), 2)

fun getColumn(input: String) =
    Integer.parseInt(input.replace('L', '0').replace('R', '1'), 2)

fun getSeatId(row: Int, column: Int) = (row * 8) + column
