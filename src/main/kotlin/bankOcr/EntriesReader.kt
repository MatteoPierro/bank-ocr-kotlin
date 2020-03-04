package bankOcr

class EntriesReader(private val linesReader: LinesReader) {

    fun readAll(): Entries {
        val lines = linesReader.readLines()

        return lines.toEntries(ENTRY_SIZE, BLOCK_SIZE)
    }

    companion object {
        private const val ENTRY_SIZE = 4
        private const val BLOCK_SIZE = 3
    }
}

private typealias RawEntry = List<List<String>>

private fun Lines.toEntries(entrySize: Int, blockSize: Int) =
        toRawEntries(entrySize, blockSize)
                .map { it.toEntry() }
                .let { Entries(it) }

private fun Lines.toRawEntries(entrySize: Int, blockSize: Int) =
        value.chunked(entrySize)
                .map { rawEntry ->
                    rawEntry.map { line -> line.chunked(blockSize) }
                }

private fun RawEntry.toEntry() =
        blocksIndexes()
                .map { blockIndex ->
                    this.joinToString(separator = "\n") { it[blockIndex] }
                }
                .map { Block(it) }
                .let { Entry(it) }

private fun RawEntry.blocksIndexes() = this.component1().indices
