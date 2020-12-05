package org.plaehn.adventofcode.day4

import org.plaehn.adventofcode.common.groupByBlankLines
import org.plaehn.adventofcode.common.tokenize

object PassportScanner {

    fun scan(input: String): List<Passport> = input.groupByBlankLines().map { toPassword(it) }

    private fun toPassword(passportString: String) = Passport(
            fields = passportString
                    .tokenize()
                    .map { PassportField.fromKeyValueString(it) }
    )
}

