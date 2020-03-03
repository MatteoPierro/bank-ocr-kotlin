package bankOcr

class BankOcr(private val entriesReader: EntriesReader, private val entriesOcr: EntriesOcr) {
    fun scan() {
        val entries = entriesReader.readAll()
        entriesOcr.scan(entries)
    }
}
