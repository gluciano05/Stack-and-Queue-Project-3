//coded by Gabriel Luciano
//Reason for arrayList, im using array bc im most comfortable with it.
import java.util.ArrayList;

/** A circular queue implementation using arrayList, is Generic to
 * promote code reusability.
 * @param <E>
 */
public class Queue <E> {
    private ArrayList<E> data;
    private int front;
    private int rear;
    private int capacity;
    private int size;

    /** Constructor for my Queue implementation
     * @param givenCapacity is the given capacity of the queue.
     */
    @SuppressWarnings("unchecked")
    //I explained the reason for the "SuppressWarnings()" on line 7 of stack
    public Queue(int givenCapacity) {
        data = new ArrayList<E>();

        //so we can use the set function.
        for (int i = 0; i < givenCapacity; i++) {
            data.add(null);
        }

        capacity = givenCapacity;
        size = 0;
        front = 0;
        rear = 0;
    }

    /** If the queue is not full, adds the item to the rear of the queue
     * then increments size and moves rear to the next position.
     * @param item item is the given generic parameter for this function.
     * @throws RuntimeException if the queue is full
     */
    public void enqueue(E item) {
        if (isFull()) {
            reallocate();
        }
        data.set(rear, item);
        rear = (rear + 1) % capacity;
        size++;
    }

    /** If the queue is not empty, saves item at the front of the queue
     * into a temporary variable, increments front to the next position,
     * decrements size by 1, then returns temporary variable.
     * @return returns the item at the front of the queue
     * @throws RuntimeException if the queue is empty
     */
    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Cannot dequeue from an empty queue");
        }
        E item = data.get(front);
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    /** Checks if the queue is empty.
     * @return true if the size of the queue equals 0, else returns false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks if the queue is full.
     * @return true if the rear equals the capacity - 1, else returns false
     */
    public boolean isFull() {
        return size == capacity;
    }

    /** doubles the queue's capacity when full. */
    private void reallocate() {
        int newCapacity = 2 * capacity;
        ArrayList<E> newData = new ArrayList<E>();

        for (int i = 0; i < newCapacity; i++) {
            newData.add(null);
        }

        int newIndex = 0;
        for (int i = 0; i < size; i++) {
            int oldIndex = (front + i) % capacity;
            newData.set(newIndex, data.get(oldIndex));
            newIndex++;
        }

        data = newData;
        front = 0;
        rear = size;
        capacity = newCapacity;
    }
}
