package org.plaehn.adventofcode.day4

object PassportScanner {

    fun scan(input: String): List<Passport> = input.groupByBlankLines().map { toPassword(it) }

    private fun toPassword(passportString: String): Passport = Passport(
            fields = passportString
                    .splitIntoKeyValueStrings()
                    .map { Passport.Field.fromKeyValueString(it) }
    )
}

private fun String.splitIntoKeyValueStrings(): List<String> = this.split("\\s+".toRegex()).filter { it.isNotBlank() }

private fun String.groupByBlankLines(): List<String> = this.split("\r\n\\s*\r\n".toRegex())

