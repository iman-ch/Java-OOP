package cp213;

/**
 * A single linked list structure of <code>Node T</code> objects. These data
 * objects must be Comparable - i.e. they must provide the compareTo method.
 * Only the <code>T</code> value contained in the priority queue is visible
 * through the standard priority queue methods. Extends the
 * <code>SingleLink</code> class.
 *
 * @author Iman Chaudhry 
 * @version 2023-06-04
 * @param <T> this SingleList data type.
 */
public class SingleList<T extends Comparable<T>> extends SingleLink<T> {

    /**
     * Searches for the first occurrence of key in this SingleList. Private helper
     * methods - used only by other ADT methods.
     *
     * @param key The value to look for.
     * @return A pointer to the node previous to the node containing key.
     */
    private SingleNode<T> linearSearch(final T key) {

	SingleNode<T> node = this.front;
	int counter = 1;

	while ((node.getData().compareTo(key) != 0) && (counter < this.getLength())) {
	    node = node.getNext();
	    counter++;
	}

	if (node.getData().compareTo(key) != 0) {
	    node = null;
	}

	return node;

    }

    /**
     * Appends data to the end of this SingleList.
     *
     * @param data The value to append.
     */
    public void append(final T data) {

	SingleNode<T> node = new SingleNode<T>(data, null);

	if (this.rear != null)
	    this.rear.setNext(node);

	this.rear = node;

	if (this.front == null) {
	    this.front = node;
	}
	this.length++;

	return;

    }

    /**
     * Removes duplicates from this SingleList. The list contains one and only one
     * of each value formerly present in this SingleList. The first occurrence of
     * each value is preserved.
     */
    public void clean() {

	SingleNode<T> curr = this.front;
	
	while(curr!=null) {
	    SingleNode<T> node = curr.getNext();
	    while(node!=null) {
		if(curr==node) {
		    curr.setNext(node.getNext());
		    node = curr.getNext();
		}
		node = node.getNext();
	    }
	    curr = curr.getNext();
	}

	return;
    }

    /**
     * Combines contents of two lists into a third. Values are alternated from the
     * origin lists into this SingleList. The origin lists are empty when finished.
     * NOTE: data must not be moved, only nodes.
     *
     * @param left  The first list to combine with this SingleList.
     * @param right The second list to combine with this SingleList.
     */
    public void combine(final SingleList<T> left, final SingleList<T> right) {

	while ((left.getLength() > 0) || (right.getLength() > 0)) {
	    if (left.getLength() > 0) {
		this.moveFrontToRear(left);
	    }

	    if (right.getLength() > 0) {
		this.moveFrontToRear(right);
	    }
	}

	return;
    }

    /**
     * Determines if this SingleList contains key.
     *
     * @param key The key value to look for.
     * @return true if key is in this SingleList, false otherwise.
     */
    public boolean contains(final T key) {

	SingleNode<T> node = this.linearSearch(key);
	boolean contains = false;

	if (node != null) {
	    contains = true;
	}

	return contains;
    }

    /**
     * Finds the number of times key appears in list.
     *
     * @param key The value to look for.
     * @return The number of times key appears in this SingleList.
     */
    public int count(final T key) {

	int count = 0;

	if (this.getLength() > 0) {
	    SingleNode<T> curr = this.front;

	    int i = 0;

	    while (i < this.getLength()) {
		if (curr.getData().compareTo(key) == 0) {
		    count++;
		}
		curr = curr.getNext();
		i++;
	    }
	}

	return count;
    }

    /**
     * Finds and returns the value in list that matches key.
     *
     * @param key The value to search for.
     * @return The value that matches key, null otherwise.
     */
    public T find(final T key) {

	SingleNode<T> node = this.linearSearch(key);

	T value = node == null ? null : node.getData();

	return value;
    }

    /**
     * Get the nth item in this SingleList.
     *
     * @param n The index of the item to return.
     * @return The nth item in this SingleList.
     * @throws ArrayIndexOutOfBoundsException if n is not a valid index.
     */
    public T get(final int n) throws ArrayIndexOutOfBoundsException {
	
	int i = 0;
	SingleNode<T> node = this.front;

	while (i < n) {
	    node = node.getNext();
	    i++;
	}
	
	return node.getData();
    }

