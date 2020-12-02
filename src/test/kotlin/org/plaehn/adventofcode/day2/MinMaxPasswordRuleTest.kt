package org.plaehn.adventofcode.day2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MinMaxPasswordRuleTest {

    private val rule = MinMaxPasswordRule(min = 2, max = 3, character = 'a')

    @Test
    fun `Too few characters is invalid`() {
        assertThat(rule.isValid("")).isFalse
        assertThat(rule.isValid("a")).isFalse
    }

    @Test
    fun `Too many characters is invalid`() {
        assertThat(rule.isValid("aaaa")).isFalse
    }

    @Test
    fun `Minimum is inclusive`() {
        assertThat(rule.isValid("aa")).isTrue
    }

    @Test
    fun `Maximum is inclusive`() {
        assertThat(rule.isValid("aaa")).isTrue
    }

    @Test
    fun `Count valid passwords in input`() {
        val numberOfValidPasswords = readInput()
                .map { it.split(":") }
                .count {
                    val rule = MinMaxPasswordRule.fromString(it[0].trim())
                    val password = it[1].trim()
                    rule.isValid(password)

                }
        assertThat(numberOfValidPasswords).isEqualTo(460)
    }

    private fun readInput(): List<String> =
            MinMaxPasswordRule::class.java
                    .getResource("input.txt")
                    .readText()
                    .lines()
                    .filter { it.isNotBlank() }
}
