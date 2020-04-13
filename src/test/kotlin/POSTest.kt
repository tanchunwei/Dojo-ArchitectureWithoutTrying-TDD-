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

    @ParameterizedTest
    @CsvSource(value = [
        "556677,This product does not exist 556677",
        "889900,This product does not exist 889900"
    ])
    fun productNotFound(barcode : String, expected : String){
        val display = Display()
        val pos = PointOfSales(display)

        pos.onBarcode(barcode)
        assertEquals(expected, display.getText())
    }

    @Test
    fun emptyBarcode(){
        val display = Display()
        val pos = PointOfSales(display)

        pos.onBarcode("")
        assertEquals("Barcode cannot be empty", display.getText())
    }
}