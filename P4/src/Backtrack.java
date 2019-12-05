
import java.util.List;

/**
 * Class with a generic backtracking method that takes instances of
 * a Visitor class to solve particular problems.
 * 
 * @author C. Fox
 * @version 10/19
 */
public class Backtrack {

  /**
   * Generic backtracking algorithm that uses a Visitor to
   * customize its processing.
   * @param k the index of the current state extension
   * @param v the visitor object whose methods are called
   */
  public static <T> void backtrack(int k, Visitor<T> v) {
    if (v.isSolution(k))
      v.processSolution(k);
    else {
      List<T> candidates = v.generateCandidates(k);
      for (T candidate : candidates) {
        v.makeMove(candidate, k);
        backtrack(k+1,v);
        v.unMakeMove(candidate, k);
        if (v.isFinished()) return;
      }
    }
  }
  
}
