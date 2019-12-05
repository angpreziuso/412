import java.util.Collections;
import java.util.List;

/**
 * 
 * This method must return the maximum play on the board given as the first
 * parameter. you may assume the array ins not null. If the array is empty, then
 * it must return 0 and do nothing else. If the second parameter is not null, it
 * must return the indices of the play generating the maximumum score, in order
 * 
 * @author angela preziuso
 *
 */
public class Hopscotch {
	public static int hopscotch(int[] array, List<Integer> solution) {
		int maxScore = 0; // Max score
		int index = 0; // Index of the array
		int n = array.length; // Size of array

		int[] table = new int[n];
		int[] score = new int[n];

		// If the array is empty then return 0
		if (n == 0) {
			return 0;
			// If the array has 1 or 2 items then add 0 to solution and then
			// return the first element in
			// the array
		} else if (n <= 2) {
			if (solution != null) {
				solution.add(0);
			}
			return array[0];
		}

		// Initialize all the arrays to 0
		for (int i = 0; i < n; i++) {
			table[i] = 0;
			score[i] = 0;
		}

		table[0] = array[0];
		score[0] = 0;

		// Make the table of the largest possibility given each index
		for (int i = 0; i < n - 2; i++) {
			if (i == 1) {
				continue;
			}
			if (table[i + 2] < table[i] + array[i + 2]) {
				table[i + 2] = table[i] + array[i + 2];
				score[i + 2] = i;
			}
			if (i < n - 3) {
				if (table[i + 3] <= 0) {
					int dblExtra = 2 * array[i + 3]; // Make sure you double the
														// given position
					table[i + 3] = table[i] + dblExtra;
					score[i + 3] = i;
				}
			}
		}

		if (table[n - 1] > table[n - 2]) {
			index = n - 1;
			maxScore = table[n - 1];
		} else {
			index = n - 2;
			maxScore = table[n - 2];
		}

		if (solution != null) {
			int i = index;
			while (i != 0) {
				solution.add(i);
				i = score[i];
			}
			// Always add 0 to the array
			solution.add(score[0]);
			// Reverse so the list going in ascending order
			Collections.reverse(solution);
		}

		return maxScore;

	}

	public static void main(String[] args) {
		int[] array = {7, 2, 4, 3, 2, 9, 6};
		List<Integer> sol = null;

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.print("\n");
		System.out.println(hopscotch(array, sol));
	}
}
