package view

class Display {
    private var text = "$7.99"

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

    fun displayPrice(price: Double) {
        setText("$$price")
    }

}
