package com.pos.repository

import com.pos.model.Product
import com.pos.repository.interfaces.IInventory

class InventoryInMemoryRepo(private val products: Map<String, Product>) :
    IInventory {
    override fun getInventory(barcode: String) : Product? {
        return products[barcode]
    }

    override fun getAllInventory(): List<Product> {
        return products.values.toList()
    }

}