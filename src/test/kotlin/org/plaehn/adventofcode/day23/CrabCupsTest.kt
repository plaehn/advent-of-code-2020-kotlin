package org.plaehn.adventofcode.day23

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.product

class CrabCupsTest {

    @Test
    fun `Play crab cups for 10 moves for test input`() {
        val crabCups = CrabCups("389125467")

        val cupsAfterCupOne = crabCups.play(10).joinToString("")

        assertThat(cupsAfterCupOne).isEqualTo("92658374")
    }

    @Test
    fun `Play crab cups for 100 moves for test input`() {
        val crabCups = CrabCups("389125467")

        val cupsAfterCupOne = crabCups.play(100).joinToString("")

        assertThat(cupsAfterCupOne).isEqualTo("67384529")
    }

    // XXX
    @Test
    fun `Play crab cups for 1000000 moves for test input`() {
        val crabCups = CrabCups("389125467")

        val cupsAfterCupOne = crabCups.play(1000000).joinToString("")

        assertThat(cupsAfterCupOne).isEqualTo("59724368")
    }

    @Test
    fun `Play crab cups for 100 moves for real input`() {
        val crabCups = CrabCups("643719258")

        val cupsAfterCupOne = crabCups.play(100).joinToString("")

        assertThat(cupsAfterCupOne).isEqualTo("54896723")
    }

    @Test
    fun `Play crab cups for 10000000 moves for test input`() {
        val crabCups = CrabCups("389125467")

        //     7s for  100
        // 1m 10s for 1000
        val productOfTwoCupsAfterOne = crabCups.play(10000000, 1000000).take(2).product()

        assertThat(productOfTwoCupsAfterOne).isEqualTo(149245887792)
    }

    @Test
    fun `Diffs are the same`() {
        val distinctDiffs = readInput("diffs.txt").zipWithNext().map { it.second - it.first }.distinct()
        println(distinctDiffs)
    }

    private fun readInput(resourceName: String): List<Int> =
        CrabCups::class.java
            .getResource(resourceName)
            .readText()
            .lines()
            .filter { it.isNotBlank() }
            .map { it.toInt() }
}