package org.plaehn.adventofcode.day8

import java.lang.Integer.parseInt

sealed class Instruction {

    object NoOp : Instruction()
    data class Jump(val offset: Int) : Instruction()
    data class Accumulator(val offset: Int) : Instruction()

    companion object {
        fun fromString(input: String): Instruction {
            val tokens = input.split(" ")
            return when (tokens[0]) {
                "nop" -> NoOp
                "jmp" -> Jump(parseInt(tokens[1]))
                "acc" -> Accumulator(parseInt(tokens[1]))
                else -> throw IllegalArgumentException("Invalid instruction $input")
            }
        }
    }
}

