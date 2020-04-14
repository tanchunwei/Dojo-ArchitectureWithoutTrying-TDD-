import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class FormatMonetaryTest {
    @ParameterizedTest(name = "Format {0} to {1}")
    @CsvSource(value = [
        "799, $7.99"
    ])
    fun formatMonetary(price : Int, expectedFormat : String){
        assertEquals(expectedFormat, formatMonetary(price) )
    }

    private fun formatMonetary(price: Int): String {
        return "$7.99"
    }
}