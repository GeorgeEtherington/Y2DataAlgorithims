package linkedList.list;

import linkedList.node.ListNode;
import linkedList.node.SingleLinkNode;

public class SingleLinkList<T> extends BasicList<SingleLinkNode<T>,T> implements List<T> {
    /**
     * A helper method to access a node at a specified index.
     *
     * @param index the index of the node to be accessed.
     * @throws ListAccessError if there is no node with the given index.
     */
    SingleLinkNode<T> getNode(int index) throws ListAccessError {
        // Is the list empty?  If so, cannot access the node.
        if (isEmpty()) {
            throw new ListAccessError("Cannot get node.  List is empty.");
        }
        // Is the given index negative?  If so, this is an error.
        if (index < 0) {
            throw new ListAccessError("Cannot get node.  Negative index.");
        }
        /*
         * Try to find the specified node by "walking" through the list, following links to successor
         * nodes.  The index tells us how many links need to be followed to reach the required node,
         * so reduce the index by one each time a link is followed.  When the index reaches zero, the
         * required node has been found.  If the end of the list is reached (next node is null), before
         * the index reaches zero, there were not enough nodes in the list (the index was too high).
         */
        SingleLinkNode<T> currentNode = getRoot(); // start at the root
        while (index != 0 && currentNode != null) { // walk along the list (if haven't reached the end by hitting null node)
            currentNode = currentNode.getNext(); // by gettting next node in the list
            index--; // and reducing index by one
        }
        // Reached the end of the list (by hitting null node)?  If so, cannot access the required node.
        if (currentNode == null) {
            throw new ListAccessError("Cannot get node.  Not enough nodes in the list.");
        }
        // Successfully found node by walking through until index was zero.
        return currentNode;
    }
    public void add(int index, T value) throws ListAccessError{
        SingleLinkNode<T> nextNode = getNode(index);
        SingleLinkNode<T>currentNode = new SingleLinkNode<T>(value, nextNode);
        if (index == 0){
            setRoot(currentNode);
        }else {
            SingleLinkNode<T> previousNode = getNode(index - 1);
            previousNode.setNext(currentNode);
        }

    }
    /**
     * Access the value at a given index.
     *
     * @param index the index of the value to be accessed.
     * @throws ListAccessError if there is no value with the given index.
     */
    public T get(int index) throws ListAccessError {
        return getNode(index).getValue();
    }
    public T remove(int index) throws ListAccessError{
        return getNode(index).getValue();
    }
}

