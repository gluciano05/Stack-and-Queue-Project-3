//Stack Implementation
public class Stack <E> {
    private E[] data;
    private int top;
    private int capacity;
    private int size;

    /** Contructs a stack and sets necessary attributes to default values.
     * This does the following,
     * sets top to -1,
     * sets capacity to 10,
     * instantiates this.data[] and casts it as a generic object.
     */
    @SuppressWarnings("unchecked") //THE COMPILER IS ANNOYING ME WITH THIS WARNING I CANT ANYMORE
    public Stack() {

        /* I am aware I am hard coding but as per the instructions
         * Stacks are limited to 10 hikers per.
         * "As soon as a stack becomes full (has 10 hikers in it)"
         */

        this.data = (E[]) new Object[10];
        this.top = -1;
        this.capacity = 10;
    }

    /** Returns the item at the top of the stack without removing it.
     * @return the item at the top of the stack
     */
    public E peek() {
        return data[top];
    }

    /** Removes and returns the item at the top of the stack.
     * @return the item that was removed from the top of the stack
     * @throws RuntimeException if the stack is empty
     */
    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Cannot pop from empty stack");
        }
        E item = data[top];
        data[top] = null;
        top--;
        return item;
    }

    /** If the stack is not full, increments the top by 1 and sets the tops index equal
     * to the given data
     * @param givenData givenData is the data inputted when calling the function
     * @throws RuntimeException if the stack is full
     */
    public void push(E givenData) {
        if (isFull()) {
            throw new RuntimeException("Cannot push to a full stack");
        }
        top++;
        data[top] = givenData;
    }

    /** Checks if the stack is empty.
     * @return true if the top equals - 1, else returns false
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /** Checks if the stack is full.
     * @return true if the top equals the capacity - 1, else returns false
     */
    public boolean isFull() {
        return top == (capacity - 1);
    }

    /** A method to get the current size of the stack
     * @return returns the tops index + 1
     */
    public int setSize() {
        return top + 1;
    }
}
