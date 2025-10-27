import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

//Stack Implementation
//Deque reason, I'm using deque because im curious how implementing it will look.
public class Stack <E> implements Deque<E> {
    private ArrayDeque<E> data;

    /** Contructs a stack and sets necessary attributes to default values.
     * This does the following,
     * sets top to -1,
     * sets capacity to 10,
     * instantiates this.data[] and casts it as a generic object.
     */
    public Stack() {
        this.data = new ArrayDeque<E>();
    }

    /** Pushes an element onto the top of the stack.
     * @param e the element to be pushed
     */
    @Override
    public void push(E e) {
        data.push(e);
    }

    /** Removes and returns the element at the top of the stack.
     * @return the element at the top of the stack
     */
    @Override
    public E pop() {
        return data.pop();
    }

    /** Returns the element at the top of the stack without removing it.
     * @return the element at the top of the stack
     */
    @Override
    public E peek() {
        return data.peek();
    }

    /** Checks if the stack is empty.
     * @return true if the stack is empty, else returns false
     */
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    //NEED THESE SO CLASS ISN'T ABSTRACT
    @Override
    public E peekLast() {
        return null;
    }
    @Override
    public void addLast(E item) {
    }
    @Override
    public E pollLast() {
        return null;
    }
    public int setSize() {
        return 0;
    }
    public boolean isFull() {
        return false;
    }
    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }
    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }
    @Override
    public boolean add(E e) {
        return false;
    }
    @Override
    public boolean offer(E e) {
        return false;
    }
    @Override
    public E remove() {
        return null;
    }
    @Override
    public E poll() {
        return null;
    }
    @Override
    public E element() {
        return null;
    }
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }
    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }
    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }
    @Override
    public void clear() {
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }
    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }
    @Override
    public boolean contains(Object o) {
        return false;
    }
    @Override
    public int size() {
        return 0;
    }
    @Override
    public Iterator<E> iterator() {
        return null;
    }
    @Override
    public Object[] toArray() {
        return new Object[0];
    }
    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }
    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }
    @Override
    public E getFirst() {
        return null;
    }
    @Override
    public E getLast() {
        return null;
    }
    @Override
    public E peekFirst() {
        return null;
    }
    @Override
    public void addFirst(E e) {
    }
    @Override
    public boolean offerFirst(E e) {
        return false;
    }
    @Override
    public boolean offerLast(E e) {
        return false;
    }
    @Override
    public E removeFirst() {
        return null;
    }
    @Override
    public E removeLast() {
        return null;
    }
    @Override
    public E pollFirst() {
        return null;
    }
}