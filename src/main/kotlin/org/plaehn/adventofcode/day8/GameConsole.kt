package org.plaehn.adventofcode.day8

import org.plaehn.adventofcode.day8.Instruction.*
import java.util.*

class GameConsole(private val bootCode: List<Instruction>) {

    private var accumulator = 0
    private var currentInstructionIndex = 0
    private var executed = BitSet(bootCode.count())

    fun runBootCode(): Int {
        while (!executed[currentInstructionIndex]) {
            execute(currentInstructionIndex)
        }
        return accumulator
    }

    private fun execute(instructionIndex: Int) {
        executed[instructionIndex] = true
        when (val instruction = bootCode[instructionIndex]) {
            NoOp -> ++currentInstructionIndex
            is Jump -> currentInstructionIndex += instruction.offset
            is Accumulator -> {
                accumulator += instruction.offset
                ++currentInstructionIndex
            }
        }
    }
}
