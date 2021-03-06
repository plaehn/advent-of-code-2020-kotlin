package org.plaehn.adventofcode.day20

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JurassicJigsawSolverTest {

    @Test
    fun `Compute product of corner tiles for small input`() {
        val solver = JurassicJigsawSolver.fromString(readInput("small_input.txt"))

        val product = solver.computeProductOfCornerTileIds()

        assertThat(product).isEqualTo(20899048083289)
    }

    @Test
    fun `Compute product of corner tiles for large input`() {
        val solver = JurassicJigsawSolver.fromString(readInput("input.txt"))

        val product = solver.computeProductOfCornerTileIds()

        assertThat(product).isEqualTo(22878471088273)
    }

    @Test
    fun `Computer water roughness for small input`() {
        val solver = JurassicJigsawSolver.fromString(readInput("small_input.txt"))

        val waterRoughness = solver.computeWaterRoughnessOfHabitat()

        assertThat(waterRoughness).isEqualTo(273)
    }

    @Test
    fun `Computer water roughness for large input`() {
        val solver = JurassicJigsawSolver.fromString(readInput("input.txt"))

        val waterRoughness = solver.computeWaterRoughnessOfHabitat()

        assertThat(waterRoughness).isEqualTo(1680)
    }

    private fun readInput(resourceName: String): String =
        JurassicJigsawSolver::class.java
            .getResource(resourceName)
            .readText()
}
