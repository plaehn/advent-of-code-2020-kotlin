package org.plaehn.adventofcode.day19

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MonsterMessagesTest {

    @Test
    fun `Count number of valid messages in small input`() {
        val monsterMessages = MonsterMessages.fromString(readInput("small_input.txt"))

        val numberOfValidMessages = monsterMessages.countNumberOfValidMessages()

        assertThat(numberOfValidMessages).isEqualTo(2)
    }

    @Test
    fun `Count number of valid messages in medium input`() {
        val monsterMessages = MonsterMessages.fromString(readInput("medium_input.txt"))

        val numberOfValidMessages = monsterMessages.countNumberOfValidMessages()

        assertThat(numberOfValidMessages).isEqualTo(8)
    }

    @Test
    fun `Count number of valid messages in large input`() {
        val monsterMessages = MonsterMessages.fromString(readInput("input.txt"))

        val numberOfValidMessages = monsterMessages.countNumberOfValidMessages()

        assertThat(numberOfValidMessages).isEqualTo(224)
    }

    @Test
    fun `Count number of valid messages with replacement rules in large input`() {
        val monsterMessages = MonsterMessages.fromString(readInput("input.txt"))

        // 8: 42 | 42 8
        // 11: 42 31 | 42 11 31
        val replacements = listOf(
            Rule(8, "( ( 42 )+ )"),
            Rule(
                // This is good enough
                11, "( ( 42 31 ) " +
                        "| ( 42 42 31 31 ) " +
                        "| ( 42 42 42 31 31 31 ) " +
                        "| ( 42 42 42 42 31 31 31 31 ) " +
                        "| ( 42 42 42 42 42 31 31 31 31 31 ) " +
                        ")"
            )
        )

        val numberOfValidMessages = monsterMessages.countNumberOfValidMessages(replacements)

        assertThat(numberOfValidMessages).isEqualTo(436)
    }

    private fun readInput(resourceName: String): String =
        MonsterMessages::class.java
            .getResource(resourceName)
            .readText()
}