package com.pos.repository.interfaces

import com.pos.model.Price

interface IInventory {
    fun getInventory(barcode: String): Price?
}