import model.Price
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import repository.InventoryRepo

// Assuming is an in-memory repo
class InventoryRepoTest {
    @Test
    fun priceFound(){
        val priceInProduct = Price(1250)

        val actualPrice = inventoryWith("12345", priceInProduct).getInventory("12345")

        assertEquals(priceInProduct, actualPrice)
    }

    @Test
    fun priceNotFound(){
        val actualPrice = inventoryWithout("12345").getInventory("12345")

        assertEquals(null, actualPrice)
    }

    private fun inventoryWith(barcode: String, price: Price): InventoryRepo {
        return InventoryRepo(mapOf(barcode to price))
    }

    private fun inventoryWithout(barcode : String): InventoryRepo {
        return InventoryRepo(mapOf("any barcode except $barcode" to Price(0)))
    }
}