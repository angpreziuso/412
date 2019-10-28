package graphs;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Graphs contains static public utility methods for traversing directed
 * and undirected graphs and determining various of their properties.
 * 
 * Methods for all graphs (undirected and directed):
 *   DFS()--recursive depth-first search with visitor
 *   DFS()--recursive depth-first search for non-connected graph with visitor
 *   stackDFS()--stack-based depth-first search with visitor
 *   BFS()--queue-based breadth-first search with visitor
 *   BFS()--queue-based breadth-first search for non-connected graph with visitor
 *  
 * @author C. Fox
 * @version 10/19
 */
public class Graphs {

  /**
   * Traversals
   * 
   * The depth-first and breadth-first algorithms below use a Visitor to
   * customize processing in various ways. The Visitor methods are called
   * based on stages of processing.
   *     
   * The node from which a search begins is immediately found and placed
   * in a stack or queue. When a node comes to the front of the queue or the
   * top of the stack, then its processing begins. When processing begins,
   * the Visitor processNode() method is called. After this the node's edges
   * are considered. Every edge is checked, and when a new edge is found,
   * the Visitor processEdge() method is called for the edge. As edges are
   * traversed, any newly discovered node is marked as found and put in the
   * stack or queue. Front or top nodes continue to be processed. Eventually,
   * all the edges incident to the node at the front of the queue or the top
   * of the stack are considered. At this point processing for the node is
   * complete. The Visitor postProcess() method is called, and the node is
   * removed from the queue or stack. This continues until the queue or the
   * stack is empty.
   */
  
  /**
   * Perform a queue-based breadth-first search from a node applying a
   * Visitor to the nodes and edges of a graph.
   * @param g the graph in which the vertices abide
   * @param u the start vertex for the search
   * @param visitor the class whose processing methods are applied
   */
  public static void BFS(Graph g, int u, Visitor visitor) {
    boolean[] isFound     = new boolean[g.vertices()];
    boolean[] isProcessed = new boolean[g.vertices()];
    Queue<Integer> queue  = new LinkedList<>();
    
    queue.add(u);
    isFound[u] = true;
    while (!queue.isEmpty()) {
      int v = queue.remove();
      visitor.processNode(g,v);
      Iterator<Integer> iter = g.edgeIterator(v);
      while (iter.hasNext()) {
        int w = iter.next();
        if (isProcessed[w] && !g.isDigraph()) continue;
        visitor.processEdge(g,v,w);
        if (isFound[w]) continue;
        queue.add(w);
        isFound[w] = true;
      }
      visitor.postProcessNode(g,v);
      isProcessed[v] = true;
    }
  }
  
  /**
   * Perform a queue-based breadth-first search applying a Visitor
   * to the vertices and edges of the graph during the search. This
   * method applies the visitor to all nodes and edges, even if
   * the graph is not connected.
   * @param g the graph in which the vertices abide
   * @param visitor the class whose processing methods are applied
   */
  public static void BFS(Graph g, Visitor visitor) {
    boolean[] isFound     = new boolean[g.vertices()];
    boolean[] isProcessed = new boolean[g.vertices()];
    Queue<Integer> queue  = new LinkedList<>();

    for (int u : g)
      if (!isProcessed[u]) {
        queue.add(u);
        isFound[u] = true;
        while (!queue.isEmpty()) {
          int v = queue.remove();
          visitor.processNode(g,v);
          Iterator<Integer> iter = g.edgeIterator(v);
          while (iter.hasNext()) {
            int w = iter.next();
            if (isProcessed[w] && !g.isDigraph()) continue;
            visitor.processEdge(g,v,w);
            if (isFound[w]) continue;
            queue.add(w);
            isFound[w] = true;
          }
          visitor.postProcessNode(g,v);
          isProcessed[v] = true;
        }
      }
  }

