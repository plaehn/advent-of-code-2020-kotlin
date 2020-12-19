package org.plaehn.adventofcode.day19

import org.junit.jupiter.api.Test

class MonsterMessagesTest {

    @Test
    fun `Parse input`() {
        val monsterMessages = MonsterMessages.fromString(readInput("medium_input.txt"))

        println(monsterMessages)
    }

    private fun readInput(resourceName: String): String =
        MonsterMessages::class.java
            .getResource(resourceName)
            .readText()
}