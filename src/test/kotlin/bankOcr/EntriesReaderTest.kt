package bankOcr

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class EntriesReaderTest {
    private val inputFile = "input.txt"

    @BeforeEach
    internal fun setUp() {
        File(inputFile).delete()
    }

    @Test
    internal fun `it reads entries`() {
        File(inputFile).bufferedWriter().use { out ->
            out.write("    _  _     _  _  _  _  _ ")
            out.write("  | _| _||_||_ |_   ||_||_|")
            out.write("  ||_  _|  | _||_|  ||_| _|")
            out.write("                           ")
        }

        val entries = EntriesReader().readAll()

        assertThat(entries).isEqualTo(Entries(listOf(Entry())))
    }
}