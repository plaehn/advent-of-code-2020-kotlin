package org.plaehn.adventofcode.day13

import org.plaehn.adventofcode.common.chineseRemainder
import kotlin.math.ceil

class BusFinder(private val earliestDeparture: Int, private val busIds: List<Int>) {

    fun findEarliestTimestampWithSubsequentDepartures(): Long {
        val busIdsAndRemainders = busIds
            .map { it.toLong() }
            .mapIndexed { index, busId -> busId to (busId - index) % busId }
            .filter { it.first > 0 }
            .unzip()

        return chineseRemainder(busIdsAndRemainders.first, busIdsAndRemainders.second)
    }

    fun findIdOfEarliestBusToAirportMultipliedByWaitingTime(): Int {
        val busIdAndNextDeparture = busIds
            .filter { it >= 0 }
            .map { it to nextDeparture(it) }
            .minByOrNull { it.second }
            ?: throw IllegalStateException()
        return busIdAndNextDeparture.first * (busIdAndNextDeparture.second - earliestDeparture)
    }

    private fun nextDeparture(busId: Int) = busId * ceil(earliestDeparture.toDouble() / busId).toInt()

    companion object {
        fun fromLines(lines: List<String>): BusFinder {
            val earliestDeparture = lines[0].toInt()
            val busIds = lines[1]
                .split(",")
                .map { if (it == "x") Int.MIN_VALUE else it.toInt() }
            return BusFinder(earliestDeparture, busIds)
        }
    }
}