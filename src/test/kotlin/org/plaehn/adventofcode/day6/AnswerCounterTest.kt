package org.plaehn.adventofcode.day6

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AnswerCounterTest {

    @Test
    fun `Count answers in small input`() {
        val numberOfAnswers = AnswerCounter.countAnswers(input = readInput("small_input.txt"))

        assertThat(numberOfAnswers).isEqualTo(11)
    }

    @Test
    fun `Count answers in large input`() {
        val numberOfAnswers = AnswerCounter.countAnswers(input = readInput("input.txt"))

        assertThat(numberOfAnswers).isEqualTo(6726)
    }

    private fun readInput(resourceName: String): String =
            AnswerCounter::class.java
                    .getResource(resourceName)
                    .readText()
}
