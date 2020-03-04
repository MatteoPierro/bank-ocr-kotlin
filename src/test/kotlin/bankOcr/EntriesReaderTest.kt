package bankOcr

import assertk.assertThat
import assertk.assertions.containsExactly
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class EntriesReaderTest {
    @RelaxedMockK
    lateinit var linesReader: LinesReader

    lateinit var entriesReader: EntriesReader

    @BeforeEach
    internal fun setUp() {
        entriesReader = EntriesReader(linesReader)
    }

    @Test
    internal fun `it reads an entry`() {
        every { linesReader.readLines() }
                .returns(
                        Lines(listOf(
                                "    _  _  _  _  _  _  _  _ ",
                                "  | _| _| _| _| _| _| _| _|",
                                "  ||_ |_ |_ |_ |_ |_ |_ |_ ",
                                "                           "
                        )))

        val entries = entriesReader.readAll()

        assertThat(entries.value)
                .containsExactly(
                        Entry(listOf(
                                Block.one,
                                Block.two,
                                Block.two,
                                Block.two,
                                Block.two,
                                Block.two,
                                Block.two,
                                Block.two,
                                Block.two
                        )))
    }
}