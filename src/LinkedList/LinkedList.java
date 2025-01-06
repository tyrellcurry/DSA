package LinkedList;

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
    class Node {
        int value;
        Node next;
        public Node(int value){
            this.value = value;
        }
    }

    public LinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if(length == 0) {
            head = newNode;
            tail = newNode;
        }
        tail.next = newNode;
        tail = newNode;
        length += 1;
    }

   public Node removeLast() {
        if(length == 0) return null;
        Node temp = head;
        Node pre = head;
        while (temp.next != null) {
            pre = temp;
            temp = temp.next;
        }
        tail = pre;
        tail.next = null;
        length -= 1;
        if(length == 0) {
            head = null;
            tail = null;
        }
        return temp;
   }

   public void prepend(int value) {
        Node newNode = new Node(value);
        if(length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
       length += 1;
   }

   public Node removeFirst() {
        if(length == 0) return null;
        Node temp =  head;
        head = head.next;
        temp.next = null;
        length -= 1;
        if(length == 0) {
            tail = null;
        }
        return temp;
   }

   public Node get(int index) {
        if(index < 0 || index >= length) return null;
        Node temp = head;
        for(int i = 0; i < index; i++) {
           temp = temp.next;
        }
        return temp;
   }

   public boolean set(int index, int value) {
        Node nodeAtIndex = get(index);
        if(nodeAtIndex != null) {
            nodeAtIndex.value = value;
            return true;
        } else {
            return false;
        }
   }

  public boolean insert(int index, int value) {
        if(index < 0 || index > length) return false;
        if(index == 0) {
            prepend(value);
            return true;
        }
        if(index == length) {
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
      if(index < 0 || index >= length) return null;
      if(index == 0) return removeFirst();
      if(index == length - 1) return removeLast();
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
        for(int i = 0; i < length; i++) {
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

}

