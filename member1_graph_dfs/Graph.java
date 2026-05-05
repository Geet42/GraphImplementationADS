import java.util.*;

/**
 * Graph.java - Adjacency List Graph Implementation
 * Geet Bhute: Graph Implementation & DFS
 * Implements a graph using adjacency list structure for efficient
 * traversal and space usage, particularly for sparse graphs.
 */
public class Graph<V, E> {
    private List<Vertex<V>> vertices;
    private List<Edge<E>> edges;
    private Map<Vertex<V>, List<Edge<E>>> adjacencyList;
    private boolean directed;
    private int vertexIdCounter;

//    Creates an empty graph
    public Graph(boolean directed) {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.adjacencyList = new HashMap<>();
        this.directed = directed;
        this.vertexIdCounter = 0;
    }
    

    public int numVertices() {
        return vertices.size();
    }
    

    public int numEdges() {
        return edges.size();
    }
    

    public List<Vertex<V>> vertices() {
        return new ArrayList<>(vertices);
    }
    

    public List<Edge<E>> edges() {
        return new ArrayList<>(edges);
    }
    

    public Vertex<V> insertVertex(V element) {
        Vertex<V> v = new Vertex<>(element, vertexIdCounter++);
        vertices.add(v);
        adjacencyList.put(v, new ArrayList<>());
        return v;
    }
    

    public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) {
        if (!vertices.contains(u) || !vertices.contains(v)) {
            throw new IllegalArgumentException("Vertex not in graph");
        }
        
        Edge<E> e = new Edge<>(u, v, element, directed);
        edges.add(e);
        
        // Add edge to adjacency list
        adjacencyList.get(u).add(e);
        if (!directed) {
            adjacencyList.get(v).add(e);
        }
        
        return e;
    }


//    Returns the edges incident to vertex v
    public List<Edge<E>> incidentEdges(Vertex<V> v) {
        return new ArrayList<>(adjacencyList.get(v));
    }

    public int degree(Vertex<V> v) {
        return adjacencyList.get(v).size();
    }

//    Tests whether vertices u and v are adjacent
    public boolean areAdjacent(Vertex<V> u, Vertex<V> v) {
        for (Edge<E> e : adjacencyList.get(u)) {
            if (e.opposite(u).equals(v)) {
                return true;
            }
        }
        return false;
    }

//    Returns whether the graph is directed
    public boolean isDirected() {
        return directed;
    }
    


    public void printGraph() {
        System.out.println("Graph (" + (directed ? "Directed" : "Undirected") + "):");
        System.out.println("Vertices: " + numVertices());
        System.out.println("Edges: " + numEdges());
        System.out.println("\nAdjacency List:");
        for (Vertex<V> v : vertices) {
            System.out.print(v + " -> ");
            List<Edge<E>> edges = adjacencyList.get(v);
            for (int i = 0; i < edges.size(); i++) {
                Edge<E> e = edges.get(i);
                System.out.print(e.opposite(v));
                if (i < edges.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
    
    //Returns a string representation of the graph
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph: ").append(numVertices()).append(" vertices, ")
          .append(numEdges()).append(" edges\n");
        for (Edge<E> e : edges) {
            sb.append(e).append("\n");
        }
        return sb.toString();
    }
}
