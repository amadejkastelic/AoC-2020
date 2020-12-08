package main.kotlin.day8

class Console {

    var accumulator = 0

    val alreadyExecuted = HashSet<Int>()

    val alreadyTested = HashSet<Int>()

    fun execute(instructions: List<String>) {
        this.accumulator = 0
        this.alreadyExecuted.clear()
        var i = 0
        while (i < instructions.size) {
            if (alreadyExecuted.contains(i)) {
                return
            }
            alreadyExecuted.add(i)
            val opVal = instructions[i].split(' ')
            if (opVal[0].trim() == "nop") {
                i++
                continue
            } else if (opVal[0].trim() == "acc") {
                this.accumulator += Integer.parseInt(opVal[1])
            } else if (opVal[0].trim() == "jmp") {
                i += Integer.parseInt(opVal[1])
                continue
            } else {
                println("Unknown command: ${opVal[0]}")
            }
            i++
        }
    }

    fun executeFix(instructions: List<String>): Boolean {
        this.accumulator = 0
        this.alreadyExecuted.clear()
        var changed = false
        var i = 0
        while (i < instructions.size) {
            println("${instructions[i]} | ${i+1}")
            if (alreadyExecuted.contains(i)) {
                return false
            }
            alreadyExecuted.add(i)
            val opVal = instructions[i].split(' ')
            if (opVal[0].trim() == "nop") {
                if (!changed && !this.alreadyTested.contains(i) && Integer.parseInt(opVal[1]) != 0) {
                    this.alreadyTested.add(i)
                    i += Integer.parseInt(opVal[1])
                    changed = true
                    continue
                }
                i++
                continue
            } else if (opVal[0].trim() == "acc") {
                this.accumulator += Integer.parseInt(opVal[1])
            } else if (opVal[0].trim() == "jmp") {
                if (!changed && !this.alreadyTested.contains(i)) {
                    this.alreadyTested.add(i)
                    i++
                    changed = true
                    continue
                }
                i += Integer.parseInt(opVal[1])
                continue
            } else {
                println("Unknown command: ${opVal[0]}")
            }
            if (i == instructions.size-1) {
                return true
            }
            i++
        }
        return true
    }
}