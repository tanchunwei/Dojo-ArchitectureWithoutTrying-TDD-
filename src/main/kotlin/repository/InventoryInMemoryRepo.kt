package repository

import model.Price
import repository.interfaces.IInventory

class InventoryInMemoryRepo(private val products: Map<String, Price>) : IInventory {
    override fun getInventory(barcode: String): Price? {
        return products[barcode]
    }

}