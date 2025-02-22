package com.interview.java;

public class Q20_ThreadsExample {
    /*
     * Question: How do threads work in Java?
     * Demonstrate basic thread creation and synchronization.
     */

    public static void main(String[] args) throws InterruptedException {
        // Using Thread class
        System.out.println("Using Thread class:");
        Counter counter1 = new Counter();
        CounterThread thread1 = new CounterThread(counter1);
        CounterThread thread2 = new CounterThread(counter1);

        thread1.start();
        thread2.start();

        thread1.join();  // Wait for thread1 to complete
        thread2.join();  // Wait for thread2 to complete

        System.out.println("Final count: " + counter1.getCount());

        // Using Runnable interface
        System.out.println("\nUsing Runnable interface:");
        Counter counter2 = new Counter();
        Thread thread3 = new Thread(new CounterRunnable(counter2));
        Thread thread4 = new Thread(new CounterRunnable(counter2));

        thread3.start();
        thread4.start();

        thread3.join();
        thread4.join();

        System.out.println("Final count: " + counter2.getCount());

        // Using Lambda expression
        System.out.println("\nUsing Lambda expression:");
        Counter counter3 = new Counter();
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                counter3.increment();
            }
        };

        Thread thread5 = new Thread(runnable);
        Thread thread6 = new Thread(runnable);

        thread5.start();
        thread6.start();

        thread5.join();
        thread6.join();

        System.out.println("Final count: " + counter3.getCount());
    }

    // Resource that will be shared between threads
    static class Counter {
        private int count = 0;

        // Synchronized method to prevent race conditions
        public synchronized void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    // Thread class example
    static class CounterThread extends Thread {
        private Counter counter;

        public CounterThread(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        }
    }

    // Runnable interface example
    static class CounterRunnable implements Runnable {
        private Counter counter;

        public CounterRunnable(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        }
    }
}