# Graph Algorithms Implementation Project

## Team Members Assignment

### Member 1: Graph Implementation & Depth-First Search
**Directory**: `member1_graph_dfs/`
- **Files**: 
  - `Graph.java` - Core graph data structure (Adjacency List implementation)
  - `Vertex.java` - Vertex representation
  - `Edge.java` - Edge representation
  - `DFS.java` - Depth-First Search algorithms
  - `PathFinder.java` - Path finding using DFS
  - `GraphDemo.java` - Demonstration and testing

**Theoretical Contribution**: 
Solving the **Connected Components Detection Problem** in social networks. DFS efficiently identifies isolated communities and suggests potential connections to improve network connectivity.

---

### Member 2: Minimum Spanning Tree Algorithms
**Directory**: `member2_mst/`
- **Files**:
  - `PrimMST.java` - Prim-Jarník's algorithm implementation
  - `KruskalMST.java` - Kruskal's algorithm implementation
  - `WeightedGraph.java` - Weighted graph representation
  - `MSTDemo.java` - Demonstration and comparison

**Theoretical Contribution**:
Solving the **Network Design Optimization Problem**. Finding minimum cost spanning trees for telecommunications, road networks, and utility distribution systems while comparing greedy strategies.

---

### Member 3: Union-Find Data Structure
**Directory**: `member3_unionfind/`
- **Files**:
  - `UnionFind.java` - Basic union-find structure
  - `OptimizedUnionFind.java` - With path compression and union by rank
  - `DisjointSet.java` - Alternative implementation
  - `UnionFindDemo.java` - Demonstration of optimizations

**Theoretical Contribution**:
Solving the **Dynamic Connectivity Problem** in distributed systems. Efficiently maintaining and querying connected components as network connections are added, critical for real-time network monitoring.

---

### Shared: Performance Analysis
**Directory**: `performance_analysis/`
- **Files**:
  - `PerformanceAnalyzer.java` - Comprehensive performance testing framework
  - `GraphGenerator.java` - Random graph generation for testing
  - `PerformanceTester.java` - Main testing suite with visualizations

**Analysis Includes**:
- Time complexity verification (O(n+m), O(m log n), etc.)
- Space complexity analysis
- Scalability testing (graphs from 100 to 100,000 vertices)
- Comparative analysis across all implementations
- Real-world scenario simulations

---

## Compilation and Execution

### Compile All Components:
```bash
# Member 1
cd member1_graph_dfs
javac *.java

# Member 2
cd ../member2_mst
javac *.java

# Member 3
cd ../member3_unionfind
javac *.java

# Performance Analysis
cd ../performance_analysis
javac *.java
```

### Run Individual Demos:
```bash
# Member 1 Demo
java -cp member1_graph_dfs GraphDemo

# Member 2 Demo
java -cp member2_mst MSTDemo

# Member 3 Demo
java -cp member3_unionfind UnionFindDemo

# Performance Analysis
java -cp performance_analysis PerformanceTester
```

---

## Theoretical Contributions Summary

### 1. Connected Components Detection (DFS)
- **Problem**: Identify isolated groups in social networks
- **Solution**: DFS-based component detection in O(n+m) time
- **Application**: Community detection, network fragmentation analysis

### 2. Network Design Optimization (MST)
- **Problem**: Minimize infrastructure cost while maintaining full connectivity
- **Solution**: Prim's and Kruskal's algorithms, both O(m log n)
- **Application**: Telecommunications, power grids, road networks

### 3. Dynamic Connectivity (Union-Find)
- **Problem**: Real-time tracking of network connectivity changes
- **Solution**: Union-Find with path compression, nearly O(1) amortized
- **Application**: Distributed systems, network monitoring, cluster management

---

## Key Experimental Results

1. **DFS Performance**: Linear O(n+m) confirmed across all graph densities
2. **MST Comparison**: Prim's faster for dense graphs, Kruskal's for sparse
3. **Union-Find Optimizations**: Path compression reduces operations by 90%+
4. **Scalability**: All algorithms handle 100K+ vertices efficiently

---

## References
- Topic 13: Graphs (Course Material)
- Cormen et al., Introduction to Algorithms
- Goodrich & Tamassia, Data Structures and Algorithms in Java
