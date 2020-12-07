package org.plaehn.adventofcode.day7

class BagRuleSolver(private val rules: List<BagRule>) {

    fun countContainedBags(bag: Bag): Int = countContainedBagsRecursively(bag) - 1

    private fun countContainedBagsRecursively(bag: Bag): Int {
        val ruleForBag = ruleForBag(bag) ?: return 0
        var count = 1
        for (bagCount in ruleForBag.canContain) {
            count += bagCount.number * countContainedBagsRecursively(bagCount.bag)
        }
        return count
    }

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
