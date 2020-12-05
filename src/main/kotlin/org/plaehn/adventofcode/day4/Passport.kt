package org.plaehn.adventofcode.day4

import org.plaehn.adventofcode.day4.PassportFieldKey.COUNTRY_ID

data class Passport(val fields: List<PassportField>) {

    private val requiredKeys = PassportFieldKey.values().subtract(setOf(COUNTRY_ID))

    fun hasAllRequiredFields(): Boolean = fields.map { it.key }.containsAll(requiredKeys)

    fun isValid(): Boolean = fields.all { it.key.isValid(it.value) }

    data class PassportField(val key: PassportFieldKey, val value: String) {
        companion object {
            fun fromKeyValueString(input: String): PassportField {
                val tokens = input.split(":")
                return PassportField(key = PassportFieldKey.from(shortForm = tokens[0]), value = tokens[1])
            }
        }
    }
}
