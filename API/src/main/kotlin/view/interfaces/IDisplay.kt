package com.pos.view.interfaces

import com.pos.model.Price

interface IDisplay {
    fun displayPrice(price: Price)
    fun displayProductNotFound(barcode: String)
    fun displayEmptyBarcode()
}