package repository

import model.Price

class InventoryRepo(private val products: Map<String, Price>) {
    fun getInventory(barcode: String): Price {
        return products[barcode]!!
    }

}