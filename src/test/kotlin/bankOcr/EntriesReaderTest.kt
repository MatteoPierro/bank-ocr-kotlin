package bankOcr

import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class EntriesReaderTest {

    @RelaxedMockK
    lateinit var linesReader: LinesReader

    @RelaxedMockK
    lateinit var linesParser: LinesParser

    private lateinit var entriesReader: EntriesReader

    @BeforeEach
    internal fun setUp() {
        entriesReader = EntriesReader(linesReader, linesParser)
    }

    @Test
    internal fun `it reads lines`() {
        entriesReader.readAll()

        verify { linesReader.readLines() }
    }

    @Test
    internal fun `it tells to parse lines`() {
        val lines = Lines(emptyList())
        every { linesReader.readLines() }.returns(lines)

        entriesReader.readAll()

        verify { linesParser.toEntries(lines) }
    }
}