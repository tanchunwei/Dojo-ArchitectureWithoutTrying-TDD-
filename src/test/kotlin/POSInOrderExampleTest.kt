import controller.PointOfSales
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.InOrder
import org.mockito.Mockito
import org.mockito.Mockito.inOrder
import org.mockito.Mockito.times
import repository.InventoryRepo
import view.Display

// This test is to practice on mockito verify
// Giving some example on how to write those code

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class POSInOrderExampleTest {
    private lateinit var display : Display
    private lateinit var displayInOrder : InOrder
    private lateinit var pos : PointOfSales

    @BeforeEach
    fun setup(){
        var spyRepo = Mockito.spy(InventoryRepo())
        Mockito.doReturn(mapOf("12345" to 7.99, "67890" to 10.99, "09876" to 20, "54321" to 10)).`when`(spyRepo).getInventoryList()
        display = Mockito.spy(Display())
        displayInOrder = inOrder(display)
        pos = PointOfSales(display, spyRepo)
    }

    @Test
    fun scanMultipleItem(){
        pos.onBarcode("12345")
        pos.onBarcode("09876")
        pos.onBarcode("67890")

        // Using in order ensure method is called on after another
        // It will fail if the order of calling is not correct
        // However it will also pass if we verify only one call. (Refer to comment example below, comment existing and uncomment below to test it)
        displayInOrder.verify(display).displayPrice(7.99)
        displayInOrder.verify(display).displayPrice(20.0)
        displayInOrder.verify(display).displayPrice(10.99)

        //displayInOrder.verify(display).displayPrice(20.0)
    }

    @Test
    fun scanMultipleItem_DuplicateCalls_Scenario1(){
        pos.onBarcode("12345")
        pos.onBarcode("09876")
        pos.onBarcode("09876")
        pos.onBarcode("67890")

        // If duplicate calls example happen
        // The inorder won't fail if you skip one verify. IT ONLY VERIFY THE ORDER
        // E.g verify: A -> B -> C -> D, it also pass as long as is in order: A -> B -> D
        // The commented code will pass
        //displayInOrder.verify(display).displayPrice(7.99)
        //displayInOrder.verify(display).displayPrice(20.0)
        //displayInOrder.verify(display).displayPrice(10.99)

        // To accurately test this is to extensively provide all verify orders:
        // However if you provide times(1) for price 20.0, it will pass too as it is consider to test A -> B/C -> D (It is same as above commented test verification)
        // Lets make it times(2) to test the exact scenario
        displayInOrder.verify(display, times(1)).displayPrice(7.99)
        displayInOrder.verify(display, times(2)).displayPrice(20.0)
        displayInOrder.verify(display, times(1)).displayPrice(10.99)
    }

    @Test
    fun scanMultipleItem_DuplicateCalls_Scenario2(){
        pos.onBarcode("12345")
        pos.onBarcode("09876")
        pos.onBarcode("09876")
        pos.onBarcode("67890")
        pos.onBarcode("09876")

        // This will still pass as it is still inorder
        //displayInOrder.verify(display, times(1)).displayPrice(7.99)
        //displayInOrder.verify(display, times(2)).displayPrice(20.0)
        //displayInOrder.verify(display, times(1)).displayPrice(10.99)

        // However if we change the times(2) to times(3) for price 20.0,
        // it will fail because it is consider to test 7.99 -> 20.0 -> 20.0 -> 20.0 -> 10.99, which is not the correct order
        //displayInOrder.verify(display, times(1)).displayPrice(7.99)
        //displayInOrder.verify(display, times(3)).displayPrice(20.0)
        //displayInOrder.verify(display, times(1)).displayPrice(10.99)

        // The correct and intended way is to verify as 7.99 -> 20.0 -> 20.0 -> 10.99 -> 20.0
        // However the test below using number of invocation fail seems like the invocation will stack base on the same input.
        // It will fail on times(2) for price 20.0 showing "display.displayPrice(20.0d);Wanted 2 times; But was 3 times
        // It will pass on when change times(2) to (times(1) for price 20.0 (I have no idea)
        // There seems to be a bug or limitation when using inorder with times
        //displayInOrder.verify(display, times(1)).displayPrice(7.99)
        //displayInOrder.verify(display, times(2)).displayPrice(20.0)
        //displayInOrder.verify(display, times(1)).displayPrice(10.99)
        //displayInOrder.verify(display, times(1)).displayPrice(20.0)

        // I fallback to the ideal to verify extensively one by one
        displayInOrder.verify(display).displayPrice(7.99)
        displayInOrder.verify(display).displayPrice(20.0)
        displayInOrder.verify(display).displayPrice(20.0)
        displayInOrder.verify(display).displayPrice(10.99)
        displayInOrder.verify(display).displayPrice(20.0)
    }
}