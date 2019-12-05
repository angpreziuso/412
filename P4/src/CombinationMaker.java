import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Creates a Set of Combinations.
 * 
 * @author angela preziuso
 */
public class CombinationMaker extends Visitor<Integer> {
	public int size; // size of set
	public int chooseK;
	public Set<Integer> combos;
	public Set<Integer> nums;
	public Set<Set<Integer>> solutionSet; // store all solutions

	/**
	 * Initializing the original values to variables.
	 * 
	 * @param k
	 *            - how many elements can be in the ComboSet
	 * @param set
	 *            - set of numbers that is being put into combinations
	 */
	public CombinationMaker(int k, Set<Integer> set) {
		if (k < 0 || set.size() < k || set.isEmpty()) {
			size = 1;
		} else {
			size = set.size();
		}
		chooseK = k;
		nums = set;
		combos = new HashSet<>();
		solutionSet = new HashSet<>();
	}

	/**
	 * Checking if the combo is a solution.
	 * 
	 * @param k
	 *            - index of the current state
	 */
	@Override
	public boolean isSolution(int k) {
		return (chooseK == combos.size());
	}

	/**
	 * Record or count or whatever a solution.
	 * 
	 * @param k
	 *            index of the current state
	 */
	@Override
	public void processSolution(int k) {
		if (size == 0) {
			return;
		}
		solutionSet.add(new HashSet<Integer>(combos));
	}

	/**
	 * Create a list of candidates for extending the current state towards a
	 * solution.
	 * 
	 * @param k
	 *            index for the current state extension
	 * @return list of candidates for extending the current state
	 */
	@Override
	public List<Integer> generateCandidates(int k) {
		List<Integer> candidate = new ArrayList<>();

		for (Integer i : nums) {
			if (!combos.contains(i)) {
				candidate.add(i);
			}

			if (isSolution(k)) {
				break;
			}
		}

		return candidate;
	}

	/**
	 * Use a candidate to extend the current state at index k.
	 * 
	 * @param candidate
	 *            an extension to the current state
	 * @param k
	 *            index for the current state extension
	 */
	@Override
	public void makeMove(Integer candidate, int k) {
		combos.add(candidate);
	}

	/**
	 * Remove a candidate at index k and revert to a previous state.
	 * 
	 * @param candidate
	 *            which candidate to remove from the state
	 * @param k
	 *            index of the removed candidate
	 */
	@Override
	public void unMakeMove(Integer candidate, int k) {
		combos.remove(candidate);
	}

	/**
	 * Stop considering candidates at index k.
	 * 
	 * @return whether to bail out of considering extensions at index k
	 */
	@Override
	public boolean isFinished() {
		return (chooseK == size);
	}

	/**
	 * Returns the solutionSet.
	 * 
	 * @return solutionSet
	 */
	public Set<Set<Integer>> result() {
		return solutionSet;
	}
}
