package org.plaehn.adventofcode.day24

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class LobbyLayoutTest {

    @Test
    fun `Apply instructions in small input`() {
        val lobbyLayout = LobbyLayout.fromString(readInput("small_input.txt"))

        val numberOfBlackTiles = lobbyLayout.applyInstructionsAndCountBlackTiles()

        assertThat(numberOfBlackTiles).isEqualTo(10)
    }

    @Test
    fun `Apply instructions in large input`() {
        val lobbyLayout = LobbyLayout.fromString(readInput("input.txt"))

        val numberOfBlackTiles = lobbyLayout.applyInstructionsAndCountBlackTiles()

        assertThat(numberOfBlackTiles).isEqualTo(300)
    }

    @Test
    fun `Living art for 10 cycles for small input`() {
        val lobbyLayout = LobbyLayout.fromString(readInput("small_input.txt"))

        val numberOfBlackTiles = lobbyLayout.livingArt(10)

        assertThat(numberOfBlackTiles).isEqualTo(37)
    }

    @Test
    @Disabled("Runs too long")
    fun `Living art for 100 cycles for small input`() {
        val lobbyLayout = LobbyLayout.fromString(readInput("small_input.txt"))

        val numberOfBlackTiles = lobbyLayout.livingArt(100)

        assertThat(numberOfBlackTiles).isEqualTo(2208)
    }

    @Test
    @Disabled("Runs too long")
    fun `Living art for 100 cycles  for large input`() {
        val lobbyLayout = LobbyLayout.fromString(readInput("input.txt"))

        val numberOfBlackTiles = lobbyLayout.livingArt(100)

        assertThat(numberOfBlackTiles).isEqualTo(3466)
    }

    private fun readInput(resourceName: String): List<String> =
        LobbyLayout::class.java
            .getResource(resourceName)
            .readText()
            .lines()
            .filter { it.isNotBlank() }
}