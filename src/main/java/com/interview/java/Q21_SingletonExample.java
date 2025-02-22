package com.interview.java;

public class Q21_SingletonExample {
    /*
     * Question: What is the Singleton pattern?
     * Demonstrate thread-safe singleton implementation.
     */

    public static void main(String[] args) {
        // Using eager singleton
        EagerSingleton eager1 = EagerSingleton.getInstance();
        EagerSingleton eager2 = EagerSingleton.getInstance();
        System.out.println("Eager instances are same: " + (eager1 == eager2));

        // Using lazy singleton
        LazySingleton lazy1 = LazySingleton.getInstance();
        LazySingleton lazy2 = LazySingleton.getInstance();
        System.out.println("Lazy instances are same: " + (lazy1 == lazy2));

        // Testing thread safety
        Runnable task = () -> {
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println("Thread " + Thread.currentThread().getId() +
                    " got instance: " + instance.hashCode());
        };

        // Create multiple threads to test singleton
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);

        thread1.start();
        thread2.start();
        thread3.start();
    }

    // Thread-safe singleton using eager initialization
    static class EagerSingleton {
        private static final EagerSingleton instance = new EagerSingleton();

        private EagerSingleton() {
        }  // Private constructor

        public static EagerSingleton getInstance() {
            return instance;
        }
    }

    // Thread-safe singleton using lazy initialization with double-checked locking
    static class LazySingleton {
        private static volatile LazySingleton instance;

        private LazySingleton() {
        }

        public static LazySingleton getInstance() {
            if (instance == null) {
                synchronized (LazySingleton.class) {
                    if (instance == null) {
                        instance = new LazySingleton();
                    }
                }
            }
            return instance;
        }
    }
}