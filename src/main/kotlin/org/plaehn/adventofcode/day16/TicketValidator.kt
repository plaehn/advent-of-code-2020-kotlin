package org.plaehn.adventofcode.day16

import org.plaehn.adventofcode.common.groupByBlankLines

data class TicketValidator(
    val fields: List<Field>,
    val myTicket: Ticket,
    val nearbyTickets: List<Ticket>
) {

    fun computeTicketScanningErrorRate(): Int = nearbyTickets
        .map { it.fieldValues }
        .flatten()
        .filter { fieldValue -> isInvalid(fieldValue) }
        .sum()

    private fun isInvalid(it: Int) = null == fields.find { field -> field.isValidValue(it) }

    companion object {
        fun fromString(input: String): TicketValidator {
            val groups = input.groupByBlankLines()

            val fields = groups[0].lines().map { Field.fromString(it) }
            val myTicket = Ticket.fromString(groups[1].lines()[1])
            val nearbyTickets = groups[2].lines().drop(1).map { Ticket.fromString(it) }

            return TicketValidator(fields, myTicket, nearbyTickets)
        }
    }
}

data class Field(val name: String, val validRanges: List<IntRange>) {

    fun isValidValue(value: Int): Boolean = null != validRanges.find { it.contains(value) }

    companion object {
        fun fromString(input: String): Field {
            val nameAndRanges = input.split(':')

            val name = nameAndRanges[0].trim()
            val validRanges = nameAndRanges[1].split(" or ")
                .map { it.trim() }
                .map { it.toIntRange() }

            return Field(name, validRanges)
        }
    }
}

data class Ticket(val fieldValues: List<Int>) {

    companion object {
        fun fromString(input: String): Ticket = Ticket(
            input
                .split(',')
                .map { it.toInt() }
        )
    }
}

private fun String.toIntRange(): IntRange {
    val fromAndTo = this.split("-")
    return IntRange(fromAndTo[0].toInt(), fromAndTo[1].toInt())
}