

import java.util.List;

/**
 * Visitor superclass for implementing backtracking algorithms.
 * 
 * @author C. Fox
 * @version 10/19
 *
 * @param <T> type of candidates for making moves
 */
public abstract class Visitor<T> {
  
  /**
   * Determine whether the current state with index k is a solution.
   * @param k the index of the current state (distance from the search tree root)
   * @return true iff the current state is a solution
   */
  public boolean isSolution(int k) { return false; }

  /**
   * Record or count or whatever a solution.
   * @param k index of the current state
   */
  public void processSolution(int k) {}
  
  /**
   * Create a list of candidates for extending the current state
   * towards a solution.
   * @param k index for the current state extension
   * @return list of candidates for extending the current state
   */
  public List<T> generateCandidates(int k) { return null; }
  
  /**
   * Use a candidate to extend the current state at index k.
   * @param candidate an extension to the current state
   * @param k index for the current state extension
   */
  public void makeMove(T candidate, int k) {}
  
  /**
   * Remove a candidate at index k and revert to a previous state.
   * @param candidate which candidate to remove from the state
   * @param k index of the removed candidate
   */
  public void unMakeMove(T candidate, int k) {}
  
  /**
   * Stop considering candidates at index k.
   * @return whether to bail out of considering extensions at index k
   */
  public boolean isFinished() { return false; }
}
