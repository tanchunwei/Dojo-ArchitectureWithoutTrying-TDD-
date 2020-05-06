package com.pos.model

data class Product(
    val id : Int,
    val barcode: String,
    val name: String,
    val description: String,
    private var price: Price
){
    val displayPrice : String
        get() = price.toString()
}