package cp213;

/**
 * A single linked priority queue structure of <code>Node T</code> objects.
 * These data objects must be Comparable - i.e. they must provide the compareTo
 * method. Only the <code>T</code> data contained in the priority queue is
 * visible through the standard priority queue methods. Extends the
 * <code>SingleLink</code> class.
 *
 * @author Iman Chaudhry 
 * @version 2023-06-04
 * @param <T> the SinglePriorityQueue data type.
 */
public class SinglePriorityQueue<T extends Comparable<T>> extends SingleLink<T> {

    /**
     * Combines the contents of the left and right SinglePriorityQueues into the
     * current SinglePriorityQueue. Moves nodes only - does not move value or call
     * the high-level methods insert or remove. left and right SinglePriorityQueues
     * are empty when done. Nodes are moved alternately from left and right to this
     * SinglePriorityQueue. When finished all nodes must be in priority order from
     * front to rear.
     *
     * Do not use the SinglePriorityQueue insert and remove methods.
     *
     * Do not assume that both right and left priority queues are of the same
     * length.
     *
     * @param left  The first SinglePriorityQueue to extract nodes from.
     * @param right The second SinglePriorityQueue to extract nodes from.
     */
    public void combine(final SinglePriorityQueue<T> left, final SinglePriorityQueue<T> right) {
	assert this.front == null : "May combine into an empty Priority Queue only";
	
	boolean lft = true;
	
	while(!left.isEmpty() && !right.isEmpty()) {
	    if (lft == true) {
		this.moveFrontToRear(left);
	    }
	    else {
		this.moveFrontToRear(right);
	    }
	    
	    lft = !lft;
	
	    while(!left.isEmpty()) {
		this.moveFrontToRear(left);
	    }
	    while(!right.isEmpty()) {
		this.moveFrontToRear(right);
	    }
	    
	}

	return;
    }

    /**
     * Adds value to the SinglePriorityQueue. Data is stored in priority order, with
     * highest priority value at the front of the SinglePriorityQueue, and lowest at
     * the rear. Priority is determined by simple comparison - lower values have
     * higher priority. For example, 1 has a higher priority than 2 because 1 is a
     * lower value than 2. (Think of the phrase, "We're number one!" as an
     * indication of priority.)
     *
     * When inserting value to the priority queue, the queue must remain sorted.
     * Hence you need to find the proper location of inserting value. use the head
     * pointer to go through the queue. e.g., use SingleNode&lt;T&gt; current =
     * this.head;
     *
     * use current = current.getNext(); to traverse the queue.
     *
     * To get access to the value inside a node of queue use current.getValue().
     *
     * @param data value to insert in sorted order in priority queue.
     */
    public void insert(final T data) {

	SingleNode<T> node = new SingleNode<T>(data, null);
	
	if (this.isEmpty()) {
		this.front = node;
	} else if (node.getData().compareTo(this.front.getData()) < 0) {
		node.setNext(this.front);
		this.front = node;
	} else {
		SingleNode<T> prev = null;
		SingleNode<T> curr = this.front;

		while (curr != null && node.getData().compareTo(curr.getData()) >= 0) {
			prev = curr;
			curr = curr.getNext();
		}

		node.setNext(curr);
		prev.setNext(node);

	}
	this.length++;

return;
}

    /**
     * Returns the highest priority value in the SinglePriorityQueue. Decrements the
     * count.
     *
     * @return the highest priority value currently in the SinglePriorityQueue.
     */
    public T remove() {

	if (this.getLength()>0) {
	    T value = this.front.getData();
	    if (this.getLength() == 1) {
		this.front = null;
		this.rear = this.front;
	    }
	    else {
		this.front = this.front.getNext();
	    }
	    this.length--;
	    return value;
	}

	return null;
    }

    /**
     * Splits the contents of this SinglePriorityQueue into the higher and lower
     * SinglePriorityQueue. Moves nodes only - does not move value or call the
     * high-level methods insert or remove. this SinglePriorityQueue is empty when
     * done. Nodes with priority value higher than key are moved to the
     * SinglePriorityQueue higher. Nodes with a priority value lower than or equal
     * to key are moved to the SinglePriorityQueue lower.
     *
     * Do not use the SinglePriorityQueue insert and remove methods.
     *
     * @param key    value to compare against node values in SinglePriorityQueue
     * @param higher an initially empty SinglePriorityQueue queue that ends up with
     *               all values with priority higher than key.
     * @param lower  an initially empty SinglePriorityQueue queue that ends up with
     *               all values with priority lower than or equal to key.
     */
    public void splitByKey(final T key, final SinglePriorityQueue<T> higher, final SinglePriorityQueue<T> lower) {
	
	while(this.front != null) {
	    SingleNode<T> curr = this.front;
	    
	    if (curr.getData().compareTo(key) >= 0) {
		lower.moveFrontToRear(this);
	    } 
	    else if (curr.getData().compareTo(key) < 0) {
		higher.moveFrontToRear(this);
	    }
	}

	return;
    }
}
