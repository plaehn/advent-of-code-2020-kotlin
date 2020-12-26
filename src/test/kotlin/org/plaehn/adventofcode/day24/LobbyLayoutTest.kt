package org.plaehn.adventofcode.day24

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LobbyLayoutTest {

    @Test
    fun `Apply instructions in small input`() {
        val lobbyLayout = LobbyLayout.fromString(readInput("small_input.txt"))

        val numberOfBlackTiles = lobbyLayout.applyInstructions()

        assertThat(numberOfBlackTiles).isEqualTo(10)
    }

    @Test
    fun `Apply instructions in large input`() {
        val lobbyLayout = LobbyLayout.fromString(readInput("input.txt"))

        val numberOfBlackTiles = lobbyLayout.applyInstructions()

        assertThat(numberOfBlackTiles).isEqualTo(300)
    }

    private fun readInput(resourceName: String): List<String> =
        LobbyLayout::class.java
            .getResource(resourceName)
            .readText()
            .lines()
            .filter { it.isNotBlank() }
}