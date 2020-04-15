package controller

import view.Display
import repository.InventoryRepo

class POSController(private val inventoryRepo: InventoryRepo, private val display: Display) {

    fun onBarcode(barcode: String) {
        if(barcode == ""){
            display.displayEmptyBarcode()
            return
        }

        val price = inventoryRepo.getInventory(barcode)

        if(price == null)
            display.displayProductNotFound(barcode)
        else
            display.displayPrice(price)
    }

}