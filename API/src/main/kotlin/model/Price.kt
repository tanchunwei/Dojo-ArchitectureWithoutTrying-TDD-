package com.pos.model

import java.math.BigDecimal

data class Price(val cents: Int) {
    override fun toString(): String {
        return "$${String.format("%,.2f", (cents.toBigDecimal().divide(BigDecimal(100))))}"
    }
}