package com.sluffin.aoc.core

import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

abstract class Day(private val year: Int, private val day: Int) {
    open val localData: String? = null
    val data: String by lazy {
        runBlocking {
            localData ?: DataFetcher.fetch(year, day).dropLastWhile { it == '\n' }
        }
    }

    val lines: List<String> by lazy { data.lines() }

    abstract fun partOne(): Any?
    abstract fun partTwo(): Any?

    companion object {
        fun execute(adventDay: Day) {
            with(adventDay) {
                println("Year $year, day $day")
                measureTimeMillis {
                    println("Part 1: ${partOne()?.toString() ?: "unsolved"}")
                }.run { println("Execution time: $this milli seconds") }
                measureTimeMillis {
                    println("Part 2: ${partTwo()?.toString() ?: "unsolved"}")
                }.run { println("Execution time: $this milli seconds") }
            }
        }
    }
}