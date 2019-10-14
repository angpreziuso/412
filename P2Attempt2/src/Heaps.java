import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * CS412 PA2: Heaps
 * 
 * @author angela preziuso
 */

/**
 * References and Acknowledgements:
 * 
 * References:
 * https://www.geeksforgeeks.org/implement-priorityqueue-comparator-java/
 * https://github.com/hemanth/algorithms/blob/master/sorting/java/Heap.java
 * http://people.fas.harvard.edu/~libs111/code/heaps/Heap.java
 * 
 * 
 * Acknowledgements: Discussed with Will Dickison and Chris Dusci regarding
 * logic for sort1() & sort().
 * 
 * Ran against HeapsTest.java that can be requested.
 **/
public class Heaps {

    private static int[] nums; // Array for sort1()
    private static int[] nums2; // Array for sort2()
    private static int elements; // Number of elements in the array

    /**
     * removeMaxElement() takes in an array and swaps the first and last and
     * then reheapifies the array.
     * 
     * @param array
     *            - given array you are trying to sort
     * @return removedEl
     */
    public static int removeMaxElement(int[] array) {
	int removedEl = array[0];

	array[0] = array[elements - 1];
	elements--;
	siftDown(array, 0); // Use siftDown to reheapify

	return removedEl;

    }

    /**
     * Sort1 heapifies the given array, then sorts the contents starting from
     * the bottom elements and works it's way up to the root. After swapping the
     * parent with the given index it has to reheapify the array while sorting.
     * 
     * @param array
     *            - given array
     */
    public static void sort1(int[] array) {
	elements = array.length;
	nums = new int[elements];

	int curr = 0;
	int last = elements - 1;

	for (int i = 0; i < elements; i++) {
	    nums[i] = array[i];
	    siftUp(curr, nums);
	    curr++;
	}
	for (int i = last; i >= 0; i--) {
	    array[last] = removeMaxElement(nums);
	    last--;
	}
    }

    /**
     * Sort2 heapifies the given array, then sorts the contents starting from
     * the top element and works it's way down to the leaves. In order to do
     * this it takes the max element of given heap and adds it to the end of the
     * array. It then decrements it to keep that element locked in the ending
     * position of the array.
     *
     * @param array
     *            - given array
     */
    public static void sort2(int[] array) {
	nums2 = array;
	elements = array.length;

	int last = elements - 1;
	int lastParent = (last - 1) / 2;

	for (int i = lastParent; i >= 0; i--) {
	    siftDown(nums2, i);
	}

	for (int i = last; i >= 0; i--) {

	    array[last] = removeMaxElement(nums2);
	    last--;
	}
    }

    /**
     * siftDown takes a given array and checks if there are no children, if the
     * given node is the max, or which is the greatest element in order to
     * heapify the array from the top (root node) to the leaves.
     * 
     * @param array
     *            - given array trying to sift through
     * @param start
     *            - index node
     * @param end
     *            - last element in the array
     */
    public static void siftDown(int[] array, int start) {
	int index = array[start];
	int parent = start;
	int child = 2 * parent + 1; // Getting left child

	while (child < elements) {
	    if (child < elements - 1 && array[child] < array[child + 1]) {
		child = child + 1; // Looking at right child
	    }
	    if (index >= array[child]) { // if already the max
		break;
	    }
	    array[parent] = array[child];
	    parent = child;
	    child = 2 * parent + 1;
	}

	array[parent] = index;

    }

    /**
     * siftUp takes a given array and checks if the give node has a parent, if
     * the given node is the max, or which is the greatest element in order to
     * heapify the array from the top (root node) to the leaves.
     * 
     * @param index
     *            - current node
     * @param array
     *            - given array
     */
    private static void siftUp(int index, int[] array) {
	int parent = (int) Math.floor((index - 1) / 2);

	if (index > 0 && array[parent] < array[index]) { //Comparisons
	    //Swap
	    int temp = array[index];
	    array[index] = array[parent];
	    array[parent] = temp;

	    siftUp(parent, array);
	}
    }

    /**
     * Helper function that swaps the two elements in the array.
     * 
     * @param a
     *            - first element that needs to be swapped with b.
     * @param b
     *            - second element that needs to be swapped with a.
     * @param array
     *            - given array that contains elements a & b.
     */
    public static void swap(int a, int b, int[] array) {
	int temp = array[a];
	array[a] = array[b];
	array[b] = temp;
    }

    /**
     * Helper function that swaps the two elements in the array of generics.
     * 
     * @param a
     *            - first element that needs to be swapped with b.
     * @param b
     *            - second element that needs to be swapped with a.
     * @param array
     *            - given array that contains elements a & b.
     */
    public static <T> void swap(int a, int b, T[] array) {
	T temp = array[a];
	array[a] = array[b];
	array[b] = temp;
    }

    /**
     * sort() takes a generic array and heapifies it, then sorts it. If the
     * start element is the max siftDown. Same idea as siftDown for the other
     * sort method, but with generics.
     * 
     * @param array
     *            - given array
     */
    public static <T extends Comparable<T>> void sort(T[] array) {
	int length = array.length;
	int last = length - 1;
	int start = (length - 2) / 2;

	while (start >= 0) {
	    siftDown(start, last, array);
	    start--;
	}

	while (last > 0) {
	    //Swap
	    T temp = array[last];
	    array[last] = array[0];
	    array[0] = temp;
	    siftDown(0, last - 1, array); //Heapify
	    last--;
	}

    }

