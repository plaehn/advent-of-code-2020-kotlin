package org.plaehn.adventofcode.day4

object PassportScanner {

    fun scan(input: String): List<Passport> = input.groupByBlankLines().map { toPassword(it) }

    private fun toPassword(passportString: String): Passport = Passport(
            fields = passportString
                    .tokenize()
                    .map { Passport.PassportField.fromKeyValueString(it) }
    )
}

private fun String.tokenize(): List<String> = this.split("\\s+".toRegex()).filter { it.isNotBlank() }

private fun String.groupByBlankLines(): List<String> = this.split("\r\n\\s*\r\n".toRegex())

