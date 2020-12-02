package org.plaehn.adventofcode.day2

data class MinMaxPasswordRule(val min: Int, val max: Int, val character: Char) {

    fun isValid(password: String): Boolean = password.count { it == character } in min..max

    companion object {
        fun fromString(input: String): MinMaxPasswordRule {
            val tokens = input.split(" ", "-")
            return MinMaxPasswordRule(
                    min = Integer.valueOf(tokens[0]),
                    max = Integer.valueOf(tokens[1]),
                    character = tokens[2].first())

        }
    }
}
