import com.pos.view.WebDisplay
import com.pos.model.Price
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WebDisplayTest{
    @Test
    fun displayPrice(){
        val display = WebDisplay()

        display.displayPrice(Price(1250))

        assertEquals("{\"display\":\"$12.50\"}", display.toJson())
    }

    @Test
    fun displayProductNotFound(){
        val display = WebDisplay()

        display.displayProductNotFound("barcode")

        assertEquals("{\"display\":\"Product not found [barcode]\"}", display.toJson())
    }

    @Test
    fun displayEmptyBarcode(){
        val display = WebDisplay()

        display.displayEmptyBarcode()

        assertEquals("{\"display\":\"Barcode is empty. Please try to rescan\"}", display.toJson())
    }
}