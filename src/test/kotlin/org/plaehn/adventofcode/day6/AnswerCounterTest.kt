package org.plaehn.adventofcode.day6

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AnswerCounterTest {

    @Test
    fun `Count answers from anyone in group using small input`() {
        val numberOfAnswers = AnswerCounter.countAnswersFromAnyoneInGroup(input = readInput("small_input.txt"))

        assertThat(numberOfAnswers).isEqualTo(11)
    }

    @Test
    fun `Count answers from anyone in group using large input`() {
        val numberOfAnswers = AnswerCounter.countAnswersFromAnyoneInGroup(input = readInput("input.txt"))

        assertThat(numberOfAnswers).isEqualTo(6726)
    }

    @Test
    fun `Count answers from everyone in group using small input`() {
        val numberOfAnswers = AnswerCounter.countAnswersFromEveryoneInGroup(input = readInput("small_input.txt"))

        assertThat(numberOfAnswers).isEqualTo(6)
    }

    @Test
    fun `Count answers from everyone in group using large input`() {
        val numberOfAnswers = AnswerCounter.countAnswersFromEveryoneInGroup(input = readInput("input.txt"))

        assertThat(numberOfAnswers).isEqualTo(3316)
    }

    private fun readInput(resourceName: String): String =
        AnswerCounter::class.java
            .getResource(resourceName)
            .readText()
}
