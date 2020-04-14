package controller

import repository.InventoryRepo
import view.Display

class PointOfSales(private val display: Display, private val inventoryRepo: InventoryRepo) {
    private val cart: MutableList<Int> = mutableListOf()

    fun onBarcode(barcode: String) {
        if(barcode == "") {
            display.displayEmptyBarcodeMessage()
            return
        }

        if(inventoryRepo.hasBarcode(barcode)) {
            val price = inventoryRepo.getPrice(barcode)
            cart.add(price)
            display.displayPrice(price)
        } else
            display.displayProductNotExistMessage(barcode)
    }

    fun checkout() {
        if(cart.isEmpty()) {
            display.displayNoItemInCartMessage()
            return
        }

        var total  = 0
        for (price in cart){
            total += price
        }
        display.displayTotal(total)
    }
}