    /**
     * siftDown takes a given array of generics and implements the same method
     * as siftDown above(without generics). It compares against the children: if
     * the given node is the max, or which is the greatest element in order to
     * heapify the array from the top (root node) to the leaves.
     * 
     * @param index
     *            - current node we are checking/working with
     * @param array
     *            - given array
     * 
     */
    private static <T extends Comparable<T>> void siftDown(int index,
	    int maxIndex, T[] array) {
	int root = index;
	while ((root * 2 + 1) <= maxIndex) {// Test if there's at least one
					    // child
	    int child = root * 2 + 1; // Gets left child
	    if (child + 1 <= maxIndex
		    && array[child].compareTo(array[child + 1]) < 0) {
		child = child + 1; // Checks right child instead
	    }

	    if (array[root].compareTo(array[child]) < 0) {
		T temp = array[root];
		array[root] = array[child];
		array[child] = temp;
		// Thought process of recursion for the child becoming the root
		// then checking their children
		root = child;
	    } else {
		return;
	    }
	}
    }

    /**
     * Creates a maxPriorityQueue.
     * 
     * @param capacity
     *            - total available space in the array
     * @return new priority queue
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> MaxPriorityQueue<T> maxPriorityQueue(int capacity) {
	// Exception thrown if capacity is less than one.
	if (capacity < 1) {
	    throw new IllegalArgumentException();
	}

	return (new Heaps()).new PriorityQueue(capacity);
    }

    private class PriorityQueue<T> implements MaxPriorityQueue<T> {
	int size;
	int capacity;
	QueueRecord[] pqueue;

	@SuppressWarnings("unchecked")
	public PriorityQueue(int capacity) {
	    this.capacity = capacity;
	    size = 0;
	    pqueue = (QueueRecord[]) Array.newInstance(QueueRecord.class,
		    capacity);
	}

	@Override
	public int size() {
	    return size;
	}

	@Override
	public int capacity() {
	    return capacity;
	}

	@Override
	public void insert(T value, int priority) {
	    if (capacity > 0) {
		pqueue[size] = new QueueRecord(priority, value);
		siftUp(size);
		// Have to increment size because if you're adding to the pqueue
		// causing it to grow, while the capacity decrements.
		size++;
		// Have to decrement capacity because if you're adding to the
		// pqueue you're making less space available.
		capacity--;
	    }
	}

	/**
	 * siftUp used for sorting as you insert the node with a value and
	 * priority into the priorityQueue. If the index is greater than the
	 * parent swap it and siftUp to reheapify.
	 * 
	 * @param index
	 */
	private void siftUp(int index) {
	    // Index of the parent in the array from formula given in class
	    int parent = (int) Math.floor((index - 1) / 2);

	    if (index > 0 && pqueue[parent].compareTo(pqueue[index]) < 0) {
		QueueRecord t = pqueue[index];
		pqueue[index] = pqueue[parent];
		pqueue[parent] = t;
		siftUp(parent);
	    }
	}

	@Override
	public T getMax() {
	    return pqueue[0].value;
	}

	@Override
	public T delete() {
	    if (size == 0) {
		throw new NoSuchElementException();
	    }

	    T topPriority = pqueue[0].value; // Get the VALUE of the first
					     // element
	    pqueue[0] = pqueue[size - 1]; // and change it to the last element
					  // of the pqueue.
	    // Have to decrement size because if you're deleting an element of
	    // the pqueue causing the size to go down, while the capacity
	    // increments because there's more available space.
	    size--;
	    // Have to increment capacity because if you're subtracting to the
	    // pqueue size you're making more space available.
	    capacity++;
	    Heaps.siftDown(0, size, pqueue);

	    return topPriority;
	}

	private class QueueRecord implements Comparable<QueueRecord> {
	    int priority;
	    T value;

	    public QueueRecord(int priority, T value) {
		this.priority = priority;
		this.value = value;
	    }

	    @Override
	    public int compareTo(QueueRecord o) {
		if (this.priority > o.priority) {
		    return 1;
		} else if (this.priority == o.priority) {
		    return 0;
		} else {
		    return -1;
		}
	    }
	}

    }

    public static void main(String[] args) {
	System.out.println(
		" Array Size\tUsing SiftUp\tUsing SiftDown\tWith Generics\t");

	int vals = 100000;
	for (int i = 0; i < 7; i++) {
	    System.out.print(vals);

	    int[] sort1 = new int[vals];
	    int[] sort2 = new int[vals];

	    Integer[] sort = new Integer[vals];

	    for (int j = 0; j < vals; j++) {
		int r = (int) (Math.random() * Integer.MAX_VALUE + 1);

		sort1[j] = r;
		sort2[j] = r;
		sort[j] = r;
	    }

	    // Sort1() times
	    long start1 = System.currentTimeMillis();
	    sort1(sort1);
	    long end1 = System.currentTimeMillis();
	    long total1 = end1 - start1;
	    float returnTime1 = (float) ((float) total1 * .001);

	    // Sort2() times
	    long start2 = System.currentTimeMillis();
	    sort1(sort2);
	    long end2 = System.currentTimeMillis();
	    long total2 = end2 - start2;
	    float returnTime2 = (float) ((float) total2 * .001);

	    // Sort() times
	    long start = System.currentTimeMillis();
	    sort(sort);
	    long end = System.currentTimeMillis();
	    long total = end - start;
	    float returnTime = (float) ((float) total * .001);

	    vals = vals * 2;

	    System.out.print("\t\t" + returnTime1 + "\t\t" + returnTime2
		    + "\t\t" + returnTime);
	    System.out.print("\n");
	}
    }

}
