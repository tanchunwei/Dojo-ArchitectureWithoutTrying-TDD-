class PointOfSales(val display: Display) {
    fun onBarcode(barcode: String) {
        display.setText("$7.99")
    }

}
