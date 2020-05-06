package com.pos.view

import com.pos.model.Price
import com.pos.model.Product
import com.pos.view.interfaces.IDisplay

class ConsoleDisplay : IDisplay {
    override fun displayProduct(product: Product) {
        println(product)
    }

    override fun displayProductNotFound(barcode: String) {
        println("Product not found [$barcode]")
    }

    override fun displayEmptyBarcode() {
        println("Barcode is empty. Please try to rescan")
    }

}