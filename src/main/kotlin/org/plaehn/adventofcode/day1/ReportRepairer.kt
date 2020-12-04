package org.plaehn.adventofcode.day1

import org.plaehn.adventofcode.common.combinations
import org.plaehn.adventofcode.common.product

class ReportRepairer {

    fun fixExpenseReport(subsetSize: Int, numbers: Set<Int>): Int? =
            numbers.combinations(ofSize = subsetSize)
                    .filter { it.sum() == 2020 }
                    .map { it.product() }
                    .firstOrNull()
}

