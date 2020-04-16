import model.Price
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PriceTest {
    @ParameterizedTest(name = "Price in cents of {0} is {1} in dollar")
    @CsvSource(value = [
        "1250| $12.50",
        "0| $0.00",
        "3| $0.03",
        "105000| $1,050.00",
        "143688291| $1,436,882.91"
    ], delimiter = '|')
    fun toString(priceInCents : Int, expectedResult : String){
        assertEquals(expectedResult, Price(priceInCents).toString())
    }
}