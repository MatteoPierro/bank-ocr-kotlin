package bankOcr

import assertk.assertThat
import assertk.assertions.containsExactly
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.io.File

@ExtendWith(MockKExtension::class)
class EntriesReaderTest {
    private val inputFile = "input.txt"

    @RelaxedMockK
    lateinit var linesReader: LinesReader

    @BeforeEach
    internal fun setUp() {
        File(inputFile).delete()
    }

    @Test
    internal fun `it reads an entry with one block`() {
        every { linesReader.readLines() }
                .returns(
                        Lines(listOf(
                                "   ",
                                "  |",
                                "  |",
                                "   "
                        )))

        val entries = EntriesReader(linesReader).readAll()

        assertThat(entries.value)
                .containsExactly(
                        Entry(listOf(Block.one))
                )
    }

    @Test
    internal fun `it reads an entry with two blocks`() {
        File(inputFile).bufferedWriter().use { out ->
            out.write("    _ \n")
            out.write("  | _|\n")
            out.write("  ||_ \n")
            out.write("      \n")
        }

        val entries = EntriesReader(LinesReader(inputFile)).readAll()

        assertThat(entries.value)
                .containsExactly(
                        Entry(listOf(Block.one, Block.two))
                )
    }
}