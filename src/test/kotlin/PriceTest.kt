import model.Price
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PriceTest {
    @ParameterizedTest(name = "Price in cents of {0} is {1} in dollar")
    @CsvSource(value = [
        "1250, $12.50"
    ])
    fun toString(priceInCents : Int, expectedResult : String){
        assertEquals(expectedResult, Price(priceInCents).toString())
    }
}