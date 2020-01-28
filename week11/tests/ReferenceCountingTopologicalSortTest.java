import graph.GraphError;
import graph.ReferenceCountingTopologicalSort;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class ReferenceCountingTopologicalSortTest {

    ReferenceCountingTopologicalSort<Integer> graph = new ReferenceCountingTopologicalSort();
    ReferenceCountingTopologicalSort<Integer> errorGraph = new ReferenceCountingTopologicalSort();

    void createGraph() throws GraphError {
        for (int i = 0; i < 5; i++){
            graph.add(i);
        }
        graph.add(0, 2);
        graph.add(2, 3);
        graph.add(3, 4);
        graph.add(4, 1);


    }

    void createErrorGraph() throws GraphError {
        for (int i = 0; i < 5; i++){
            errorGraph.add(i);
        }
        errorGraph.add(0, 0);
        errorGraph.add(1, 1);
        errorGraph.add(2, 2);
        errorGraph.add(3, 3);
        errorGraph.add(4, 4);

    }
    
    /**
     * Checks that a topologicalsort happens
     */
    @Test
    void topologicalSortTest() throws GraphError {
        createGraph();
        List<Integer> graphSort = graph.getSort();
        List<Integer> sortedList = Arrays.asList(0, 2, 3, 4, 1);
        assertEquals(graphSort, sortedList);
    }

    /**
     * Tests that it throws an error when presented with an acyclic graph
     * @throws GraphError
     */
    @Test
    void errorGraphTest()throws GraphError{
        createErrorGraph();
        assertThrows(GraphError.class, ()->
                errorGraph.getSort());
    }
}


