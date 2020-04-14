import controller.PointOfSales
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ComputeCartTotalTest {
    @Test
    fun computeZeroItem(){
        val listOfInt = mutableListOf<Int>()
        assertEquals(0, computeCartTotal(listOfInt))
    }

    @Test
    fun computeOneItem(){
        val listOfInt = mutableListOf(4)
        assertEquals(4, computeCartTotal(listOfInt))
    }

    @Test
    fun computeSeveralItem(){
        val listOfInt = mutableListOf(4, 3, 1, 10)
        assertEquals(18, computeCartTotal(listOfInt))
    }

    private fun computeCartTotal(listOfInt: MutableList<Int>): Int {
        return PointOfSales.computeCartTotal(listOfInt)
    }

}