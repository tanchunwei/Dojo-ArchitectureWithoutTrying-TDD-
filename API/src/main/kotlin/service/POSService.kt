package com.pos.service

import com.pos.model.Product
import com.pos.model.view.WebDisplayViewModel
import com.pos.repository.interfaces.IInventory
import com.pos.view.interfaces.IDisplay

class POSService(private val inventory: IInventory, private val display: IDisplay) {

    fun onBarcode(barcode: String) {
        if(barcode == ""){
            display.displayEmptyBarcode()
            return
        }

        val product = inventory.getInventory(barcode)

        if(product == null)
            display.displayProductNotFound(barcode)
        else
            display.displayProduct(product)
    }

    fun getAllInventory(): WebDisplayViewModel<List<Product>> {
        var products = inventory.getAllInventory();
        return WebDisplayViewModel(true, "", products)
    }

}