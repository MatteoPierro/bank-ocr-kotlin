package bankOcr

fun main(args: Array<String>) {
    val entriesReader = EntriesReader()
    val entriesOcr = EntriesOcr()
    BankOcr(entriesReader, entriesOcr).scan()
}