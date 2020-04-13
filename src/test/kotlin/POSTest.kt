import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class POSTest {
    private lateinit var display : Display
    private lateinit var pos : PointOfSales
    @BeforeAll
    fun setup(){
        display = Display()
        pos = PointOfSales(display)
    }

    @ParameterizedTest
    @CsvSource(value = [
        "12345,$7.99",
        "67890,$10.99"
    ])
    fun productFound(barcode : String, expected : String){
        pos.onBarcode(barcode)
        assertEquals(expected, display.getText())
    }

    @ParameterizedTest
    @CsvSource(value = [
        "556677,This product does not exist 556677",
        "889900,This product does not exist 889900"
    ])
    fun productNotFound(barcode : String, expected : String){
        pos.onBarcode(barcode)
        assertEquals(expected, display.getText())
    }

    @Test
    fun emptyBarcode(){
        pos.onBarcode("")
        assertEquals("Barcode cannot be empty", display.getText())
    }
}