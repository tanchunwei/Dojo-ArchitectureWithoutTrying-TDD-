class PointOfSales(val display: Display) {
    fun onBarcode(barcode: String) {
        if(barcode == "12345")
            display.setText("$7.99")
        else if(barcode == "67890")
            display.setText("$10.99")
        else
            display.setText("This product does not exist $barcode")
    }

}
