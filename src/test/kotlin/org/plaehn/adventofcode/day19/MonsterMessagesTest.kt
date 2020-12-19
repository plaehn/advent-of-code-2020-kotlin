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

    private fun readInput(resourceName: String): String =
        MonsterMessages::class.java
            .getResource(resourceName)
            .readText()
}