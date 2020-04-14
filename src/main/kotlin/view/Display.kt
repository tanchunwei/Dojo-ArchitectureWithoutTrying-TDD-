package view

class Display {
    private var text = ""

    fun getText(): String {
        return text
    }

    fun setText(text:String){
        this.text = text
    }

    fun displayProductNotExistMessage(barcode: String) {
        setText("This product does not exist $barcode")
    }

    fun displayEmptyBarcodeMessage() {
        setText("Barcode cannot be empty")
    }

    fun displayPrice(price: Int) {
        setText("$${formatPrice(price)}")
    }

    fun displayTotal(price: Int) {
        setText("Total: $${formatPrice(price)}")
    }

    fun displayNoItemInCartMessage() {
        setText("No sale in progress. Try scanning a product.")
    }

    private fun formatPrice(price: Int) = price.toFloat() / 100
}
