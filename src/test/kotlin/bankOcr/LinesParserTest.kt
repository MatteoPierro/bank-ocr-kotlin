package bankOcr

import assertk.assertThat
import assertk.assertions.containsExactly
import org.junit.jupiter.api.Test

class LinesParserTest {

    @Test
    internal fun `it parses an entry`() {
        val lines = Lines(listOf(
                "    _  _  _  _  _  _  _  _ ",
                "  | _| _| _| _| _| _| _| _|",
                "  ||_ |_ |_ |_ |_ |_ |_ |_ ",
                "                           "
        ))

        val entries = LinesParser().toEntries(lines)

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

    @Test
    internal fun `it parses multiple entries`() {
        val lines = Lines(listOf(
                "    _  _  _  _  _  _  _  _ ",
                "  | _| _| _| _| _| _| _| _|",
                "  ||_ |_ |_ |_ |_ |_ |_ |_ ",
                "                           ",
                "    _  _  _  _  _  _  _  _ ",
                "  | _| _| _| _| _| _| _| _|",
                "  ||_ |_ |_ |_ |_ |_ |_ |_ ",
                "                           "
        ))

        val entries = LinesParser().toEntries(lines)

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
                        )),
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
                        ))
                )
    }
}