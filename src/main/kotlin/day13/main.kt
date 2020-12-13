package main.kotlin.day13

import main.kotlin.utils.Utils

fun main(args: Array<String>) {
    val puzzle = Utils.readFromFs("/opt/projects/AoC-2020/inputs/day13/input.txt").split('\n')

    val busArrivals = HashMap<Int, ArrayList<Int>>()

    val myArrival = Integer.parseInt(puzzle[0].trim())
    val busIds = HashSet(puzzle[1].split(',').mapNotNull {
        it.toIntOrNull()
    })

    for (busId in busIds) {
        busArrivals[busId] = ArrayList()
        var timestamp = busId
        do {
            busArrivals[busId]!!.add(timestamp)
            timestamp += busId
        } while (timestamp <= myArrival+busId)
    }

    var closest = Int.MAX_VALUE
    var closestId = -1
    for (busId in busArrivals.keys) {
        for (timestamp in busArrivals[busId]!!) {
            if (timestamp >= myArrival && timestamp < closest) {
                closest = timestamp
                closestId = busId
            }
        }
    }

    println("Part 1: ${closestId * (closest - myArrival)}")

    val busLines = mutableListOf<Pair<Int, Long>>()
    puzzle[1].split(",").forEachIndexed { index, it ->
        if (it != "x") busLines.add(Pair(index,it.toLong()))
    }
    val productOfAllLineNumbers = busLines.fold(1L, { acc, int -> acc*int.second })

    var timestamp = busLines.first().second
    for (i in 1 until busLines.size) {
        timestamp = (timestamp..productOfAllLineNumbers step (busLines.take(i).fold(1L, { acc, int -> acc*int.second })))
            .first { (it + busLines[i].first) % busLines[i].second == 0L }
    }
    println("Part 2: $timestamp")
}
