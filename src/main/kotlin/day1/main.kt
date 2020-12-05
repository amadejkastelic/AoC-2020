package main.kotlin.day1

fun main(args: Array<String>) {
    var line = readLine()!!
    val puzzle = ArrayList<Int>()
    while (line.isNotEmpty()) {
        puzzle.add(Integer.parseInt(line))
        line = readLine()!!
    }

    println("Part 1: ${solve(puzzle)}")
    println("Part 2: ${solve2(puzzle)}")
}

fun solve(puzzle: ArrayList<Int>): Int {
    for (i in 0 until puzzle.size) {
        val num = puzzle[i]
        for (j in 0 until puzzle.size) {
            if (i == j) {
                continue
            }
            val curNum = puzzle[j]
            if ((num + curNum) == 2020) {
                return (num * curNum)
            }
        }
    }

    return 0
}

fun solve2(puzzle: ArrayList<Int>): Int {
    for (i in 0 until puzzle.size) {
        val num = puzzle[i]
        for (j in 0 until puzzle.size) {
            val num2 = puzzle[j]
            for (k in 0 until puzzle.size) {
                if (i == j || j == k || k == i) {
                    continue
                }

                val curNum = puzzle[k]
                if ((num + curNum + num2) == 2020) {
                    return (num * num2 * curNum)
                }
            }
        }
    }

    return 0
}
