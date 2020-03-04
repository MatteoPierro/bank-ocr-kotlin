package bankOcr

import java.io.File
import kotlin.streams.toList

private typealias RawEntry = List<List<String>>

class EntriesReader(private val inputFile: String = "") {

    fun readAll(): Entries {
        val reader = File(inputFile).bufferedReader()

        val rawEntries = reader.lines().toList().chunked(ENTRY_SIZE)
        val entries = rawEntries.map {
            it.map { line -> line.chunked(BLOCK_SIZE) }
        }.map { toEntry(it) }

        return Entries(entries)
    }

    private fun toEntry(rawEntry: RawEntry): Entry {
        val blocksIndexes = rawEntry.component1().indices
        val blocks = blocksIndexes
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
