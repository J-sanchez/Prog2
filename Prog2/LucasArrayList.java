package Prog2;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author PUT YOUR NAME HERE
 *
 */

public class LucasArrayList<E> {
    // Data/Instance Fields of the ArrayList

    /** The default initial capacity */
    private static final int INITIAL_CAPACITY = 4;

    /** The underlying data array */
    private E[] theData;

    /** The current size */
    private int size;

    /**Construct an empty List with the default initial capacity */
    public LucasArrayList() {
        size = 0;
        theData = (E[]) new Object[INITIAL_CAPACITY];
    }

    public LucasArrayList(int initialCapacity) {
        if (initialCapacity < 1)
            throw new IllegalArgumentException("List capcacity must be positive");
        size = 0;
        theData = (E[]) new Object[initialCapacity];
    }

    /**Get a value in the array based on its index.
     * @param index The index of the item desired
     * @return The contents of the array at that index
     * @throws IndexOutOfBoundsException - if the index
     *         is negative or if it is greater than or equal to the
     *         current size
     */
    public E get(int index) {

        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index);

        return theData[index];
    }

    /**Set the value in the array based on the given index.
     * @param index The index of the item to be altered
     * @param newValue The new value to store at this position
     * @return The old value at this position
     * @throws IndexOutOfBoundsException - if the index
     *         is negative or if it is greater than or equal to the
     *         current size
     */
    public E set(int index, E newValue) {

        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index);

        E oldValue = theData[index];

        theData[index] = newValue;

        return oldValue;
    }

    /**Remove an entry based on its index
     * @param index The index of the entry to be removed
     * @return The value removed
     * @throws IndexOutOfBoundsException - if the index
     *         is negative or if it is greater than or equal to the
     *         current size
     */
    public E remove(int index) {

        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index);

        E returnValue = theData[index];

        // slide data to the left to fill in the gap
        System.arraycopy(theData, index + 1, theData, index, size - (index + 1));

        size--;

        return returnValue;
    }

    /**Add an entry to the end of the List
     * @param anEntry The value to be inserted
     */
    public boolean add(E anEntry) {

        if (size == theData.length)
            reallocate(); // create more capacity in the array

        theData[size] = anEntry;
        size++;
        return true;
    }

    /**Add the item in the specified position in this List
     * @param index position where the item is to be added
     * @param newData the value to be added 
     * @throws IndexOutOfBoundsException if index is
     *         less than zero or greater than size
     */
    public void add(int index, E newData) {

        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(index);

        if (size == theData.length)
            reallocate(); // create more capacity in the array

        /** shove items over to the right to make room for new item */
        System.arraycopy(theData, index, theData, index + 1, size - index);

        theData[index] = newData;
        size++;
    }

    /**Allocate a new, larger array and move all the data into this
     * larger array
     */
    private void reallocate() {

        /** create an array with twice the memory as the previous array */
        E[] biggerTable = (E[]) new Object[2 * theData.length];

        /** copy all the data from the old array to this new array */
        System.arraycopy(theData, 0, biggerTable, 0, size);

        /** theData now refers to this larger table */
        theData = biggerTable;
    }

    /**Get the current size of the array
     * @return The current size of the array
     */
    public int size() {
        return size;
    }

    /**Return a String that represents all of the items in the List */
    public String toString() {

        String result = "[";

        for (int i = 0; i < size; i++) {
            result += ((theData[i] != null) ? theData[i].toString() : "null") + ", ";
        }
        if (!result.equals("[")) // remove trailing comma
            result = result.substring(0, result.length() - 2);
        result += "]";

        return result;
    }

    /**Return a ListIterator that is initialized to the start (left end)
     * of this List  
     */
    public ListIterator<E> listIterator() {

        return new CurrentPosition();
    }

    private class CurrentPosition implements ListIterator<E> {
        private int currentIndex = -1;
        private boolean forward = true;

        private boolean Result(int index) {
            return index >= 0 && index < size;
        }
            
        
        @Override
        public boolean hasNext() {
            
            return Result(nextIndex());
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            currentIndex = nextIndex();
            forward = true;
            return get(currentIndex);
        }

        @Override
        public boolean hasPrevious() {
            
            return Result(previousIndex());
        }

        @Override
        public E previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            currentIndex = previousIndex();
            forward = false;
            return get(currentIndex);
        }

        @Override
        public int nextIndex() {
            if (forward)
                return currentIndex;
            else
            return currentIndex -1;
        }

        @Override
        public int previousIndex() {
            if (forward)
                return currentIndex;

            else
                return currentIndex - 1;
        }

        @Override
        public void remove() {
            if (!Result(currentIndex))
                throw new IllegalStateException();
            LucasArrayList.this.remove(currentIndex);

            
        }

        @Override
        public void set(E e) {
            if (!Result(currentIndex))
                throw new IllegalStateException();
            LucasArrayList.this.set(currentIndex, e);
            
        }

        @Override
        public void add(E e) {
            LucasArrayList.this.add(nextIndex(), e);
            currentIndex++;
            
        }
    
    
    }
}