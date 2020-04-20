import com.pos.service.POSService
import model.Price
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import repository.interfaces.IInventory
import view.interfaces.IDisplay

class POSServiceTest() {
    private lateinit var displayMock: IDisplay
    private lateinit var inventorMock: IInventory

    @BeforeEach
    fun setup(){
        displayMock = Mockito.mock(IDisplay::class.java)
        inventorMock = Mockito.mock(IInventory::class.java)
        //inventoryRepoMock = Mockito.spy(repository.InventoryRepo())
    }

    @Test
    fun productFound(){
        val price = Price(1250)
        Mockito.doReturn(price).`when`(inventorMock).getInventory("::product found barcode::")

        POSService(inventorMock, displayMock).onBarcode("::product found barcode::")

        Mockito.verify(displayMock).displayPrice(price)
        Mockito.verifyNoMoreInteractions(displayMock)
    }

    @Test
    fun productNotFound(){
        Mockito.doReturn(null).`when`(inventorMock).getInventory("::product not found barcode::")

        POSService(inventorMock, displayMock).onBarcode("::product not found barcode::")

        Mockito.verify(displayMock).displayProductNotFound("::product not found barcode::")
        Mockito.verifyNoMoreInteractions(displayMock)
    }

    @Test
    fun emptyBarcode(){
        POSService(inventorMock, displayMock).onBarcode("")

        Mockito.verify(displayMock).displayEmptyBarcode()
        Mockito.verifyNoMoreInteractions(displayMock)
    }
}

