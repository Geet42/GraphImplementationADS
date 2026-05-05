/**
 * Vertex.java - Represents a vertex in a graph
 * Member 1: Graph Implementation & DFS
 * A lightweight vertex that stores an arbitrary element
 */
public class Vertex<V> {
    private V element;
    private int id;

//    Creates a vertex with the given element and ID
    public Vertex(V element, int id) {
        this.element = element;
        this.id = id;
    }
    

    public V getElement() {
        return element;
    }
    

    public void setElement(V element) {
        this.element = element;
    }
    

    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return element.toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return id == vertex.id;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
