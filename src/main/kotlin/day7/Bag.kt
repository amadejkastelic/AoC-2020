package main.kotlin.day7

class Bag(var name: String, bags: String) {

    val bags: HashMap<Bag, Int> = HashMap()

    private val sBags = bags

    val canHoldShiny = bags.contains("shiny gold")

    fun correct(allBags: HashMap<String, Bag>) {
        if (sBags.trim() != "no other bags.") {
            val temp = sBags.split(", ")
            temp.forEach {
                val temp2 = it.split(" ")
                val name = temp2[1] + " " + temp2[2]
                this.bags[allBags[name]!!] = Integer.parseInt(temp2[0])
            }
        }
    }

    fun couldHoldShiny(): Boolean {
        if (this.canHoldShiny) {
            return true
        }
        bags.forEach {
            if (it.key.couldHoldShiny()) {
                return true
            }
        }
        return false
    }

    fun countContainingBags(): Int {
        var count = 1
        bags.forEach {
            count += it.value * it.key.countContainingBags()
        }
        return count
    }
}