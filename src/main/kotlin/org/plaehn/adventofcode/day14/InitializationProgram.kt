package org.plaehn.adventofcode.day14

import org.plaehn.adventofcode.day14.Bitmask.Bit.*

data class InitializationProgram(private val instructions: List<Instruction>) {

    companion object {
        fun fromLines(lines: List<String>): InitializationProgram =
            InitializationProgram(lines.map { Instruction.fromInputLine(it) })
    }
}

sealed class Instruction {
    data class SetBitmask(private val bitmask: Bitmask) : Instruction()
    data class SetMemory(private val address: Int, private val value: Int) : Instruction()

    companion object {
        fun fromInputLine(line: String): Instruction {
            val tokens = line.split("=").map { it.trim() }
            val instruction = tokens[0]
            val value = tokens[1]
            return when (instruction) {
                "mask" -> SetBitmask(Bitmask.fromString(value))
                else -> {
                    val address = instruction.split('[', ']')[1]
                    SetMemory(address.toInt(), value.toInt())
                }
            }
        }
    }
}

data class Bitmask(val bits: List<Bit>) {

    enum class Bit {
        ZERO, ONE, DO_NOT_CHANGE
    }

    companion object {
        fun fromString(input: String): Bitmask {
            return Bitmask(input.map {
                when (it) {
                    'X' -> DO_NOT_CHANGE
                    '1' -> ONE
                    '0' -> ZERO
                    else -> throw IllegalArgumentException("Cannot use char $it in bitmask")
                }
            })
        }
    }
}
