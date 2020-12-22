package org.plaehn.adventofcode.day21

import org.junit.jupiter.api.Test

class AllergenAssessorTest {
    
    @Test
    fun `Parse input for small input`() {
        val allergenAssessor = AllergenAssessor.fromString(readInput("small_input.txt"))

        println(allergenAssessor)
    }

    private fun readInput(resourceName: String): List<String> =
        AllergenAssessor::class.java
            .getResource(resourceName)
            .readText()
            .lines()
            .filter { it.isNotBlank() }
}