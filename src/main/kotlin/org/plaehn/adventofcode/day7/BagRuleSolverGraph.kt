package org.plaehn.adventofcode.day7

import com.google.common.graph.ImmutableValueGraph
import com.google.common.graph.ValueGraphBuilder

class BagRuleSolverGraph {

    private val valueGraph: ImmutableValueGraph<Bag, Int>

    constructor(rules: List<BagRule>) {
        valueGraph = ValueGraphBuilder
                .directed()
                .expectedNodeCount(rules.count())
                .immutable<Bag, Int>()
                .apply {
                    for (rule in rules) {
                        for (bagCount in rule.canContain) {
                            this.putEdgeValue(rule.container, bagCount.bag, bagCount.number)
                        }
                    }
                }
                .build()
    }

    fun countContainedBags(node: Bag): Int = countContainedBagsIncludingStart(node) - 1

    private fun countContainedBagsIncludingStart(bag: Bag): Int =
            valueGraph
                    .successors(bag)
                    .fold(1) { count, adjacentBag ->
                        count + valueGraph.edgeValue(bag, adjacentBag).get() * countContainedBagsIncludingStart(adjacentBag)
                    }

    fun canContainTransitively(bag: Bag): Set<Bag> = valueGraph
            .predecessors(bag)
            .fold(HashSet()) { containingBags, adjacentBag ->
                containingBags.apply {
                    containingBags.add(adjacentBag)
                    containingBags.addAll(canContainTransitively(adjacentBag))
                }
            }
}
