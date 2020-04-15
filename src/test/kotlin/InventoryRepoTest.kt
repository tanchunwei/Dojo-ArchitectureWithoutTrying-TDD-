import model.Price
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import repository.InventoryRepo

// Assuming is an in-memory repo
class InventoryRepoTest {
    @Test
    fun priceFound(){
        val priceInProduct = Price(1250)
        val inventoryRepo = InventoryRepo(mapOf("12345" to priceInProduct))

        val actualPrice = inventoryRepo.getInventory("12345")

        assertEquals(priceInProduct, actualPrice)
    }

    @Test
    fun priceNotFound(){
        val inventoryRepo = InventoryRepo(mapOf())

        val actualPrice = inventoryRepo.getInventory("67890")

        assertEquals(null, actualPrice)
    }
}