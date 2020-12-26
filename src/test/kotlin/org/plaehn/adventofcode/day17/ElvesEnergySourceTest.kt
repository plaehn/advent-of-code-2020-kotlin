package org.plaehn.adventofcode.day17

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ElvesEnergySourceTest {

    private val charToActive: (Char) -> Boolean = { it == '#' }

    @Test
    fun `Count active cubes in 3D after boot process for small input`() {
        val elvesEnergySource = ElvesEnergySource(
            Grid.from2DInput(
                dimensions = 3,
                lines = readInput("small_input.txt"),
                charToValue = charToActive
            )
        )

        val numberOfActiveCubes = elvesEnergySource.countActiveCubesAfterBootProcess(6)

        assertThat(numberOfActiveCubes).isEqualTo(112)
    }

    @Test
    fun `Count active cubes in 3D after boot process for large input`() {
        val elvesEnergySource = ElvesEnergySource(
            Grid.from2DInput(
                dimensions = 3,
                lines = readInput("input.txt"),
                charToValue = charToActive
            )
        )

        val numberOfActiveCubes = elvesEnergySource.countActiveCubesAfterBootProcess(6)

        assertThat(numberOfActiveCubes).isEqualTo(289)
    }

    @Test
    fun `Count active cubes in 4D after boot process for small input`() {
        val elvesEnergySource = ElvesEnergySource(
            Grid.from2DInput(
                dimensions = 4,
                lines = readInput("small_input.txt"),
                charToValue = charToActive
            )
        )

        val numberOfActiveCubes = elvesEnergySource.countActiveCubesAfterBootProcess(6)

        assertThat(numberOfActiveCubes).isEqualTo(848)
    }

    @Test
    fun `Count active cubes in 4D after boot process for large input`() {

        val elvesEnergySource = ElvesEnergySource(
            Grid.from2DInput(
                dimensions = 4,
                lines = readInput("input.txt"),
                charToValue = charToActive
            )
        )

        val numberOfActiveCubes = elvesEnergySource.countActiveCubesAfterBootProcess(6)

        assertThat(numberOfActiveCubes).isEqualTo(2084)
    }

    private fun readInput(resourceName: String): List<String> =
        ElvesEnergySource::class.java
            .getResource(resourceName)
            .readText()
            .lines()
            .filter { it.isNotBlank() }
}