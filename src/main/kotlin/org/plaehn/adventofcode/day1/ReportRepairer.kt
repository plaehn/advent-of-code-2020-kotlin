package org.plaehn.adventofcode.day1

class ReportRepairer {

    fun fixExpenseReport(numbers: Set<Int>): Int {
        for (firstNumber in numbers) {
            for (secondNumber in numbers) {
                if (firstNumber != secondNumber) {
                    if (firstNumber + secondNumber == 2020) {
                        return firstNumber * secondNumber
                    }
                }
            }
        }
        return -1
    }
}
