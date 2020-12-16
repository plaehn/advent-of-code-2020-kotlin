package org.plaehn.adventofcode.day16

import org.junit.Test

class TicketValidatorTest {

    @Test
    fun `Parse small input`() {
        val validator = TicketValidator.fromString(readInput("small_input.txt"))

        println(validator)
    }

    private fun readInput(resourceName: String): String =
        TicketValidator::class.java
            .getResource(resourceName)
            .readText()
}