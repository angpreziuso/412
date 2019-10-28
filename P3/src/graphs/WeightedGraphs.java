package graphs;

import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * This class implements two different ways of building a Minimal Spanning Tree
 * (Kruskal's and Prim's Algorithms).
 * 
 * References & Acknowledgements:
 * https://algorithms.tutorialhorizon.com/kruskals-algorithm-minimum-spanning-tree-mst-complete-java-implementation/
 * https://www.coursera.org/lecture/algorithms-part2/prims-algorithm-HoHKu (Videos for both Prim's & Kruskal's)
 * 
 * @author angela preziuso
 *
 */
public class WeightedGraphs {
    /**
     * This is a helper class for WeightedGraphs and UnionFind.
     * 
     * @author angelapreziuso
     *
     */
    static class Edge implements Comparable<Edge> {

	private int src, dest;
	private double weight;

	public Edge(int src, int dest, double d) {
	    this.src = src;
	    this.dest = dest;
	    this.weight = d;
	}

	public int compareTo(Edge compareEdge) {
	    if (this.weight < compareEdge.weight)
		return -1;
	    else if (this.weight > compareEdge.weight)
		return 1;
	    else
		return 0;
	}

    }

    /**
     * Kruskal's Algorithm
     * 
     * @param g
     *            - original graph
     * @return result - the new ArrayWeightedGraph that is sorted with weights
     *         and is MST
     */
    public static WeightedGraph kruskal(WeightedGraph g) {
	// Get the num of vertices in graph
	int vertices = g.vertices();

	// Null Check
	if (g.edges() == 0 || vertices == 0) {
	    return new ArrayWeightedGraph(0);
	}

	ArrayWeightedGraph result = new ArrayWeightedGraph(vertices);
	PriorityQueue<Edge> pq = new PriorityQueue<>();

	// Get all the edges in the graph & add to PQ
	for (int i = 0; i < vertices; i++) {
	    Iterator<Integer> it = g.edgeIterator(i);

	    while (it.hasNext()) {
		Integer next = it.next();
		pq.add(new Edge(i, next, g.weight(i, next)));
	    }
	}

	UnionFind union = new UnionFind(vertices);

	while (union.components() > 1) {

	    // Find parent of lowest weight & continue up
	    Edge edge = pq.remove();
	    int i = union.find(edge.src);
	    int j = union.find(edge.dest);

	    // If the parents are not in the same component add to tree
	    if (i != j) {
		result.addEdge(edge.src, edge.dest, edge.weight);
		i++;
		union.union(edge.src, edge.dest);
	    }
	}

	return result;

    }

    /**
     * Prim's Algorithm
     * 
     * @param g
     *            - original graph
     * @return result - the new ArrayWeightedGraph that is sorted with weights
     *         and is MST
     */
    public static WeightedGraph prim(WeightedGraph g) {
	// Get the num of vertices in graph
	int vertices = g.vertices();

	// Null Check
	if (g.edges() == 0 || vertices == 0) {
	    return new ArrayWeightedGraph(0);
	}

	ArrayWeightedGraph result = new ArrayWeightedGraph(vertices);
	PriorityQueue<Edge> pq = new PriorityQueue<>();
	boolean[] contains = new boolean[vertices + 1];

	int i = 0;

	// Setting all the vertices to false then the start node will be tree
	for (int j = 1; j < vertices; j++) {
	    contains[j] = false;
	}
	contains[i] = true;

	// All edges added to PQ
	Iterator<Integer> it = g.edgeIterator(i);

	while (it.hasNext()) {
	    int next = it.next();
	    pq.add(new Edge(i, next, g.weight(i, next)));
	}

	int n = 1;

	// Go through all the vertices and if the dest is already in the tree,
	// continue onto next edge
	while (n < vertices) {
	    Edge edge = pq.remove();

	    if (contains[edge.dest] == true) {
		continue;
	    }

	    // Mark that dest is now in tree
	    contains[edge.dest] = true;
	    result.addEdge(edge.src, edge.dest, edge.weight);
	    n++; // increment num of nodes in tree

	    // Add all nodes to the PQ
	    Iterator<Integer> it2 = g.edgeIterator(edge.dest);

	    while (it2.hasNext()) {
		int k = it2.next();
		pq.add(new Edge(edge.dest, k, g.weight(edge.dest, k)));
	    }
	}
	return result;
    }

    public static void printEdges(WeightedGraph g, String[] vertexNames) {

    }

    public static boolean isEqual(WeightedGraph g1, WeightedGraph g2) {
	return false;

    }

}
