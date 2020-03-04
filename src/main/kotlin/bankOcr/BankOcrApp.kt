package bankOcr

fun main(args: Array<String>) {
    val inputFile = args.component1()
    val linesReader = LinesReader(inputFile)
    val entriesReader = EntriesReader(linesReader)
    val entriesOcr = EntriesOcr()
    BankOcr(entriesReader, entriesOcr).scan()
}