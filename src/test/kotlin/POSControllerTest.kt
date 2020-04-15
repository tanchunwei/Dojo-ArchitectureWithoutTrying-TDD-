import org.junit.jupiter.api.Test
import org.mockito.Mockito

class POSControllerTest {
    @Test
    fun productFound(){
        val price = Price()
        val displayMock = Mockito.mock(Display::class.java)
        val inventoryRepoMock = Mockito.spy(InventoryRepo())
        Mockito.doReturn(price).`when`(inventoryRepoMock).getInventory("12345")

        POSController(inventoryRepoMock, displayMock).onBarcode("12345")

        Mockito.verify(displayMock).displayPrice(price)
    }

    @Test
    fun productNotFound(){
        val displayMock = Mockito.mock(Display::class.java)
        val inventoryRepoMock = Mockito.spy(InventoryRepo())
        Mockito.doReturn(null).`when`(inventoryRepoMock).getInventory("::product not found barcode::")

        POSController(inventoryRepoMock, displayMock).onBarcode("::product not found barcode::")

        Mockito.verify(displayMock).displayProductNotFound("::product not found barcode::")
    }
}

class InventoryRepo {
    fun getInventory(barcode: String): Price {
        TODO("Not yet implemented")
    }

}

class POSController(private val inventoryRepo: InventoryRepo, private val display: Display) {

    fun onBarcode(barcode: String) {
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

}

class Price {

}
