package org.plaehn.adventofcode.common

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class VectorTest {

    @Test
    fun `Operators work correctly for vectors of same dimension`() {
        assertThat(Vector(1, 2, 3, 4) + Vector(3, 7, 11, 13)).isEqualTo(Vector(4, 9, 14, 17))
        assertThat(Vector(1, 2, 3, 4) - Vector(3, 7, 11, 13)).isEqualTo(Vector(-2, -5, -8, -9))
        assertThat(Vector(1, 2, 3, 4) * 7).isEqualTo(Vector(7, 14, 21, 28))
    }

    @Test
    fun `One-dimensional neighbors`() {
        assertThat(Vector(7).neighborOffsets()).containsExactly(Vector(-1), Vector(1))
        assertThat(Vector(7).neighbors()).containsExactly(Vector(6), Vector(8))
    }

    @Test
    fun `Two-dimensional neighbors`() {
        assertThat(Vector(7, 11).neighborOffsets()).containsExactlyInAnyOrder(
            Vector(-1, -1), Vector(0, -1), Vector(1, -1),
            Vector(-1, 0), Vector(1, 0),
            Vector(-1, 1), Vector(0, 1), Vector(1, 1),
        )
        assertThat(Vector(7, 11).neighbors()).containsExactlyInAnyOrder(
            Vector(6, 10), Vector(7, 10), Vector(8, 10),
            Vector(6, 11), Vector(8, 11),
            Vector(6, 12), Vector(7, 12), Vector(8, 12)
        )
    }

    @Test
    fun `Higher dimensional neighbors`() {
        assertThat(Vector(7, 11, 13).neighborOffsets().count()).isEqualTo(26)
        assertThat(Vector(7, 11, 13, 21).neighborOffsets().count()).isEqualTo(80)
    }
}