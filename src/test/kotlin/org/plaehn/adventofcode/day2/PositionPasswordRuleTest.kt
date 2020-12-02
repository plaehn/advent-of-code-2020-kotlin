package org.plaehn.adventofcode.day2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PositionPasswordRuleTest {

    private val rule = PositionPasswordRule(position1 = 1, position2 = 2, character = 'a')

    @Test
    fun `Both chars not matching is invalid`() {
        assertThat(rule.isValid("bb")).isFalse
    }

    @Test
    fun `Both chars matching is invalid`() {
        assertThat(rule.isValid("aa")).isFalse
    }

    @Test
    fun `Exactly one char matching is valid`() {
        assertThat(rule.isValid("ab")).isTrue
        assertThat(rule.isValid("ba")).isTrue
    }

    @Test
    fun `Count valid passwords in input`() {
        val numberOfValidPasswords = readInput()
                .map { it.split(":") }
                .count {
                    val rule = PositionPasswordRule.fromString(it[0].trim())
                    val password = it[1].trim()
                    rule.isValid(password)

                }
        assertThat(numberOfValidPasswords).isEqualTo(251)
    }

    private fun readInput(): List<String> =
            PositionPasswordRule::class.java
                    .getResource("input.txt")
                    .readText()
                    .lines()
                    .filter { it.isNotBlank() }
}
