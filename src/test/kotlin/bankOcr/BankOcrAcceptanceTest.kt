package bankOcr

import assertk.assertThat
import assertk.assertions.containsExactly
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class BankOcrAcceptanceTest {

    private val inputFile = "input.txt"
    private val outputFile = "output.txt"

    @BeforeEach
    internal fun setUp() {
        File(inputFile).delete()
        File(outputFile).delete()
    }

    @Test
    internal fun `it parses a file`() {
        File(inputFile).bufferedWriter().use { out ->
            out.write("    _  _     _  _  _  _  _ ")
            out.write("  | _| _||_||_ |_   ||_||_|")
            out.write("  ||_  _|  | _||_|  ||_| _|")
            out.write("                           ")
        }

        startOcr(inputFile, outputFile)

        val outputLines = File(outputFile).bufferedReader().readLines()
        assertThat(outputLines).containsExactly("123456789")
    }

    private fun startOcr(inputFile: String, outputFile: String) {
        main(arrayOf(this.inputFile, this.outputFile))
    }
}
