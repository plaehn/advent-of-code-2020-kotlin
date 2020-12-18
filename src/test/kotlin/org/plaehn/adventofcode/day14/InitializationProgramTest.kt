package org.plaehn.adventofcode.day14

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InitializationProgramTest {

    @Test
    fun `Apply instructions and sum memory values for small input`() {
        val initializationProgram = InitializationProgram.fromLines(readInput("small_input.txt"))

        initializationProgram.applyInstructions()
        val summedMemoryValues = initializationProgram.sumMemoryValues()

        assertThat(summedMemoryValues).isEqualTo(165)
    }

    @Test
    fun `Apply instructions and sum memory values for large input`() {
        val initializationProgram = InitializationProgram.fromLines(readInput("input.txt"))

        initializationProgram.applyInstructions()
        val summedMemoryValues = initializationProgram.sumMemoryValues()

        assertThat(summedMemoryValues).isEqualTo(7997531787333)
    }

    @Test
    fun `Apply instructions using v2 and sum memory values for small input`() {
        val initializationProgram = InitializationProgram.fromLines(readInput("small_input_v2.txt"), true)

        initializationProgram.applyInstructions()
        val summedMemoryValues = initializationProgram.sumMemoryValues()

        assertThat(summedMemoryValues).isEqualTo(208)
    }

    @Test
    fun `Apply instructions using v2 and sum memory values for large input`() {
        val initializationProgram = InitializationProgram.fromLines(readInput("input.txt"), true)

        initializationProgram.applyInstructions()
        val summedMemoryValues = initializationProgram.sumMemoryValues()

        assertThat(summedMemoryValues).isEqualTo(3564822193820)
    }

    private fun readInput(resourceName: String): List<String> =
        InitializationProgram::class.java
            .getResource(resourceName)
            .readText()
            .lines()
            .filter { it.isNotBlank() }
}