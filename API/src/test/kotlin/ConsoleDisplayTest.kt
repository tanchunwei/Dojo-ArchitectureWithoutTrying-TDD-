import com.pos.model.Price
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import com.pos.view.ConsoleDisplay
import com.pos.view.interfaces.IDisplay
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
        val display : IDisplay =
            ConsoleDisplay()
        display.displayPrice(Price(1250))

        verify(outMock).println(Price(1250))
    }

    @Test
    fun displayProductNotFound(){
        val display : IDisplay =
            ConsoleDisplay()
        display.displayProductNotFound("barcode")

        // Mockito in kotlin String verification for println special
        // As the println is overloaded function, this resulted:
        // kotlin test case calling println(String)
        // and kotlin production code calling println(Any)
        // hence verification always fail even string is match
        // Change expected result to upcast to Any instead of String
        val expectedResult : Any = "Product not found [barcode]"
        verify(outMock).println(expectedResult)
    }

    @Test
    fun displayEmptyBarcode(){
        val display : IDisplay =
            ConsoleDisplay()
        display.displayEmptyBarcode()

        val expectedResult : Any = "Barcode is empty. Please try to rescan"
        verify(outMock).println(expectedResult)
    }
}