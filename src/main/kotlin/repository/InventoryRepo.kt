package repository

class InventoryRepo {
    fun getInventoryList() : Map<String, Double> {
        TODO("Implement production code to get inventory list")
    }

    fun getPrice(barcode: String) = getInventoryList().getValue(barcode)

    fun hasBarcode(barcode: String) = getInventoryList().containsKey(barcode)
}
