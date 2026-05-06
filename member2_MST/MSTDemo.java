//
 //MSTDemo.java - Demonstration of MST Algorithms
 //THEORETICAL CONTRIBUTION:
 //Solves the Network Design Optimization Problem
 //
 //Problem: Design a telecommunications or utility network that connects all
 //nodes with minimum total cost while maintaining full connectivity.
 //
 //Solution: MST algorithms (Prim's and Kruskal's) find the optimal network
 //layout in O(m log n) time, applicable to infrastructure planning.
 //
public class MSTDemo {
    
    public static void main(String[] args) {
        System.out.println("=".repeat(70));
        System.out.println("MEMBER 2: MINIMUM SPANNING TREE ALGORITHMS");
        System.out.println("=".repeat(70));
        
        // Demo 1: Basic MST with Prim's and Kruskal's
        demo1_BasicMST();
        
        // Demo 2: Algorithm Comparison
        demo2_AlgorithmComparison();
        
        // Demo 3: Theoretical Contribution - Network Design
        demo3_NetworkDesignOptimization();
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("THEORETICAL CONTRIBUTION DEMONSTRATION");
        System.out.println("=".repeat(70));
        telecommunicationsNetwork();
    }
    
    //
     //Demo 1: Basic MST computation with both algorithms
     //
    private static void demo1_BasicMST() {
        System.out.println("\n--- DEMO 1: Basic MST Computation ---\n");
        
        WeightedGraph graph = createSampleGraph();
        graph.printGraph();
        
        // Prim's Algorithm
        PrimMST prim = new PrimMST(graph);
        prim.computeMST();
        prim.printMST();
        
        // Kruskal's Algorithm
        KruskalMST kruskal = new KruskalMST(graph);
        kruskal.computeMST();
        kruskal.printMST();
        
        // Verify both produce same weight
        System.out.println("\nVerification:");
        System.out.println("Prim's MST weight: " + prim.getMSTWeight());
        System.out.println("Kruskal's MST weight: " + kruskal.getMSTWeight());
        System.out.println("Weights match: " + (prim.getMSTWeight() == kruskal.getMSTWeight()));
    }
    
    //
     //Demo 2: Comparing algorithm performance
     //
    private static void demo2_AlgorithmComparison() {
        System.out.println("\n--- DEMO 2: Algorithm Comparison ---\n");
        
        int[] sizes = {10, 50, 100};
        
        for (int n : sizes) {
            System.out.println("Graph size: " + n + " vertices");
            WeightedGraph graph = createRandomGraph(n, n * 2);
            
            PrimMST prim = new PrimMST(graph);
            prim.computeMST();
            
            KruskalMST kruskal = new KruskalMST(graph);
            kruskal.computeMST();
            
            System.out.println("  Prim's time: " + String.format("%.3f", prim.getExecutionTimeMs()) + " ms");
            System.out.println("  Kruskal's time: " + String.format("%.3f", kruskal.getExecutionTimeMs()) + " ms");
            System.out.println("  MST Weight: " + prim.getMSTWeight());
            System.out.println();
        }
    }
    
    //
     //Demo 3: Network Design Optimization
     //
    private static void demo3_NetworkDesignOptimization() {
        System.out.println("\n--- DEMO 3: Network Design Optimization ---\n");
        
        // Create a network representing cities and connection costs
        WeightedGraph cityNetwork = new WeightedGraph();
        
        WeightedGraph.Vertex nyc = cityNetwork.addVertex("NYC");
        WeightedGraph.Vertex boston = cityNetwork.addVertex("Boston");
        WeightedGraph.Vertex philly = cityNetwork.addVertex("Philadelphia");
        WeightedGraph.Vertex dc = cityNetwork.addVertex("Washington DC");
        WeightedGraph.Vertex atlanta = cityNetwork.addVertex("Atlanta");
        
        // Connection costs (in thousands of dollars)
        cityNetwork.addEdge(nyc, boston, 215);
        cityNetwork.addEdge(nyc, philly, 95);
        cityNetwork.addEdge(nyc, dc, 225);
        cityNetwork.addEdge(boston, philly, 310);
        cityNetwork.addEdge(philly, dc, 140);
        cityNetwork.addEdge(philly, atlanta, 730);
        cityNetwork.addEdge(dc, atlanta, 640);
        
        System.out.println("Original Network:");
        System.out.println("Total connection cost: $" + cityNetwork.getTotalWeight() + "K");
        System.out.println("Number of connections: " + cityNetwork.numEdges());
        
        KruskalMST mst = new KruskalMST(cityNetwork);
        mst.computeMST();
        
        System.out.println("\nOptimized Network (MST):");
        System.out.println("Minimum cost: $" + mst.getMSTWeight() + "K");
        System.out.println("Number of connections: " + mst.getMSTEdges().size());
        System.out.println("Cost savings: $" + (cityNetwork.getTotalWeight() - mst.getMSTWeight()) + "K");
        System.out.println("\nRequired connections:");
        for (WeightedGraph.Edge e : mst.getMSTEdges()) {
            System.out.println("  " + e);
        }
    }
    
