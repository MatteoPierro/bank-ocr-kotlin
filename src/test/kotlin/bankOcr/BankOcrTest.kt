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
    lateinit var entriesReader: EntriesReader

    @RelaxedMockK
    lateinit var entriesOcr: EntriesOcr

    private lateinit var bankOcr: BankOcr

    @BeforeEach
    internal fun setUp() {
        bankOcr = BankOcr(entriesReader, entriesOcr)
    }

    @Test
    internal fun `it reads all the entries`() {
        bankOcr.scan()

        verify { entriesReader.readAll() }
    }

    @Test
    internal fun `it scans entries`() {
        val entries = Entries()
        every { entriesReader.readAll() }.returns(entries)

        bankOcr.scan()

        verify { entriesOcr.scan(entries) }
    }
}