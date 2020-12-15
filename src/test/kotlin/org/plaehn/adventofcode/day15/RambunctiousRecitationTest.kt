package org.plaehn.adventofcode.day15

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class RambunctiousRecitationTest {

    @Test
    fun `Rambunctious recitation number spoken at turn 2020 for starting numbers 0, 3, 6`() {
        val rambunctiousRecitation = RambunctiousRecitation(startingNumbers = listOf(0, 3, 6))

        val number = rambunctiousRecitation.numberSpokenAt(lastTurn = 2020)

        assertThat(number).isEqualTo(436)
    }

    @Test
    fun `Rambunctious recitation number spoken at turn 2020 for starting numbers 1, 3, 2`() {
        val rambunctiousRecitation = RambunctiousRecitation(startingNumbers = listOf(1, 3, 2))

        val number = rambunctiousRecitation.numberSpokenAt(lastTurn = 2020)

        assertThat(number).isEqualTo(1)
    }

    @Test
    fun `Rambunctious recitation number spoken at turn 2020 for starting numbers 14, 3, 1, 0, 9, 5`() {
        val rambunctiousRecitation = RambunctiousRecitation(startingNumbers = listOf(14, 3, 1, 0, 9, 5))

        val number = rambunctiousRecitation.numberSpokenAt(lastTurn = 2020)

        assertThat(number).isEqualTo(614)
    }

    @Test
    fun `Rambunctious recitation number spoken at turn 30000000 for starting numbers 0, 3, 6`() {
        val rambunctiousRecitation = RambunctiousRecitation(startingNumbers = listOf(0, 3, 6))

        val number = rambunctiousRecitation.numberSpokenAt(lastTurn = 30000000)

        assertThat(number).isEqualTo(175594)
    }

    @Test
    fun `Rambunctious recitation number spoken at turn 30000000 for starting numbers 1, 3, 2`() {
        val rambunctiousRecitation = RambunctiousRecitation(startingNumbers = listOf(1, 3, 2))

        val number = rambunctiousRecitation.numberSpokenAt(lastTurn = 30000000)

        assertThat(number).isEqualTo(2578)
    }

    @Test
    fun `Rambunctious recitation number spoken at turn 30000000 for starting numbers 14, 3, 1, 0, 9, 5`() {
        val rambunctiousRecitation = RambunctiousRecitation(startingNumbers = listOf(14, 3, 1, 0, 9, 5))

        val number = rambunctiousRecitation.numberSpokenAt(lastTurn = 30000000)

        assertThat(number).isEqualTo(1065)
    }
}