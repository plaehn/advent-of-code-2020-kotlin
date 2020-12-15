package org.plaehn.adventofcode.day15

class RambunctiousRecitation(private val startingNumbers: List<Int>) {

    fun numberSpokenAt(lastTurn: Int): Int {
        val lastSeenAtTurn = startingNumbers
            .dropLast(1)
            .withIndex()
            .associate { it.value to it.index + 1 }
            .toMutableMap()

        var lastSpoken = startingNumbers.last()
        var newNumber: Int = lastSpoken
        (1 + startingNumbers.count()..lastTurn).forEach { turn ->
            val index = lastSeenAtTurn[lastSpoken]
            lastSeenAtTurn[newNumber] = turn - 1
            newNumber = if (index == null) 0 else turn - index - 1
            lastSpoken = newNumber
        }

        return lastSpoken
    }
}