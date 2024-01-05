package com.sluffin.aoc.y2015

object Day05 : DayOf2015(5) {
    private val BAD_2_CHARS_REGEX = "ab|cd|pq|xy".toRegex()
    private val AA_REGEX = "([a-z])\\1".toRegex()
    private val ABA_REGEX = "([a-z]).\\1".toRegex()
    private val ABAB_REGEX = "([a-z]{2}).*\\1".toRegex()

    override fun partOne(): Any = lines
        .filterNot { BAD_2_CHARS_REGEX in it }
        .filter { line -> line.count { it in "aeiou" } >= 3 }
        .count { AA_REGEX in it }

    override fun partTwo(): Any = lines
        .filter { ABA_REGEX in it }
        .count { ABAB_REGEX in it }
}