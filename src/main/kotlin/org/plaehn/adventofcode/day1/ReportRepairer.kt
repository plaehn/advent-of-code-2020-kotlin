package org.plaehn.adventofcode.day1

import com.google.common.collect.Sets

class ReportRepairer {

    fun fixExpenseReport(subsetSize: Int, numbers: Set<Int>): Int? =
            numbers.combinations(ofSize = subsetSize)
                    .filter { it.sum() == 2020 }
                    .map { it.product() }
                    .firstOrNull()
}

fun <E> Set<E>.combinations(ofSize: Int): Set<Set<E>> = Sets.combinations(this, ofSize)

fun Iterable<Int>.product(): Int = this.reduce(Int::times)
