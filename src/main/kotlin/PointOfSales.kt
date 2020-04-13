class PointOfSales(val display: Display, val inventoryRepo: InventoryRepo) {
    fun onBarcode(barcode: String) {
        if(barcode == "") {
            displayEmptyBarcodeMessage()
            return
        }

        val inventoryList = inventoryRepo.getInventoryList()
        if(inventoryList.containsKey(barcode)) {
            val priceText = inventoryList.getValue(barcode)
            displayPrice(priceText)
        } else
            displayProductNotExistMessage(barcode)
    }

    private fun displayProductNotExistMessage(barcode: String) {
        display.setText("This product does not exist $barcode")
    }

    private fun displayEmptyBarcodeMessage() {
        display.setText("Barcode cannot be empty")
    }

    private fun displayPrice(price: String) {
        display.setText("$$price")
    }

}
