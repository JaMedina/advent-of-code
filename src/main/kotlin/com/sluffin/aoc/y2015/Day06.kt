package com.sluffin.aoc.y2015

object Day06 : DayOf2015(6) {
    override fun partOne(): Any = lines.fold(Array(1000) { Array(1000) { false } }) { acc, value ->
        val points = toPoints(value)
        (points[0]..points[2]).forEach { row ->
            (points[1]..points[3]).forEach { column ->
                acc[row][column] = when {
                    value.startsWith("toggle") -> !acc[row][column]
                    value.startsWith("turn on") -> true
                    value.startsWith("turn off") -> false
                    else -> acc[row][column]
                }
            }
        }
        acc
    }.sumOf { row -> row.count { it } }


    override fun partTwo(): Any = lines.fold(Array(1000) { Array(1000) { 0 } }) { acc, value ->
        val points = toPoints(value)
        (points[0]..points[2]).forEach { row ->
            (points[1]..points[3]).forEach { column ->
                acc[row][column] += when {
                    value.startsWith("toggle") -> 2
                    value.startsWith("turn on") -> 1
                    value.startsWith("turn off") && acc[row][column] > 0 -> -1
                    else -> acc[row][column]
                }
            }
        }
        acc
    }.sumOf { it.sum() }

    private fun toPoints(command: String): List<Int> {
        val matcher = "\\D*(\\d+),(\\d+)\\D*(\\d+),(\\d+)\\D*".toPattern().matcher(command)
        matcher.find()
        return (1..4).map { matcher.group(it).toInt() }
    }
}