import java.util.Set;

/**
 * Recall that a thief has a knapsack with a weight capacity of W. She must
 * choose from a set of items numbered 0 to n-1 to steal and carry away in her
 * knapsack. Each item has a weight and a value. Obviously, she wants to steal
 * the most valuable collection of items she can fit in her knapsack. Thus the
 * goal is to maximize the total value of the items in the knapsack under the
 * constraint that the total weight of the items not exceed W.
 * 
 * References & Acknowledgments: https://www.youtube.com/watch?v=zRza99HPvkQ
 * 
 * @author angelapreziuso
 */
public class Knapsack {
	public static int knapsack(int W, int[] weight, int[] value,
			Set<Integer> solution) {
		int x = weight.length;
		int Weight = W;
		int[][] k = new int[x + 1][Weight + 1];

		if (W == 0) {
			return 0;
		}
		for (int i = 0; i <= x; i++) {
			for (int w = 0; w <= Weight; w++) {
				if (i == 0 || w == 0) {
					k[i][Weight] = 0;
				} else if (weight[i - 1] <= w) {
					k[i][w] = max(value[i - 1] + k[i - 1][w - weight[i - 1]],
							k[i - 1][w]);
				} else {
					k[i][w] = k[i - 1][w];
				}
			}
		}
		if (solution != null) {
			int i = x;
			int j = Weight;
			while (i > 0 && j > 0) {
				if (k[i][j] == k[i - 1][j]) {
					i--;
				} else {
					solution.add(i - 1);
					i--;
					j = j - weight[i];
				}
			}
		}
		return k[x][Weight];
	}
	public static int max(int a, int b) {
		return (a > b) ? a : b;
	}
}
