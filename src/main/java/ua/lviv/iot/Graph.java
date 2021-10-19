package ua.lviv.iot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class Graph {
    private int V; // Num of vertices

    // Array  of lists for Adjacency List Representation
    private final LinkedList<Integer>[] adj;

    // Constructor
    @SuppressWarnings("unchecked")
    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<>();
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj[v].add(w); // Add w to v's list.
    }

    // A function used by DFS
    void DFSUtil(int v, boolean[] visited) {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v + " ");

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    // The function to do DFS traversal. It uses recursive DFSUtil()
    void DFS(int v) {
        // Mark all the vertices as not visited(set as false by default in java)
        boolean[] visited = new boolean[V];

        // Call the recursive helper function to print DFS traversal
        DFSUtil(v, visited);
    }

    // Driver Code
    public static void main(String[] args) {
        Graph g = createGraphFromFile("graph.in");
        g.DFS(2);
    }

    public static Graph createGraphFromFile(String fileName) {
        Set<Integer> vertexes = new HashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] vertexesFromEdge = line.split(" ");
                for (String v : vertexesFromEdge) {
                    vertexes.add(Integer.parseInt(v));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }

        int extraDiff = 10; // as a result of given data being
        Graph graph = new Graph(vertexes.size() + extraDiff);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] vertexesFromEdge = line.split(" ");
                int vertex1 = Integer.parseInt(vertexesFromEdge[0]);
                int vertex2 = Integer.parseInt(vertexesFromEdge[1]);
                graph.addEdge(vertex1, vertex2);
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }

        return graph;
    }
}
