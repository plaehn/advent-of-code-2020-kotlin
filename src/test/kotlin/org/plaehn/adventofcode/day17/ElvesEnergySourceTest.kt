package org.plaehn.adventofcode.day17

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ElvesEnergySourceTest {

    @Test
    fun `Count active cubes in 3D after boot process for small input`() {
        val elvesEnergySource = ElvesEnergySource(Grid.from2DInput(dimensions = 3, readInput("small_input.txt")))

        val numberOfActiveCubes = elvesEnergySource.countActiveCubesAfterBootProcess(6)

        assertThat(numberOfActiveCubes).isEqualTo(112)
    }

    @Test
    fun `Count active cubes in 3D after boot process for large input`() {
        val elvesEnergySource = ElvesEnergySource(Grid.from2DInput(dimensions = 3, readInput("input.txt")))

        val numberOfActiveCubes = elvesEnergySource.countActiveCubesAfterBootProcess(6)

        assertThat(numberOfActiveCubes).isEqualTo(289)
    }

    @Test
    fun `Count active cubes in 4D after boot process for small input`() {
        val elvesEnergySource = ElvesEnergySource(Grid.from2DInput(dimensions = 4, readInput("small_input.txt")))

        val numberOfActiveCubes = elvesEnergySource.countActiveCubesAfterBootProcess(6)

        assertThat(numberOfActiveCubes).isEqualTo(848)
    }

    @Test
    fun `Count active cubes in 4D after boot process for large input`() {
        val elvesEnergySource = ElvesEnergySource(Grid.from2DInput(dimensions = 4, readInput("input.txt")))

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