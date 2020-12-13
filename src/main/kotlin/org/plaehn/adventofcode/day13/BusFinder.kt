package org.plaehn.adventofcode.day13

import kotlin.math.ceil

class BusFinder(private val earliestDeparture: Int, private val busIds: List<Int>) {

    fun findIdOfEarliestBusToAirportMultipliedByWaitingTime(): Int {
        val busIdAndNextDeparture = busIds
            .map { it to nextDeparture(it) }
            .minByOrNull { it.second }
            ?: throw IllegalStateException()
        return busIdAndNextDeparture.first * (busIdAndNextDeparture.second - earliestDeparture)
    }

    private fun nextDeparture(busId: Int) = busId * ceil(earliestDeparture.toDouble() / busId).toInt()

    companion

    object {
        fun fromLines(lines: List<String>): BusFinder {
            val earliestDeparture = lines[0].toInt()
            val busIds = lines[1].split(",").filter { it != "x" }.map { it.toInt() }
            return BusFinder(earliestDeparture, busIds)
        }
    }
}