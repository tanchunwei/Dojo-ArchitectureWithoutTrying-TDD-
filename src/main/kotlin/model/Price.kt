package model

data class Price(val cents: Int) {
    override fun toString(): String {
        return cents.toString()
    }
}