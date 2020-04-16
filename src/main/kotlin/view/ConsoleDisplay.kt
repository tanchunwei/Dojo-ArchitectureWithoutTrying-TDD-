package view

import model.Price
import view.interfaces.IDisplay

class ConsoleDisplay : IDisplay {
    override fun displayPrice(price: Price) {
        println(price)
    }

    override fun displayProductNotFound(barcode: String) {
        println("Product not found [$barcode]")
    }

    override fun displayEmptyBarcode() {
        TODO("Not yet implemented")
    }

}