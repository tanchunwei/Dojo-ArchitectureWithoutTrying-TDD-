import junit.framework.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test

class POSTest {

    @Test
    fun productFound(){

        val display = Display()
        val pos = PointOfSales(display)

        pos.onBarcode("12345")
        assertEquals("$7.99", display.getText())
    }

    @Ignore("Refactoring - moving output closer to input")
    @Test
    fun productFoundAnotherBarcode(){
        val display = Display()
        val pos = PointOfSales(display)


        pos.onBarcode("67890")
        assertEquals("$10.99", display.getText())
    }
}