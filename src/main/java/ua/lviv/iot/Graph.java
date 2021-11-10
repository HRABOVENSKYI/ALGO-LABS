package ua.lviv.iot;

// Java program for Kruskal's algorithm to find Minimum Spanning Tree of a given connected, undirected and weighted graph

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.util.*;

class Graph {

    private static final Logger LOGGER = Logger.getLogger(Graph.class);

    static {
        BasicConfigurator.configure();
    }

    // A class to represent a graph edge
    private static class Edge implements Comparable<Edge> {
        int src;
        int dest;
        int weight;

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return weight == edge.weight;
        }

        @Override
        public int hashCode() {
            return Objects.hash(weight);
        }
    }

    // A class to represent a subset for union-find
    private static class Subset {
        int parent;
        int rank;
    }

    private final int v; // number of vertices
    private final Edge[] edge; // collection of all edges

    // Creates a graph with v vertices
    Graph(int v) {
        int e = v + 1; // number of edges
        this.v = v;
        edge = new Edge[e];
        for (int i = 0; i < e; i++) {
            edge[i] = new Edge();
        }
    }

    // A utility function to find set of an element i (uses path compression technique)
    int find(Subset[] subsets, int i) {
        // find root and make root as parent of i (path compression)
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }

        return subsets[i].parent;
    }

    // A function that does union of two sets of x and y (uses union by rank)
    void union(Subset[] subsets, int x, int y) {
        int xRoot = find(subsets, x);
        int yRoot = find(subsets, y);

        // Attach smaller rank tree under root of high rank tree (Union by Rank)
        if (subsets[xRoot].rank < subsets[yRoot].rank) {
            subsets[xRoot].parent = yRoot;
        } else if (subsets[xRoot].rank > subsets[yRoot].rank) {
            subsets[yRoot].parent = xRoot;
        } else { // If ranks are same, then make one as root and increment its rank by one
            subsets[yRoot].parent = xRoot;
            subsets[xRoot].rank++;
        }
    }

    // The main function to construct MST using Kruskal's algorithm
    void kruskalMST() {
        // This will store the resultant MST
        Edge[] result = new Edge[this.v];

        // An index variable, used for result[]
        int eIndex = 0;

        // An index variable, used for sorted edges
        int i;

        for (i = 0; i < this.v; i++) {
            result[i] = new Edge();
        }

        // Step 1: Sort all the edges in non-decreasing order of their weight. If we are not allowed to change
        // the given graph, we can create a copy of array of edges
        Arrays.sort(edge);

        // Allocate memory for creating V subsets
        Subset[] subsets = new Subset[this.v];
        for (i = 0; i < this.v; i++) {
            subsets[i] = new Subset();
        }

        // Create V subsets with single elements
        for (int vIndex = 0; vIndex < this.v; ++vIndex) {
            subsets[vIndex].parent = vIndex;
            subsets[vIndex].rank = 0;
        }

        i = 0; // Index used to pick next edge

        // Number of edges to be taken is equal to V-1
        while (eIndex < this.v - 1) {
            // Step 2: Pick the smallest edge. And increment the index for next iteration
            Edge nextEdge = edge[i++];

            int x = find(subsets, nextEdge.src);
            int y = find(subsets, nextEdge.dest);

            // If including this edge doesn't cause cycle, include it in result and increment the index of
            // result for next edge
            if (x != y) {
                result[eIndex++] = nextEdge;
                union(subsets, x, y);
            }
            // Else discard the nextEdge
        }

        printResult(result, eIndex);
    }

    private void printResult(Edge[] result, int eIndex) {
        int i;
        // print the contents of result[] to display the built MST
        LOGGER.info("Following are the edges in the constructed MST");
        int minimumCost = 0;
        for (i = 0; i < eIndex; ++i) {
            LOGGER.info(result[i].src + " -- " + result[i].dest + " == " + result[i].weight);
            minimumCost += result[i].weight;
        }
        LOGGER.info("Minimum Cost Spanning Tree " + minimumCost);
    }

    // Driver Code
    public static void main(String[] args) {

        int v = 5; // Number of vertices in graph
        Graph graph = new Graph(v);

        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = 10;

        // add edge 0-2
        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 6;

        // add edge 0-3
        graph.edge[2].src = 0;
        graph.edge[2].dest = 3;
        graph.edge[2].weight = 5;

        // add edge 1-3
        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 15;

        // add edge 2-3
        graph.edge[4].src = 2;
        graph.edge[4].dest = 3;
        graph.edge[4].weight = 4;

        // add edge 2-4
        graph.edge[5].src = 2;
        graph.edge[5].dest = 4;
        graph.edge[5].weight = 4;

        // Method call
        graph.kruskalMST();
    }
}

// Overall complexity: O(ELogE + ELogV)
// O(ELogE) - for sorting edges
// O(ELogV) - iterating in while loop
