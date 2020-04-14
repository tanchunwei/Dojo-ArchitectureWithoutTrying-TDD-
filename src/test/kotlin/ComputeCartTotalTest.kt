import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ComputeCartTotalTest {
    @Test
    fun computeZeroItem(){
        val listOfInt = mutableListOf<Int>()
        assertEquals(0, listOfInt.computeCartTotal())
    }

    @Test
    fun computeOneItem(){
        val listOfInt = mutableListOf(4)
        assertEquals(4, listOfInt.computeCartTotal())
    }

    @Test
    fun computeSeveralItem(){
        val listOfInt = mutableListOf(4, 3, 1, 10)
        assertEquals(18, listOfInt.computeCartTotal())
    }

    private fun MutableList<Int>.computeCartTotal(): Int {
        var total = 0
        for (price in this) {
            total += price
        }
        return total
    }

}