/**
 * Edge.java - Represents an edge in a graph
 * Geet Bhute: Graph Implementation & DFS
 * An edge connects two vertices and can store additional information
 */
public class Edge<E> {
    private E element;
    private Vertex<?> origin;
    private Vertex<?> destination;
    private boolean directed;
    

    public Edge(Vertex<?> origin, Vertex<?> destination, E element, boolean directed) {
        this.origin = origin;
        this.destination = destination;
        this.element = element;
        this.directed = directed;
    }
    

    public E getElement() {
        return element;
    }
    

    public Vertex<?> getOrigin() {
        return origin;
    }
    

    public Vertex<?> getDestination() {
        return destination;
    }
    

    public boolean isDirected() {
        return directed;
    }

//    Returns the opposite vertex from the given vertex
    public Vertex<?> opposite(Vertex<?> v) {
        if (v.equals(origin)) {
            return destination;
        } else if (v.equals(destination)) {
            return origin;
        }
        throw new IllegalArgumentException("Vertex is not incident to this edge");
    }
    
    @Override
    public String toString() {
        String arrow = directed ? " -> " : " -- ";
        return origin + arrow + destination + 
               (element != null ? " [" + element + "]" : "");
    }
}
