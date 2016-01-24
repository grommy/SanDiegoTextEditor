package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = new LLNode<>(null);
        tail = new LLNode<>(null);
        size = 0;
        head.next = tail;
        tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
        if (element != null) {
            LLNode<E> newNode = new LLNode<>(element);
            newNode.next = tail;
            newNode.prev = tail.prev;
            newNode.prev.next = newNode;
            tail.prev = newNode;

            size++;
            return true;
        }
        else throw new NullPointerException("Element, you try to add is Null");
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
        LLNode<E> currentElement = getElementByIndex(index);
        return currentElement.data;
	}

	/** Add an element to the list at the specified index
	 * @param index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
        if (element!=null) {
            boolean isThisAFirstElemToAdd = (size == index);
            if (isThisAFirstElemToAdd) {
                add(element);
            }
            else {
                LLNode<E> currentIndexElement = getElementByIndex(index);
                LLNode<E> newIndexElement = new LLNode<>(element);

                // switch links
                newIndexElement.next = currentIndexElement;
                newIndexElement.prev = currentIndexElement.prev;

                currentIndexElement.prev.next = newIndexElement;
                currentIndexElement.prev = newIndexElement;


                size++;
            }
        }
        else throw new NullPointerException("Element, you try to add is Null");

	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
        LLNode<E> currentElement = getElementByIndex(index);

        // switch links
        currentElement.next.prev = currentElement.prev;
        currentElement.prev.next = currentElement.next;

        size--;

        return currentElement.data;

	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
        if (element!=null) {
            E oldElement;
            LLNode<E> indexNode = getElementByIndex(index);

            oldElement = indexNode.data;
            indexNode.data = element;
            return oldElement;
        }
        else throw new NullPointerException("Element you try to set is null");

	}

    private LLNode<E> getElementByIndex(int index) {
        LLNode<E> currentElement = head.next;

        boolean indexIsCorrect = (index<size) && (index>=0);

        if (indexIsCorrect) {
            for(int i=0; i < index; i++) {
                try {
                    currentElement = currentElement.next;
                }
                catch (NullPointerException e){
                    throw new NullPointerException(String.format("Next link is broken on node %d",i));
                }

            }
            return currentElement;
        }
        else {
            throw new IndexOutOfBoundsException(String.format("%d Index out of bounds", index));
        }
    }
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
