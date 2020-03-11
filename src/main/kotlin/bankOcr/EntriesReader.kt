package bankOcr

class EntriesReader(private val linesReader: LinesReader, private val linesParser: LinesParser = LinesParser()) {

    fun readAll(): Entries {
        val lines = linesReader.readLines()

        return linesParser.toEntries(lines)
    }
}