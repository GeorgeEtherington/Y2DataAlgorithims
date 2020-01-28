package graph;

import java.util.*;

/**
 * Perform a reference counting topological sort on a graph.
 *
 * This version is slightly different from the one presented in the lecture, as that version did not properly identify
 * cyclic graphs.
 *
 * @author Hugh Osborne
 * @version December 2019
 */

public class ReferenceCountingTopologicalSort<T> extends AdjacencyGraph<T> implements TopologicalSort<T> {
    Stack<T> sort;
    private HashMap<T, Integer> neighbors = new HashMap<T, Integer>();


    @Override
    public List<T> getSort() throws GraphError {
      sort = new Stack<T>();
        for (T node: getNodes()) {
            neighbors.put(node, 0);
        }

        for (T node : getNodes()) {
            for (T neighbour : getNeighbours(node)) {
                neighbors.put(neighbour, neighbors.get(neighbour)+1);
            }
        }

        T node;
        while ((node = noPredecessorsNode()) != null) {
            visitNode(node);
        }
        if (sort.size() != getNodes().size()) {
            throw new GraphError();
        }

        return sort;
    }


    /**
     * Method used to visit nodes and set up the future nodes to visit
     * @param node
     * @throws GraphError
     */
    private void visitNode(T node) throws GraphError {

        sort.add(node);
        for (T neighbour : getNeighbours(node)) {
            neighbors.put(neighbour, neighbors.get(neighbour) - 1);
        }
        neighbors.remove(node);
    }

    /**
     *Checks if the stored value has any neighbours
     * @return node
     */
    private T noPredecessorsNode() {

        for (Map.Entry<T, Integer> entryPoint : neighbors.entrySet()) {
            if (entryPoint.getValue() == 0) {
                return entryPoint.getKey();
            }

        }
        return null;
    }
}

