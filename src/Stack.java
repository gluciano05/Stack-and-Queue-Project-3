import java.util.Collection;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.Iterator;

//Stack Implementation
//Deque reason, I'm using deque because im curious how implementing it will look.
public class Stack <E> implements Deque<E> {
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

    @Override
    public E peekLast() {
        return data[top];
    }

    @Override
    public void addLast(E item) {
        if (isFull()) {
            throw new IllegalStateException();
        }
        top++;
        data[top] = item;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E temp = data[top];
        data[top] = null;
        top--;
        return temp;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == (capacity - 1);
    }

    public int setSize() {
        return top + 1;
    }

    //NEED THESE SO CLASS ISN'T ABSTRACT
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
    public E peek() {
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
    public void push(E e) {
    }
    @Override
    public E pop() {
        return null;
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