import java.util.*;

//
 //KruskalMST.java - Kruskal's Algorithm for Minimum Spanning Tree
 //
 //Builds MST by sorting all edges and adding them in increasing weight order,
 //using Union-Find to detect cycles.
 //
 //Time Complexity: O(m log m) for sorting + O(m α(n)) for union-find ≈ O(m log n)
 //Space Complexity: O(n + m)
 //
public class KruskalMST {
    
    private WeightedGraph graph;
    private List<WeightedGraph.Edge> mstEdges;
    private int mstWeight;
    private long executionTime;
    
    public KruskalMST(WeightedGraph graph) {
        this.graph = graph;
        this.mstEdges = new ArrayList<>();
        this.mstWeight = 0;
    }
    
    //
     //Computes the MST using Kruskal's algorithm
     //
    public void computeMST() {
        long startTime = System.nanoTime();
        
        // Sort all edges by weight
        List<WeightedGraph.Edge> sortedEdges = new ArrayList<>(graph.getEdges());
        Collections.sort(sortedEdges);
        
        // Create Union-Find structure
        UnionFind uf = new UnionFind(graph.getVertices());
        
        // Process edges in sorted order
        for (WeightedGraph.Edge e : sortedEdges) {
            WeightedGraph.Vertex u = e.getU();
            WeightedGraph.Vertex v = e.getV();
            
            // If vertices are in different components, add edge to MST
            if (uf.find(u) != uf.find(v)) {
                mstEdges.add(e);
                mstWeight += e.getWeight();
                uf.union(u, v);
                
                // Stop if we have n-1 edges
                if (mstEdges.size() == graph.numVertices() - 1) {
                    break;
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
        System.out.println("\n=== Kruskal's Algorithm MST ===");
        System.out.println("MST Edges (in order added):");
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
     //Simple Union-Find data structure for Kruskal's algorithm
     //
    private static class UnionFind {
        private Map<WeightedGraph.Vertex, WeightedGraph.Vertex> parent;
        private Map<WeightedGraph.Vertex, Integer> rank;
        
        public UnionFind(List<WeightedGraph.Vertex> vertices) {
            parent = new HashMap<>();
            rank = new HashMap<>();
            
            for (WeightedGraph.Vertex v : vertices) {
                parent.put(v, v);
                rank.put(v, 0);
            }
        }
        
        public WeightedGraph.Vertex find(WeightedGraph.Vertex v) {
            if (!parent.get(v).equals(v)) {
                parent.put(v, find(parent.get(v))); // Path compression
            }
            return parent.get(v);
        }
        
        public void union(WeightedGraph.Vertex u, WeightedGraph.Vertex v) {
            WeightedGraph.Vertex rootU = find(u);
            WeightedGraph.Vertex rootV = find(v);
            
            if (rootU.equals(rootV)) return;
            
            // Union by rank
            int rankU = rank.get(rootU);
            int rankV = rank.get(rootV);
            
            if (rankU < rankV) {
                parent.put(rootU, rootV);
            } else if (rankU > rankV) {
                parent.put(rootV, rootU);
            } else {
                parent.put(rootV, rootU);
                rank.put(rootU, rankU + 1);
            }
        }
    }
}
