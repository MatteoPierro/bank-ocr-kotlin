package bankOcr

import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class BankOcrTest {
    @RelaxedMockK
    lateinit var entryReader: EntryReader

    @Test
    internal fun `it reads all the entries`() {
        BankOcr(entryReader).scan()

        verify { entryReader.readAll() }
    }
}