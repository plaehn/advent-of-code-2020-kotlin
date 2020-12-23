package org.plaehn.adventofcode.day22

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CrabCombatTest {

    @Test
    fun `Compute winner score for small input`() {
        val crabCombat = CrabCombat.fromString(readInput("small_input.txt"))

        val score = crabCombat.playUntilWeHaveAWinnerAndReturnScore()

        assertThat(score).isEqualTo(306)
    }

    @Test
    fun `Compute winner score for large input`() {
        val crabCombat = CrabCombat.fromString(readInput("input.txt"))

        val score = crabCombat.playUntilWeHaveAWinnerAndReturnScore()

        assertThat(score).isEqualTo(34664)
    }

    private fun readInput(resourceName: String): String =
        CrabCombat::class.java
            .getResource(resourceName)
            .readText()
}