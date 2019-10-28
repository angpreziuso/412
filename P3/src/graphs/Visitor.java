/*
 * Visitor is used to implement the Visitor pattern for graph traversals.
 * 
 * @author C. Fox
 * @version 10/19
 */
package graphs;

import graphs.Graph;

/**
 * Encapsulate various visitor methods applied to vertices and
 * edges when they are considered in graph traversals.
 */
public abstract class Visitor {

  /**
   * Do initial processing on a graph vertex before its edges
   * to unvisited nodes have been processed.
   * @param g the graph in which this edge reside
   * @param v vertex processed
   */
  public void processNode(Graph g, int v) {}

  /**
   * Do final processing on a graph vertex after its edges
   * to unvisited nodes have been processed.
   * @param g the graph in which this edge reside
   * @param v vertex processed
   */
  public void postProcessNode(Graph g, int v) {}
  
  /**
   * Process an edge between nodes v (visited) and w (unvisited).
   * @param g the graph in which this edge reside
   * @param v source vertex in the edge
   * @param w target vertex in the edge
   */
  public void processEdge(Graph g, int v, int w) {}
  
}
