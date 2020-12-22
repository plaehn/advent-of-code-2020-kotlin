package org.plaehn.adventofcode.day21

data class AllergenAssessor(val foods: List<Food>) {

    fun findCanonicalDangerousIngredientList(): String {

        val allergenToPotentialContainer = computeAllergenToPotentialContainerMap()
        val uniqueAllergenToContainer = mutableMapOf<String, String>()

        do {
            val uniquePairs = allergenToPotentialContainer.filter { it.value.count() == 1 }
            uniquePairs.forEach { uniquePair ->
                uniqueAllergenToContainer[uniquePair.key] = uniquePair.value.first()
                allergenToPotentialContainer.forEach { pair ->
                    if (pair != uniquePair) {
                        pair.value.removeAll(uniquePair.value)
                    }
                }
                allergenToPotentialContainer.remove(uniquePair.key)
            }
        } while (uniquePairs.isNotEmpty())

        return uniqueAllergenToContainer.entries.sortedBy { it.key }.joinToString(",") { it.value }
    }

    fun findAllergenFreeIngredients(): List<String> {

        val allergenToPotentialContainer = computeAllergenToPotentialContainerMap()

        val allIngredientsThatPotentiallyContainAnAllergen = allergenToPotentialContainer
            .flatMap { it.value }
            .toSet()

        val allIngredients = foods.map { it.ingredients }.flatten()

        return allIngredients.minus(allIngredientsThatPotentiallyContainAnAllergen)
    }

    private fun computeAllergenToPotentialContainerMap(): MutableMap<String, MutableSet<String>> =
        foods.fold(mutableMapOf()) { map, food ->
            food.allergens.forEach { allergen ->
                map[allergen] =
                    if (map.containsKey(allergen)) {
                        (map[allergen]!! intersect food.ingredients).toMutableSet()
                    } else {
                        food.ingredients.toMutableSet()
                    }
            }
            map
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
