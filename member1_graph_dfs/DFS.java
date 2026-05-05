import java.util.*;

/**
 * DFS.java - Depth-First Search Implementation
 * Geet Bhute: Graph Implementation & DFS
 * Implements DFS algorithms for graph traversal, connectivity detection,
 * and spanning tree construction.
 */
public class DFS<V, E> {
    private Graph<V, E> graph;
    private Map<Vertex<V>, VertexLabel> vertexLabels;
    private Map<Edge<E>, EdgeLabel> edgeLabels;
    private List<Vertex<V>> visitOrder;
    
    public enum VertexLabel { UNEXPLORED, VISITED }
    public enum EdgeLabel { UNEXPLORED, DISCOVERY, BACK }
    
//    Creates a DFS object for the given graph

    public DFS(Graph<V, E> graph) {
        this.graph = graph;
        this.vertexLabels = new HashMap<>();
        this.edgeLabels = new HashMap<>();
        this.visitOrder = new ArrayList<>();
    }
    
//    Initializes all labels to UNEXPLORED

    private void initializeLabels() {
        vertexLabels.clear();
        edgeLabels.clear();
        visitOrder.clear();
        
        for (Vertex<V> v : graph.vertices()) {
            vertexLabels.put(v, VertexLabel.UNEXPLORED);
        }
        for (Edge<E> e : graph.edges()) {
            edgeLabels.put(e, EdgeLabel.UNEXPLORED);
        }
    }
    
    //Performs DFS traversal from vertex v

    public void dfs(Vertex<V> v) {
        setLabel(v, VertexLabel.VISITED);
        visitOrder.add(v);
        
        for (Edge<E> e : graph.incidentEdges(v)) {
            if (getLabel(e) == EdgeLabel.UNEXPLORED) {
                Vertex<V> w = (Vertex<V>) e.opposite(v);
                if (getLabel(w) == VertexLabel.UNEXPLORED) {
                    setLabel(e, EdgeLabel.DISCOVERY);
                    dfs(w);
                } else {
                    setLabel(e, EdgeLabel.BACK);
                }
            }
        }
    }
    
    //Performs DFS on the entire graph and returns the number of connected components
    public int dfsComplete() {
        initializeLabels();
        int componentCount = 0;
        
        for (Vertex<V> v : graph.vertices()) {
            if (getLabel(v) == VertexLabel.UNEXPLORED) {
                System.out.println("\n--- Component " + (componentCount + 1) + " ---");
                dfs(v);
                componentCount++;
            }
        }
        
        return componentCount;
    }
    
    // Returns connected components as a list of lists
    public List<List<Vertex<V>>> getConnectedComponents() {
        initializeLabels();
        List<List<Vertex<V>>> components = new ArrayList<>();
        
        for (Vertex<V> v : graph.vertices()) {
            if (getLabel(v) == VertexLabel.UNEXPLORED) {
                visitOrder.clear();
                dfs(v);
                components.add(new ArrayList<>(visitOrder));
            }
        }
        
        return components;
    }
    
    //Tests if the graph is connected

    public boolean isConnected() {
        if (graph.numVertices() == 0) return true;
        
        initializeLabels();
        dfs(graph.vertices().get(0));
        
        // Check if all vertices were visited
        for (Vertex<V> v : graph.vertices()) {
            if (getLabel(v) == VertexLabel.UNEXPLORED) {
                return false;
            }
        }
        return true;
    }
    
    //Computes a spanning tree (or forest if disconnected) and returns edges in the spanning tree

    public List<Edge<E>> getSpanningTree() {
        initializeLabels();
        List<Edge<E>> spanningTree = new ArrayList<>();
        
        for (Vertex<V> v : graph.vertices()) {
            if (getLabel(v) == VertexLabel.UNEXPLORED) {
                dfs(v);
            }
        }
        
        // Collect discovery edges
        for (Edge<E> e : graph.edges()) {
            if (getLabel(e) == EdgeLabel.DISCOVERY) {
                spanningTree.add(e);
            }
        }
        
        return spanningTree;
    }
    
    //visit order of the last dfs
    public List<Vertex<V>> getVisitOrder() {
        return new ArrayList<>(visitOrder);
    }
    
    //getters and setters
    private VertexLabel getLabel(Vertex<V> v) {
        return vertexLabels.get(v);
    }

    private void setLabel(Vertex<V> v, VertexLabel label) {
        vertexLabels.put(v, label);
    }

    private EdgeLabel getLabel(Edge<E> e) {
        return edgeLabels.get(e);
    }

    private void setLabel(Edge<E> e, EdgeLabel label) {
        edgeLabels.put(e, label);
    }

//    Prints DFS statistics
    public void printStatistics() {
        int discoveryEdges = 0;
        int backEdges = 0;
        
        for (Edge<E> e : graph.edges()) {
            if (getLabel(e) == EdgeLabel.DISCOVERY) discoveryEdges++;
            else if (getLabel(e) == EdgeLabel.BACK) backEdges++;
        }
        
        System.out.println("\nDFS Statistics:");
        System.out.println("Vertices visited: " + visitOrder.size());
        System.out.println("Discovery edges: " + discoveryEdges);
        System.out.println("Back edges: " + backEdges);
    }
}
