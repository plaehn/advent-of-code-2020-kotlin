package org.plaehn.adventofcode.day15

class RambunctiousRecitation(private val startingNumbers: List<Int>) {

    fun numberSpokenAt(turn: Int): Int {
        val numbersSpoken = startingNumbers.toMutableList()
        repeat(turn - startingNumbers.count()) {
            val number = numbersSpoken.last()
            val index = numbersSpoken.dropLast(1).lastIndexOf(number)
            val newNumber = if (index < 0) 0 else numbersSpoken.count() - 1 - index
            numbersSpoken.add(newNumber)
        }
        return numbersSpoken.last()
    }
}