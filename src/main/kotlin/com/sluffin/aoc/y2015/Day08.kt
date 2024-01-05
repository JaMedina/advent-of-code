package com.sluffin.aoc.y2015

object Day08 : DayOf2015(8) {
    override fun partOne() = lines.sumOf { it.length } - lines.sumOf { lines ->
        lines
            .replace("^\"".toRegex(), "") // Remove initial quotes
            .replace("\"$".toRegex(), "") // Remove end quotes
            .replace("\\\"", "\"") // Replace escaping characters
            .replace("\\\\", "\\") // Replace double backslash
            .replace("\\\\x[\\da-f]{2}".toRegex(), "#")  // Replace hexadecimal number
            .length
    }


    override fun partTwo() = lines.sumOf { line ->
        line
            .map { char ->
                when (char) {
                    '\"' -> "\\\""
                    '\\' -> "\\\\"
                    else -> "$char"
                }
            }
            .joinToString(separator = "", prefix = "\"", postfix = "\"")
            .length
    } - lines.sumOf { it.length }
}