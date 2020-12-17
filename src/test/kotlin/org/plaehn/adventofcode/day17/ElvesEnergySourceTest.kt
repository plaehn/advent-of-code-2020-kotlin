package org.plaehn.adventofcode.day17

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ElvesEnergySourceTest {

    @Test
    fun `Count active cubes after boot process for small input`() {
        val elvesEnergySource = ElvesEnergySource(Grid3D.from2DInput(readInput("small_input.txt")))

        val numberOfActiveCubes = elvesEnergySource.countActiveCubesAfterBootProcess(6)

        assertThat(numberOfActiveCubes).isEqualTo(112)
    }

    @Test
    fun `Count active cubes after boot process for large input`() {
        val elvesEnergySource = ElvesEnergySource(Grid3D.from2DInput(readInput("input.txt")))

        val numberOfActiveCubes = elvesEnergySource.countActiveCubesAfterBootProcess(6)

        assertThat(numberOfActiveCubes).isEqualTo(289)
    }

    private fun readInput(resourceName: String): List<String> =
        ElvesEnergySource::class.java
            .getResource(resourceName)
            .readText()
            .lines()
            .filter { it.isNotBlank() }

}