    /**
     * Determines whether two lists are identical.
     *
     * @param source The list to compare against this SingleList.
     * @return true if this SingleList contains the same values in the same order as
     *         source, false otherwise.
     */
    public boolean identical(final SingleList<T> source) {

	boolean identical = true;

	if (this.getLength() != source.getLength()) {
	    identical = false;
	} else {
	    int i = 0;
	    SingleNode<T> curr = this.front;
	    SingleNode<T> source_curr = source.front;

	    while ((i < this.getLength()) && (identical)) {
		if (curr.getData().compareTo(source_curr.getData()) != 0) {
		    identical = false;
		}

		curr = curr.getNext();
		source_curr = source_curr.getNext();
		i++;
	    }
	}

	return identical;
    }

    /**
     * Finds the first location of a value by key in this SingleList.
     *
     * @param key The value to search for.
     * @return The index of key in this SingleList, -1 otherwise.
     */
    public int index(final T key) {

	SingleNode<T> curr = this.front;
	int i = 0;

	while ((curr.getData().compareTo(key) != 0) && (i < this.getLength() - 1)) {
	    curr = curr.getNext();
	    i++;
	}

	if (this.getLength() > 0) {
	    if (curr.getData().compareTo(key) != 0) {
		i = -1;
	    }
	}

	return i;
    }

    /**
     * Inserts value into this SingleList at index i. If i greater than the length
     * of this SingleList, append data to the end of this SingleList.
     *
     * @param i     The index to insert the new data at.
     * @param data The new value to insert into this SingleList.
     */
    public void insert(int i, final T data) {

	SingleNode<T> node = new SingleNode<T>(data, null);
	
	if (this.getLength() <= i) {
	    if (this.rear == null) {
		this.front = node;
		this.rear = node;
	    } else {
		this.rear.setNext(node);
		this.rear = node;
	    }

	} else if (i == 0) {
	    node.setNext(this.front);
	    this.front = node;
	} else {
	    SingleNode<T> curr = this.front;
	    for (int index = 1; index < i; index++) {
		curr = curr.getNext();
	    }

	    node.setNext(curr.getNext());
	    curr.setNext(node);
	}

	this.length++;
	return;
    }

    /**
     * Creates an intersection of two other SingleLists into this SingleList. Copies
     * data to this SingleList. left and right SingleLists are unchanged. Values
     * from left are copied in order first, then values from right are copied in
     * order.
     *
     * @param left  The first SingleList to create an intersection from.
     * @param right The second SingleList to create an intersection from.
     */
    public void intersection(final SingleList<T> left, final SingleList<T> right) {

	SingleNode<T> l_node = left.front;

	for (int i = 0; i < left.getLength(); i++) {
	    if (right.contains(l_node.getData())) {
		this.append(l_node.getData());
	    }
	    l_node = l_node.getNext();
	}

	return;
    }

    /**
     * Finds the maximum value in this SingleList.
     *
     * @return The maximum value.
     */
    public T max() {

	T max = this.front.getData();
	SingleNode<T> curr = this.front;

	for (int i = 0; i < this.getLength(); i++) {
	    if (curr.getData().compareTo(max) > 0) {
		max = curr.getData();
	    }
	    curr = curr.getNext();
	}

	return max;
    }

    /**
     * Finds the minimum value in this SingleList.
     *
     * @return The minimum value.
     */
    public T min() {

	T min = this.front.getData();
	SingleNode<T> cur = this.front;

	for (int i = 0; i < this.getLength(); i++) {
	    if (cur.getData().compareTo(min) < 0) {
		min = cur.getData();
	    }
	    cur = cur.getNext();
	}

	return min;
    }

    /**
     * Inserts value into the front of this SingleList.
     *
     * @param data The value to insert into the front of this SingleList.
     */
    public void prepend(final T data) {

	SingleNode<T> node = new SingleNode<T>(data, this.front);

	this.front = node;

	if (this.rear == null) {
	    this.rear = this.front;
	}

	this.length++;

	return;
    }

