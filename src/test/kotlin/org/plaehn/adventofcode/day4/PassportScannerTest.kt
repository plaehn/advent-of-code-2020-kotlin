package org.plaehn.adventofcode.day4

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.day4.PassportFieldKey.*

class PassportScannerTest {

    @Test
    fun `Scan passports from small input`() {
        val passports = PassportScanner.scan(input = readInput("small_input.txt"))

        assertThat(passports.count()).isEqualTo(4)
        assertThat(passports[3].fields).containsExactlyInAnyOrder(
                PassportField(key = HAIR_COLOR, value = "#cfa07d"),
                PassportField(key = EXPIRATION_YEAR, value = "2025"),
                PassportField(key = PASSPORT_ID, value = "166559648"),
                PassportField(key = ISSUE_YEAR, value = "2011"),
                PassportField(key = EYE_COLOR, value = "brn"),
                PassportField(key = HEIGHT, value = "59in"),
        )
    }

    @Test
    fun `Count passports that have all required fields in small input`() {
        val passports = PassportScanner.scan(input = readInput("small_input.txt"))

        val numberOfValidPassports = passports.count { it.hasAllRequiredFields() }

        assertThat(numberOfValidPassports).isEqualTo(2)
    }

    @Test
    fun `Count passports that have all required fields in large input`() {
        val passports = PassportScanner.scan(input = readInput("input.txt"))

        val numberOfValidPassports = passports.count { it.hasAllRequiredFields() }

        assertThat(numberOfValidPassports).isEqualTo(219)
    }

    @Test
    fun `Check valid passports`() {
        val passports = PassportScanner.scan(input = readInput("valid.txt"))

        assertThat(passports.all { it.hasAllRequiredFields() && it.isValid() }).isTrue
    }

    @Test
    fun `Check invalid passports`() {
        val passports = PassportScanner.scan(input = readInput("invalid.txt"))

        assertThat(passports.none { it.hasAllRequiredFields() && it.isValid() }).isTrue
    }

    @Test
    fun `Count passports that have all required fields and are valid in large input`() {
        val passports = PassportScanner.scan(input = readInput("input.txt"))

        val numberOfValidPassports = passports.count { it.hasAllRequiredFields() && it.isValid() }

        assertThat(numberOfValidPassports).isEqualTo(127)
    }

    private fun readInput(resourceName: String): String =
            PassportScanner::class.java
                    .getResource(resourceName)
                    .readText()
}
