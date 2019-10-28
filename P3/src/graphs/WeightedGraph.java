package graphs;

/**
 * Interface for undirected weighted graphs.
 * This interface is implemented by all classes that represent weighted graphs
 * (typically using either an adjacency list or an adjacency matrix data structure).
 * 
 * @author C. Fox
 * @version 9/19
 */


public interface WeightedGraph extends Graph {

  // default null weight for non-existent edges
  public final double NULL_WEIGHT = Double.POSITIVE_INFINITY;

  /**
   * The weight for non-existent edges in the graph.
   * @param weight the null weight
   */
  void setNullWeight(double weight);
  
  /**
   * Put an edge in the graph.
   * This method does nothing if a precondition is violated.
   * @pre 0 <= v,w and v,w < vertices() and v != w
   * @param v one vertex
   * @param w the other vertex
   * @param weight on the edge
   */
  void addEdge(int v, int w, double weight);
  
  /**
   * Retrieve the weight of an edge; returns the null weight
   * if there is no such edge.
   * @param v one vertex
   * @param w the other vertex
   * @return the weight of edge {v,w} or the null weight if no such edge
   */
  double weight(int v, int w);
}