    /**
     * Finds, removes, and returns the value in this SingleList that matches key.
     *
     * @param key The value to search for.
     * @return The value matching key, null otherwise.
     */
    public T remove(final T key) {

	SingleNode<T> curr = this.front;
	SingleNode<T> prev = this.front;
	T data;
	int i = 1;

	while ((curr.getData().compareTo(key) != 0) && (i < this.getLength())) {
	    prev = curr;
	    curr = curr.getNext();
	    i++;
	}

	if (curr != null) {
	    if (curr.getData().compareTo(key) != 0) {
		curr = null;
	    } else {
		if (curr == this.front) {
		    this.front = this.front.getNext();
		} else {
		    prev.setNext(curr.getNext());
		}
		this.length--;
		if (this.getLength() == 0) {
		    this.front = null;
		    this.rear = null;
		}
	    }
	}
	
	data = curr == null ? null : curr.getData();
	return data;
    }

    /**
     * Removes the value at the front of this SingleList.
     *
     * @return The value at the front of this SingleList.
     */
    public T removeFront() {

	SingleNode<T> front = this.front;

	this.front = this.front.getNext();

	this.length--;

	if (this.getLength() == 0) {
	    this.rear = null;
	}

	return front.getData();
    }

    /**
     * Finds and removes all values in this SingleList that match key.
     *
     * @param key The value to search for.
     */
    public void removeMany(final T key) {
	SingleNode<T> curr = this.front;
	SingleNode<T> prev = this.front;
	int i = 0;

	while (i < this.getLength()) {
	    
	    if (curr.getData().compareTo(key) == 0) {
		if (curr == this.front) {
		    this.front = this.front.getNext();
		    curr = this.front;
		} else {
		    prev.setNext(curr.getNext());
		    if (curr == this.rear) {
			this.rear = prev;
		    }
		    curr = curr.getNext();
		}
		this.length--;
		
	    } else {
		prev = curr;
		curr = curr.getNext();
		i++;
	    }
	}

	if (this.front == null) {
	    this.rear = null;
	}

	return;
    }

    /**
     * Reverses the order of the values in this SingleList.
     */
    public void reverse() {

	SingleNode<T> prev = null;
	SingleNode<T> curr = this.front;
	SingleNode<T> next;

	for (int i = 0; i < this.getLength(); i++) {
	    next = curr.getNext();
	    curr.setNext(prev);
	    prev = curr;
	    curr = next;
	}

	next = this.front;
	this.front = this.rear;
	this.rear = next;

	return;
    }

    /**
     * Splits the contents of this SingleList into the left and right SingleLists.
     * Moves nodes only - does not move value or call the high-level methods insert
     * or remove. this SingleList is empty when done. The first half of this
     * SingleList is moved to left, and the last half of this SingleList is moved to
     * right. If the resulting lengths are not the same, left should have one more
     * item than right. Order is preserved.
     *
     * @param left  The first SingleList to move nodes to.
     * @param right The second SingleList to move nodes to.
     */
    public void split(final SingleList<T> left, final SingleList<T> right) {

	int mid = (this.getLength() / 2);

	while (this.getLength() > 0) {
	    if (this.getLength() > mid) {
		left.moveFrontToRear(this);
	    } else {
		right.moveFrontToRear(this);
	    }
	}

	return;
    }

    /**
     * Splits the contents of this SingleList into the left and right SingleLists.
     * Moves nodes only - does not move value or call the high-level methods insert
     * or remove. this SingleList is empty when done. Nodes are moved alternately
     * from this SingleList to left and right. Order is preserved.
     *
     * @param left  The first SingleList to move nodes to.
     * @param right The second SingleList to move nodes to.
     */
    public void splitAlternate(final SingleList<T> left, final SingleList<T> right) {

	while (this.getLength() > 0) {
	    left.moveFrontToRear(this);

	    if (this.getLength() > 0) {
		right.moveFrontToRear(this);
	    }
	}

	return;
    }

    /**
     * Creates a union of two other SingleLists into this SingleList. Copies value
     * to this list. left and right SingleLists are unchanged. Values from left are
     * copied in order first, then values from right are copied in order.
     *
     * @param left  The first SingleList to create a union from.
     * @param right The second SingleList to create a union from.
     */
    public void union(final SingleList<T> left, final SingleList<T> right) {

	SingleNode<T> l_node = left.front;
	SingleNode<T> r_node = right.front;

	for (int i = 0; i < left.getLength(); i++) {
	    this.append(l_node.getData());
	    l_node = l_node.getNext();
	}

	for (int i = 0; i < right.getLength(); i++) {
	    this.append(r_node.getData());
	    r_node = r_node.getNext();
	}

	return;
    }
}
