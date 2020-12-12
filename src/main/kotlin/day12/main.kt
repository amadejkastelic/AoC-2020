package main.kotlin.day12

import main.kotlin.utils.Utils

fun main(args: Array<String>) {
    val puzzle = Utils.readFromFs("/opt/projects/AoC-2020/inputs/day12/input.txt").split('\n')

    val ship = Ship(0, 0, 0, 0)
    val waypoint = Ship(10, 0, 1, 0)
    waypoint.ship = Ship(0, 0, 0, 0)
    for (action in puzzle) {
        ship.doAction(action)
        waypoint.doAction2(action)
    }

    println("Part 1: ${ship.manhattanDistance()}")
    println("Part 2: ${waypoint.ship.manhattanDistance()}")
}
