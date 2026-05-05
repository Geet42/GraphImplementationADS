import java.util.*;

/**
 * PathFinder.java - Path Finding using DFS
 * Geet Bhute: Graph Implementation & DFS
 * Uses modified DFS to find paths between vertices and detect cycles
 */
public class PathFinder<V, E> {
    private Graph<V, E> graph;

//    Creates a PathFinder for the given graph

    public PathFinder(Graph<V, E> graph) {
        this.graph = graph;
    }

//    Finds a path from start to end using DFS
//    Returns null if no path exists
    public List<Vertex<V>> findPath(Vertex<V> start, Vertex<V> end) {
        Map<Vertex<V>, Boolean> visited = new HashMap<>();
        Map<Vertex<V>, Vertex<V>> parent = new HashMap<>();
        
        for (Vertex<V> v : graph.vertices()) {
            visited.put(v, false);
        }
        
        if (pathDFS(start, end, visited, parent)) {
            return reconstructPath(start, end, parent);
        }
        return null;
    }

//    DFS helper for path finding
    private boolean pathDFS(Vertex<V> current, Vertex<V> target,
                           Map<Vertex<V>, Boolean> visited,
                           Map<Vertex<V>, Vertex<V>> parent) {
        visited.put(current, true);
        
        if (current.equals(target)) {
            return true;
        }
        
        for (Edge<E> e : graph.incidentEdges(current)) {
            Vertex<V> neighbor = (Vertex<V>) e.opposite(current);
            if (!visited.get(neighbor)) {
                parent.put(neighbor, current);
                if (pathDFS(neighbor, target, visited, parent)) {
                    return true;
                }
            }
        }
        
        return false;
    }

//    Reconstructs the path from parent pointers
    private List<Vertex<V>> reconstructPath(Vertex<V> start, Vertex<V> end,
                                            Map<Vertex<V>, Vertex<V>> parent) {
        LinkedList<Vertex<V>> path = new LinkedList<>();
        Vertex<V> current = end;
        
        while (current != null) {
            path.addFirst(current);
            current = parent.get(current);
        }
        
        return path;
    }

//    Finds all paths from start to end (up to maxPaths)
    public List<List<Vertex<V>>> findAllPaths(Vertex<V> start, Vertex<V> end, int maxPaths) {
        List<List<Vertex<V>>> allPaths = new ArrayList<>();
        List<Vertex<V>> currentPath = new ArrayList<>();
        Map<Vertex<V>, Boolean> visited = new HashMap<>();
        
        for (Vertex<V> v : graph.vertices()) {
            visited.put(v, false);
        }
        
        findAllPathsDFS(start, end, visited, currentPath, allPaths, maxPaths);
        return allPaths;
    }

//    DFS helper for finding all paths
    private void findAllPathsDFS(Vertex<V> current, Vertex<V> target,
                                Map<Vertex<V>, Boolean> visited,
                                List<Vertex<V>> currentPath,
                                List<List<Vertex<V>>> allPaths,
                                int maxPaths) {
        if (allPaths.size() >= maxPaths) return;
        
        visited.put(current, true);
        currentPath.add(current);
        
        if (current.equals(target)) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            for (Edge<E> e : graph.incidentEdges(current)) {
                Vertex<V> neighbor = (Vertex<V>) e.opposite(current);
                if (!visited.get(neighbor)) {
                    findAllPathsDFS(neighbor, target, visited, currentPath, allPaths, maxPaths);
                }
            }
        }
        
        currentPath.remove(currentPath.size() - 1);
        visited.put(current, false);
    }

//    Detects if the graph contains a cycle
    public boolean hasCycle() {
        Map<Vertex<V>, Boolean> visited = new HashMap<>();
        Map<Vertex<V>, Boolean> recStack = new HashMap<>();
        
        for (Vertex<V> v : graph.vertices()) {
            visited.put(v, false);
            recStack.put(v, false);
        }
        
        for (Vertex<V> v : graph.vertices()) {
            if (hasCycleDFS(v, visited, recStack, null)) {
                return true;
            }
        }
        
        return false;
    }

//    DFS helper for cycle detection
    private boolean hasCycleDFS(Vertex<V> v, 
                               Map<Vertex<V>, Boolean> visited,
                               Map<Vertex<V>, Boolean> recStack,
                               Vertex<V> parent) {
        if (recStack.get(v)) {
            return true; // Back edge found
        }
        
        if (visited.get(v)) {
            return false;
        }
        
        visited.put(v, true);
        recStack.put(v, true);
        
        for (Edge<E> e : graph.incidentEdges(v)) {
            Vertex<V> neighbor = (Vertex<V>) e.opposite(v);
            
            // For undirected graphs, skip the parent
            if (!graph.isDirected() && neighbor.equals(parent)) {
                continue;
            }
            
            if (hasCycleDFS(neighbor, visited, recStack, v)) {
                return true;
            }
        }
        
        recStack.put(v, false);
        return false;
    }
    

    public void printPath(List<Vertex<V>> path) {
        if (path == null || path.isEmpty()) {
            System.out.println("No path found");
            return;
        }
        
        System.out.print("Path: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println(" (Length: " + (path.size() - 1) + ")");
    }
}
