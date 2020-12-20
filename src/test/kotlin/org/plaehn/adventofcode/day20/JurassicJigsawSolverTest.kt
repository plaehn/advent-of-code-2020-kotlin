package org.plaehn.adventofcode.day20

import org.junit.jupiter.api.Test

class JurassicJigsawSolverTest {

    @Test
    fun `Parse input`() {
        val solver = JurassicJigsawSolver.fromString(readInput("small_input.txt"))

        println(solver)
    }

    private fun readInput(resourceName: String): String =
        JurassicJigsawSolver::class.java
            .getResource(resourceName)
            .readText()
}
