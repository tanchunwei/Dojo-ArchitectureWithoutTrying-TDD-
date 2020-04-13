import junit.framework.Assert.assertEquals
import org.junit.Test

class POSTest {

    @Test
    fun productFound(){
        val pos = PointOfSales()
        val display = Display()

        pos.onBarcode("12345")
        assertEquals("$7.99", display.getText())
    }
}