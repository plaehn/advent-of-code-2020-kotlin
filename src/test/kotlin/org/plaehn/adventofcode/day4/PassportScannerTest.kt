package org.plaehn.adventofcode.day4

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.day4.Passport.Field

class PassportScannerTest {

    @Test
    fun `Scan passports from small input`() {
        val passports = PassportScanner.scan(input = readInput("small_input.txt"))

        assertThat(passports.count()).isEqualTo(4)
        assertThat(passports[3].fields).containsExactlyInAnyOrder(
                Field(key = "hcl", value = "#cfa07d"),
                Field(key = "eyr", value = "2025"),
                Field(key = "pid", value = "166559648"),
                Field(key = "iyr", value = "2011"),
                Field(key = "ecl", value = "brn"),
                Field(key = "hgt", value = "59in"),
        )
    }

    @Test
    fun `Count valid passports in small input`() {
        val passports = PassportScanner.scan(input = readInput("small_input.txt"))

        val numberOfValidPassports = passports.count { it.isValid() }

        assertThat(numberOfValidPassports).isEqualTo(2)
    }

    @Test
    fun `Count valid passports in large input`() {
        val passports = PassportScanner.scan(input = readInput("input.txt"))

        val numberOfValidPassports = passports.count { it.isValid() }

        assertThat(numberOfValidPassports).isEqualTo(219)
    }

    private fun readInput(resourceName: String): String =
            PassportScanner::class.java
                    .getResource(resourceName)
                    .readText()
}
