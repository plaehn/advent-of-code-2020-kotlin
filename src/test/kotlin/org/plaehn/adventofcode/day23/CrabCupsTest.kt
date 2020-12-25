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

    @Test
    fun `Play crab cups for 100 moves for real input`() {
        val crabCups = CrabCups("643719258")

        val cupsAfterCupOne = crabCups.play(100).joinToString("")

        assertThat(cupsAfterCupOne).isEqualTo("54896723")
    }

    @Test
    fun `Play crab cups for 10000000 moves for test input`() {
        val crabCups = CrabCups("389125467")

        // 7,5 sec for 1 mio
        val productOfTwoCupsAfterOne = crabCups.play(100, 1000000).take(2).product()

        assertThat(productOfTwoCupsAfterOne).isEqualTo(149245887792)
    }
}