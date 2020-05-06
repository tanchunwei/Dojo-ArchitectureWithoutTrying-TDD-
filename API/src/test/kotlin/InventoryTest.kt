import com.pos.model.Price
import com.pos.model.Product
import com.pos.repository.interfaces.IInventory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

abstract class InventoryTest {
    @Test
    fun itemFound(){
        val product : Product = Product(
                1,
                "12345",
                "Fish",
                "Fresh water fish",
                Price(1250)
        )

        val actualPrice = inventoryWith("12345", product).getInventory("12345")

        Assertions.assertEquals(product, actualPrice)
    }

    @Test
    fun itemNotFound(){
        val actualPrice = inventoryWithout("12345").getInventory("12345")

        Assertions.assertEquals(null, actualPrice)
    }

    protected abstract fun inventoryWith(barcode: String, price: Product): IInventory

    protected abstract fun inventoryWithout(barcode : String): IInventory
}

