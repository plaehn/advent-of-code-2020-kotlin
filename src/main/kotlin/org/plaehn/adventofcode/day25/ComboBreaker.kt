package org.plaehn.adventofcode.day25

class ComboBreaker(private val cardsPublicKey: Long, private val doorsPublicKey: Long) {

    private val subjectNumber = 7

    fun findEncryptionKey(): Long {
        val cardsLoopSize = computeLoopSize(cardsPublicKey)
        val doorsLoopSize = computeLoopSize(doorsPublicKey)

        val encryptionKeyFromDoorsPublicKey = computeEncryptionKey(doorsPublicKey, cardsLoopSize)
        val encryptionKeyFromCardsPublicKey = computeEncryptionKey(cardsPublicKey, doorsLoopSize)
        assert(encryptionKeyFromDoorsPublicKey == encryptionKeyFromCardsPublicKey)

        return encryptionKeyFromDoorsPublicKey
    }

    private fun computeLoopSize(publicKey: Long): Int {
        var key = 1L
        var loopSize = 0
        do {
            ++loopSize
            key *= subjectNumber
            key %= 20201227
        } while (key != publicKey)
        return loopSize
    }

    private fun computeEncryptionKey(subjectNumber: Long, loopSize: Int): Long =
        (1..loopSize).fold(1L) { key, _ -> (key * subjectNumber) % 20201227 }
}
