package org.plaehn.adventofcode.day19

import org.plaehn.adventofcode.common.groupByBlankLines

data class MonsterMessages(
    private val rules: List<Rule>,
    private val messages: List<String>
) {

    fun countNumberOfValidMessages(): Int {
        val words = enumerateWords()
        return messages.count { words.contains(it) }
    }

    private fun enumerateWords(): Set<String> {
        val rulesByNumber = rules.associateBy { it.number }.toMutableMap()
        do {
            val lexicalRules = rules.find { it.hasOnlyStringRuleParts() }
        } while (true)
        TODO()
    }

    override fun toString() = "${rules.joinToString("\n")}\n\n${messages.joinToString("\n")}"

    companion object {
        fun fromString(input: String): MonsterMessages {
            val groups = input.groupByBlankLines()
            val rules = groups[0].lines().map { Rule.fromString(it) }
            val messages = groups[1].lines().map { it.trim() }
            return MonsterMessages(rules, messages)
        }
    }
}

data class Rule(
    val number: Int,
    val alternatives: List<List<RulePart>>
) {

    fun hasOnlyStringRuleParts(): Boolean {
        TODO("Not yet implemented")
    }

    override fun toString(): String = "$number: ${alternatives.joinToString(" | ") { it.joinToString(" ") }}"

    companion object {
        fun fromString(input: String): Rule {
            val ruleNumberAndParts = input.split(":")
            val ruleNumber = ruleNumberAndParts[0].toInt()
            val alternativeStrings = ruleNumberAndParts[1].trim().split("|")

            val alternatives = alternativeStrings.map { alternative ->
                alternative.trim().split(" ").map { RulePart.fromString(it) }
            }

            return Rule(ruleNumber, alternatives)
        }
    }
}

sealed class RulePart {
    companion object {
        fun fromString(input: String): RulePart =
            if (input.matches("\"[a-z]\"".toRegex())) {
                StringRulePart(input.substring(1, 2))
            } else {
                RuleNumberRulePart(input.toInt())
            }
    }
}

data class StringRulePart(val string: String) : RulePart() {
    override fun toString() = "\"$string\""
}

data class RuleNumberRulePart(val ruleNumber: Int) : RulePart() {
    override fun toString() = "$ruleNumber"
}

