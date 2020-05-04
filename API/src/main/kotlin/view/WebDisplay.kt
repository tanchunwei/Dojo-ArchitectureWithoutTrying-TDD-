package com.pos.view

import com.pos.model.Price
import com.pos.view.interfaces.IDisplay

class WebDisplay : IDisplay {
    private var display: String = ""

    override fun displayPrice(price: Price) {
        display = price.toString()
    }

    override fun displayProductNotFound(barcode: String) {
        display = "Product not found [$barcode]"
    }

    override fun displayEmptyBarcode() {
        display = "Barcode is empty. Please try to rescan"
    }

    fun toJson(): String {
        return "{\"display\":\"$display\"}"
    }
}
