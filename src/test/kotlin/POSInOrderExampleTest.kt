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
        Mockito.doReturn(mapOf("12345" to 799, "67890" to 1099, "09876" to 2000, "54321" to 1000)).`when`(spyRepo).getInventoryList()
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
        displayInOrder.verify(display).displayPrice(799)
        displayInOrder.verify(display).displayPrice(2000)
        displayInOrder.verify(display).displayPrice(1099)

        //displayInOrder.verify(display).displayPrice(2000)
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
        //displayInOrder.verify(display).displayPrice(799)
        //displayInOrder.verify(display).displayPrice(2000)
        //displayInOrder.verify(display).displayPrice(1099)

        // To accurately test this is to extensively provide all verify orders:
        // However if you provide times(1) for price 2000, it will pass too as it is consider to test A -> B/C -> D (It is same as above commented test verification)
        // Lets make it times(2) to test the exact scenario
        displayInOrder.verify(display, times(1)).displayPrice(799)
        displayInOrder.verify(display, times(2)).displayPrice(2000)
        displayInOrder.verify(display, times(1)).displayPrice(1099)
    }

    @Test
    fun scanMultipleItem_DuplicateCalls_Scenario2(){
        pos.onBarcode("12345")
        pos.onBarcode("09876")
        pos.onBarcode("09876")
        pos.onBarcode("67890")
        pos.onBarcode("09876")

        // This will still pass as it is still inorder
        //displayInOrder.verify(display, times(1)).displayPrice(799)
        //displayInOrder.verify(display, times(2)).displayPrice(2000)
        //displayInOrder.verify(display, times(1)).displayPrice(1099)

        // However if we change the times(2) to times(3) for price 2000,
        // it will fail because it is consider to test 799 -> 2000 -> 2000 -> 2000 -> 1099, which is not the correct order
        //displayInOrder.verify(display, times(1)).displayPrice(799)
        //displayInOrder.verify(display, times(3)).displayPrice(2000)
        //displayInOrder.verify(display, times(1)).displayPrice(1099)

        // The correct and intended way is to verify as 799 -> 2000 -> 2000 -> 1099 -> 2000
        // However the test below using number of invocation fail seems like the invocation will stack base on the same input.
        // It will fail on times(2) for price 2000 showing "display.displayPrice(2000);Wanted 2 times; But was 3 times
        // It will pass on when change times(2) to (times(1) for price 2000 (I have no idea)
        // There seems to be a bug or limitation when using inorder with times
        //displayInOrder.verify(display, times(1)).displayPrice(799)
        //displayInOrder.verify(display, times(2)).displayPrice(2000)
        //displayInOrder.verify(display, times(1)).displayPrice(1099)
        //displayInOrder.verify(display, times(1)).displayPrice(2000)

        // I fallback to the ideal to verify extensively one by one
        displayInOrder.verify(display).displayPrice(799)
        displayInOrder.verify(display).displayPrice(2000)
        displayInOrder.verify(display).displayPrice(2000)
        displayInOrder.verify(display).displayPrice(1099)
        displayInOrder.verify(display).displayPrice(2000)
    }
}