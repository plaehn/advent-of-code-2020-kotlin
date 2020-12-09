import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.plaehn.adventofcode.day9.XmasDecoder

class XmasDecoderTest {

    @Test
    fun `Find first wrongly decoded number in small input`() {
        val input = readInput("small_input.txt")

        val wronglyDecodedNumber = XmasDecoder(input = input, windowSize = 5).findFirstWronglyEncodedNumber()

        assertThat(wronglyDecodedNumber).isEqualTo(127)
    }

    @Test
    fun `Find first wrongly decoded number in large input`() {
        val input = readInput("input.txt")

        val wronglyDecodedNumber = XmasDecoder(input = input, windowSize = 25).findFirstWronglyEncodedNumber()

        assertThat(wronglyDecodedNumber).isEqualTo(776203571)
    }

    private fun readInput(resourceName: String): List<Long> =
            XmasDecoder::class.java
                    .getResource(resourceName)
                    .readText()
                    .lines()
                    .filter { it.isNotBlank() }
                    .map { it.toLong() }
}
