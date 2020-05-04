package com.pos.service

import com.pos.repository.interfaces.IInventory
import com.pos.view.interfaces.IDisplay

class POSService(private val inventory: IInventory, private val display: IDisplay) {

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