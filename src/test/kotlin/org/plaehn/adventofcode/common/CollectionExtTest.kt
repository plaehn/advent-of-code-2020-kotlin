package org.plaehn.adventofcode.common

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CollectionExtTest {

    @Test
    fun `Create all combinations of size 1`() {
        val numbers: Set<Int> = setOf(1, 2, 3)
        assertThat(numbers.combinations(ofSize = 1)).contains(setOf(1), setOf(2), setOf(3))
    }

    @Test
    fun `Create all combinations of size 2`() {
        val numbers: Set<Int> = setOf(1, 2, 3)
        assertThat(numbers.combinations(ofSize = 2)).containsExactlyInAnyOrder(setOf(1, 2), setOf(1, 3), setOf(2, 3))
    }
}
