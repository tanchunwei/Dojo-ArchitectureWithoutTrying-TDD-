package model

data class Price(val cents: Int) {
    override fun toString(): String {
        return "$${String.format("%,.2f", (cents.toFloat() / 100))}"
    }
}