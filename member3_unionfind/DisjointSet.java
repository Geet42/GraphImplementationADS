import java.util.*;

/**
 * DisjointSet.java - Generic Disjoint Set Implementation
 * Muskaan: Union-Find with Optimizations
 * 
 * A generic implementation that works with any object type
 */
public class DisjointSet<T> {
    
    private static class Node<T> {
        T data;
        Node<T> parent;
        int rank;
        
        Node(T data) {
            this.data = data;
            this.parent = this;
            this.rank = 0;
        }
    }
    
    private Map<T, Node<T>> nodeMap;
    private int numSets;
    
    /**
     * Creates an empty disjoint set
     */
    public DisjointSet() {
        nodeMap = new HashMap<>();
        numSets = 0;
    }
    
    /**
     * Makes a new set containing element
     */
    public void makeSet(T element) {
        if (nodeMap.containsKey(element)) {
            return; // Element already exists
        }
        nodeMap.put(element, new Node<>(element));
        numSets++;
    }
    
    /**
     * Finds the representative of the set containing element
     */
    public T find(T element) {
        Node<T> node = nodeMap.get(element);
        if (node == null) {
            throw new IllegalArgumentException("Element not in any set");
        }
        
        return findNode(node).data;
    }
    
    /**
     * Internal find with path compression
     */
    private Node<T> findNode(Node<T> node) {
        if (node.parent != node) {
            node.parent = findNode(node.parent); // Path compression
        }
        return node.parent;
    }
    
    /**
     * Unions the sets containing x and y
     */
    public void union(T x, T y) {
        Node<T> nodeX = nodeMap.get(x);
        Node<T> nodeY = nodeMap.get(y);
        
        if (nodeX == null || nodeY == null) {
            throw new IllegalArgumentException("Element not in any set");
        }
        
        Node<T> rootX = findNode(nodeX);
        Node<T> rootY = findNode(nodeY);
        
        if (rootX == rootY) return;
        
        // Union by rank
        if (rootX.rank < rootY.rank) {
            rootX.parent = rootY;
        } else if (rootX.rank > rootY.rank) {
            rootY.parent = rootX;
        } else {
            rootY.parent = rootX;
            rootX.rank++;
        }
        
        numSets--;
    }
    
    /**
     * Tests if x and y are in the same set
     */
    public boolean connected(T x, T y) {
        try {
            return find(x).equals(find(y));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    /**
     * Returns the number of disjoint sets
     */
    public int getNumSets() {
        return numSets;
    }
    
    /**
     * Returns all elements in the same set as element
     */
    public Set<T> getSet(T element) {
        T root = find(element);
        Set<T> result = new HashSet<>();
        
        for (Map.Entry<T, Node<T>> entry : nodeMap.entrySet()) {
            if (findNode(entry.getValue()).data.equals(root)) {
                result.add(entry.getKey());
            }
        }
        
        return result;
    }
    
    /**
     * Returns all disjoint sets
     */
    public List<Set<T>> getAllSets() {
        Map<T, Set<T>> setsMap = new HashMap<>();
        
        for (T element : nodeMap.keySet()) {
            T root = find(element);
            setsMap.putIfAbsent(root, new HashSet<>());
            setsMap.get(root).add(element);
        }
        
        return new ArrayList<>(setsMap.values());
    }
    
    /**
     * Prints all disjoint sets
     */
    public void printSets() {
        List<Set<T>> sets = getAllSets();
        System.out.println("Disjoint Sets (" + numSets + " total):");
        int i = 1;
        for (Set<T> set : sets) {
            System.out.println("  Set " + i++ + ": " + set);
        }
    }
}
