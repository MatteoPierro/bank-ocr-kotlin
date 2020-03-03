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
    internal fun `it reads an entry with one block`() {
        File(inputFile).bufferedWriter().use { out ->
            out.write("   \n")
            out.write("  |\n")
            out.write("  |\n")
            out.write("   \n")
        }

        val entries = EntriesReader(inputFile).readAll()

        assertThat(entries).isEqualTo(Entries(listOf(
                Entry(listOf(
                        Block("   \n" +
                                "  |\n" +
                                "  |\n" +
                                "   ")
                )
                ))))

    }

    @Test
    internal fun `it reads an entry with two blocks`() {
        File(inputFile).bufferedWriter().use { out ->
            out.write("    _ \n")
            out.write("  | _|\n")
            out.write("  ||_ \n")
            out.write("      \n")
        }

        val entries = EntriesReader(inputFile).readAll()

        assertThat(entries).isEqualTo(Entries(listOf(
                Entry(listOf(
                        Block("   \n" +
                                "  |\n" +
                                "  |\n" +
                                "   "),
                        Block(" _ \n" +
                                " _|\n" +
                                "|_ \n" +
                                "   ")
                )))))

    }
}