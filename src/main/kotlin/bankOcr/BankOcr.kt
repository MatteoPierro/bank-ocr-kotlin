package bankOcr

class BankOcr(private val entryReader: EntryReader) {
    fun scan() {
        entryReader.readAll()
    }
}
