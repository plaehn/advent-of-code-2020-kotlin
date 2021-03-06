package org.plaehn.adventofcode.day21

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AllergenAssessorTest {

    @Test
    fun `Find allergen free ingredients for small input`() {
        val allergenAssessor = AllergenAssessor.fromString(readInput("small_input.txt"))

        val allergenFreeIngredients = allergenAssessor.findAllergenFreeIngredients()

        assertThat(allergenFreeIngredients).containsExactlyInAnyOrder("kfcds", "nhms", "trh", "sbzzf", "sbzzf")
    }

    @Test
    fun `Find allergen free ingredients for large input`() {
        val allergenAssessor = AllergenAssessor.fromString(readInput("input.txt"))

        val allergenFreeIngredients = allergenAssessor.findAllergenFreeIngredients()

        assertThat(allergenFreeIngredients.count()).isEqualTo(2423)
    }

    @Test
    fun `Find canonical dangerous ingredient list for small input`() {
        val allergenAssessor = AllergenAssessor.fromString(readInput("small_input.txt"))

        val ingredientList = allergenAssessor.findCanonicalDangerousIngredientList()

        assertThat(ingredientList).isEqualTo("mxmxvkd,sqjhc,fvjkl")
    }

    @Test
    fun `Find canonical dangerous ingredient list for large input`() {
        val allergenAssessor = AllergenAssessor.fromString(readInput("input.txt"))

        val ingredientList = allergenAssessor.findCanonicalDangerousIngredientList()

        assertThat(ingredientList).isEqualTo("jzzjz,bxkrd,pllzxb,gjddl,xfqnss,dzkb,vspv,dxvsp")
    }

    private fun readInput(resourceName: String): List<String> =
        AllergenAssessor::class.java
            .getResource(resourceName)
            .readText()
            .lines()
            .filter { it.isNotBlank() }
}