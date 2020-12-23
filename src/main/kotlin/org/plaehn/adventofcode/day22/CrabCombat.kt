package org.plaehn.adventofcode.day22

import org.plaehn.adventofcode.common.findUnique
import org.plaehn.adventofcode.common.groupByBlankLines

class CrabCombat(private var decks: List<Deck>) {

    fun playUntilWeHaveAWinnerAndReturnScore(): Int {
        var winner: Deck?
        do {
            playRound()
            winner = determineWinner()
        } while (winner == null)

        return winner.computeScore()
    }

    private fun playRound() {
        val playerToTopCard = decks
            .filter { !it.isEmpty() }
            .mapIndexed { _, deck ->
                deck to deck.removeTopCard()
            }
        val roundWinner = playerToTopCard.maxByOrNull { it.second } ?: throw IllegalStateException()
        val topCardsSortedDesc = playerToTopCard.map { it.second }.sortedByDescending { it }
        roundWinner.first.addToBottom(topCardsSortedDesc)
    }

    private fun determineWinner(): Deck? = decks.findUnique { !it.isEmpty() }

    companion object {
        fun fromString(input: String): CrabCombat = CrabCombat(
            input.groupByBlankLines().map { group ->
                Deck.fromString(group)
            }
        )
    }
}

class Deck(private var cards: MutableList<Int>) {

    fun removeTopCard(): Int = cards.removeFirst()

    fun addToBottom(newCards: List<Int>) {
        cards.addAll(newCards)
    }

    fun isEmpty(): Boolean = cards.isEmpty()

    fun computeScore(): Int =
        cards
            .reversed()
            .foldIndexed(0) { index, sum, card ->
                sum + card * (index + 1)
            }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Deck

        if (cards != other.cards) return false

        return true
    }

    override fun hashCode(): Int = cards.hashCode()

    companion object {
        fun fromString(input: String): Deck = Deck(
            input
                .lines()
                .drop(1)
                .filter { it.isNotBlank() }
                .map { it.toInt() }
                .toMutableList()
        )
    }
}
