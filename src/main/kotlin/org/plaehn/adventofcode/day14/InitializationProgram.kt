package org.plaehn.adventofcode.day14

import org.plaehn.adventofcode.day14.Bitmask.Bit.*
import java.math.BigInteger


class InitializationProgram(private val instructions: List<Instruction>) {

    private var currentBitmask: Bitmask = Bitmask.fromString("X".repeat(36))
    private var memory: MutableMap<Int, Long> = mutableMapOf()

    fun applyInstructions() {
        instructions.forEach {
            when (it) {
                is Instruction.SetBitmask -> currentBitmask = it.bitmask
                is Instruction.SetMemory -> memory[it.address] = currentBitmask.applyTo(it.value)
            }
        }
    }

    fun sumMemoryValues(): Long = memory.values.sum()

    companion object {
        fun fromLines(lines: List<String>): InitializationProgram =
            InitializationProgram(lines.map { Instruction.fromInputLine(it) })
    }
}

sealed class Instruction {
    data class SetBitmask(val bitmask: Bitmask) : Instruction()
    data class SetMemory(val address: Int, val value: Long) : Instruction()

    companion object {
        fun fromInputLine(line: String): Instruction {
            val tokens = line.split("=").map { it.trim() }
            val instruction = tokens[0]
            val value = tokens[1]
            return when (instruction) {
                "mask" -> SetBitmask(Bitmask.fromString(value))
                else -> {
                    val address = instruction.split('[', ']')[1]
                    SetMemory(address.toInt(), value.toLong())
                }
            }
        }
    }
}

data class Bitmask(val bits: List<Bit>) {

    enum class Bit {
        ZERO, ONE, DO_NOT_CHANGE
    }

    fun applyTo(value: Long): Long =
        bits.foldIndexed(value) { index, modifiedValue, bit ->
            when (bit) {
                ZERO -> setBit(index, modifiedValue, 0)
                ONE -> setBit(index, modifiedValue, 1)
                DO_NOT_CHANGE -> modifiedValue
            }
        }

    private fun setBit(index: Int, value: Long, bit: Int): Long {
        val binaryString = value.toString(2).padStart(36, '0')
        val changed = StringBuilder(binaryString).also { it.setCharAt(index, '0' + bit) }
        return BigInteger(changed.toString(), 2).toLong()
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
