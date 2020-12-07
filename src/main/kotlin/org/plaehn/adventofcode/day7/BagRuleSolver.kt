package org.plaehn.adventofcode.day7

class BagRuleSolver(private val rules: List<BagRule>) {

    fun canContainTransitively(bag: Bag): Set<Bag> = HashSet(
            rules
                    .filter { rule -> canContainTransitively(rule, bag) }
                    .map { rule -> rule.container }
                    .filter { it != bag }
    )

    private fun canContainTransitively(rule: BagRule?, bag: Bag): Boolean =
            rule != null &&
                    (rule.container == bag
                            || rule.canContain.any { canContainTransitively(ruleForBag(it.bag), bag) })

    private fun ruleForBag(bag: Bag): BagRule? = rules.find { it.container == bag }
}
