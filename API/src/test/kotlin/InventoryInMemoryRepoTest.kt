import com.pos.model.Price
import com.pos.model.Product
import com.pos.repository.interfaces.IInventory
import com.pos.repository.InventoryInMemoryRepo

class InventoryInMemoryRepoTest : InventoryTest() {

    override fun inventoryWith(barcode: String, product: Product): IInventory {
        return InventoryInMemoryRepo(mapOf(barcode to product))
    }

    override fun inventoryWithout(barcode : String): IInventory {
        return InventoryInMemoryRepo(
            mapOf(
                "any barcode except $barcode" to Product(0,"", "", "", Price(0))
            )
        )
    }
}