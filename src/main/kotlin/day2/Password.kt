package main.kotlin.day2

class Password(private var character: Char,
               private var min: Int,
               private var max: Int,
               private var password: String) {

    fun isLegal(): Boolean {
        val count = password.count { it == character }
        return count in min..max
    }

    fun isLegal2(): Boolean {
        if (password[min-1] == character && max-1 >= password.length) {
            return true
        } else if (max-1 >= password.length) {
            return false
        } else if (password[min-1] == character && password[max-1] != character) {
            return true
        } else if (password[max-1] == character && password[min-1] != character) {
            return true
        }
        return false
    }

    companion object {
        fun parse(string: String): Password {
            var temp = string.split(":")
            val password = temp[1].trim()
            temp = temp[0].trim().split(" ")
            val range = temp[0].trim()
            val char = temp[1].trim()
            temp = range.split("-")
            val min = Integer.parseInt(temp[0])
            val max = Integer.parseInt(temp[1])
            return Password(char[0], min, max, password)
        }
    }
}