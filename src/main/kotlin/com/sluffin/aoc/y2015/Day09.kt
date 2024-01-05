package com.sluffin.aoc.y2015

import com.sluffin.aoc.utils.permutations

object Day09 : DayOf2015(9) {

    private val nodePattern: Regex = "^\\b(\\w*)\\b to \\b(\\w*)\\b = (\\d*)$".toRegex()
    private val nodes = lines.flatMap { line ->
        nodePattern.matchEntire(line)?.let { match ->
            val (origin, destination, distance) = match.destructured
            listOf(
                Pair(origin, destination) to distance.toInt(),
                Pair(destination, origin) to distance.toInt()
            )
        }.orEmpty()
    }.toMap()
    private val cities = nodes.keys.flatMap { listOf(it.first, it.second) }.distinct()

    override fun partOne() = cities.permutations()  // Get all permutations of the list of cities
        .minOf { city ->
            city.zipWithNext()                  // Create pars on the previous permutation and add total distance
                .sumOf { nodes[it] ?: 0 }
        } // Find the smallest distance in all permutations

    override fun partTwo() = cities.permutations()  // Get all permutations of the list of cities
        .maxOf { city ->
            city.zipWithNext()                  // Create pars on the previous permutation and add total distance
                .sumOf { nodes[it] ?: 0 }
        }
}