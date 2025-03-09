package com.interview.problems.junior;

public class Q23_QueueDemo {
    public static void main(String[] args) {
        int[] queue = new int[5];
        int front = 0, rear = 0;

        // Enqueue elements
        queue[rear++] = 10;
        queue[rear++] = 20;
        queue[rear++] = 30;

        // Dequeue elements
        System.out.println("Dequeued: " + queue[front++]);
        System.out.println("Dequeued: " + queue[front++]);
    }
}
