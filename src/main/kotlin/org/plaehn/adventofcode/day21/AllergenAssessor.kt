package org.plaehn.adventofcode.day21

data class AllergenAssessor(val foods: List<Food>) {

    fun findAllergenFreeIngredients(): List<String> {

        val allergenToPotentialContainer: Map<String, Set<String>> =
            foods.fold(mutableMapOf()) { map, food ->
                food.allergens.forEach { allergen ->
                    map[allergen] =
                        if (map.containsKey(allergen)) {
                            map[allergen]!! intersect food.ingredients
                        } else {
                            food.ingredients.toSet()
                        }
                }
                map
            }

        val allIngredientsThatPotentiallyContainAnAllergen = allergenToPotentialContainer
            .flatMap { it.value }
            .toSet()

        val allIngredients = foods.map { it.ingredients }.flatten()

        return allIngredients.minus(allIngredientsThatPotentiallyContainAnAllergen)
    }

    companion object {
        fun fromString(input: List<String>): AllergenAssessor = AllergenAssessor(input.map { Food.fromString(it) })
    }
}

data class Food(val ingredients: List<String>, val allergens: List<String>) {

    companion object {
        fun fromString(input: String): Food {
            val ingredientsAndAllergens = input.split("(contains ", ")")
            val ingredients = ingredientsAndAllergens[0].splitByAndCleanUp(" ")
            val allergens = ingredientsAndAllergens[1].splitByAndCleanUp(", ")
            return Food(ingredients, allergens)
        }
    }
}

private fun String.splitByAndCleanUp(separator: String): List<String> =
    split(separator)
        .map { it.trim() }
        .filter { it.isNotBlank() }
        .toList()
