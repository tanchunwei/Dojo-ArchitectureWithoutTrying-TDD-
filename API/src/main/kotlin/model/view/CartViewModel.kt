package com.pos.model.view

import com.pos.model.Product
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CartViewModel(
    val clientID: String,
    val transactionDateTime: LocalDateTime,
    val product: Product
){
    val displayDateTime : String
        get() {
            return DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm").format(transactionDateTime)
        }
}