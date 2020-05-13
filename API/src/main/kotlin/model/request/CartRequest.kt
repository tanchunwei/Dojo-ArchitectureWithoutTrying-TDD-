package com.pos.model.request

data class CartRequest (
    val clientID : String,
    val barcode : String
)