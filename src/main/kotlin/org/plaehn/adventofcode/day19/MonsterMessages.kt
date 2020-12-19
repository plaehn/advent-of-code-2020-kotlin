package org.plaehn.adventofcode.day19

import org.plaehn.adventofcode.common.groupByBlankLines

data class MonsterMessages(
    private val rules: List<Rule>,
    private val messages: List<String>
) {

    fun countNumberOfValidMessages(replacements: List<Rule> = emptyList()): Int {
        val rulesByNumber = rules.associateBy { it.number }.toMutableMap()

        replacements.forEach { replacement ->
            rulesByNumber[replacement.number] = replacement
        }

        var regexString = rulesByNumber[0]!!.regexString

        do {
            val tokens = regexString.split('(', ')', ' ', '|')
            tokens.forEach { token ->
                if (token.matches(numberRegex)) {
                    val ruleNumber = token.toInt()
                    val rule = rulesByNumber[ruleNumber]!!
                    regexString = regexString.replace(" $token ", " " + rule.regexString + " ")
                }
            }
        } while (regexString.contains(numberRegex))

        val regex = regexString.replace(" ", "").toRegex()

        return messages.count { regex.matches(it) }
    }

    override fun toString() = "${rules.joinToString("\n")}\n\n${messages.joinToString("\n")}"

    companion object {

        private val numberRegex = "[0-9]+".toRegex()

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
    val regexString: String
) {
    override fun toString(): String = "$number: $regexString"

    companion object {
        fun fromString(input: String): Rule {
            val numberAndRegex = input.split(":")
            val number = numberAndRegex[0].toInt()
            val regex = numberAndRegex[1].trim().removePrefix("\"").removeSuffix("\"")
            return Rule(number, "( $regex )")
        }
    }
}

