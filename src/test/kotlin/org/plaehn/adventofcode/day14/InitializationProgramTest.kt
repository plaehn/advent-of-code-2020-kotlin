package org.plaehn.adventofcode.day14

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

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

    private fun readInput(resourceName: String): List<String> =
        InitializationProgram::class.java
            .getResource(resourceName)
            .readText()
            .lines()
            .filter { it.isNotBlank() }
}