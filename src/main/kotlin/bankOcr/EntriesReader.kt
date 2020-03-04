package bankOcr

private typealias RawEntry = List<List<String>>

class EntriesReader(private val linesReader: LinesReader) {

    fun readAll(): Entries {
        val lines = linesReader.readLines()

        val entries = entriesFrom(lines)

        return Entries(entries)
    }

    private fun entriesFrom(lines: List<String>): List<Entry> {
        val rawEntries = lines.chunked(ENTRY_SIZE)
        val entries = rawEntries.map { rawEntry ->
            rawEntry.map { line -> line.chunked(BLOCK_SIZE) }
        }.map { toEntry(it) }
        return entries
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
        const val ENTRY_SIZE = 4
        const val BLOCK_SIZE = 3
    }
}

private fun RawEntry.blocksIndexes() = this.component1().indices
