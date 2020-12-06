package org.plaehn.adventofcode.day6

import org.plaehn.adventofcode.common.countCharsAppearing
import org.plaehn.adventofcode.common.countTokens
import org.plaehn.adventofcode.common.countUniqueChars
import org.plaehn.adventofcode.common.groupByBlankLines

object AnswerCounter {

    fun countAnswersFromAnyoneInGroup(input: String): Int = input
            .groupByBlankLines()
            .fold(0) { answerCount, group -> answerCount + group.filter { it in 'a'..'z' }.countUniqueChars() }

    fun countAnswersFromEveryoneInGroup(input: String): Int = input
            .groupByBlankLines()
            .fold(0) { answerCount, group ->
                answerCount + group.filter { it in 'a'..'z' }.countCharsAppearing(ntimes = group.countTokens())
            }
}