  /**
   * Perform a recursive depth-first search from a node applying a Visitor
   * to the vertices and edges of the graph during the search.
   * @param g the graph in which the vertices abide
   * @param v the start vertex for the search
   * @param visitor the class whose processing methods are applied
   */
  public static void DFS(Graph g, int v, Visitor visitor) {
    boolean[] isFound     = new boolean[g.vertices()];
    boolean[] isProcessed = new boolean[g.vertices()];
    dfs(g, -1, v, visitor, isFound, isProcessed);
  }

  /**
   * Perform a recursive depth-first search applying a Visitor to
   * the vertices and edges of the graph during the search. This
   * method applies the visitor to all nodes and edges, even if
   * the graph is not connected.
   * @param g the graph in which the vertices abide
   * @param visitor the class whose processing methods are applied
   */
  public static void DFS(Graph g, Visitor visitor) {
    boolean[] isFound     = new boolean[g.vertices()];
    boolean[] isProcessed = new boolean[g.vertices()];
    for (int v : g) {
      if (!isProcessed[v])
        dfs(g, -1, v, visitor, isFound, isProcessed);
    }
  }

  /**
   * Perform a stack-based depth-first search applying a Visitor
   * to the nodes and edges in a graph.
   * Note that this algorithm exactly mimics recursive algorithm
   * and so it really just an exercise in conversion from recursive
   * to stack-based algorithms. The recursive code is called in all
   * the applications below.
   * @param g the graph in which the vertices abide
   * @param u the start vertex for the search
   * @param visitor the class whose processing methods are applied
   */
  @SuppressWarnings("unchecked")
  public static void stackDFS(Graph g, int u, Visitor visitor) {
    boolean[] isFound        = new boolean[g.vertices()];
    boolean[] isProcessed    = new boolean[g.vertices()];
    Stack<Integer> stack     = new Stack<>();
    int[] parent             = new int[g.vertices()];
    Iterator<Integer>[] iter =
       (Iterator<Integer>[]) Array.newInstance(Iterator.class, g.vertices());

    // initialize parent array--the others are fine with the default
    for (int i = 0; i < parent.length; i++) parent[i] = -1;
    
    stack.push(u);
    isFound[u] = true;
    while (!stack.isEmpty()) {
      int v = stack.peek();
      if (iter[v] == null) {
        visitor.processNode(g,v);
        iter[v] = g.edgeIterator(v);
      }
      if (iter[v].hasNext()) {
        int w = iter[v].next();
        if (g.isDigraph() || (w != parent[v] && !isProcessed[w])) {
          visitor.processEdge(g,v,w);
          if (parent[w] == -1) parent[w] = v;
          if (!isFound[w]) {
            stack.push(w);
            isFound[w] = true;
          }
        }
      }
      else {
        visitor.postProcessNode(g,v);
        isProcessed[v] = true;
        stack.pop();
      }
    }
  }
  
  ////////////////////////////////////////////////////////////////////////////
  ////   Private Methods and Classes   ///////////////////////////////////////
  
  /**
   * Do a recursive depth-first search; this is the method that is called
   * by DFS to do the real work.
   * @param g the graph examined
   * @param v the node from which to proceed
   * @param visitor class with the visit methods applied during the search
   * @param isFound track which vertices have been discovered so far
   * @param isProcessed tracks which vertices have been completely visited
   */
  private static void dfs(Graph g, int p, int v, Visitor visitor,
                          boolean[] isFound, boolean[] isProcessed) {
    isFound[v] = true;
    visitor.processNode(g,v);
    Iterator<Integer> iter = g.edgeIterator(v);
    while (iter.hasNext()) {
      int w = iter.next();
      if ((isProcessed[w] || w == p) && !g.isDigraph()) continue;
      visitor.processEdge(g,v,w);
      if (isFound[w]) continue;
      dfs(g, v, w, visitor, isFound, isProcessed);
    }
    visitor.postProcessNode(g,v);
    isProcessed[v] = true;
  }

} // Graphs
