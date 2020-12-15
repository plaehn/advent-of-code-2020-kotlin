package org.plaehn.adventofcode.day14

import org.junit.Test

class InitializationProgramTest {

    @Test
    fun `Parse input into initialization program`() {
        val initializationProgram = InitializationProgram.fromLines(readInput("small_input.txt"))

        println(initializationProgram)
    }

    private fun readInput(resourceName: String): List<String> =
        InitializationProgram::class.java
            .getResource(resourceName)
            .readText()
            .lines()
            .filter { it.isNotBlank() }
}