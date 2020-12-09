import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.plaehn.adventofcode.day9.XmasDecoder

class XmasDecoderTest {

    @Test
    fun `Find first wrongly decoded number in small input`() {
        val input = readInput("small_input.txt")

        val wronglyDecodedNumber = XmasDecoder(input, windowSize = 5).findFirstWronglyEncodedNumber()

        assertThat(wronglyDecodedNumber).isEqualTo(127)
    }

    @Test
    fun `Find first wrongly decoded number in large input`() {
        val input = readInput("input.txt")

        val wronglyDecodedNumber = XmasDecoder(input, windowSize = 25).findFirstWronglyEncodedNumber()

        assertThat(wronglyDecodedNumber).isEqualTo(776203571)
    }

    @Test
    fun `Find encryption weakness in small input`() {
        val input = readInput("small_input.txt")

        val encryptionWeakness = XmasDecoder(input, windowSize = 5).findEncryptionWeakness()

        assertThat(encryptionWeakness).isEqualTo(62)
    }

    @Test
    fun `Find encryption weakness in large input`() {
        val input = readInput("input.txt")

        val encryptionWeakness = XmasDecoder(input, windowSize = 25).findEncryptionWeakness()

        assertThat(encryptionWeakness).isEqualTo(104800569)
    }

    private fun readInput(resourceName: String): List<Long> =
            XmasDecoder::class.java
                    .getResource(resourceName)
                    .readText()
                    .lines()
                    .filter { it.isNotBlank() }
                    .map { it.toLong() }
}
