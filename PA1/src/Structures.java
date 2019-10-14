import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * CS412 PA1 Structures
 * 
 * @author angela preziuso
 * @param <T>
 */

/**
 * References and Acknowledgments:
 * 
 * https://www.baeldung.com/java-combinations-algorithm
 * https://www.programcreek.com/2013/02/leetcode-permutations-java/
 * https://javarevisited.blogspot.com/2015/08/how-to-find-all-permutations-of-
 * string-java-example.html
 * 
 * Discussed with Chris Dusci about logic regarding how to find permutations.
 */

public class Structures<T> {
    /**
     * Returns a set of all the subsets of the given set of numbers.
     * 
     * @param set
     *            - Given set of numbers.
     * @return powerSet results
     */
    public Set<Set<T>> powerSet(Set<T> set) {
	Set<Set<T>> results = new HashSet<Set<T>>();
	int size = set.size();

	if (set.isEmpty()) { // Checks if the set is empty and creates the empty
			     // set.
	    results.add(new HashSet<T>());
	    return results;
	} else {
	    List<T> list = new ArrayList<T>(set);
	    T top = list.get(0);

	    Set<T> oneToEnd = new HashSet<T>(list.subList(1, size));
	    // checking the rest of the
	    // list minus the first element
	    for (Set<T> s : powerSet(oneToEnd)) {
		Set<T> r = new HashSet<T>();
		r.addAll(s);
		r.add(top);
		results.add(s); // Add the individual sets to the (main) results
				// set.
		results.add(r);
	    }
	}
	return results;
    }

    /**
     * This method gets all the k numbered combinations of the set of numbers
     * given.
     * 
     * @param k
     *            - amount of numbers wanted to make the combo
     * @param set
     *            - given set of numbers
     * @return combos - set of set of k-combinations of numbers in the set
     */
    public Set<Set<T>> choose(int k, Set<T> set) {
	Set<Set<T>> combos = new HashSet<Set<T>>();

	if (k == 0 || set.isEmpty() || k > set.size()) {
	    combos.add(new HashSet<T>()); // Check for empty set and ranges
	}
	if (k == set.size()) {
	    combos.add(new HashSet<T>());
	}

	List<T> setList = new ArrayList<T>(set);
	List<Set<T>> list = new ArrayList<>();

	combos(setList, k, 0, new HashSet<T>(), list); // Helper function

	return new HashSet<Set<T>>(list);

    }

    /**
     * Helper method to actually get/make the combinations.
     * 
     * @param set
     *            - set of numbers given to run combinations against.
     * @param k
     *            - number of numbers making up each subset.
     * @param index
     *            - index of set
     * @param current
     *            - current number
     * @param combinations
     *            - list of combinations
     */
    private void combos(List<T> set, int k, int index, Set<T> current,
	    List<Set<T>> combinations) {
	if (k == current.size()) {
	    combinations.add(new HashSet<>(current));
	    return;
	} else if (index == set.size()) {
	    return;
	} else {
	    T i = set.get(index);
	    current.add(i);
	    combos(set, k, index + 1, current, combinations);
	    current.remove(i);
	    combos(set, k, index + 1, current, combinations);
	}
    }

    /**
     * Finding all the k-permutations given a set of numbers.
     * 
     * @param k
     *            - number of numbers making up each subset.
     * @param set
     *            - set of numbers given.
     * @return perms - Set of list of permutations.
     */
    public Set<List<T>> permute(int k, Set<T> set) {
	Set<List<T>> perms = new HashSet<>();
	List<T> list = new ArrayList<T>(set);

	// Checking the ranges and making sure it's not empty.
	if (set.size() == 0 || set.isEmpty() || k < 0 || k > set.size()) {
	    return new HashSet<List<T>>();
	}

	List<List<T>> permutations = permutation(list);
	Set<List<T>> temp = new HashSet<List<T>>();
	for (List<T> l : permutations) {
	    temp.add(l);
	}

	for (List<T> l : temp) {
	    int size = l.size(); // Have to declare size outside of for loop
				 // because it changes in for each loop.
	    for (int i = k; i < size; i++) {
		l.remove(k);
	    }
	}

	for (List<T> l : temp) {
	    perms.add(l);// Adding the T elements to the main perms List.
	}

	return perms;
    }

    /**
     * Helper function that get/makes permutations of given set based on number
     * of elements (k).
     * 
     * @param setList
     *            - List of the set
     * @return list - list of the possible permutations
     */
    private List<List<T>> permutation(List<T> setList) {
	// Returning the empty set if the set is empty.
	if (setList.size() == 0) {
	    List<List<T>> emptyList = new ArrayList<List<T>>();
	    emptyList.add(new ArrayList<T>());

	    return emptyList;
	}

	List<List<T>> list = new ArrayList<List<T>>();
	T top = setList.remove(0); // Removes the first element

	// Retrieving and generating the permutations of the set.
	for (List<T> l : permutation(setList)) {
	    for (int i = 0; i <= l.size(); i++) {
		List<T> temp = new ArrayList<T>(l);
		temp.add(i, top);
		list.add(temp);
	    }
	}
	return list;
    }

    /**
     * Runs the program and tests the output.
     * 
     * @param args
     */
    public static void main(String[] args) {
	Set<Integer> set = new HashSet<>();
	set.add(0);
	set.add(1);
	set.add(2);

	set.add(5); // Checking to see if adding a number would work.

	Structures<Integer> s = new Structures<>();
	System.out.println(s.powerSet(set));
	for (int i = 0; i <= set.size(); i++)
	    System.out.println(s.choose(i, set));
	for (int i = 0; i <= set.size(); i++)
	    System.out.println(s.permute(i, set));
    }
}
