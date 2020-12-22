package org.plaehn.adventofcode.day21

data class AllergenAssessor(val foods: List<Food>) {

    fun findAllergenFreeIngredients(): List<String> {

        val allergenToPotentialContainer: Map<String, MutableSet<String>> =
            foods.fold(mutableMapOf()) { map, food ->
                food.allergens.forEach { allergen ->
                    if (!map.containsKey(allergen)) {
                        map[allergen] = food.ingredients.toSet().toMutableSet()
                    } else {
                        map[allergen] = map[allergen]!!.intersect(food.ingredients).toMutableSet()
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
            val ingredients = ingredientsAndAllergens[0]
                .split(' ')
                .map { it.trim() }
                .filter { it.isNotBlank() }
                .toList()
            val allergens = ingredientsAndAllergens[1]
                .split(", ")
                .map { it.trim() }
                .filter { it.isNotBlank() }
                .toList()
            return Food(ingredients, allergens)
        }
    }
}
