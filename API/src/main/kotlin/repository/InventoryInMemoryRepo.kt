package com.pos.repository

import com.pos.model.Price
import com.pos.repository.interfaces.IInventory

class InventoryInMemoryRepo(private val products: Map<String, Price>) :
    IInventory {
    override fun getInventory(barcode: String): Price? {
        return products[barcode]
    }

}