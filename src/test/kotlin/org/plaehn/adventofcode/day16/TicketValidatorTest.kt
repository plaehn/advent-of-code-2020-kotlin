package org.plaehn.adventofcode.day16

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TicketValidatorTest {

    @Test
    fun `Compute ticket scanning error rate for small input`() {
        val validator = TicketValidator.fromString(readInput("small_input.txt"))

        val errorRate = validator.computeTicketScanningErrorRate()

        assertThat(errorRate).isEqualTo(71)
    }

    @Test
    fun `Compute ticket scanning error rate for large input`() {
        val validator = TicketValidator.fromString(readInput("input.txt"))

        val errorRate = validator.computeTicketScanningErrorRate()

        assertThat(errorRate).isEqualTo(21980)
    }

    private fun readInput(resourceName: String): String =
        TicketValidator::class.java
            .getResource(resourceName)
            .readText()
}