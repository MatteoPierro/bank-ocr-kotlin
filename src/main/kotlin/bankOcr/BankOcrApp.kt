package bankOcr

fun main(args: Array<String>) {
    val entriesReader = EntriesReader(LinesReader(""))
    val entriesOcr = EntriesOcr()
    BankOcr(entriesReader, entriesOcr).scan()
}