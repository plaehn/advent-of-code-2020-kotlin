package org.plaehn.adventofcode.day8

import java.lang.Integer.parseInt

sealed class Instruction() {

    data class NoOp(val offset: Int) : Instruction()
    data class Jump(val offset: Int) : Instruction()
    data class Accumulator(val offset: Int) : Instruction()

    companion object {
        fun fromString(input: String): Instruction {
            val tokens = input.split(" ")
            val instruction = tokens[0]
            val offset = parseInt(tokens[1])
            return when (instruction) {
                "nop" -> NoOp(offset)
                "jmp" -> Jump(offset)
                "acc" -> Accumulator(offset)
                else -> throw IllegalArgumentException("Invalid instruction $input")
            }
        }
    }
}
