package com.sluffin.aoc.y2015

import com.sluffin.aoc.utils.md5

object Day04 : DayOf2015(4) {
    override fun partOne(): Any = generateSequence(1) { it + 1 }.first { "$data$it".md5().startsWith("00000") }
    override fun partTwo(): Any = generateSequence(1) { it + 1 }.first { "$data$it".md5().startsWith("000000") }
}