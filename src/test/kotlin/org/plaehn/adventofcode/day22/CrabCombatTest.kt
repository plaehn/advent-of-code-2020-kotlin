package org.plaehn.adventofcode.day22

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CrabCombatTest {

    @Test
    fun `Compute winner score for small input`() {
        val crabCombat = CrabCombat.fromString(readInput("small_input.txt"))

        val score = crabCombat.playUntilWeHaveMatchWinnerAndReturnScore()

        assertThat(score).isEqualTo(306)
    }

    @Test
    fun `Compute winner score for large input`() {
        val crabCombat = CrabCombat.fromString(readInput("input.txt"))

        val score = crabCombat.playUntilWeHaveMatchWinnerAndReturnScore()

        assertThat(score).isEqualTo(34664)
    }

    @Test
    fun `Recursive combat has recursion end`() {
        val crabCombat = CrabCombat.fromString(readInput("recursive_small_input.txt"), isRecursive = true)

        val score = crabCombat.playUntilWeHaveMatchWinnerAndReturnScore()

        assertThat(score).isEqualTo(105)
    }

    @Test
    fun `Compute recursive combat winner score for small input`() {
        val crabCombat = CrabCombat.fromString(readInput("small_input.txt"), isRecursive = true)

        val score = crabCombat.playUntilWeHaveMatchWinnerAndReturnScore()

        assertThat(score).isEqualTo(291)
    }

    @Test
    fun `Compute recursive combat winner score for large input`() {
        val crabCombat = CrabCombat.fromString(readInput("input.txt"), isRecursive = true)

        val score = crabCombat.playUntilWeHaveMatchWinnerAndReturnScore()

        assertThat(score).isEqualTo(32018)
    }

    private fun readInput(resourceName: String): String =
        CrabCombat::class.java
            .getResource(resourceName)
            .readText()
}