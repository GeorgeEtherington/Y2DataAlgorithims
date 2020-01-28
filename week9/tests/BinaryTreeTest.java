import binaryTree.BinaryTree;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class BinaryTreeTest {


/**
 * Tests that an integer binary tree can be set up and that it contains the values in the correct order
 */
@Test
void testIntTreeSet(){
    Integer[] intArray = {2,4,1,6,8,5,3};
    BinaryTree intTree = new BinaryTree();

    assertTrue(intTree.isEmpty());
    intTree.insert(7);
    assertEquals(7, intTree.getValue());
    for(int i = 0; i < intArray.length; i++) {
        intTree.insert(intArray[i]);
    }
    List<Integer> intList = intTree.traverse();

    for(int i = 0; i < intList.size(); i++){
      assertEquals(intList.get(i), i+1);
    }

    assertTrue(intTree.contains(3));
    assertFalse(intTree.contains(98));
    assertFalse(intTree.contains("e"));
}

/**
 * Tests that a String binary tree can be set up and that it contains the values in the correct order
 */
@Test
void testStringTreeSet() {
    String[] stringArray = {"A", "C", "D", "B", "Z", "X", "T", "M"};
    String[] testArray = {"A", "A", "B", "C", "D", "M", "T", "X", "Z"};
    BinaryTree stringTree = new BinaryTree();

    assertTrue(stringTree.isEmpty());
    stringTree.insert("A");
    assertEquals("A", stringTree.getValue());
    for (int i = 0; i < stringArray.length; i++) {
        stringTree.insert(stringArray[i]);
    }

    List<String> stringList = stringTree.traverse();

    for (int i = 0; i < stringList.size(); i++) {
        assertEquals(stringList.get(i), testArray[i]);
    }

    assertTrue(stringTree.contains("B"));
    assertFalse(stringTree.contains("O"));
}

/**
 * Tests that an empty binary tree throws a null pointer error
 */
@Test
void testNullPointers(){
    BinaryTree errorTree = new BinaryTree();
    assertThrows(NullPointerException.class, ()->
            errorTree.getValue());
    assertThrows(NullPointerException.class, ()->
            errorTree.getLeft());
    assertThrows(NullPointerException.class, ()->
            errorTree.getRight());

}


}
