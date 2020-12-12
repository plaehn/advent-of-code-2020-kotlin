package org.plaehn.adventofcode.day12

data class NavigationInstruction(val action: Action, val value: Int) {

    enum class Action {
        NORTH,
        SOUTH,
        EAST,
        WEST,
        LEFT,
        RIGHT,
        FORWARD;

        companion object {
            fun fromCharacterRepresentation(characterRepresentation: Char): Action =
                values().find { it.name.first() == characterRepresentation }
                    ?: throw IllegalArgumentException("No Action for $characterRepresentation")
        }
    }

    companion object {
        fun fromString(input: String): NavigationInstruction {
            val action = Action.fromCharacterRepresentation(input.first())
            val value = input.drop(1).toInt()
            return NavigationInstruction(action, value)
        }
    }
}
