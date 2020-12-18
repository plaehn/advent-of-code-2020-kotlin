package org.plaehn.adventofcode.common

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StringExtKtTest {

    @Test
    fun `Tokenize splits by whitespace`() {
        assertThat("A \r\nwee \t \ttest".tokenize()).containsExactly("A", "wee", "test")
    }

    @Test
    fun `Count tokens`() {
        assertThat("A \r\nwee \t \ttest".countTokens()).isEqualTo(3)
    }

    @Test
    fun `GroupByBlankLines works for CR LF`() {
        assertThat("A\r\n\r\nwee\r\n  \r\ntest".groupByBlankLines()).containsExactly("A", "wee", "test")
    }

    @Test
    fun `Count chars appearing n times`() {
        assertThat("daaabbcecabcf".countCharsAppearing(ntimes = 3)).isEqualTo(2)
        assertThat("daaabbcecabcf".countCharsAppearing(ntimes = 1)).isEqualTo(3)
        assertThat("daaabbcecabcf".countCharsAppearing(ntimes = 4)).isEqualTo(1)
    }
}
