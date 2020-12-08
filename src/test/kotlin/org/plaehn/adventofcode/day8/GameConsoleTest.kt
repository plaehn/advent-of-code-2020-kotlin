package org.plaehn.adventofcode.day8

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.plaehn.adventofcode.day8.Instruction.*

class GameConsoleTest {

    @Test
    fun `fromString parses small input correctly`() {
        val instructions = readInput("small_input.txt").map { Instruction.fromString(it) }

        assertThat(instructions).containsExactly(
                NoOp,
                Accumulator(1),
                Jump(4),
                Accumulator(3),
                Jump(-3),
                Accumulator(-99),
                Accumulator(1),
                Jump(-4),
                Accumulator(6)
        )
    }

    @Test
    fun `Run boot code from small input `() {
        val instructions = readInput("small_input.txt").map { Instruction.fromString(it) }
        val accumulatorValueBeforeInfiniteLoop = GameConsole(instructions).runBootCode()

        assertThat(accumulatorValueBeforeInfiniteLoop).isEqualTo(5)
    }

    @Test
    fun `Run boot code from large input `() {
        val instructions = readInput("input.txt").map { Instruction.fromString(it) }
        val accumulatorValueBeforeInfiniteLoop = GameConsole(instructions).runBootCode()

        assertThat(accumulatorValueBeforeInfiniteLoop).isEqualTo(1262)
    }

    private fun readInput(resourceName: String): List<String> =
            Instruction::class.java
                    .getResource(resourceName)
                    .readText()
                    .lines()
                    .filter { it.isNotBlank() }
}
