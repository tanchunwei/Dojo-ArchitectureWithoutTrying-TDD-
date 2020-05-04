import com.pos.model.Price
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import com.pos.repository.interfaces.IInventory

abstract class InventoryTest {
    @Test
    fun priceFound(){
        val priceInProduct = Price(1250)

        val actualPrice = inventoryWith("12345", priceInProduct).getInventory("12345")

        Assertions.assertEquals(priceInProduct, actualPrice)
    }

    @Test
    fun priceNotFound(){
        val actualPrice = inventoryWithout("12345").getInventory("12345")

        Assertions.assertEquals(null, actualPrice)
    }

    protected abstract fun inventoryWith(barcode: String, price: Price): IInventory

    protected abstract fun inventoryWithout(barcode : String): IInventory
}