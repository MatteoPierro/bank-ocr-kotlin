package bankOcr

import java.io.File

class EntriesReader(private val inputFile: String = "") {
    fun readAll(): Entries {
        val reader = File(inputFile).bufferedReader()
        val firstLine = reader.readLine()
        val secondLine = reader.readLine()
        val thirdLine = reader.readLine()
        val forthLine = reader.readLine()
        val firstBlock = Block(
                firstLine.substring(0, 3) + "\n" +
                        secondLine.substring(0, 3) + "\n"+
                        thirdLine.substring(0, 3) + "\n" +
                        forthLine.substring(0, 3)
        )
        if (firstLine.length == 3) {
            return Entries(listOf(Entry(listOf(firstBlock))))
        }

        val secondBlock = Block(
                firstLine.substring(3, 6) + "\n" +
                        secondLine.substring(3, 6) + "\n" +
                        thirdLine.substring(3, 6) + "\n" +
                        forthLine.substring(3, 6)
        )
        return Entries(listOf(Entry(listOf(firstBlock, secondBlock))))
    }
}
