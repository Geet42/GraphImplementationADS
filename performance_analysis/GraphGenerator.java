import java.util.*;

/**
 * GraphGenerator.java - Random Graph Generation for Performance Testing
 * Performance Analysis Module
 * 
 * Generates various types of graphs for benchmarking
 */
public class GraphGenerator {
    
    private Random random;
    
    public GraphGenerator(long seed) {
        this.random = new Random(seed);
    }
    
    /**
     * Generates a random connected graph
     */
    public static class GraphData {
        public int numVertices;
        public List<Edge> edges;
        
        public GraphData(int numVertices, List<Edge> edges) {
            this.numVertices = numVertices;
            this.edges = edges;
        }
    }
    
    public static class Edge {
        public int u, v, weight;
        
        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }
    
    /**
     * Generates a random sparse graph (m ≈ n)
     */
    public GraphData generateSparseGraph(int n) {
        List<Edge> edges = new ArrayList<>();
        
        // Create a spanning tree first to ensure connectivity
        for (int i = 1; i < n; i++) {
            int parent = random.nextInt(i);
            int weight = random.nextInt(100) + 1;
            edges.add(new Edge(parent, i, weight));
        }
        
        // Add a few random edges
        int extraEdges = n / 4;
        for (int i = 0; i < extraEdges; i++) {
            int u = random.nextInt(n);
            int v = random.nextInt(n);
            if (u != v) {
                edges.add(new Edge(u, v, random.nextInt(100) + 1));
            }
        }
        
        return new GraphData(n, edges);
    }
    
    /**
     * Generates a random dense graph (m ≈ n²)
     */
    public GraphData generateDenseGraph(int n) {
        List<Edge> edges = new ArrayList<>();
        Set<String> edgeSet = new HashSet<>();
        
        // Add many edges
        int targetEdges = (n * (n - 1)) / 4; // About 50% of possible edges
        
        while (edges.size() < targetEdges) {
            int u = random.nextInt(n);
            int v = random.nextInt(n);
            
            if (u != v) {
                String key = Math.min(u, v) + "-" + Math.max(u, v);
                if (!edgeSet.contains(key)) {
                    edgeSet.add(key);
                    edges.add(new Edge(u, v, random.nextInt(100) + 1));
                }
            }
        }
        
        return new GraphData(n, edges);
    }
    
    /**
     * Generates a grid graph (like a mesh network)
     */
    public GraphData generateGridGraph(int rows, int cols) {
        List<Edge> edges = new ArrayList<>();
        int n = rows * cols;
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int v = r * cols + c;
                
                // Right neighbor
                if (c < cols - 1) {
                    edges.add(new Edge(v, v + 1, random.nextInt(50) + 1));
                }
                
                // Bottom neighbor
                if (r < rows - 1) {
                    edges.add(new Edge(v, v + cols, random.nextInt(50) + 1));
                }
            }
        }
        
        return new GraphData(n, edges);
    }
    
    /**
     * Generates a complete graph (every vertex connected to every other)
     */
    public GraphData generateCompleteGraph(int n) {
        List<Edge> edges = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                edges.add(new Edge(i, j, random.nextInt(100) + 1));
            }
        }
        
        return new GraphData(n, edges);
    }
    
    /**
     * Generates a random tree
     */
    public GraphData generateTree(int n) {
        List<Edge> edges = new ArrayList<>();
        
        for (int i = 1; i < n; i++) {
            int parent = random.nextInt(i);
            int weight = random.nextInt(100) + 1;
            edges.add(new Edge(parent, i, weight));
        }
        
        return new GraphData(n, edges);
    }
    
    /**
     * Generates a scale-free graph (power-law distribution)
     * Simulates real-world networks like social networks
     */
    public GraphData generateScaleFreeGraph(int n, int m) {
        List<Edge> edges = new ArrayList<>();
        List<Integer> degrees = new ArrayList<>();
        
        // Start with a small connected graph
        for (int i = 0; i < Math.min(3, n); i++) {
            degrees.add(i);
            degrees.add(i);
        }
        
        edges.add(new Edge(0, 1, random.nextInt(100) + 1));
        edges.add(new Edge(1, 2, random.nextInt(100) + 1));
        
        // Preferential attachment
        for (int v = 3; v < n; v++) {
            int edgesToAdd = Math.min(m, v);
            Set<Integer> connected = new HashSet<>();
            
            for (int i = 0; i < edgesToAdd; i++) {
                // Select target with probability proportional to degree
                int target;
                do {
                    target = degrees.get(random.nextInt(degrees.size()));
                } while (connected.contains(target) || target == v);
                
                connected.add(target);
                edges.add(new Edge(v, target, random.nextInt(100) + 1));
                
                degrees.add(v);
                degrees.add(target);
            }
        }
        
        return new GraphData(n, edges);
    }
    
    /**
     * Prints graph statistics
     */
    public static void printGraphStats(GraphData graph) {
        System.out.println("Graph Statistics:");
        System.out.println("  Vertices: " + graph.numVertices);
        System.out.println("  Edges: " + graph.edges.size());
        System.out.println("  Density: " + 
            String.format("%.4f", 
                2.0 * graph.edges.size() / (graph.numVertices * (graph.numVertices - 1))));
        
        int totalWeight = 0;
        for (Edge e : graph.edges) {
            totalWeight += e.weight;
        }
        System.out.println("  Total weight: " + totalWeight);
        System.out.println("  Average edge weight: " + 
            String.format("%.2f", (double) totalWeight / graph.edges.size()));
    }
}
