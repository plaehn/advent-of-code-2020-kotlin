package org.plaehn.adventofcode.day16

import org.plaehn.adventofcode.common.groupByBlankLines

class TicketValidator(
    private val fieldValidators: List<FieldValidator>,
    private val myTicketValues: TicketValues,
    private val nearbyTicketValues: List<TicketValues>
) {

    fun determineFieldMappingsForMyTicket(): Ticket {
        val validNearbyTicketValues = nearbyTicketValues.filter {
            it.fieldValues.all { fieldValue -> isValid(fieldValue) }
        }

        val valueToMatchingFieldValidators = myTicketValues.fieldValues
            .mapIndexed { index, value ->
                val nearbyValuesAtIndex = validNearbyTicketValues.map { it.fieldValues[index] }
                val matchingFieldValidators = fieldValidators.filter { fieldValidator ->
                    nearbyValuesAtIndex.all { fieldValidator.isValidValue(it) }
                }
                value to matchingFieldValidators.toMutableList()
            }

        val fieldMappings: MutableList<Pair<String, Int>> = mutableListOf()

        do {
            val uniquelyMappedPairs = valueToMatchingFieldValidators.filter { it.second.count() == 1 }
            uniquelyMappedPairs.forEach { uniquelyMapped ->
                val validator = uniquelyMapped.second.first()
                fieldMappings.add(validator.name to uniquelyMapped.first)
                valueToMatchingFieldValidators.forEach { it.second.remove(validator) }
            }
        } while (fieldMappings.count() < fieldValidators.count())

        return Ticket(fieldMappings)
    }

    fun computeTicketScanningErrorRate(): Int = nearbyTicketValues
        .map { it.fieldValues }
        .flatten()
        .filter { fieldValue -> !isValid(fieldValue) }
        .sum()

    private fun isValid(it: Int) = null != fieldValidators.find { field -> field.isValidValue(it) }

    companion object {
        fun fromString(input: String): TicketValidator {
            val groups = input.groupByBlankLines()

            val fields = groups[0].lines().map { FieldValidator.fromString(it) }
            val myTicket = TicketValues.fromString(groups[1].lines()[1])
            val nearbyTickets = groups[2].lines().drop(1).map { TicketValues.fromString(it) }

            return TicketValidator(fields, myTicket, nearbyTickets)
        }
    }
}

data class FieldValidator(val name: String, val validRanges: List<IntRange>) {

    fun isValidValue(value: Int): Boolean = null != validRanges.find { it.contains(value) }

    companion object {
        fun fromString(input: String): FieldValidator {
            val nameAndRanges = input.split(':')

            val name = nameAndRanges[0].trim()
            val validRanges = nameAndRanges[1].split(" or ")
                .map { it.trim() }
                .map { it.toIntRange() }

            return FieldValidator(name, validRanges)
        }
    }
}

data class TicketValues(val fieldValues: List<Int>) {

    companion object {
        fun fromString(input: String): TicketValues = TicketValues(
            input
                .split(',')
                .map { it.toInt() }
        )
    }
}

data class Ticket(val fieldMappings: List<Pair<String, Int>>)

private fun String.toIntRange(): IntRange {
    val fromAndTo = this.split("-")
    return IntRange(fromAndTo[0].toInt(), fromAndTo[1].toInt())
}