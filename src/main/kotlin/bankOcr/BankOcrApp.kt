package bankOcr

fun main(args: Array<String>) {
    val inputFile = args.component1()
    val linesReader = LinesReader(inputFile)
    val linesParser = LinesParser()
    val entriesReader = EntriesReader(linesReader, linesParser)
    val entriesOcr = EntriesOcr()
    BankOcr(entriesReader, entriesOcr).scan()
}