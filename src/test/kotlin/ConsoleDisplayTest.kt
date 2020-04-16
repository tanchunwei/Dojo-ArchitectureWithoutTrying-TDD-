import model.Price
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import view.ConsoleDisplay
import view.interfaces.IDisplay
import java.io.PrintStream

class ConsoleDisplayTest {
    @Test
    fun displayPrice(){
        val out : PrintStream = mock(PrintStream :: class.java)
        System.setOut(out)

        val display : IDisplay = ConsoleDisplay()
        display.displayPrice(Price(1250))

        verify(out).println(Price(1250))
    }
}