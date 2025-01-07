package LinkedList.src;

/* Linked Lists:
 * Linked Lists (LL) are commonly compared to array lists, because they are both dynamic in length as opposed to arrays which are fixed in length.
 * One main difference between array lists and LL, is LL's do not have indexes assigned directly to a particular item in the list. Rather it has items (nodes) that point to the next one in sequence until it gets to the end. The final node will point to null.
 * LL items are also not in a contiguous place in memory, rather they are all spread out. That is why it cannot have indexes, because the items are spread in memory and not adjoining.
 *
 * Variables (pointers that 'point' to nodes):
 *   - head (points to first node)
 *   - tail (points to last node)
 *
 * Methods in Linked Lists and their Big O:
 *  **for reference, 'n' refers to the number of nodes in the LL**
 *
 * - Appending node:
 *   - O(1)
 *
 * - Removing node:
 *   - O(n)
 *
 * - Prepending node:
 *   - O(1)
 *
 * - Removing first node:
 *   - O(1)
 *
 * - Inserting a node inside list:
 *   - O(n)
 *
 * - Finding a node:
 *   - O(n)
 *
 * Linked Lists under the hood:
 *
 * What is a node?
 *   - It is both a value and a pointer
 *   - We can think of a node like a Hash Map:
 *       {
 *           "value" = 4,
 *           "next" = null
 *       }
 *   Therefore, as an example if we add this node into our linked list, the "next" value will point to the node in front of it (if applicable), and the node behind it will point to it.
 *
 * */

import org.w3c.dom.Node;

public class LinkedList {
    private Node head;
    private Node tail;
    private int length;

    // A Node class is created to be reused because many methods, including the constructor, of LL will need to create new Nodes.
    public class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public LinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public int getMiddleNodeValue() {
        Node middleNode = findMiddleNode();
        return middleNode.value;
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        }
        tail.next = newNode;
        tail = newNode;
        length += 1;
    }

    public Node removeLast() {
        if (length == 0) return null;
        Node temp = head;
        Node pre = head;
        while (temp.next != null) {
            pre = temp;
            temp = temp.next;
        }
        tail = pre;
        tail.next = null;
        length -= 1;
        if (length == 0) {
            head = null;
            tail = null;
        }
        return temp;
    }

    public void prepend(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        length += 1;
    }

    public Node removeFirst() {
        if (length == 0) return null;
        Node temp = head;
        head = head.next;
        temp.next = null;
        length -= 1;
        if (length == 0) {
            tail = null;
        }
        return temp;
    }

    public Node get(int index) {
        if (index < 0 || index >= length) return null;
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public boolean set(int index, int value) {
        Node nodeAtIndex = get(index);
        if (nodeAtIndex != null) {
            nodeAtIndex.value = value;
            return true;
        } else {
            return false;
        }
    }

    public boolean insert(int index, int value) {
        if (index < 0 || index > length) return false;
        if (index == 0) {
            prepend(value);
            return true;
        }
        if (index == length) {
            append(value);
            return true;
        }
        Node newNode = new Node(value);
        Node nodeBeforeIndex = get(index - 1);
        newNode.next = nodeBeforeIndex.next;
        nodeBeforeIndex.next = newNode;
        length += 1;
        return true;
    }

    public Node remove(int index) {
        if (index < 0 || index >= length) return null;
        if (index == 0) return removeFirst();
        if (index == length - 1) return removeLast();
        Node nodeBeforeIndex = get(index - 1);
        Node temp = nodeBeforeIndex.next;
        nodeBeforeIndex.next = temp.next;
        temp.next = null;
        length -= 1;
        return temp;
    }

    public void reverse() {
        // create a temp node and set it to head
        Node temp = head;
        // have the head be assigned to the tail
        head = tail;
        // have the tail be assigned to the head via the temp Node
        tail = temp;
        // after is set to the next node after head (1st node)
        Node after = temp.next;
        // before is initialized to null
        Node before = null;

        // iterate through the length of the nodes
        for (int i = 0; i < length; i++) {
            // Set after to be the next node after the current node
            after = temp.next;
            // Set the current node's next pointer to the previous node
            temp.next = before;
            // Set before to be the current node, as it will be the previous node in the next iteration of the loop
            before = temp;
            // Set temp to be the next node in the linked list
            temp = after;
        }
    }

    //LeetCode methods:

    // Find middle of node:
    public Node findMiddleNode() {
        // create slow node (1 step at a time)
        Node slow = head;
        // create fast node (2 steps at a time)
        Node fast = head;

        // iterate while the fast node is not null and the next node after fast is also not null
        while (fast != null && fast.next != null) {
            // move slow 1 step forward
            slow = slow.next;
            // move fast 2 steps forward
            fast = fast.next.next;
        }
        // once the loop has ended, the slow node will be in the middle of the list
        return slow;
    }

    // checking if list has a loop
    public boolean hasLoop() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // checking if slow and fast are the same node
            if (slow == fast) {
                // if they are, it is a loop because they have met
                return true;
            }
        }
        // if not, we can return false
        return false;
    }

    // Find the k-th node from the end of the linked list
    public Node findKthFromEnd(int k) {
        // initialized slow and fast pointers to point to head
        Node slow = head;
        Node fast = head;
        // move the fast pointer ahead k amount of times
        for (int i = 0; i < k; i++) {
            if (fast == null) return null;
            fast = fast.next;
        }
        // now that there is a gap of k between the slow and fast pointers, we can now move them together 1 by 1 until we reach the end of the list
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // once fast reaches null, we are at the end of the list, so we return the slow pointer which will be k away from the list end
        return slow;
    }

    //Given a value x you the following method is used to rearrange the linked list such that all nodes with a value less than x come before all nodes with a value greater than or equal to x.
    public void partitionList(int x) {
        // If the head node is null, terminate the method (bare return)
        if (head == null) return;

        // Create two dummy nodes to serve as the starting points for two partitions (aka sub-lists)
        Node dummy1 = new Node(0);
        Node dummy2 = new Node(0);

        // Create two pointers initially pointing to the dummy nodes
        Node prev1 = dummy1;
        Node prev2 = dummy2;

        // Initialize the current pointer to traverse the original linked list
        Node current = head;

        // Iterate through the linked list
        while (current != null) {
            // If the current node's value is less than the partition value `x`
            if (current.value < x) {
                // Append the current node to the first partition
                prev1.next = current;
                // Move the prev1 pointer to the current node
                prev1 = current;
            } else {
                // Otherwise, append the current node to the second partition
                prev2.next = current;
                // Move the prev2 pointer to the current node
                prev2 = current;
            }
            // Move to the next node in the original list
            current = current.next;
        }

        // Terminate the second partition by setting the last node's `next` to null
        prev2.next = null;

        // Link the end of the first partition to the start of the second partition
        prev1.next = dummy2.next;

        // Update the head of the list to the first node of the merged partitions
        head = dummy1.next;

        /*
         * Notes about dummy nodes:
         * The dummy nodes themselves (dummy1 and dummy2) don’t change; their next pointers are what point to the actual first nodes in their respective partitions.
         * The dummy nodes have a value of 0 (or any default value), which is not part of the original list. This would corrupt your final list. When we create a new instance of Node, we must pass a value to the constructor if the Node class requires one. This ensures the object is properly initialized and avoids compilation errors.
         * Even though the value is not meaningful in the context of a dummy node, we still need to provide something for the Node object to be created.
         *  */
    }

}

