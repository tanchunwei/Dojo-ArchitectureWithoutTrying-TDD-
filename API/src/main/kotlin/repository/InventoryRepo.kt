package com.pos.repository

import com.pos.model.Price
import com.pos.model.Product
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import com.pos.repository.interfaces.IInventory

class InventoryRepo(val template : NamedParameterJdbcTemplate) :
    IInventory {
    override fun getInventory(barcode: String): Product? {
        mapOf(
            "barcode" to barcode
        )
        val priceInCents = template.query("select * from inventory where barcode = :barcode",
            mapOf(
                "barcode" to barcode
            )){
            rs, _-> rs.getInt("price")
        }[0]
        return Product(1, barcode, "Fake", "Fake", Price(priceInCents))
    }

    override fun getAllInventory(): List<Product> {
        val products = template.query("select * from inventory"){
            rs, _->
                val price = Price(rs.getInt("price"))
                Product(1, "Fake", "Fake", "Fake", price)
        }
        return products.toList()
    }

}