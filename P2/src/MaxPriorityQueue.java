
/**
 * Interface for a max-priority queue (items with the highest priority come out first).
 * Items with equal priority come out at random.
 * @author C. Fox
 * @version 9/2019
 * @param <T> the type of the items stored in the queue
 */
public interface MaxPriorityQueue<T> {

  /**
   * How many items are in the queue.
   * @return 0..capacity
   */
  int size();
  
  /**
   * How many slots are available in the queue.
   * @return 0..
   */
  int capacity();
  
  /**
   * Put an item into the priority queue with the given priority.
   * @param value a class instance of some kind
   * @param priority the priority of the instance
   * @throws IllegalStateException when the queue is full (size == capacity).
   */
  void insert(T value, int priority);
  
  /**
   * An item in the queue with the highest priority. Note that if several
   * items have the same priority, it is not clear which will be first.
   * @return item with the highest priority in the queue
   * @throws IllegalStateException when the queue is empty.
   */
  T getMax();
  
  /**
   * Delete and return an item in the queue with the highest priority.
   * Note that if several items have the same priority, it is not clear
   * which will emerge first.
   * @return item with the highest priority in the queue
   * @throws IllegalStateException when the queue is empty.
   */
  T delete();
}
