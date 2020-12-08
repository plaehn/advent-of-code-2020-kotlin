package org.plaehn.adventofcode.day8

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.plaehn.adventofcode.day8.GameConsole.CompletionType.INFINITE_LOOP
import org.plaehn.adventofcode.day8.GameConsole.CompletionType.NORMAL
import org.plaehn.adventofcode.day8.Instruction.*

class GameConsoleTest {

    @Test
    fun `fromString parses small input correctly`() {
        val instructions = readInput("small_input.txt").map { Instruction.fromString(it) }

        assertThat(instructions).containsExactly(
                NoOp(0),
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
        val completionResult = GameConsole(instructions).runBootCodeUntilCompletion()

        assertThat(completionResult.completionType).isEqualTo(INFINITE_LOOP)
        assertThat(completionResult.accumulator).isEqualTo(5)
    }

    @Test
    fun `Run boot code from large input `() {
        val instructions = readInput("input.txt").map { Instruction.fromString(it) }
        val completionResult = GameConsole(instructions).runBootCodeUntilCompletion()

        assertThat(completionResult.completionType).isEqualTo(INFINITE_LOOP)
        assertThat(completionResult.accumulator).isEqualTo(1262)
    }

    @Test
    fun `Fix boot code from small input `() {
        val instructions = readInput("small_input.txt").map { Instruction.fromString(it) }
        val completionResult = GameConsole(instructions).fixBootCode()

        assertThat(completionResult.completionType).isEqualTo(NORMAL)
        assertThat(completionResult.accumulator).isEqualTo(8)
    }

    @Test
    fun `Fix boot code from large input `() {
        val instructions = readInput("input.txt").map { Instruction.fromString(it) }
        val completionResult = GameConsole(instructions).fixBootCode()

        assertThat(completionResult.completionType).isEqualTo(NORMAL)
        assertThat(completionResult.accumulator).isEqualTo(1643)
    }

    private fun readInput(resourceName: String): List<String> =
            Instruction::class.java
                    .getResource(resourceName)
                    .readText()
                    .lines()
                    .filter { it.isNotBlank() }
}
