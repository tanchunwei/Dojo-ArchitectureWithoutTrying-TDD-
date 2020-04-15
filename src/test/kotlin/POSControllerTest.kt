import controller.POSController
import view.Display
import model.Price
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import repository.InventoryRepo

class POSControllerTest() {
    private lateinit var displayMock: Display
    private lateinit var inventoryRepoMock: InventoryRepo

    @BeforeEach
    fun setup(){
        displayMock = Mockito.mock(Display::class.java)
        inventoryRepoMock = Mockito.mock(InventoryRepo::class.java)
        //inventoryRepoMock = Mockito.spy(repository.InventoryRepo())
    }

    @Test
    fun productFound(){
        val price = Price(1250)
        Mockito.doReturn(price).`when`(inventoryRepoMock).getInventory("::product found barcode::")

        POSController(inventoryRepoMock, displayMock).onBarcode("::product found barcode::")

        Mockito.verify(displayMock).displayPrice(price)
        Mockito.verifyNoMoreInteractions(displayMock)
    }

    @Test
    fun productNotFound(){
        Mockito.doReturn(null).`when`(inventoryRepoMock).getInventory("::product not found barcode::")

        POSController(inventoryRepoMock, displayMock).onBarcode("::product not found barcode::")

        Mockito.verify(displayMock).displayProductNotFound("::product not found barcode::")
        Mockito.verifyNoMoreInteractions(displayMock)
    }

    @Test
    fun emptyBarcode(){
        POSController(inventoryRepoMock, displayMock).onBarcode("")

        Mockito.verify(displayMock).displayEmptyBarcode()
        Mockito.verifyNoMoreInteractions(displayMock)
    }
}

