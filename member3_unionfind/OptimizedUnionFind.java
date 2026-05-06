import java.util.*;

/**
 * OptimizedUnionFind.java - Union-Find with Path Compression and Union by Rank
 * Muskaan: Union-Find with Optimizations
 * 
 * Optimized implementation with:
 * - Path Compression: Makes find() nearly O(1) amortized
 * - Union by Rank: Keeps trees balanced
 * 
 * Time Complexity: O(α(n)) amortized per operation (inverse Ackermann)
 * Practically constant time!
 */
public class OptimizedUnionFind {
    
    private Map<Integer, Integer> parent;
    private Map<Integer, Integer> rank;
    private int numSets;
    private long unionOperations;
    private long findOperations;
    private long pathCompressions;
    
    /**
     * Creates an optimized union-find structure with n elements
     */
    public OptimizedUnionFind(int n) {
        parent = new HashMap<>();
        rank = new HashMap<>();
        numSets = n;
        unionOperations = 0;
        findOperations = 0;
        pathCompressions = 0;
        
        for (int i = 0; i < n; i++) {
            parent.put(i, i);
            rank.put(i, 0);
        }
    }
    
    /**
     * Finds the set containing element x
     * WITH PATH COMPRESSION: Makes all nodes point directly to root
     */
    public int find(int x) {
        findOperations++;
        
        if (parent.get(x) != x) {
            int originalParent = parent.get(x);
            parent.put(x, find(parent.get(x))); // Path compression
            
            if (parent.get(x) != originalParent) {
                pathCompressions++;
            }
        }
        return parent.get(x);
    }
    
    /**
     * Unions the sets containing x and y
     * WITH UNION BY RANK: Attaches shorter tree to taller tree
     */
    public void union(int x, int y) {
        unionOperations++;
        
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX == rootY) return;
        
        // Union by rank
        int rankX = rank.get(rootX);
        int rankY = rank.get(rootY);
        
        if (rankX < rankY) {
            parent.put(rootX, rootY);
        } else if (rankX > rankY) {
            parent.put(rootY, rootX);
        } else {
            parent.put(rootY, rootX);
            rank.put(rootX, rankX + 1);
        }
        
        numSets--;
    }
    
    /**
     * Tests if x and y are in the same set
     */
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
    
    /**
     * Returns the number of disjoint sets
     */
    public int getNumSets() {
        return numSets;
    }
    
    /**
     * Returns the rank of element x's tree
     */
    public int getRank(int x) {
        return rank.get(find(x));
    }
    
    /**
     * Returns statistics
     */
    public long getUnionOperations() {
        return unionOperations;
    }
    
    public long getFindOperations() {
        return findOperations;
    }
    
    public long getPathCompressions() {
        return pathCompressions;
    }
    
    /**
     * Prints the structure
     */
    public void printStructure() {
        Map<Integer, List<Integer>> sets = new HashMap<>();
        
        for (int i : parent.keySet()) {
            int root = find(i);
            sets.putIfAbsent(root, new ArrayList<>());
            sets.get(root).add(i);
        }
        
        System.out.println("Optimized Disjoint Sets (" + numSets + " total):");
        int setNum = 1;
        for (Map.Entry<Integer, List<Integer>> entry : sets.entrySet()) {
            Collections.sort(entry.getValue());
            System.out.println("  Set " + setNum++ + ": " + entry.getValue() + 
                             " (rank: " + rank.get(entry.getKey()) + ")");
        }
    }
    
    /**
     * Prints optimization statistics
     */
    public void printStats() {
        System.out.println("\nOptimization Statistics:");
        System.out.println("  Union operations: " + unionOperations);
        System.out.println("  Find operations: " + findOperations);
        System.out.println("  Path compressions: " + pathCompressions);
        System.out.println("  Compression ratio: " + 
            String.format("%.2f%%", 100.0 * pathCompressions / Math.max(1, findOperations)));
    }
}
