package hw_day14;


public class LinkedList {

	private Node head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    
    private class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    
    public Node getNodeValue(int value) {
        Node current = head;
        while (current != null) {
            if (current.data == value) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    
    public Node get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    
    public void add(Node node, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        if (index == 0) {
            node.next = head;
            head = node;
        } else {
            Node previous = get(index - 1);
            node.next = previous.next;
            previous.next = node;
        }
        size++;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        
        list.add(list.new Node(10), 0);
        list.add(list.new Node(20), 1);
        list.add(list.new Node(30), 2);

        
        Node current = list.head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }

       
        Node node = list.getNodeValue(20);
        if (node != null) {
            System.out.println("Node with value 20 found");
        } else {
            System.out.println("Node with value 20 not found");
        }

       
        try {
            node = list.get(1);
            System.out.println("Node at index 1: " + node.data);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index");
        }
    }
}
