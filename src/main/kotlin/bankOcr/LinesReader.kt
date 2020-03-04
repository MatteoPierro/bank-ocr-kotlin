package bankOcr

import java.io.File
import kotlin.streams.toList

class LinesReader(private val inputFile: String) {
    fun readLines(): List<String> {
        val reader = File(inputFile).bufferedReader()
        return reader.lines().toList()
    }
}
