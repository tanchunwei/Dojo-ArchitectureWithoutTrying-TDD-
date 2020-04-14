import controller.PointOfSales
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.mockito.Mockito
import repository.InventoryRepo
import view.Display

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class POSTest {
    private lateinit var display : Display
    private lateinit var pos : PointOfSales
    @BeforeEach
    fun setup(){
        var spyRepo = Mockito.spy(InventoryRepo())
        Mockito.doReturn(mapOf("12345" to 799, "67890" to 1099, "09876" to 2000, "54321" to 1000)).`when`(spyRepo).getInventoryList()
        display = Display()
        pos = PointOfSales(display, spyRepo)
    }

    @ParameterizedTest
    @CsvSource(value = [
        "12345,$7.99",
        "67890,$10.99"
    ])
    fun scanOneItem_ProductFound(barcode : String, expected : String){
        pos.onBarcode(barcode)
        assertEquals(expected, display.getText())
    }

    @ParameterizedTest
    @CsvSource(value = [
        "556677,This product does not exist 556677",
        "889900,This product does not exist 889900"
    ])
    fun scanOneItem_ProductNotFound(barcode : String, expected : String){
        pos.onBarcode(barcode)
        assertEquals(expected, display.getText())
    }

    @Test
    fun scanOneItem_EmptyBarcode(){
        pos.onBarcode("")
        assertEquals("Barcode cannot be empty", display.getText())
    }

    @ParameterizedTest
    @CsvSource(value = [
        "12345, 67890, 09876, Total: \$38.98",
        "12345, 67890, 54321, Total: \$28.98"
    ])
    fun sellMultipleItem_CheckoutThreeProduct(p1 : String, p2 : String, p3: String, expected : String){
        pos.onBarcode(p1)
        pos.onBarcode(p2)
        pos.onBarcode(p3)
        pos.checkout()
        assertEquals(expected, display.getText())
    }

    @ParameterizedTest
    @CsvSource(value = [
        "12345, 778899, 09876, Total: \$27.99"
    ])
    fun sellMultipleItem_CheckoutThreeProductWithOneProductNotFound(p1 : String, p2 : String, p3: String, expected : String){
        pos.onBarcode(p1)
        pos.onBarcode(p2)
        pos.onBarcode(p3)
        pos.checkout()
        assertEquals(expected, display.getText())
    }

    @Test
    fun sellMultipleItem_CheckoutOneProduct(){
        pos.onBarcode("12345")
        pos.checkout()
        assertEquals("Total: $7.99", display.getText())
    }

    @Test
    fun sellMultipleItem_CheckoutOneProductNotFound(){
        pos.onBarcode("778899")
        pos.checkout()
        assertEquals("No sale in progress. Try scanning a product.", display.getText())
    }

    @Test
    fun sellMultipleItem_CheckoutZeroProduct(){
        pos.checkout()
        assertEquals("No sale in progress. Try scanning a product.", display.getText())
    }
}