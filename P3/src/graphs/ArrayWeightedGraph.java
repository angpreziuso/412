package graphs;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Undirected weighted graphs implemented using an adjacency 
 * Edge weights are recorded as matrix entries. All edges {v,w} and their
 * weights are recorded twice in the matrix.
 * 
 * @author C. Fox
 * @version 9/19
 */
public class ArrayWeightedGraph extends AbstractWeightedGraph {
  private double[][] matrix;   // matrix[v][w] != nullWeight iff {v,w} is an edge

  /**
   * Make a graph with n nodes and no edges.
   * @param n how many vertices in the graph
   */
  public ArrayWeightedGraph(int n) {
    super(n);
    matrix = new double[nodes][nodes];
    for (int i = 0; i < nodes; i++)
      for (int j = 0; j < nodes; j++) matrix[i][j] = nullWeight;
  }
  
  @Override
  public void addEdge(int v, int w, double weight) {
    if (v < 0 || w < 0 || nodes <= v || nodes <= w || v == w) return;
    if (matrix[v][w] != nullWeight) return;
    matrix[v][w] = matrix[w][v] = weight;
    edges++;
  }

  @Override
  public boolean isEdge(int v, int w) {
    if (v < 0 || w < 0 || nodes <= v || nodes <= w) return false;
    return matrix[v][w] != nullWeight;
  }

  @Override
  public void setNullWeight(double weight) {
    for (int v = 0; v < nodes; v++)
      for (int w = 0; w < nodes; w++)
        if (matrix[v][w] == nullWeight) matrix[v][w] = weight;
    nullWeight = weight;
  }

  @Override
  public double weight(int v, int w) {
    if (v < 0 || w < 0 || nodes <= v || nodes <= w) return nullWeight;
    return matrix[v][w];
  }

  @Override
  public Iterator<Integer> edgeIterator(int v) { return new EdgeIterator(v); }

  @Override
  public String toString() {
    StringBuffer result = new StringBuffer();
    result.append("Vertex count: ").append(nodes).append('\n')
          .append("Edge count: ").append(edges).append('\n');
    for (int v = 0; v < nodes; v++) {
      Iterator<Integer> iter = edgeIterator(v);
      while (iter.hasNext()) {
        int w = iter.next();
        result.append("{").append(v).append(", ")
              .append(w).append(", ")
              .append(matrix[v][w]).append("} ");
      }
      result.append('\n');
    }
    return result.toString();
  }

  //////////////////////////////////////////////////////////////////////////////
  ///   Private Classes   //////////////////////////////////////////////////////
  
  /**
   * Return in iterator traversing all the vertices emanating from a vertex.
   */
  private class EdgeIterator implements Iterator<Integer> {
    private int v;
    private int w;
    
    /**
     * Arrange to iterator over all edges from vertex x
     * @param x the vertex involved in the iterated edges
     */
    public EdgeIterator(int x) {
      v = x;
      w = nodes;
      if (v < 0 || nodes <= v) return;
      for (int i = 0; i < nodes; i++) {
        if (matrix[v][i] != nullWeight) {
          w = i;
          break;
        }
      }
    }

    @Override
    public boolean hasNext() { return w < nodes; }

    @Override
    public Integer next() {
      if (nodes <= w) throw new NoSuchElementException();
      int result = w;
      w = nodes;
      for (int i = result+1; i < nodes; i++) {
        if (matrix[v][i] != nullWeight) {
          w = i;
          break;
        }
      }
      return result;
    }
  } // EdgeIterator
  
}
