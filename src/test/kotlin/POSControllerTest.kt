import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class POSControllerTest() {
    private lateinit var displayMock: Display
    private lateinit var inventoryRepoMock: InventoryRepo

    @BeforeEach
    fun setup(){
        displayMock = Mockito.mock(Display::class.java)
        inventoryRepoMock = Mockito.mock(InventoryRepo::class.java)
        //inventoryRepoMock = Mockito.spy(InventoryRepo())
    }

    @Test
    fun productFound(){
        val price = Price()
        //val inventoryRepoMock = Mockito.spy(InventoryRepo())
        Mockito.doReturn(price).`when`(inventoryRepoMock).getInventory("::product found barcode::")

        POSController(inventoryRepoMock, displayMock).onBarcode("::product found barcode::")

        Mockito.verify(displayMock).displayPrice(price)
        Mockito.verifyNoMoreInteractions(displayMock)
    }

    @Test
    fun productNotFound(){
        val displayMock = Mockito.mock(Display::class.java)
        var inventoryRepoMock = Mockito.mock(InventoryRepo::class.java)
        Mockito.doReturn(null).`when`(inventoryRepoMock).getInventory("::product not found barcode::")

        POSController(inventoryRepoMock, displayMock).onBarcode("::product not found barcode::")

        Mockito.verify(displayMock).displayProductNotFound("::product not found barcode::")
        Mockito.verifyNoMoreInteractions(displayMock)
    }

    @Test
    fun emptyBarcode(){
        val displayMock = Mockito.mock(Display::class.java)
        var inventoryRepoMock = Mockito.mock(InventoryRepo::class.java)

        POSController(inventoryRepoMock, displayMock).onBarcode("")

        Mockito.verify(displayMock).displayEmptyBarcode()
        Mockito.verifyNoMoreInteractions(displayMock)
    }
}

class InventoryRepo {
    fun getInventory(barcode: String): Price {
        TODO("Not yet implemented")
    }

}

class POSController(private val inventoryRepo: InventoryRepo, private val display: Display) {

    fun onBarcode(barcode: String) {
        if(barcode == ""){
            display.displayEmptyBarcode()
            return
        }

        val price = inventoryRepo.getInventory(barcode)

        if(price == null)
            display.displayProductNotFound(barcode)
        else
            display.displayPrice(price)
    }

}

class Display {
    fun displayPrice(price: Price) {
        TODO("Not yet implemented")
    }

    fun displayProductNotFound(barcode: String) {
        TODO("Not yet implemented")
    }

    fun displayEmptyBarcode() {
        TODO("Not yet implemented")
    }

}

class Price {

}
