package org.plaehn.adventofcode.day4

data class PassportField(val key: PassportFieldKey, val value: String) {

    companion object {
        fun fromKeyValueString(input: String): PassportField {
            val tokens = input.split(":")
            return PassportField(key = PassportFieldKey.from(shortForm = tokens[0]), value = tokens[1])
        }
    }
}
