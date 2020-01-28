import graph.DepthFirstTraversal;
import graph.GraphError;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Arrays;

public class DepthFirstTest<T> {
    DepthFirstTraversal depthFirstTraversal = new DepthFirstTraversal();

    /**
     * Creates a graph of nodes
     * @throws GraphError
     */
    void createGraph() throws GraphError{

        for (int i = 0; i < 6; i++){
            depthFirstTraversal.add(i);
        }
        depthFirstTraversal.add(0, 1);
        depthFirstTraversal.add(0, 3);
        depthFirstTraversal.add(1, 2);
        depthFirstTraversal.add(2, 1);
        depthFirstTraversal.add(2, 5);
        depthFirstTraversal.add(2, 4);
        depthFirstTraversal.add(5, 4);
        depthFirstTraversal.add(4, 5);

    }

    /**
     * Tests that the DepthFirstTraversal works as intended with integers
     */
    @Test
    void traverseTest() throws GraphError {
        createGraph();
        List<Integer> intList = Arrays.asList(0,3,1,2,5,4);
        assertEquals(depthFirstTraversal.traverse(), intList);
    }

    /**
     * Tests the add function and contains function.
     */
    @Test
    void addTest() throws GraphError{
        createGraph();
        assertFalse(depthFirstTraversal.contains(9));
        depthFirstTraversal.add(9);
        assertTrue(depthFirstTraversal.contains(9));
        assertThrows(GraphError.class, ()->
                depthFirstTraversal.add(5));
    }

    /**
     * Tests that check whether the correct amount of nodes exist within the graph
     */
    @Test
    void checkNodeCount() throws GraphError{
        createGraph();
        List<Integer> intList = depthFirstTraversal.traverse();
        assertEquals(intList.size(), 6);
        depthFirstTraversal.add(8);
        intList = depthFirstTraversal.traverse();
        assertEquals(intList.size(), 7);
    }

}
