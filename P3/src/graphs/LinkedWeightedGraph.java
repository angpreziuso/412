package graphs;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Undirected weighted graphs implemented using an adjacency list.
 * All edges {v,w} have a double weight and are recorded twice in the data
 * structure.
 * 
 * @author C. Fox
 * @version 9/19
 */
public class LinkedWeightedGraph extends AbstractWeightedGraph {
  private List<Node>[] adjacent;  // lists of vertices with edges to w

  /**
   * Make a graph with n nodes and no edges.
   * @param n how many vertices in the graph 
   */
  @SuppressWarnings("unchecked")
  public LinkedWeightedGraph(int n) {
    super(n);
    adjacent = (LinkedList<Node>[]) Array.newInstance(LinkedList.class, nodes);
    for (int i = 0; i < nodes; i++) adjacent[i] = new LinkedList<Node>();
  }

  @Override
  public void setNullWeight(double weight) { nullWeight = weight; }

  @Override
  public void addEdge(int v, int w, double weight)
  {
    if (v < 0 || w < 0 || nodes <= v || nodes <= w || v == w) return;
    if (isEdge(v,w)) return;
    adjacent[v].add(new Node(w, weight));
    adjacent[w].add(new Node(v, weight));
    edges++;
  }

  @Override
  public boolean isEdge(int v, int w) {
    if (v < 0 || w < 0 || nodes <= v || nodes <= w) return false;
    for (Node node : adjacent[v])
      if (node.w == w) return true;
    return false;
  }

  @Override
  public double weight(int v, int w)
  {
    if (v < 0 || w < 0 || nodes <= v || nodes <= w) return nullWeight;
    for (Node node : adjacent[v])
      if (node.w == w) return node.weight;
    return nullWeight;
  }

  @Override
  public Iterator<Integer> edgeIterator(int v) {
    if (v < 0 || nodes <= v) {
      LinkedList<Integer> empty = new LinkedList<>();
      return empty.iterator();
    }
    return new EdgeIterator(v);
  }
  
  @Override
  public String toString() {
    StringBuffer result = new StringBuffer();
    result.append("Vertex count: ").append(nodes).append('\n')
          .append("Edge count: ").append(edges).append('\n');
    for (int v = 0; v < nodes; v++) {
      for (Node node : adjacent[v]) {
        result.append("{").append(v).append(", ")
              .append(node.w).append(", ")
              .append(node.weight).append("} ");
      }
      result.append('\n');
    }
    return result.toString();
  }

  //////////////////////////////////////////////////////////////////////////////
  ///   Private Classes   //////////////////////////////////////////////////////
  
  /**
   * Store the vertex at the far end of an edge and the weight of
   * the edge. This is the data stored in the adjacency lists.
   */
  private class Node {
    public int w;
    public double weight;
    
    public Node(int w, double weight) {
      this.w = w;
      this.weight = weight;
    }
  } // Node
  
  /**
   * Iterate over all the vertices adjacent to a given vertex.
   */
  private class EdgeIterator implements Iterator<Integer> {
    private Iterator<Node> iter;
    
    /**
     * Start an adjecency list node iterator at list v.
     * @param v whose adjacent vertices are traversed
     */
    public EdgeIterator(int v) {
      iter = adjacent[v].iterator();
    }

    @Override
    public boolean hasNext() {
      return iter.hasNext();
    }

    @Override
    public Integer next() {
      return iter.next().w;
    }
  } // EdgeIterator
}
