package org.plaehn.adventofcode.day8

import org.plaehn.adventofcode.day8.GameConsole.CompletionType.*
import org.plaehn.adventofcode.day8.Instruction.*
import java.util.*

class GameConsole {

    data class CompletionResult(val completionType: CompletionType, val accumulator: Int)

    enum class CompletionType {
        INFINITE_LOOP,
        NORMAL,
        NOT_FINISHED_YET
    }

    private val bootCode: MutableList<Instruction>
    private var executed: BitSet

    private var accumulator = 0
    private var currentInstructionIndex = 0

    constructor(bootCode: List<Instruction>) {
        this.bootCode = bootCode.toMutableList()
        this.executed = BitSet(bootCode.count())
    }

    fun fixBootCode(): CompletionResult {
        for ((index, instruction) in bootCode.withIndex()) {
            when (instruction) {
                is Accumulator -> continue
                is Jump -> bootCode[index] = NoOp(instruction.offset)
                is NoOp -> bootCode[index] = Jump(instruction.offset)
            }
            val completionResult = runBootCodeUntilCompletion()
            if (completionResult.completionType == NORMAL) {
                return completionResult
            }
            bootCode[index] = instruction
        }
        throw IllegalStateException("Couldn't fix boot code :-(")
    }

    fun runBootCodeUntilCompletion(): CompletionResult {
        resetState()
        while (NOT_FINISHED_YET == checkCompletion()) {
            executeCurrentInstruction()
        }
        return CompletionResult(checkCompletion(), accumulator)
    }

    private fun resetState() {
        accumulator = 0
        currentInstructionIndex = 0
        executed.clear()
    }

    private fun checkCompletion(): CompletionType {
        return when {
            executed[currentInstructionIndex] -> INFINITE_LOOP
            currentInstructionIndex == bootCode.count() -> NORMAL
            else -> NOT_FINISHED_YET
        }
    }

    private fun executeCurrentInstruction() {
        executed[currentInstructionIndex] = true
        when (val instruction = bootCode[currentInstructionIndex]) {
            is NoOp -> ++currentInstructionIndex
            is Jump -> currentInstructionIndex += instruction.offset
            is Accumulator -> {
                accumulator += instruction.offset
                ++currentInstructionIndex
            }
        }
    }
}

