import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class POSTest {

    @ParameterizedTest
    @CsvSource(value = [
        "12345,$7.99",
        "67890,$10.99"
    ])
    fun productFound(barcode : String, expected : String){
        val display = Display()
        val pos = PointOfSales(display)

        pos.onBarcode(barcode)
        assertEquals(expected, display.getText())
    }

    @Test
    fun productNotFound(){
        val display = Display()
        val pos = PointOfSales(display)

        pos.onBarcode("556677")
        assertEquals("This product does not exist 556677", display.getText())
    }
}