import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigDecimal

class FormatMonetaryTest {
    @ParameterizedTest(name = "Format {0} to {1}")
    @CsvSource(value = [
        "799| $7.99",
        "1299| $12.99",
        "1000| $10.00",
        "0| $0.00",
        "3| $0.03",
        "123456| $1,234.56",
        "122033981| $1,220,339.81"
    ], delimiter = '|')
    fun formatMonetary(price : Int, expectedFormat : String){
        assertEquals(expectedFormat, formatMonetary(price) )
    }

    private fun formatMonetary(price: Int): String {
        return "$${String.format("%,.2f",price.toBigDecimal().divide(BigDecimal(100)))}"
    }
}