package controller

import repository.interfaces.IInventory
import view.Display

class POSController(private val inventory: IInventory, private val display: Display) {

    fun onBarcode(barcode: String) {
        if(barcode == ""){
            display.displayEmptyBarcode()
            return
        }

        val price = inventory.getInventory(barcode)

        if(price == null)
            display.displayProductNotFound(barcode)
        else
            display.displayPrice(price)
    }

}