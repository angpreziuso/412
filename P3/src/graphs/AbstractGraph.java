package graphs;

import java.util.Iterator;

/**
 * Features common to directed and undirected unweighted graphs no matter how implemented.
 * Vertices are numbers 0 to n-1.
 * 
 * @author C. Fox
 * @version 9/19
 */
public abstract class AbstractGraph implements Graph {
  protected int nodes;   // vertex count
  protected int edges;   // edge count

  /**
   * Make a graph with n nodes and no edges.
   * @param n how many vertices in the graph
   */
  public AbstractGraph(int n) {
    if (n < 0)
      throw new IllegalArgumentException("Graphs cannot have negative sizes");
    nodes = n;
    edges = 0;
  }

  @Override
  public boolean isDigraph() { return false; }

  @Override
  public int edges() { return edges; }

  @Override
  public int vertices() { return nodes; }

  @Override
  public Iterator<Integer> iterator() { return new VertexIterator(nodes); }

  @Override
  public String toString() {
    StringBuffer result = new StringBuffer();
    result.append("Vertex count: ").append(nodes).append('\n')
          .append("Edge count: ").append(edges).append('\n');
    for (int v = 0; v < nodes; v++) {
      Iterator<Integer> iter = edgeIterator(v);
      while (iter.hasNext()) {
        int w = iter.next();
        result.append("{").append(v).append(", ").append(w).append("} ");
      }
      result.append('\n');
    }
    return result.toString();
  }

  //////////////////////////////////////////////////////////////////////////////
  ///   Private Classes   //////////////////////////////////////////////////////
  
  /**
   * Return an iterator over all the vertices in the graph (numbers 0..(n-1)).
   */
  private class VertexIterator implements Iterator<Integer> {
    private int v;   // the current vertex during iteration
    private int n;   // how many vertices in the graph
    
    /**
     * Initialize private fields.
     * @param n how many vertices in the graph
     */
    public VertexIterator(int n) {
      this.n = n;
      v = 0;
    }

    @Override
    public boolean hasNext() { return v < n; }

    @Override
    public Integer next() { return v++; }
  }

} // AbstractGraph
