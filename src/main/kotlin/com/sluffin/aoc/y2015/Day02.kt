package com.sluffin.aoc.y2015

import com.sluffin.aoc.utils.parseInts

object Day02 : DayOf2015(2) {
    private val boxes = lines.map { it.parseInts("x").sorted() }

    override fun partOne(): Any = boxes.sumOf { 3 * it[0] * it[1] + 2 * it[0] * it[2] + 2 * it[1] * it[2] }
    override fun partTwo(): Any = boxes.sumOf { 2 * (it[0] + it[1]) + it[0] * it[1] * it[2] }
}