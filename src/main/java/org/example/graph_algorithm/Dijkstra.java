package org.example.graph_algorithm;

import java.util.*;

class Graph {
    public final Map<Integer, List<Edge>> adjList;
    public Graph() {
        this.adjList = new HashMap<>();
    }

    // Add an edge to the graph
    public void addEdge(int source, int target, int weight) {
        adjList.putIfAbsent(source, new ArrayList<>());
        adjList.get(source).add(new Edge(target, weight));

        // For undirected graph, add the reverse edge as well
        adjList.putIfAbsent(target, new ArrayList<>());
        adjList.get(target).add(new Edge(source, weight));
    }

    // Get neighbors of a node
    public List<Edge> getNeighbors(int node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }
}

class Edge {
    int target;
    int weight;

    public Edge(int target, int weight) {
        this.target = target;
        this.weight = weight;
    }
}

class Dijkstra {
    public static void shortestPath(Graph graph, int source) {

        // Priority queue to select the edge with the smallest weight
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        Map<Integer, Integer> distances = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        // Initialize distances with infinity
        for (Integer node : graph.adjList.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(source, 0);

        pq.add(new Edge(source, 0));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int currentNode = edge.target;

            if (visited.contains(currentNode)) continue;
            visited.add(currentNode);

            for (Edge neighbor : graph.getNeighbors(currentNode)) {
                if (!visited.contains(neighbor.target)) {
                    int newDist = distances.get(currentNode) + neighbor.weight;

                    if (newDist < distances.get(neighbor.target)) {
                        distances.put(neighbor.target, newDist);
                        pq.add(new Edge(neighbor.target, newDist));
                    }
                }
            }
        }

        // Print the shortest distances from the source
        for (Map.Entry<Integer, Integer> entry : distances.entrySet()) {
            System.out.println("Distance from source " + source + " to node " + entry.getKey() + " is " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 1);
        graph.addEdge(2, 1, 2);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 5);

        Dijkstra.shortestPath(graph, 0);
    }
}

