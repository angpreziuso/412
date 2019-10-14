
import org.junit.Test;

public class HeapsTest {

    @Test
    public void test() {
	System.out.print("\nSort1 Test\n");

	int[] array = { 8, 5, 3, 9, 1, 11, 2, 7, 10, 25, 32, 67, 34, 12, 15,
		13, 85, 94, 91, 21, 89, 44, 46, 43, 67, 74, 71, 83 };
	for (int i = 0; i < array.length; i++) {
	    System.out.print(array[i] + " ");
	}
	Heaps.sort1(array);
	System.out.print("\n");

	for (int i = 0; i < array.length; i++) {
	    System.out.print(array[i] + " ");
	}
	System.out.print("\n");

    }

    @Test
    public void test2() {
	System.out.print("\nSort2 Test\n");

	int[] array = { 8, 5, 3, 9, 1, 11, 2, 7, 10, 25, 32, 67, 34, 12, 15,
		13, 85, 94, 91, 21, 89, 44, 46, 43, 67, 74, 71, 83, 128, 123,
		82, 41, 29 };
	for (int i = 0; i < array.length; i++) {
	    System.out.print(array[i] + " ");
	}
	Heaps.sort2(array);
	System.out.print("\n");

	for (int i = 0; i < array.length; i++) {
	    System.out.print(array[i] + " ");
	}
	System.out.print("\n");

    }

    @Test
    public void test3() {
	System.out.print("\nSort Generics Test\n");

	Integer[] array = { 8, 5, 3, 9, 1, 11, 2, 7, 10, 25, 32, 67, 34, 12,
		15, 13, 85, 94, 91, 21, 89, 44, 46, 43, 67, 74, 71, 83, 128,
		123, 82, 41, 29 };

	for (int i = 0; i < array.length; i++) {
	    System.out.print(array[i] + " ");
	}
	Heaps.sort(array);
	System.out.print("\n");

	for (int i = 0; i < array.length; i++) {
	    System.out.print(array[i] + " ");
	}
    }

}
