package org.plaehn.adventofcode.day16

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.product

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

    @Test
    fun `Determine field mapping for my ticket for small input`() {
        val validator = TicketValidator.fromString(readInput("small_input_for_part_2.txt"))

        val ticket = validator.determineFieldMappingsForMyTicket()

        assertThat(ticket.fieldMappings).containsExactlyInAnyOrder("class" to 12, "row" to 11, "seat" to 13)
    }

    @Test
    fun `Determine field mapping for my ticket for large input`() {
        val validator = TicketValidator.fromString(readInput("input.txt"))

        val ticket = validator.determineFieldMappingsForMyTicket()

        val productOfDepartureFields = ticket.fieldMappings
            .filter { it.first.startsWith("departure") }
            .map { it.second.toLong() }
            .product()

        assertThat(productOfDepartureFields).isEqualTo(1439429522627)
    }

    private fun readInput(resourceName: String): String =
        TicketValidator::class.java
            .getResource(resourceName)
            .readText()
}