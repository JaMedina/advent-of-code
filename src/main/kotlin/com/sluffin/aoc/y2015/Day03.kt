package com.sluffin.aoc.y2015

object Day03 : DayOf2015(3) {
    override fun partOne(): Any = process()
    override fun partTwo(): Any = process(2)

    private fun process(numberOfSantas: Int = 1): Int {
        val santaPositions = List(numberOfSantas) { SantaCoordinates() }

        val map = mutableSetOf(*santaPositions.toTypedArray())
        var currentSanta = 0
        data.forEach { c ->
            with(santaPositions[currentSanta]) {
                when (c) {
                    '^' -> second++
                    'v' -> second--
                    '>' -> first++
                    '<' -> first--
                }
                map.add(copy())
            }
            currentSanta = (currentSanta + 1) % numberOfSantas
        }
        return map.size
    }

    data class SantaCoordinates(
        var first: Int = 0,
        var second: Int = 0
    )
}