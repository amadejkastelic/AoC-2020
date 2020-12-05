package main.kotlin.day4

class Passport {

    private val data = HashMap<String, String>()

    fun isValid(): Boolean {
        return byrValid() && iyrValid() && eyrValid() && hgtValid() && hclValid() && eclValid() && pidValid()
    }

    fun byrValid(): Boolean {
        if (!data.containsKey("byr")) {
            return false
        }

        val value: Int
        try {
            value = Integer.parseInt(data["byr"])
        } catch (exc: NumberFormatException) {
            return false
        }

        return value in 1920..2002
    }

    fun iyrValid(): Boolean {
        if (!data.containsKey("iyr")) {
            return false
        }

        val value: Int
        try {
            value = Integer.parseInt(data["iyr"])
        } catch (exc: NumberFormatException) {
            return false
        }

        return value in 2010..2020
    }

    fun eyrValid(): Boolean {
        if (!data.containsKey("eyr")) {
            return false
        }

        val value: Int
        try {
            value = Integer.parseInt(data["eyr"])
        } catch (exc: NumberFormatException) {
            return false
        }

        return value in 2020..2030
    }

    fun hgtValid(): Boolean {
        if (!data.containsKey("hgt")) {
            return false
        }

        var hgt = data["hgt"]!!
        if (hgt.contains("cm")) {
            hgt = hgt.replace("cm", "")
            val value: Int
            try {
                value = Integer.parseInt(hgt)
            } catch (exc: NumberFormatException) {
                return false
            }
            return value in 150..193
        } else if (hgt.contains("in")) {
            hgt = hgt.replace("in", "")
            val value: Int
            try {
                value = Integer.parseInt(hgt)
            } catch (exc: NumberFormatException) {
                return false
            }
            return value in 59..76
        } else {
            return false
        }
    }

    fun hclValid(): Boolean {
        if (!data.containsKey("hcl")) {
            return false
        }

        return data["hcl"]!!.matches(Regex("^#[a-f0-9]{6}\$"))
    }

    fun eclValid(): Boolean {
        if (!data.containsKey("ecl")) {
            return false
        }

        val valid = arrayOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

        return valid.contains(data["ecl"])
    }

    fun pidValid(): Boolean {
        if (!data.containsKey("pid")) {
            return false
        }

        return data["pid"]!!.matches(Regex("^[0-9]{9}\$"))
    }

    fun put(key: String, value: String) {
        data[key] = value
    }

    companion object {
        fun fromString(string: String): Passport {
            val passport = Passport()
            val data = string.replace('\n', ' ')
            for (param in data.split(' ')) {
                val keyValue = param.split(':')
                passport.put(keyValue[0].trim(), keyValue[1].trim())
            }
            return passport
        }
    }
}