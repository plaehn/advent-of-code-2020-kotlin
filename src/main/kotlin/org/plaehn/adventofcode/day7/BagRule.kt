package org.plaehn.adventofcode.day7

import org.plaehn.adventofcode.common.tokenize

data class BagRule(val container: Bag, val canContain: List<BagCount>) {

    companion object {
        fun fromString(input: String): BagRule {
            val containerContainedSplit = input.dropLast(1).split(" contain ")
            val container = Bag.fromString(containerContainedSplit[0])
            val contained = containerContainedSplit[1]
                    .split(",")
                    .filter { !it.contains("no other bag") }
                    .map { BagCount.fromString(it) }
            return BagRule(container, contained)
        }
    }

    override fun toString(): String {
        var canContainString = canContain.joinToString(separator = ", ")
        if (canContainString.isEmpty()) {
            canContainString = "no other bags"
        }
        return "${container}s contain $canContainString."
    }
}

data class Bag(val modifier: String, val color: String) {

    companion object {
        fun fromString(input: String): Bag {
            val tokens = input.tokenize()
            return Bag(modifier = tokens[0], color = tokens[1])
        }
    }

    override fun toString(): String {
        return "$modifier $color bag"
    }
}

data class BagCount(val number: Int, val bag: Bag) {

    companion object {
        fun fromString(input: String): BagCount {
            val tokens = input.tokenize()
            return BagCount(number = Integer.parseInt(tokens[0]), Bag(modifier = tokens[1], color = tokens[2]))
        }
    }

    override fun toString(): String {
        return "$number $bag"
    }
}
