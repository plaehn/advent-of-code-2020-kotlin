package org.plaehn.adventofcode.day1

import com.google.common.collect.Sets

class ReportRepairer {

    fun fixExpenseReport(subsetSize: Int, numbers: Set<Int>): Int {
        val subsets = numbers.combinations(ofSize = subsetSize)
        for (subset in subsets) {
            if (subset.sum() == 2020) {
                return subset.product()
            }
        }
        throw NoSolutionException()
    }
}

fun <E> Set<E>.combinations(ofSize: Int): Set<Set<E>> = Sets.combinations(this, ofSize)

fun Iterable<Int>.product(): Int = this.reduce(Int::times)

