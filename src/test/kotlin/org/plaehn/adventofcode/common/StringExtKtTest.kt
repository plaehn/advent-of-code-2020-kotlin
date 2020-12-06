package org.plaehn.adventofcode.common

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class StringExtKtTest {

    @Test
    fun `Tokenize splits by whitespace`() {
        assertThat("A \r\nwee \t \ttest".tokenize()).containsExactly("A", "wee", "test")
    }

    @Test
    fun `GroupByBlankLines works for CR LF`() {
        assertThat("A\r\n\r\nwee\r\n  \r\ntest".groupByBlankLines()).containsExactly("A", "wee", "test")
    }

    @Test
    fun `Count unique chars`() {
        assertThat("aaabbccabc".countUniqueChars()).isEqualTo(3)
    }
}
