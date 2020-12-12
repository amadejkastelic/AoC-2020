package main.kotlin.day12

import kotlin.math.abs

class Ship(var east: Int, var west: Int, var north: Int, var south: Int) {

    lateinit var ship: Ship

    private var direction = 0
    private val directions = listOf('E', 'S', 'W', 'N')

    private val rotations = listOf('R', 'L')

    fun doAction(instruction: String) {
        when {
            instruction[0] == 'F' -> {
                this.move(this.directions[direction], Integer.parseInt(instruction.substring(1)))
            }
            this.directions.contains(instruction[0]) -> {
                this.move(instruction[0], Integer.parseInt(instruction.substring(1)))
            }
            this.rotations.contains(instruction[0]) -> {
                this.rotate(instruction[0], Integer.parseInt(instruction.substring(1)))
            }
        }
    }

    private fun moveToWaypoint(waypoint: Ship, amount: Int) {
        println("Got move instruction. Amount: $amount")
        this.south += waypoint.south * amount
        this.north += waypoint.north * amount
        this.east += waypoint.east * amount
        this.west += waypoint.west * amount
    }

    fun doAction2(instruction: String) {
        when {
            instruction[0] == 'F' -> {
                this.ship.moveToWaypoint(this, Integer.parseInt(instruction.substring(1)))
            }
            this.directions.contains(instruction[0]) -> {
                this.move(instruction[0], Integer.parseInt(instruction.substring(1)))
            }
            this.rotations.contains(instruction[0]) -> {
                this.rotate2(instruction[0], Integer.parseInt(instruction.substring(1)))
            }
        }
    }

    fun manhattanDistance() = abs(this.south - this.north) + abs(this.west - this.east)

    private fun move(direction: Char, units: Int) {
        println("Got move instruction. Direction: $direction, units: $units")
        when (direction) {
            'N' -> {
                this.north += units
            }
            'S' -> {
                this.south += units
            }
            'E' -> {
                this.east += units
            }
            'W' -> {
                this.west += units
            }
        }
    }

    private fun rotate(direction: Char, degrees: Int) {
        println("Got rotate instruction. Direction: $direction, degrees: $degrees")
        println("Previous direction: ${this.directions[this.direction]}")

        var temp = degrees
        if (direction == 'L') {
            temp = 360 - degrees
        }

        val rotation: Int = temp / 90

        this.direction = abs(this.direction + rotation) % 4

        println("New direction: ${this.directions[this.direction]}")
    }

    private fun rotate2(direction: Char, degrees: Int) {
        var temp = degrees
        if (direction == 'L') {
            temp = 360 - degrees
        }

        val east = this.east
        val north = this.north
        val south = this.south
        val west = this.west
        if (temp == 90) {
            this.east = north
            this.south = east
            this.west = south
            this.north = west
        } else if (temp == 180) {
            this.east = west
            this.south = north
            this.west = east
            this.north = south
        } else if (temp == 270) {
            this.east = south
            this.south = west
            this.west = north
            this.north = east
        }
    }
}