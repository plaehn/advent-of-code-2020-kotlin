package org.plaehn.adventofcode.day23

import com.ginsberg.cirkle.circular

class CrabCups(private val input: String) {

    fun play(moves: Int): String {
        var cupCircle = input.map { it.toInt() - 48 }.toMutableList().circular()
        var currentCupIndex = 0
        repeat(moves) {
            val currentCup = cupCircle[currentCupIndex]
            val pickedUpCups = pickUpCups(cupCircle, currentCupIndex)
            val destinationCupIndex = findDestinationCup(currentCup, cupCircle)
            cupCircle.addAll(destinationCupIndex + 1, pickedUpCups)
            cupCircle = shiftUntilCurrentCupIsAtCorrectPosition(cupCircle, currentCupIndex, currentCup)
            currentCupIndex++
        }
        return cupsAfterCupOne(cupCircle)
    }

    private fun pickUpCups(cupCircle: MutableList<Int>, currentCupIndex: Int): List<Int> {
        val pickedUpCups = (currentCupIndex + 1..currentCupIndex + 3).map { cupCircle[it] }.toList()
        cupCircle.removeAll(pickedUpCups)
        return pickedUpCups
    }

    private fun findDestinationCup(currentCupLabel: Int, cupCircle: MutableList<Int>): Int {
        var destinationCupLabel = currentCupLabel - 1
        var destinationCupIndex: Int
        do {
            destinationCupIndex = cupCircle.indexOf(destinationCupLabel)
            destinationCupLabel--
            if (destinationCupLabel < 0) {
                destinationCupLabel = 9
            }
        } while (destinationCupIndex < 0)
        return destinationCupIndex
    }

    private fun shiftUntilCurrentCupIsAtCorrectPosition(
        cupCircle: MutableList<Int>,
        currentCupIndex: Int,
        currentCup: Int
    ): MutableList<Int> {
        val newCurrentCupIndex = cupCircle.indexOf(currentCup)
        val offset = newCurrentCupIndex - currentCupIndex
        return (0 until cupCircle.count()).map { index ->
            cupCircle[index + offset]
        }.toMutableList().circular()
    }

    private fun cupsAfterCupOne(cupCircle: MutableList<Int>) =
        (1 until 9).map { cupCircle[it + cupCircle.indexOf(1)] }.joinToString("")
}