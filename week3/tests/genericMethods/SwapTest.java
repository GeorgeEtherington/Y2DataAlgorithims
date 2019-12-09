package genericMethods;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SwapTest {

    private String[] stringArray = {"A", "B", "C", "D", "E"};
    private Number[] intArray = {1,2,3,4,5};
   @Test
    void testSwappedStrings() {
       String[] compareArray = {"E", "B", "C", "D", "A"};
        assertArrayEquals(compareArray, Swap.swap(stringArray, 0, 4));
    }

    @Test
    void testSwappedInt() {
       Number[] compareArray = {1,3,2,4,5};
        assertArrayEquals(compareArray, Swap.swap(intArray, 1, 2));
    }

    @Test
    void testNullArrayException() {
        assertThrows(NullPointerException.class, () ->
                Swap.swap(null, 0, 4));
   }

    @Test
    void testOutOfBoundsException() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                Swap.swap(stringArray, 1, 8));
    }


    @Test
    void testNegativeException() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                Swap.swap(stringArray, -1, 4));
    }

}


