package org.plaehn.adventofcode.day23

import com.ginsberg.cirkle.circular

class CrabCups(private val input: String) {

    fun play(moves: Int, numberOfCups: Int = 9): List<Int> {
        var cupCircle = buildInitialCupCircle(numberOfCups)
        var currentCupIndex = 0
        repeat(moves) { move ->

//            println("-- move ${move + 1} --")
//            val cupOutput = cupCircle.mapIndexed { index, cup ->
//                if (index == currentCupIndex) "($cup)" else "$cup"
//            }.joinToString("  ")
//            println("cups: $cupOutput")

            val currentCup = cupCircle[currentCupIndex]
            val pickedUpCups = pickUpCups(cupCircle, currentCupIndex)

            // println("pick up: ${pickedUpCups.joinToString(", ")}")

            val destinationCupIndex = findDestinationCup(currentCup, pickedUpCups, cupCircle)

            // println("destination: ${cupCircle[destinationCupIndex]}")

            cupCircle.addAll(destinationCupIndex + 1, pickedUpCups)
            cupCircle = shiftUntilCurrentCupIsAtCorrectPosition(cupCircle, currentCupIndex, currentCup)
            currentCupIndex++
        }
        return cupsAfterCupOne(cupCircle)
    }

    private fun buildInitialCupCircle(numberOfCups: Int): MutableList<Int> {
        val cupsFromInput = input.map { it.toInt() - 48 }
        val moreCups = (cupsFromInput.size + 1..numberOfCups)
        return (cupsFromInput + moreCups).toMutableList().circular()
    }

    private fun pickUpCups(cupCircle: MutableList<Int>, currentCupIndex: Int): List<Int> {
        val pickedUpCups = (currentCupIndex + 1..currentCupIndex + 3).map { cupCircle[it] }.toList()
        cupCircle.removeAll(pickedUpCups)
        return pickedUpCups
    }

    private fun findDestinationCup(currentCupLabel: Int, pickedUpCups: List<Int>, cupCircle: MutableList<Int>): Int {
        var destinationCupLabel = previousCupLabel(currentCupLabel, cupCircle)
        while (pickedUpCups.contains(destinationCupLabel)) {
            destinationCupLabel--
            if (destinationCupLabel == 0) {
                destinationCupLabel = cupCircle.count() + 3
            }
        }
        return cupCircle.indexOf(destinationCupLabel)
    }

    private fun previousCupLabel(cupLabel: Int, cupCircle: MutableList<Int>): Int =
        if (cupLabel == 1) cupCircle.count() + 3 else cupLabel - 1

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
        (1 until 9).map { cupCircle[it + cupCircle.indexOf(1)] }
}