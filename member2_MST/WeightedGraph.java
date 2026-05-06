import java.util.*;

//
 //WeightedGraph.java - Weighted Graph for MST Algorithms
 //
 //Adjacency list implementation optimized for weighted graphs
 //
public class WeightedGraph {
    
    public static class Vertex {
        String label;
        int id;
        
        public Vertex(String label, int id) {
            this.label = label;
            this.id = id;
        }
        
        @Override
        public String toString() {
            return label;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Vertex)) return false;
            return id == ((Vertex) o).id;
        }
        
        @Override
        public int hashCode() {
            return Integer.hashCode(id);
        }
    }
    
    public static class Edge implements Comparable<Edge> {
        Vertex u, v;
        int weight;
        
        public Edge(Vertex u, Vertex v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
        
        public Vertex getU() { return u; }
        public Vertex getV() { return v; }
        public int getWeight() { return weight; }
        
        public Vertex opposite(Vertex vertex) {
            if (vertex.equals(u)) return v;
            if (vertex.equals(v)) return u;
            throw new IllegalArgumentException("Invalid vertex");
        }
        
        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
        
        @Override
        public String toString() {
            return u + " -- " + v + " [" + weight + "]";
        }
    }
    
    private List<Vertex> vertices;
    private List<Edge> edges;
    private Map<Vertex, List<Edge>> adjacencyList;
    private int vertexCounter;
    
    public WeightedGraph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.adjacencyList = new HashMap<>();
        this.vertexCounter = 0;
    }
    
    public Vertex addVertex(String label) {
        Vertex v = new Vertex(label, vertexCounter++);
        vertices.add(v);
        adjacencyList.put(v, new ArrayList<>());
        return v;
    }
    
    public Edge addEdge(Vertex u, Vertex v, int weight) {
        Edge e = new Edge(u, v, weight);
        edges.add(e);
        adjacencyList.get(u).add(e);
        adjacencyList.get(v).add(e);
        return e;
    }
    
    public List<Vertex> getVertices() {
        return new ArrayList<>(vertices);
    }
    
    public List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }
    
    public List<Edge> getIncidentEdges(Vertex v) {
        return new ArrayList<>(adjacencyList.get(v));
    }
    
    public int numVertices() {
        return vertices.size();
    }
    
    public int numEdges() {
        return edges.size();
    }
    
    public int getTotalWeight() {
        return edges.stream().mapToInt(e -> e.weight).sum();
    }
    
    public void printGraph() {
        System.out.println("Weighted Graph:");
        System.out.println("Vertices: " + numVertices());
        System.out.println("Edges: " + numEdges());
        System.out.println("Total Weight: " + getTotalWeight());
        System.out.println("\nEdges:");
        for (Edge e : edges) {
            System.out.println("  " + e);
        }
    }
}
