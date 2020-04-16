import model.Price
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import view.ConsoleDisplay
import view.interfaces.IDisplay
import java.io.PrintStream

class ConsoleDisplayTest() {
    private lateinit var outMock : PrintStream
    private lateinit var stdout: PrintStream

    @BeforeEach
    private fun setup() {
        stdout = System.out
        outMock = mock(PrintStream :: class.java)
        System.setOut(outMock)
    }

    @AfterEach
    private fun teardown(){
        System.setOut(stdout)
    }

    @Test
    fun displayPrice(){
        val display : IDisplay = ConsoleDisplay()
        display.displayPrice(Price(1250))

        verify(outMock).println(Price(1250))
    }
}