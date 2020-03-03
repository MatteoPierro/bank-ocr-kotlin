package bankOcr

class BankOcr(private val entryReader: EntryReader, private val entriesOcr: EntryOcr) {
    fun scan() {
        val entries = entryReader.readAll()
        entriesOcr.scan(entries)
    }
}
