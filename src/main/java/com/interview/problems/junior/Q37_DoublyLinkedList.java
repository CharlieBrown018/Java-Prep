package com.interview.problems.junior;

class DoublyNode {
    int value;
    DoublyNode next, prev;

    DoublyNode(int value) {
        this.value = value;
        next = prev = null;
    }
}

public class Q37_DoublyLinkedList {
    DoublyNode head;

    public static void main(String[] args) {
        Q37_DoublyLinkedList dll = new Q37_DoublyLinkedList();
        dll.insert(1);
        dll.insert(2);
        dll.insert(3);
        dll.display();
    }

    public void insert(int value) {
        DoublyNode newNode = new DoublyNode(value);
        if (head == null) {
            head = newNode;
            return;
        }
        DoublyNode temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = newNode;
        newNode.prev = temp;
    }

    public void display() {
        DoublyNode temp = head;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
    }
}
