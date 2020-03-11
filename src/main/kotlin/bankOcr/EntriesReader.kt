package bankOcr

class EntriesReader(private val linesReader: LinesReader, private val linesParser: LinesParser) {

    fun readAll(): Entries = linesReader.readLines().let { linesParser.toEntries(it) }
}