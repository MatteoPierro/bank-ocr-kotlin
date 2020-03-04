package bankOcr

class EntriesReader(private val linesReader: LinesReader) {

    fun readAll(): Entries {
        val lines = linesReader.readLines()

        val entries = entriesFrom(lines)
        return Entries(entries)
    }

    private fun entriesFrom(lines: Lines): List<Entry> {
        val rawEntries = lines.value.chunked(ENTRY_SIZE)
        return rawEntries.map { rawEntry ->
            rawEntry.map { line -> line.chunked(BLOCK_SIZE) }
        }.map { toEntry(it) }
    }

    private fun toEntry(rawEntry: RawEntry): Entry {
        val blocks = rawEntry.blocksIndexes()
                .map { blockIndex ->
                    rawEntry.joinToString(separator = "\n") { it[blockIndex] }
                }
                .map { Block(it) }
        return Entry(blocks)
    }

    companion object {

        private const val ENTRY_SIZE = 4
        private const val BLOCK_SIZE = 3
    }
}

private typealias RawEntry = List<List<String>>

private fun RawEntry.blocksIndexes() = this.component1().indices