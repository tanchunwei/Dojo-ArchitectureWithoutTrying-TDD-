import com.pos.view.ProductWebDisplay
import com.pos.model.Price
import com.pos.model.Product
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ProductWebDisplayTest{
    @Test
    fun displayProduct(){
        val display = ProductWebDisplay()
        val product = Product(1, "12345", "Fish", "Fresh water fish", Price(1250))

        display.displayProduct(product)

        assertEquals(true, display.getVM().result)
        assertEquals(product, display.getVM().response)
    }

    @Test
    fun displayProductNotFound(){
        val display = ProductWebDisplay()

        display.displayProductNotFound("barcode")

        assertEquals(false, display.getVM().result)
        assertEquals("Product not found [barcode]", display.getVM().message)
    }

    @Test
    fun displayEmptyBarcode(){
        val display = ProductWebDisplay()

        display.displayEmptyBarcode()

        assertEquals(false, display.getVM().result)
        assertEquals("Barcode is empty. Please try to rescan", display.getVM().message)
    }
}