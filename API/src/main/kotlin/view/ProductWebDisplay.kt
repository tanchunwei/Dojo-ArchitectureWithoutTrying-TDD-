package com.pos.view

import com.pos.model.Price
import com.pos.model.Product
import com.pos.model.view.WebDisplayViewModel
import com.pos.view.interfaces.IDisplay

class ProductWebDisplay : IDisplay {
    private lateinit var vm: WebDisplayViewModel<Product>

    override fun displayProduct(product: Product) {
        vm = WebDisplayViewModel(true, "", product)
    }

    override fun displayProductNotFound(barcode: String) {
        vm = WebDisplayViewModel(false, "Product not found [$barcode]", null)
    }

    override fun displayEmptyBarcode() {
        vm = WebDisplayViewModel(false, "Barcode is empty. Please try to rescan", null)
    }

    fun getVM() : WebDisplayViewModel<Product>{
        return vm;
    }
}
