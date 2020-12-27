package org.plaehn.adventofcode.day25

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ComboBreakerTest {

    @Test
    fun `Compute encryption key for test input`() {
        val comboBreaker = ComboBreaker(doorsPublicKey = 17807724, cardsPublicKey = 5764801)

        assertThat(comboBreaker.findEncryptionKey()).isEqualTo(14897079)
    }

    @Test
    fun `Compute encryption key for real input`() {
        val comboBreaker = ComboBreaker(doorsPublicKey = 19241437, cardsPublicKey = 17346587)

        assertThat(comboBreaker.findEncryptionKey()).isEqualTo(12181021)
    }
}