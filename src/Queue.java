//Queue implementation
public class Queue <E> {
    private E[] data;
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
        data = (E[]) new Object[givenCapacity];
        capacity = givenCapacity;
        size = 0;
        front = 0;
        rear = 0;
    }

    /** If the queue is not full, adds the item to the rear of the queue
     * then increments size and rear by 1.
     * @param item item is the given generic parameter for this function.
     * @throws RuntimeException if the queue is full
     */
    public void enqueue(E item) {
        if (isFull()) {
            throw new RuntimeException("Cannot enqueue to a full queue");
        }
        data[rear] = item;
        rear++;
        size++;
    }

    /** If the queue is not empty, saves item at the front of the queue
     * into a temporary variable, increments the front by 1 and decrements size by 1
     * then, returns the temporary variable.
     * @return returns the item at the front of the queue
     * @throws RuntimeException if the queue is empty
     */
    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Cannot dequeue from an empty queue");
        }
        E item = data[front];
        front++;
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
        return rear == capacity - 1;
    }
}
