package org.plaehn.adventofcode.day14

import java.math.BigInteger


class InitializationProgram(
    private val instructions: List<Instruction>,
    private val useVersionTwo: Boolean = false
) {

    private var currentBitmask: Bitmask = Bitmask("X".repeat(36))
    private var memory: MutableMap<Long, Long> = mutableMapOf()

    fun applyInstructions() {
        instructions.forEach { instruction ->
            when (instruction) {
                is Instruction.SetBitmask -> currentBitmask = instruction.bitmask
                is Instruction.SetMemory -> {
                    if (useVersionTwo) {
                        // XXX use strategy?
                        val allAddresses = currentBitmask.applyToVersionTwo(instruction.address)
                        allAddresses.forEach { memory[it] = instruction.value }
                    } else {
                        memory[instruction.address] = currentBitmask.applyTo(instruction.value)
                    }
                }
            }
        }
    }

    fun sumMemoryValues(): Long = memory.values.sum()

    companion object {
        fun fromLines(lines: List<String>, useVersionTwo: Boolean = false): InitializationProgram =
            InitializationProgram(lines.map { Instruction.fromInputLine(it) }, useVersionTwo)
    }
}

sealed class Instruction {
    data class SetBitmask(val bitmask: Bitmask) : Instruction()
    data class SetMemory(val address: Long, val value: Long) : Instruction()

    companion object {
        fun fromInputLine(line: String): Instruction {
            val tokens = line.split("=").map { it.trim() }
            val instruction = tokens[0]
            val value = tokens[1]
            return when (instruction) {
                "mask" -> SetBitmask(Bitmask(value))
                else -> {
                    val address = instruction.split('[', ']')[1]
                    SetMemory(address.toLong(), value.toLong())
                }
            }
        }
    }
}

data class Bitmask(val bits: String) {

    fun applyTo(value: Long): Long =
        bits.foldIndexed(value) { index, modifiedValue, bit ->
            when (bit) {
                '0' -> setBit(index, modifiedValue, 0)
                '1' -> setBit(index, modifiedValue, 1)
                'X' -> modifiedValue
                else -> throw IllegalArgumentException()
            }
        }

    fun applyToVersionTwo(value: Long): List<Long> =
        bits.foldIndexed(mutableListOf(value)) { index, modifiedValues, bit ->
            val newList = mutableListOf<Long>()
            modifiedValues.forEach { modifiedValue ->
                when (bit) {
                    '0' -> newList.add(modifiedValue)
                    '1' -> newList.add(setBit(index, modifiedValue, 1))
                    'X' -> {
                        newList.add(setBit(index, modifiedValue, 0))
                        newList.add(setBit(index, modifiedValue, 1))
                    }
                    else -> throw IllegalArgumentException()
                }
            }
            newList
        }

    private fun setBit(index: Int, value: Long, bit: Int): Long {
        val binaryString = value.toString(2).padStart(36, '0')
        val changed = StringBuilder(binaryString).also { it.setCharAt(index, '0' + bit) }
        return BigInteger(changed.toString(), 2).toLong()
    }
}
