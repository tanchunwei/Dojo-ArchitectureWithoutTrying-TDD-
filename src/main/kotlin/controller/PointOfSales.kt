package controller

import repository.InventoryRepo
import view.Display

class PointOfSales(private val display: Display, private val inventoryRepo: InventoryRepo) {
    fun onBarcode(barcode: String) {
        if(barcode == "") {
            display.displayEmptyBarcodeMessage()
            return
        }

        if(inventoryRepo.hasBarcode(barcode)) {
            display.displayPrice(inventoryRepo.getPrice(barcode))
        } else
            display.displayProductNotExistMessage(barcode)
    }
}
