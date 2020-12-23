package org.plaehn.adventofcode.day22

import org.plaehn.adventofcode.common.findUnique
import org.plaehn.adventofcode.common.groupByBlankLines

class CrabCombat(
    private val initialGame: Game,
    private val isRecursive: Boolean
) {

    fun playUntilWeHaveMatchWinnerAndReturnScore(): Int = playGame(initialGame).computeScore()

    private fun playGame(game: Game): Deck {
        val seenGames = mutableSetOf<Game>()
        var currentGame = game
        do {
            if (seenGames.contains(currentGame)) {
                return currentGame.firstDeck()
            }
            seenGames.add(currentGame)

            currentGame.determineMatchWinner()?.let { return it }
            
            val roundWinner = if (isRecursive && currentGame.allPlayersHaveEnoughCardsForRecursiveCombat()) {
                playGame(currentGame.newRecursiveGameDeterminedByTopCard())
            } else {
                playRound(currentGame)
            }

            currentGame = currentGame.newGameFromWinner(roundWinner.player)
        } while (true)
    }

    private fun playRound(game: Game): Deck = game.deckWithHighestTopCard()

    companion object {
        fun fromString(input: String, isRecursive: Boolean = false): CrabCombat = CrabCombat(
            Game(input.groupByBlankLines().mapIndexed() { player, group ->
                Deck.fromString(player, group)
            }),
            isRecursive
        )
    }
}

data class Game(val decks: List<Deck>) {

    fun firstDeck() = decks.first()

    fun allPlayersHaveEnoughCardsForRecursiveCombat() = decks.all { deck -> deck.topCard() <= deck.count() - 1 }

    fun deckWithHighestTopCard(): Deck = decks.maxByOrNull { it.topCard() }!!

    fun determineMatchWinner() = decks.findUnique { !it.isEmpty() }

    fun newRecursiveGameDeterminedByTopCard() = Game(decks.map { it.newDeckDeterminedByTopCard() })

    fun newGameFromWinner(winner: Int) = Game(
        decks.mapIndexed() { player, deck ->
            if (player == winner) {
                Deck(player, newCardsForWinner(winner))
            } else {
                Deck(player, deck.dropFirst())
            }
        }
    )

    private fun newCardsForWinner(winner: Int): List<Int> {
        val allOtherTopCards = decks
            .filter { it.player != winner }
            .map { it.topCard() }
        return decks[winner].dropFirst() + listOf(decks[winner].topCard()) + allOtherTopCards
    }
}

data class Deck(val player: Int, private val cards: List<Int>) {

    fun topCard() = cards.first()

    fun newDeckDeterminedByTopCard() = Deck(player, cards.take(topCard() + 1).drop(1).toMutableList())

    fun dropFirst() = cards.drop(1)

    fun count() = cards.count()

    fun isEmpty() = cards.isEmpty()

    fun computeScore() =
        cards
            .reversed()
            .foldIndexed(0) { index, sum, card ->
                sum + card * (index + 1)
            }

    companion object {
        fun fromString(player: Int, input: String) = Deck(player,
            input
                .lines()
                .drop(1)
                .filter { it.isNotBlank() }
                .map { it.toInt() }
        )
    }
}
