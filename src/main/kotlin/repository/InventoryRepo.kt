package com.pos.repository

import model.Price
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import repository.interfaces.IInventory

class InventoryRepo(val template : NamedParameterJdbcTemplate) : IInventory {
    override fun getInventory(barcode: String): Price? {
        mapOf(
            "barcode" to barcode
        )
        val priceInCents = template.query("select * from inventory where barcode = :barcode",
            mapOf(
                "barcode" to barcode
            )){
            rs, _-> rs.getInt("price")
        }[0]
        return Price(priceInCents)
    }

}