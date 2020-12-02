package org.plaehn.adventofcode.day2

data class PositionPasswordRule(val position1: Int, val position2: Int, val character: Char) {

    fun isValid(password: String): Boolean =
            (password[position1 - 1] == character).xor(password[position2 - 1] == character)

    companion object {
        fun fromString(input: String): PositionPasswordRule {
            val tokens = input.split(" ", "-")
            return PositionPasswordRule(
                    position1 = Integer.valueOf(tokens[0]),
                    position2 = Integer.valueOf(tokens[1]),
                    character = tokens[2].first())
        }
    }
}
