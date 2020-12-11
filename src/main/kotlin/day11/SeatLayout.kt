package main.kotlin.day11

class SeatLayout(var seats: ArrayList<CharArray>) {

    private var previous = ArrayList<CharArray>()

    private var changes = -1

    private fun move1() {
        changes = 0
        this.copyList()
        for (i in 0 until this.seats.size) {
            for (j in this.seats[i].indices) {
                if (this.previous[i][j] == '#' && this.getAdjacentCount(i, j) >= 4) {
                    this.seats[i][j] = 'L'
                } else if (this.previous[i][j] == 'L' && this.getAdjacentCount(i, j) == 0) {
                    this.seats[i][j] = '#'
                } else {
                    continue
                }
                changes++
            }
        }
    }

    private fun move2() {
        changes = 0
        this.copyList()
        for (i in 0 until this.seats.size) {
            for (j in this.seats[i].indices) {
                if (this.previous[i][j] == '#' && this.getSeenCount(i, j) >= 5) {
                    this.seats[i][j] = 'L'
                } else if (this.previous[i][j] == 'L' && this.getSeenCount(i, j) == 0) {
                    this.seats[i][j] = '#'
                } else {
                    continue
                }
                changes++
            }
        }
    }

    fun occupiedSeats1(): Int {
        while (!this.checkIfNoChange()) {
            this.move1()
        }

        var count = 0
        this.seats.forEach {
            count += it.count { char -> char == '#' }
        }
        return count
    }

    fun occupiedSeats2(): Int {
        this.printSeats()
        while (!this.checkIfNoChange()) {
            this.move2()
            this.printSeats()
        }

        var count = 0
        this.seats.forEach {
            count += it.count { char -> char == '#' }
        }
        return count
    }

    private fun checkIfNoChange() = this.changes == 0

    private fun getChar(y: Int, x: Int): Char {
        if (y < 0 || x < 0 || y >= this.previous.size || x >= this.previous[0].size) {
            return '.'
        }

        return this.previous[y][x]
    }

    private fun printSeats() {
        for (i in 0 until seats.size) {
            println(String(seats[i]))
        }
        println("--------------------")
    }

    private fun getAdjacentCount(y: Int, x: Int): Int {
        var count = 0
        for (i in y-1..y+1) {
            for (j in x-1..x+1) {
                if (i == y && j == x) {
                    continue
                }
                if (this.getChar(i, j) == '#') {
                    count++
                }
            }
        }
        return count
    }

    private fun countDiagonal(y: Int, x: Int): Int {
        var count = 0

        var startX = x-1
        var startY = y-1
        while (startX >= 0 && startY >= 0) {
            if (this.getChar(startY, startX) == '#') {
                count++
                break
            } else if (this.getChar(startY, startX)  == 'L') {
                break
            }
            startX--
            startY--
        }

        startX = x + 1
        startY = y + 1
        while (startX < this.previous[0].size && startY < this.previous.size) {
            if (this.getChar(startY, startX)  == '#') {
                count++
                break
            } else if (this.getChar(startY, startX)  == 'L') {
                break
            }
            startX++
            startY++
        }

        startX = x + 1
        startY = y - 1
        while (startX < this.previous[0].size && startY >= 0) {
            if (this.getChar(startY, startX)  == '#') {
                count++
                break
            } else if (this.getChar(startY, startX)  == 'L') {
                break
            }
            startX++
            startY--
        }

        startX = x - 1
        startY = y + 1
        while (startX >= 0 && startY < this.previous.size) {
            if (this.getChar(startY, startX)  == '#') {
                count++
                break
            } else if (this.getChar(startY, startX)  == 'L') {
                break
            }
            startX--
            startY++
        }

        return count
    }

    private fun countRow(y: Int, x: Int): Int {
        var count = 0

        var i = y - 1
        while (i >= 0) {
            if (this.getChar(i, x) == '#') {
                count++
                break
            } else if (this.getChar(i, x) == 'L') {
                break
            }
            i--
        }
        i = y + 1
        while (i < this.previous.size) {
            if (this.getChar(i, x) == '#') {
                count++
                break
            } else if (this.getChar(i, x) == 'L') {
                break
            }
            i++
        }

        return count
    }

    private fun countLine(y: Int, x: Int): Int {
        var count = 0

        var i = x - 1
        while (i >= 0) {
            if (this.getChar(y, i) == '#') {
                count++
                break
            } else if (this.getChar(y, i) == 'L') {
                break
            }
            i--
        }
        i = x + 1
        while (i < this.previous[0].size) {
            if (this.getChar(y, i) == '#') {
                count++
                break
            } else if (this.getChar(y, i) == 'L') {
                break
            }
            i++
        }

        return count
    }

    private fun getSeenCount(y: Int, x: Int): Int {
        return this.countDiagonal(y, x) + this.countLine(y, x) + this.countRow(y, x)
    }

    private fun copyList() {
        this.previous.clear()
        this.seats.forEach {
            this.previous.add(it.copyOf())
        }
    }
}