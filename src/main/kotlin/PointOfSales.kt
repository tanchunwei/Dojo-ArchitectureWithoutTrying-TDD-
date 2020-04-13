class PointOfSales(val display: Display) {
    fun onBarcode(barcode: String) {
        if(barcode == "12345")
            display.setText("$7.99")
        else
            display.setText("$10.99")
    }

}
