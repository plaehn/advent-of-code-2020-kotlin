package org.plaehn.adventofcode.day4

import org.plaehn.adventofcode.day4.PassportFieldKey.COUNTRY_ID

data class Passport(val fields: List<PassportField>) {

    private val requiredKeys = PassportFieldKey.values().subtract(setOf(COUNTRY_ID))

    fun hasAllRequiredFields() = fields.map { it.key }.containsAll(requiredKeys)

    fun isValid() = fields.all { it.key.isValid(it.value) }
}
