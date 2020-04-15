import model.Price
import repository.interfaces.IInventory
import repository.InventoryInMemoryRepo

class InventoryInMemoryRepoTest : InventoryTest() {

    override fun inventoryWith(barcode: String, price: Price): IInventory {
        return InventoryInMemoryRepo(mapOf(barcode to price))
    }

    override fun inventoryWithout(barcode : String): IInventory {
        return InventoryInMemoryRepo(mapOf("any barcode except $barcode" to Price(0)))
    }
}