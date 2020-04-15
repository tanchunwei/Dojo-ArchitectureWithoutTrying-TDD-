package repository.interfaces

import model.Price

interface IInventory {
    fun getInventory(barcode: String): Price?
}