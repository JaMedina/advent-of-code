package com.sluffin.aoc.y2015

object Day07 : DayOf2015(7) {

    private val wireValueMap = mutableMapOf<String, Int>()
    private var wireConnectionMap: MutableMap<String, () -> Int> = initMap()

    override fun partOne(): Any = evaluate("a")

    override fun partTwo(): Any {
        val result = partOne()
        wireValueMap.clear()
        wireConnectionMap = initMap(mapOf("b" to result as Int))
        return partOne()
    }

    private fun evaluate(wire: String): Int = wire.toIntOrNull() ?: wireValueMap.getOrPut(wire) { wireConnectionMap.getValue(wire)() }

    private fun initMap(overrides: Map<String, Int> = mapOf()) = lines.associate { line ->
        line.split(" ").let { cmd ->
            when (cmd[1]) {
                "AND" -> cmd[4] to { evaluate(cmd[0]) and evaluate(cmd[2]) }
                "OR" -> cmd[4] to { evaluate(cmd[0]) or evaluate(cmd[2]) }
                "LSHIFT" -> cmd[4] to { evaluate(cmd[0]) shl evaluate(cmd[2]) }
                "RSHIFT" -> cmd[4] to { evaluate(cmd[0]) ushr evaluate(cmd[2]) }
                "->" -> cmd[2] to {
                    if (overrides.containsKey(cmd[2])) overrides[cmd[2]]!!
                    else evaluate(cmd[0])
                }
                else -> cmd[3] to { evaluate(cmd[1]).inv() }
            }
        }
    }.toMutableMap()
}