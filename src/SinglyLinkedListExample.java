import java.util.Set;

public class SinglyLinkedListExample {

    class Node<V> {
        // instance variables
        public V element;
        private Node<V> next;

        // constructor first
        public Node() {
            this(null, null);
        }

        public Node(V element, Node<V> next) {
            this.element = element;
            this.next = next;
        }

        public V getElement() {
            return element;
        }

        public void setElement(V element) {
            this.element = element;
        }

        public Node<V> getNext() {
            return next;
        }

        public void setNext(Node<V> next) {
            this.next = next;
        }
    }

    class SinglyLinkedList<V> {
        // Instance Variables. Add the tail reference
        protected Node<V> head, tail;
        protected long size;

        // Empty list constructor first
        public SinglyLinkedList() {
            head = null;
            tail = null;
            size = 0;
        }

        // Method to add Nodes to the list.
        // Storage space for the Node is already allocated in the calling method
        public void addFirst(Node<V> node) {
            // SetImplExample the tail only if this is very first Node
            if (tail == null) {
                tail = node;
            }

            node.setNext(head); // Make the next of the new node refer to the head
            head = node; // Give head a new value

            // change the size
            size++;
        }

        // Add new node after current node, checking to see if we are at the tail
        public void addAfter(Node<V> currentNode, Node<V> newNode) {
            if (currentNode == tail) {
                tail = newNode;
            }
            newNode.setNext(currentNode.getNext());
            currentNode.setNext(newNode);

            // change the size
            size++;
        }

        // Add new node after the tail node
        public void addLast(Node<V> node) {
            node.setNext(null);
            tail.setNext(node);
            tail = node;
            size++;
        }

        // Method to remove node from the list
        // Unfortunately, with a singly linked list
        // There is no way to remove last
        // Need previous reference to do that
        public Node<V> removeFirst() {
            if (head == null) {
                System.out.println("Error: Attempt to remove from an empty list");
            }

            // save the one to return
            Node<V> temp = head;

            // do reference manipulation
            head = head.getNext();
            temp.setNext(null);
            size--;

            return temp;
        }

        // Remove the node at the end of the list
        // tail refers to this node, but since the list is single linked
        // there is no way to refer to node before the tail node
        // Need to traverse the list
        public Node<V> removeLast() {
            // Declare local variables/objects
            Node<V> nodeBefore;
            Node<V> nodeToRemove;

            // Make sure we have something to remove
            if (size == 0) {
                System.err.println("Error: Attempt to remove from an empty list");
            }

            // Traverse through the list, getting a reference to the node before the trailer.
            // Since there is no previous reference
            nodeBefore = getFirst();

            for (int count = 0; count < size - 3; count++) {
                nodeBefore = nodeBefore.getNext();
            }

            // Save the last node
            nodeToRemove = tail;

            // Let's do the pointer manipulation
            nodeBefore.setNext(null);
            tail = nodeBefore;
            size--;

            return nodeToRemove;
        }

        // Remove the known node from the list.
        // No need to search or return a value
        // This method make use of 'before' reference in order to allow list manipulation
        public void remove(Node<V> nodeToRemove) {
            // Declare local variables/references
            Node<V> nodeBefore, currentNode;

            // Make sure we have something to remove
            if (size == 0) {
                System.err.println("Error: Attempt to remove from an empty list");
            }

            // Starting at the beginning check for removal
            currentNode = getFirst();
            if (currentNode == nodeToRemove) {
                removeFirst();
            }
            currentNode = getLast();
            if (currentNode == nodeToRemove) {
                removeLast();
            }

            // We've already check two nodes, check the rest
            if (size - 2 > 0) {
                nodeBefore = getFirst();
                currentNode = getFirst().getNext();
                for (int count = 0; count < size - 2; count++) {
                    if (currentNode == nodeToRemove) {
                        // remove current node
                        nodeBefore.setNext(currentNode.getNext());
                        size--;
                        break;
                    }

                    // Change references
                    nodeBefore = currentNode;
                    currentNode = currentNode.getNext();
                }
            }
        }

        // The gets to return the head and/or tail nodes and the size of the list
        public Node<V> getFirst() {
            return head;
        }

        public Node<V> getLast() {
            return tail;
        }

        public long getSize() {
            return size;

        }


    }
}
