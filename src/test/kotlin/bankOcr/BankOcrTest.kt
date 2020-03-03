package bankOcr

import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class BankOcrTest {
    @RelaxedMockK
    lateinit var entryReader: EntryReader

    @RelaxedMockK
    lateinit var entriesOcr: EntryOcr

    lateinit var bankOcr: BankOcr

    @BeforeEach
    internal fun setUp() {
        bankOcr = BankOcr(entryReader, entriesOcr)
    }

    @Test
    internal fun `it reads all the entries`() {
        bankOcr.scan()

        verify { entryReader.readAll() }
    }

    @Test
    internal fun `it scans entries`() {
        val entries = Entries()
        every { entryReader.readAll() }.returns(entries)

        bankOcr.scan()

        verify { entriesOcr.scan(entries) }
    }
}