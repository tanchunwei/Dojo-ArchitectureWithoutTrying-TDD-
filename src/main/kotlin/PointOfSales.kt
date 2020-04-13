class PointOfSales(val display: Display, val inventoryRepo: InventoryRepo) {
    fun onBarcode(barcode: String) {
        if(barcode == "") {
            display.setText("Barcode cannot be empty")
            return
        }

        val inventoryList = inventoryRepo.getInventoryList()
        if(inventoryList.containsKey(barcode))
            display.setText(inventoryList.getValue(barcode))
        else
            display.setText("This product does not exist $barcode")
    }

}
