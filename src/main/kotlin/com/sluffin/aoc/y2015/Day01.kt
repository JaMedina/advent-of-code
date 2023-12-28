package com.sluffin.aoc.y2015

object Day01 : DayOf2015(1) {
    private val floors = data.map { 1 - 2 * it.minus('(') }

    override fun partOne(): Any = floors.sum()
    override fun partTwo(): Any = floors.runningReduce { acc, value -> acc + value }.indexOfFirst { it < 0 } + 1
}