    //
     //Main theoretical contribution: Telecommunications network optimization
     //
    private static void telecommunicationsNetwork() {
        System.out.println("\nPROBLEM: Telecommunications Network Design");
        System.out.println("Build a fiber optic network connecting all offices with minimum cable cost");
        System.out.println();
        
        // Create network of offices
        WeightedGraph network = new WeightedGraph();
        
        WeightedGraph.Vertex hq = network.addVertex("HQ");
        WeightedGraph.Vertex office1 = network.addVertex("Office-1");
        WeightedGraph.Vertex office2 = network.addVertex("Office-2");
        WeightedGraph.Vertex office3 = network.addVertex("Office-3");
        WeightedGraph.Vertex office4 = network.addVertex("Office-4");
        WeightedGraph.Vertex office5 = network.addVertex("Office-5");
        WeightedGraph.Vertex datacenter = network.addVertex("DataCenter");
        
        // All possible cable routes with costs (cable length in meters)
        network.addEdge(hq, office1, 1200);
        network.addEdge(hq, office2, 1800);
        network.addEdge(hq, datacenter, 800);
        network.addEdge(office1, office2, 1500);
        network.addEdge(office1, office3, 2200);
        network.addEdge(office2, office3, 1000);
        network.addEdge(office2, office4, 1700);
        network.addEdge(office3, office4, 900);
        network.addEdge(office3, office5, 1300);
        network.addEdge(office4, office5, 1100);
        network.addEdge(office4, datacenter, 2000);
        network.addEdge(office5, datacenter, 1400);
        
        System.out.println("NETWORK ANALYSIS:");
        System.out.println("Locations: " + network.numVertices());
        System.out.println("Possible cable routes: " + network.numEdges());
        System.out.println("Total cable if all routes used: " + network.getTotalWeight() + "m");
        
        // Apply both algorithms
        System.out.println("\n--- PRIM'S ALGORITHM ---");
        PrimMST prim = new PrimMST(network);
        prim.computeMST();
        
        System.out.println("\n--- KRUSKAL'S ALGORITHM ---");
        KruskalMST kruskal = new KruskalMST(network);
        kruskal.computeMST();
        
        System.out.println("\n=== OPTIMAL SOLUTION ===");
        System.out.println("Required cable length: " + kruskal.getMSTWeight() + "m");
        System.out.println("Number of connections: " + kruskal.getMSTEdges().size());
        System.out.println("Cable saved: " + (network.getTotalWeight() - kruskal.getMSTWeight()) + "m");
        System.out.println("Percentage saved: " + 
            String.format("%.1f%%", 100.0 * (network.getTotalWeight() - kruskal.getMSTWeight()) / network.getTotalWeight()));
        
        System.out.println("\nOptimal cable routes:");
        for (WeightedGraph.Edge e : kruskal.getMSTEdges()) {
            System.out.println("  " + e);
        }
        
        System.out.println("\nALGORITHM COMPARISON:");
        System.out.println("Prim's execution time: " + String.format("%.3f", prim.getExecutionTimeMs()) + " ms");
        System.out.println("Kruskal's execution time: " + String.format("%.3f", kruskal.getExecutionTimeMs()) + " ms");
        
        System.out.println("\nCOMPLEXITY ANALYSIS:");
        System.out.println("Prim's: O((n + m) log n) = O(" + 
            (network.numVertices() + network.numEdges()) + " * log " + network.numVertices() + ")");
        System.out.println("Kruskal's: O(m log m) = O(" + network.numEdges() + " * log " + network.numEdges() + ")");
        
        System.out.println("\nPRACTICAL APPLICATIONS:");
        System.out.println("✓ Telecommunications networks");
        System.out.println("✓ Power grid distribution");
        System.out.println("✓ Road network planning");
        System.out.println("✓ Pipeline infrastructure");
        System.out.println("✓ Circuit board design");
        
        System.out.println("\nALGORITHM SELECTION GUIDELINES:");
        System.out.println("• Use Prim's for DENSE graphs (many connections)");
        System.out.println("• Use Kruskal's for SPARSE graphs (few connections)");
        System.out.println("• Both guarantee optimal solution");
        System.out.println("• Both are greedy algorithms");
    }
    
    //
     //Helper: Creates a sample weighted graph
     //
    private static WeightedGraph createSampleGraph() {
        WeightedGraph g = new WeightedGraph();
        
        WeightedGraph.Vertex a = g.addVertex("A");
        WeightedGraph.Vertex b = g.addVertex("B");
        WeightedGraph.Vertex c = g.addVertex("C");
        WeightedGraph.Vertex d = g.addVertex("D");
        WeightedGraph.Vertex e = g.addVertex("E");
        WeightedGraph.Vertex f = g.addVertex("F");
        
        g.addEdge(a, b, 2);
        g.addEdge(a, c, 5);
        g.addEdge(b, c, 8);
        g.addEdge(b, d, 7);
        g.addEdge(c, d, 8);
        g.addEdge(c, e, 3);
        g.addEdge(d, e, 4);
        g.addEdge(d, f, 9);
        g.addEdge(e, f, 7);
        
        return g;
    }
    
    //
     //Helper: Creates a random weighted graph
     //
    private static WeightedGraph createRandomGraph(int numVertices, int numEdges) {
        WeightedGraph g = new WeightedGraph();
        java.util.List<WeightedGraph.Vertex> vertices = new java.util.ArrayList<>();
        
        for (int i = 0; i < numVertices; i++) {
            vertices.add(g.addVertex("V" + i));
        }
        
        java.util.Random rand = new java.util.Random(42);
        int edgesAdded = 0;
        
        while (edgesAdded < numEdges && edgesAdded < numVertices * (numVertices - 1) / 2) {
            int i = rand.nextInt(numVertices);
            int j = rand.nextInt(numVertices);
            if (i != j) {
                g.addEdge(vertices.get(i), vertices.get(j), rand.nextInt(100) + 1);
                edgesAdded++;
            }
        }
        
        return g;
    }
}
