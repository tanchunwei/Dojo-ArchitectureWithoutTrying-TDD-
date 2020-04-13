package controller

import repository.InventoryRepo
import view.Display

class PointOfSales(private val display: Display, private val inventoryRepo: InventoryRepo) {
    fun onBarcode(barcode: String) {
        if(barcode == "") {
            display.displayEmptyBarcodeMessage()
            return
        }

        val inventoryList = inventoryRepo.getInventoryList()
        if(inventoryList.containsKey(barcode)) {
            val priceText = inventoryList.getValue(barcode)
            display.displayPrice(priceText)
        } else
            display.displayProductNotExistMessage(barcode)
    }

}
