package main.kotlin.utils

import java.io.File

class Utils {
    companion object {
        fun readResourceAsText(path: String): String {
            val url = Utils::class.java.getResource("day3/short.txt")
            return Utils::class.java.getResource(path).readText()
        }

        fun readFromFs(path: String): String {
            return File(path).readText()
        }
    }
}