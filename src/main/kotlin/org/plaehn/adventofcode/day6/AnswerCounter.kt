package org.plaehn.adventofcode.day6

import org.plaehn.adventofcode.common.countUniqueChars
import org.plaehn.adventofcode.common.groupByBlankLines

object AnswerCounter {

    fun countAnswers(input: String): Int = input
            .groupByBlankLines()
            .map { group -> group.filter { it in 'a'..'z' } }
            .fold(0) { count, group -> count + group.countUniqueChars() }
}
