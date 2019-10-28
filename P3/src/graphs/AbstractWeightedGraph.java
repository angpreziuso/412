package graphs;

/**
 * Common weighted graphs implementation.
 * Vertices are numbers 0 to n-1. Edge weights are double values.
 * 
 * @author C. Fox
 * @version 9/19
 */
public abstract class AbstractWeightedGraph extends AbstractGraph implements WeightedGraph {
  protected double nullWeight;   // weight indicating a non-edge in the graph

  /**
   * Make a graph with n nodes and no edges.
   * @param n how many vertices in the graph
   */
  public AbstractWeightedGraph(int n) {
    super(n);
    nullWeight = NULL_WEIGHT;
  }
  
  /**
   * An edge without a weight cannot be added, so throw an exception
   * if this method is called.
   */
  @Override
  public void addEdge(int v, int w) {
    throw new UnsupportedOperationException("Weighted graph edges must have weights");
  }

}
