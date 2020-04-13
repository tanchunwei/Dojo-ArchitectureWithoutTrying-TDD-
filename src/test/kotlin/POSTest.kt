import controller.PointOfSales
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.mockito.Mockito
import org.mockito.Mockito.mock
import repository.InventoryRepo
import view.Display

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class POSTest {
    private lateinit var display : Display
    private lateinit var pos : PointOfSales
    @BeforeAll
    fun setup(){
        var spyRepo = Mockito.spy(InventoryRepo())
        Mockito.doReturn(mapOf("12345" to 7.99, "67890" to 10.99, "09876" to 20)).`when`(spyRepo).getInventoryList()
        display = Display()
        pos = PointOfSales(display, spyRepo)
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

    @Test
    fun checkoutThreeProduct(){
        pos.onBarcode("12345")
        pos.onBarcode("67890")
        pos.onBarcode("09876")
        pos.checkout()
        assertEquals("Total: $38.98", display.getText())
    }
}