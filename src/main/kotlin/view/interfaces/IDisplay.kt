package view.interfaces

import model.Price

interface IDisplay {
    fun displayPrice(price: Price)
    fun displayProductNotFound(barcode: String)
    fun displayEmptyBarcode()
}