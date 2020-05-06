package com.pos.view.interfaces

import com.pos.model.Price
import com.pos.model.Product

interface IDisplay {
    fun displayProduct(product: Product)
    fun displayProductNotFound(barcode: String)
    fun displayEmptyBarcode()
}