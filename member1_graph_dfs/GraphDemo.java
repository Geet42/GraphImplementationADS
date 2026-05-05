import java.util.*;

/**
 * GraphDemo.java - Demonstration of Graph and DFS implementations
 * Geet Bhute: Graph Implementation & DFS
 * Solves the Connected Components Detection Problem in Social Networks
 * Problem: In a social network, identify isolated communities and suggest
 * connections to improve overall network connectivity.
 * Solution: DFS efficiently detects connected components in O(n+m) time,
 * enabling real-time community analysis and connection recommendations.
 */
public class GraphDemo {
    
    public static void main(String[] args) {
        System.out.println("=".repeat(70));
        System.out.println("Geet Bhute: GRAPH IMPLEMENTATION & DEPTH-FIRST SEARCH");
        System.out.println("=".repeat(70));
        
        // Demo 1: Basic Graph Operations
        demo1_BasicGraphOperations();
        
        // Demo 2: DFS Traversal
        demo2_DFSTraversal();
        
        // Demo 3: Connected Components (Main Contribution)
        demo3_ConnectedComponents();
        
        // Demo 4: Path Finding
        demo4_PathFinding();
        
        // Demo 5: Spanning Tree
        demo5_SpanningTree();
        
        // Demo 6: Cycle Detection
        demo6_CycleDetection();
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("=".repeat(70));
        socialNetworkAnalysis();
    }
    
    /**
     * Demo 1: Basic graph operations and adjacency list structure
     */
    private static void demo1_BasicGraphOperations() {
        System.out.println("\n--- DEMO 1: Basic Graph Operations ---\n");
        
        Graph<String, Integer> graph = new Graph<>(false);
        
        // Create vertices (airports)
        Vertex<String> sfo = graph.insertVertex("SFO");
        Vertex<String> lax = graph.insertVertex("LAX");
        Vertex<String> ord = graph.insertVertex("ORD");
        Vertex<String> dfw = graph.insertVertex("DFW");
        Vertex<String> mia = graph.insertVertex("MIA");
        
        // Create edges (flight routes with distances)
        graph.insertEdge(sfo, lax, 337);
        graph.insertEdge(sfo, ord, 1846);
        graph.insertEdge(lax, dfw, 1235);
        graph.insertEdge(ord, dfw, 802);
        graph.insertEdge(dfw, mia, 1121);
        
        graph.printGraph();
        
        System.out.println("\nGraph Statistics:");
        System.out.println("Total vertices: " + graph.numVertices());
        System.out.println("Total edges: " + graph.numEdges());
        System.out.println("ORD degree: " + graph.degree(ord));
        System.out.println("Are SFO and MIA adjacent? " + graph.areAdjacent(sfo, mia));
    }
    
    /**
     * Demo 2: DFS traversal and visit order
     */
    private static void demo2_DFSTraversal() {
        System.out.println("\n--- DEMO 2: DFS Traversal ---\n");
        
        Graph<String, String> graph = createSampleGraph();
        DFS<String, String> dfs = new DFS<>(graph);
        
        System.out.println("Starting DFS from vertex A:");
        dfs.dfs(graph.vertices().get(0));
        
        System.out.println("Visit order: " + dfs.getVisitOrder());
        dfs.printStatistics();
    }
    
    /**
     * Demo 3: Connected Components Detection (Main Theoretical Contribution)
     */
    private static void demo3_ConnectedComponents() {
        System.out.println("\n--- DEMO 3: Connected Components Detection ---\n");
        
        // Create a graph with multiple components
        Graph<String, String> graph = new Graph<>(false);

        // Component 1: A-B-C
        Vertex<String> a = graph.insertVertex("A");
        Vertex<String> b = graph.insertVertex("B");
        Vertex<String> c = graph.insertVertex("C");
        graph.insertEdge(a, b, "e1");
        graph.insertEdge(b, c, "e2");

        // Component 2: D-E
        Vertex<String> d = graph.insertVertex("D");
        Vertex<String> e = graph.insertVertex("E");
        graph.insertEdge(d, e, "e3");

        // Component 3: F (isolated)
        Vertex<String> f = graph.insertVertex("F");

        DFS<String, String> dfs = new DFS<>(graph);

        System.out.println("Detecting connected components...");
        int numComponents = dfs.dfsComplete();
        
        System.out.println("\nTotal components found: " + numComponents);
        System.out.println("Graph is " + (dfs.isConnected() ? "connected" : "disconnected"));
        
        List<List<Vertex<String>>> components = dfs.getConnectedComponents();
        for (int i = 0; i < components.size(); i++) {
            System.out.println("Component " + (i + 1) + ": " + components.get(i));
        }
    }

    /**
     * Demo 4: Path finding between vertices
     */
    private static void demo4_PathFinding() {
        System.out.println("\n--- DEMO 4: Path Finding ---\n");
        
        Graph<String, String> graph = createSampleGraph();
        PathFinder<String, String> pathFinder = new PathFinder<>(graph);
        
        List<Vertex<String>> vertices = graph.vertices();
        Vertex<String> start = vertices.get(0); // A
        Vertex<String> end = vertices.get(4);   // E
        
        System.out.println("Finding path from " + start + " to " + end + ":");
        List<Vertex<String>> path = pathFinder.findPath(start, end);
        pathFinder.printPath(path);
        
        System.out.println("\nFinding all paths (max 5):");
        List<List<Vertex<String>>> allPaths = pathFinder.findAllPaths(start, end, 5);
        for (int i = 0; i < allPaths.size(); i++) {
            System.out.print("Path " + (i + 1) + ": ");
            pathFinder.printPath(allPaths.get(i));
        }
    }
    
