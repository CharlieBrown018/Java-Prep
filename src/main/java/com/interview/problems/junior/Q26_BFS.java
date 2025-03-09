package com.interview.problems.junior;

import java.util.LinkedList;
import java.util.Queue;

public class Q26_BFS {
    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);

        System.out.println("BFS starting from node 0:");
        g.BFS(0);
    }

    static class Graph {
        private int vertices; // Number of vertices
        private LinkedList<Integer>[] adjacencyList;

        // Constructor
        public Graph(int v) {
            vertices = v;
            adjacencyList = new LinkedList[v];
            for (int i = 0; i < v; i++) {
                adjacencyList[i] = new LinkedList<>();
            }
        }

        // Add edge to the graph
        public void addEdge(int src, int dest) {
            adjacencyList[src].add(dest);
            adjacencyList[dest].add(src); // For an undirected graph
        }

        // BFS traversal from a given source node
        public void BFS(int startNode) {
            boolean[] visited = new boolean[vertices]; // To track visited nodes
            Queue<Integer> queue = new LinkedList<>();

            visited[startNode] = true;
            queue.add(startNode);

            while (!queue.isEmpty()) {
                int node = queue.poll();
                System.out.print(node + " "); // Print the node

                for (int neighbor : adjacencyList[node]) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.add(neighbor);
                    }
                }
            }
        }
    }
}
