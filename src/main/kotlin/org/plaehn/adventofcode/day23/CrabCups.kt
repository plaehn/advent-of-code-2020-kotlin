package org.plaehn.adventofcode.day23

class CrabCups(input: String, numberOfCups: Int) {

    private val nodes: Array<Node>

    init {
        this.nodes = createLinkedList(input, numberOfCups)
    }

    private fun createLinkedList(input: String, numberOfCups: Int): Array<Node> {
        val nodes = Array(numberOfCups) { index ->
            val label = if (index < input.count()) input[index].toDigit() else index + 1
            Node(label, index + 1)
        }
        makeCircular(nodes, numberOfCups)
        return nodes
    }

    private fun makeCircular(nodes: Array<Node>, numberOfCups: Int) {
        nodes[numberOfCups - 1].nextIdx = 0
    }

    fun play(moves: Int): List<Int> {
        var currentIndex = 0
        repeat(moves) {
            val (firstRemovedCupIndex, lastRemovedCupIndex) = removeThreeCupsAfter(currentIndex)

            val destinationLabel = determineDestinationLabel(currentIndex, firstRemovedCupIndex)
            val destinationIndex = getIndexForCup(destinationLabel)

            addThreeCupsAfter(destinationIndex, firstRemovedCupIndex, lastRemovedCupIndex)

            currentIndex = nodes[currentIndex].nextIdx
        }
        return firstEightCupsAfterCupOne()
    }

    private fun removeThreeCupsAfter(currentIndex: Int): Pair<Int, Int> {
        val cutIdx = nodes[currentIndex].nextIdx
        val cut2Idx = nodes[cutIdx].nextIdx
        val cut3Idx = nodes[cut2Idx].nextIdx
        val cutAfterIdx = nodes[cut3Idx].nextIdx

        nodes[currentIndex].nextIdx = cutAfterIdx

        return Pair(cutIdx, cut3Idx)
    }

    private fun determineDestinationLabel(currentIndex: Int, firstRemovedCupIndex: Int): Int {
        var destinationLabel = nodes[currentIndex].label
        do {
            --destinationLabel
            if (destinationLabel < 1) {
                destinationLabel = numberOfCups()
            }
        } while (labelContainedInNextThreeNodes(destinationLabel, firstRemovedCupIndex))
        return destinationLabel
    }

    private fun numberOfCups(): Int = nodes.count()

    private fun labelContainedInNextThreeNodes(label: Int, index: Int): Boolean {
        var nodeIndex = index
        repeat(3) {
            if (nodes[nodeIndex].label == label) {
                return true
            }
            nodeIndex = nodes[nodeIndex].nextIdx
        }
        return false
    }

    private fun getIndexForCup(destinationLabel: Int): Int {
        if (destinationLabel < 10) {
            (0 until 9).forEach { index ->
                if (nodes[index].label == destinationLabel) {
                    return index
                }
            }
            throw IllegalStateException()
        }
        return destinationLabel - 1
    }

    private fun addThreeCupsAfter(
        destinationIndex: Int,
        firstRemovedCupIndex: Int,
        lastRemovedCupIndex: Int
    ) {
        val destinationAfterIndex = nodes[destinationIndex].nextIdx
        nodes[destinationIndex].nextIdx = firstRemovedCupIndex
        nodes[lastRemovedCupIndex].nextIdx = destinationAfterIndex
    }

    private fun firstEightCupsAfterCupOne(): List<Int> {
        var index = getIndexForCup(1)
        return (0 until 8).map {
            index = nodes[index].nextIdx
            nodes[index].label
        }
    }
}

private fun Char.toDigit(): Int = this.toInt() - 48

class Node(val label: Int, var nextIdx: Int) {
    override fun toString(): String {
        return "Node(label=$label, nextIdx=$nextIdx)"
    }
}