    /**
     * Demo 5: Spanning tree construction
     */
    private static void demo5_SpanningTree() {
        System.out.println("\n--- DEMO 5: Spanning Tree Construction ---\n");
        
        Graph<String, String> graph = createSampleGraph();
        DFS<String, String> dfs = new DFS<>(graph);
        
        List<Edge<String>> spanningTree = dfs.getSpanningTree();
        
        System.out.println("Spanning tree edges:");
        for (Edge<String> edge : spanningTree) {
            System.out.println("  " + edge);
        }
        System.out.println("\nSpanning tree has " + spanningTree.size() + " edges");
        System.out.println("Original graph has " + graph.numVertices() + " vertices");
        System.out.println("Expected edges in spanning tree: " + (graph.numVertices() - 1));
    }
    
    /**
     * Demo 6: Cycle detection
     */
    private static void demo6_CycleDetection() {
        System.out.println("\n--- DEMO 6: Cycle Detection ---\n");
        
        // Graph without cycle (tree)
        Graph<String, String> tree = new Graph<>(false);
        Vertex<String> a = tree.insertVertex("A");
        Vertex<String> b = tree.insertVertex("B");
        Vertex<String> c = tree.insertVertex("C");
        tree.insertEdge(a, b, "e1");
        tree.insertEdge(b, c, "e2");
        
        PathFinder<String, String> pf1 = new PathFinder<>(tree);
        System.out.println("Tree structure: " + (pf1.hasCycle() ? "has cycle" : "no cycle"));
        
        // Graph with cycle
        Graph<String, String> cyclic = createSampleGraph();
        PathFinder<String, String> pf2 = new PathFinder<>(cyclic);
        System.out.println("Cyclic graph: " + (pf2.hasCycle() ? "has cycle" : "no cycle"));
    }
    
    /**
     * Demonstrates the main theoretical contribution:
     * Social Network Analysis for Community Detection
     */
    private static void socialNetworkAnalysis() {
        System.out.println("\nPROBLEM: Social Network Community Detection");
        System.out.println("Identify isolated communities in a social network");
        System.out.println();
        
        // Create a social network with multiple communities
        Graph<String, String> socialNetwork = new Graph<>(false);
        
        // Community 1: Tech enthusiasts
        Vertex<String> alice = socialNetwork.insertVertex("Alice");
        Vertex<String> bob = socialNetwork.insertVertex("Bob");
        Vertex<String> carol = socialNetwork.insertVertex("Carol");
        Vertex<String> dave = socialNetwork.insertVertex("Dave");
        socialNetwork.insertEdge(alice, bob, "friends");
        socialNetwork.insertEdge(bob, carol, "friends");
        socialNetwork.insertEdge(carol, dave, "friends");
        socialNetwork.insertEdge(dave, alice, "friends");
        
        // Community 2: Artists
        Vertex<String> eve = socialNetwork.insertVertex("Eve");
        Vertex<String> frank = socialNetwork.insertVertex("Frank");
        Vertex<String> grace = socialNetwork.insertVertex("Grace");
        socialNetwork.insertEdge(eve, frank, "friends");
        socialNetwork.insertEdge(frank, grace, "friends");
        
        // Community 3: Isolated user
        Vertex<String> henry = socialNetwork.insertVertex("Henry");
        
        System.out.println("Social Network Statistics:");
        System.out.println("Total users: " + socialNetwork.numVertices());
        System.out.println("Total connections: " + socialNetwork.numEdges());
        
        // Apply DFS to detect communities
        DFS<String, String> communityDetector = new DFS<>(socialNetwork);
        List<List<Vertex<String>>> communities = communityDetector.getConnectedComponents();
        
        System.out.println("\nDETECTED COMMUNITIES: " + communities.size());
        for (int i = 0; i < communities.size(); i++) {
            List<Vertex<String>> community = communities.get(i);
            System.out.println("\nCommunity " + (i + 1) + " (" + community.size() + " users):");
            System.out.println("  Members: " + community);
            
            if (community.size() == 1) {
                System.out.println("  ALERT: Isolated user detected!");
                System.out.println("  Recommendation: Suggest connections to other communities");
            }
        }
        
        System.out.println("\nANALYSIS COMPLEXITY:");
        System.out.println("Time: O(n + m) where n = users, m = connections");
        System.out.println("Space: O(n) for tracking visited users");

    }
    
    /**
     * Helper: Creates a sample graph for demonstrations
     */
    private static Graph<String, String> createSampleGraph() {
        Graph<String, String> g = new Graph<>(false);
        
        Vertex<String> a = g.insertVertex("A");
        Vertex<String> b = g.insertVertex("B");
        Vertex<String> c = g.insertVertex("C");
        Vertex<String> d = g.insertVertex("D");
        Vertex<String> e = g.insertVertex("E");
        
        g.insertEdge(a, b, "e1");
        g.insertEdge(a, d, "e2");
        g.insertEdge(b, c, "e3");
        g.insertEdge(b, d, "e4");
        g.insertEdge(d, e, "e5");
        
        return g;
    }
}
