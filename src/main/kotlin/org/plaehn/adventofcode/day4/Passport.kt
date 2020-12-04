package org.plaehn.adventofcode.day4

data class Passport(val fields: List<Field>) {

    private val requiredKeys = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    fun isValid(): Boolean = fields.map { it.key }.containsAll(requiredKeys)

    data class Field(val key: String, val value: String) {
        companion object {
            fun fromKeyValueString(input: String): Field {
                val tokens = input.split(":")
                return Field(key = tokens[0], value = tokens[1])
            }
        }
    }

}
