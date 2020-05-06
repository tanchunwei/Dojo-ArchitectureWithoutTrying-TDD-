import com.pos.service.POSService
import com.pos.model.Price
import com.pos.model.Product
import com.pos.model.view.WebDisplayViewModel
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import com.pos.repository.interfaces.IInventory
import com.pos.view.interfaces.IDisplay
import org.junit.Assert

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
        val product = Product(1, "", "", "", price)
        Mockito.doReturn(product).`when`(inventorMock).getInventory("::product found barcode::")

        POSService(inventorMock, displayMock).onBarcode("::product found barcode::")

        Mockito.verify(displayMock).displayProduct(product)
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

    @Test
    fun displayListOfProducts(){
        val price = Price(1250)
        val product = Product(1, "", "", "", price)
        Mockito.doReturn(listOf(product)).`when`(inventorMock).getAllInventory()

        val vm : WebDisplayViewModel<List<Product>> = POSService(inventorMock, displayMock).getAllInventory()

        Assert.assertEquals(1, vm.response?.size)
    }
}

