import java.util.*;

//
 //PrimMST.java - Prim-Jarník's Algorithm for Minimum Spanning Tree
 //
 //Grows the MST from a starting vertex by always adding the
 //minimum-weight edge that connects a tree vertex to a non-tree vertex.
 //
 //Time Complexity: O((n + m) log n) with priority queue
 //Space Complexity: O(n)
 //
public class PrimMST {
    
    private WeightedGraph graph;
    private List<WeightedGraph.Edge> mstEdges;
    private int mstWeight;
    private long executionTime;
    
    public PrimMST(WeightedGraph graph) {
        this.graph = graph;
        this.mstEdges = new ArrayList<>();
        this.mstWeight = 0;
    }
    
    //
     //Computes the MST using Prim's algorithm
     //
    public void computeMST() {
        long startTime = System.nanoTime();
        
        if (graph.numVertices() == 0) {
            executionTime = System.nanoTime() - startTime;
            return;
        }
        
        // Priority queue stores (vertex, edge, weight)
        PriorityQueue<EdgeEntry> pq = new PriorityQueue<>();
        Set<WeightedGraph.Vertex> inMST = new HashSet<>();
        Map<WeightedGraph.Vertex, WeightedGraph.Edge> edgeTo = new HashMap<>();
        Map<WeightedGraph.Vertex, Integer> distanceTo = new HashMap<>();
        
        // Initialize distances to infinity
        for (WeightedGraph.Vertex v : graph.getVertices()) {
            distanceTo.put(v, Integer.MAX_VALUE);
        }
        
        // Start from the first vertex
        WeightedGraph.Vertex start = graph.getVertices().get(0);
        distanceTo.put(start, 0);
        pq.offer(new EdgeEntry(start, null, 0));
        
        while (!pq.isEmpty()) {
            EdgeEntry entry = pq.poll();
            WeightedGraph.Vertex v = entry.vertex;
            
            // Skip if already in MST
            if (inMST.contains(v)) continue;
            
            // Add vertex to MST
            inMST.add(v);
            if (entry.edge != null) {
                mstEdges.add(entry.edge);
                mstWeight += entry.weight;
            }
            
            // Update priorities of adjacent vertices
            for (WeightedGraph.Edge e : graph.getIncidentEdges(v)) {
                WeightedGraph.Vertex w = e.opposite(v);
                
                if (!inMST.contains(w) && e.getWeight() < distanceTo.get(w)) {
                    distanceTo.put(w, e.getWeight());
                    edgeTo.put(w, e);
                    pq.offer(new EdgeEntry(w, e, e.getWeight()));
                }
            }
        }
        
        executionTime = System.nanoTime() - startTime;
    }
    
    //
     //Returns the edges in the MST
     //
    public List<WeightedGraph.Edge> getMSTEdges() {
        return new ArrayList<>(mstEdges);
    }
    
    //
     //Returns the total weight of the MST
     //
    public int getMSTWeight() {
        return mstWeight;
    }
    
    //
     //Returns the execution time in milliseconds
     //
    public double getExecutionTimeMs() {
        return executionTime / 1_000_000.0;
    }
    
    //
     //Prints the MST
     //
    public void printMST() {
        System.out.println("\n=== Prim's Algorithm MST ===");
        System.out.println("MST Edges:");
        for (WeightedGraph.Edge e : mstEdges) {
            System.out.println("  " + e);
        }
        System.out.println("Total MST Weight: " + mstWeight);
        System.out.println("Number of Edges: " + mstEdges.size());
        System.out.println("Execution Time: " + String.format("%.3f", getExecutionTimeMs()) + " ms");
    }
    
    //
     //Verifies that the MST is valid
     //
    public boolean isValidMST() {
        return mstEdges.size() == graph.numVertices() - 1;
    }
    
    //
     //Helper class for priority queue entries
     //
    private static class EdgeEntry implements Comparable<EdgeEntry> {
        WeightedGraph.Vertex vertex;
        WeightedGraph.Edge edge;
        int weight;
        
        EdgeEntry(WeightedGraph.Vertex vertex, WeightedGraph.Edge edge, int weight) {
            this.vertex = vertex;
            this.edge = edge;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(EdgeEntry other) {
            return Integer.compare(this.weight, other.weight);
        }
    }
}
