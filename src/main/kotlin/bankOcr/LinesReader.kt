package bankOcr

import java.io.File
import kotlin.streams.toList

class LinesReader(private val inputFile: String) {
    fun readLines(): Lines {
        val reader = File(inputFile).bufferedReader()
        return Lines(reader.lines().toList())
    }
}
