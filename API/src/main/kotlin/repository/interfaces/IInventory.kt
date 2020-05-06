package com.pos.repository.interfaces

import com.pos.model.Price
import com.pos.model.Product

interface IInventory {
    fun getInventory(barcode: String): Product?
    fun getAllInventory(): List<Product>
}