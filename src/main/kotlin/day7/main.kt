package main.kotlin.day7

import main.kotlin.utils.Utils

fun main(args: Array<String>) {
    val puzzle = Utils.readFromFs("/opt/projects/AoC-2020/inputs/day7/input.txt")

    var count = 0
    val bags = buildMap(puzzle.split('\n'))
    bags.forEach {
        if (it.value.couldHoldShiny()) {
            count++
        }
    }
    println("Part 1: $count")
    println("Part 2: ${bags["shiny gold"]!!.countContainingBags()-1}")
}

fun buildMap(puzzle: List<String>): HashMap<String, Bag> {
    val bags = HashMap<String, Bag>()
    for (rule in puzzle.reversed()) {
        val temp = rule.split("bags contain")
        bags[temp[0].trim()] = Bag(temp[0].trim(), temp[1].trim())
    }
    bags.forEach {
        it.value.correct(bags)
    }

    return bags
}
