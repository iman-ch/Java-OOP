package cp213;

/**
 * Implements a Popularity Tree. Extends BST.
 *
 * @author your name here
 * @author David Brown
 * @version 2023-06-04
 */
public class PopularityTree<T extends Comparable<T>> extends BST<T> {

    /**
     * Auxiliary method for valid. May force node rotation if the retrieval count of
     * the located node item is incremented.
     *
     * @param node The node to examine for key.
     * @param key  The item to search for. Count is updated to count in matching
     *             node item if key is found.
     * @return The updated node.
     */
    private TreeNode<T> retrieveAux(TreeNode<T> node, final CountedItem<T> key) {

	if (node == null)
	    return node;

	if (node.getdata().compareTo(key) == 0) {
	    node.getdata().incrementCount();
	    key.setCount(node.getdata().getCount());
	} else if (node.getLeft() != null && node.getdata().compareTo(key) > 0) {
	    node.setLeft(retrieveAux(node.getLeft(), key));
	    if (node.getLeft() != null && node.getLeft().getdata().getCount() > node.getdata().getCount())
		node = rotateRight(node);
	} else {
	    node.setRight(retrieveAux(node.getRight(), key));
	    if (node.getRight() != null && node.getRight().getdata().getCount() > node.getdata().getCount())
		node = rotateLeft(node);
	}

	this.comparisons++;
	node.updateHeight();

	return node;
    }

    /**
     * Performs a left rotation around node.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> parent) {

	TreeNode<T> root = parent.getRight();
	parent.setRight(root.getLeft());
	root.setLeft(parent);

	parent.updateHeight();
	root.updateHeight();

	return root;
    }

    /**
     * Performs a right rotation around {@code node}.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> parent) {

	TreeNode<T> root = parent.getLeft();
	parent.setLeft(root.getRight());
	root.setRight(parent);

	parent.updateHeight();
	root.updateHeight();

	return root;
    }

    /**
     * Replaces BST insertAux - does not increment count on repeated insertion.
     * Counts are incremented only on retrieve.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedItem<T> data) {
	if (node == null) {
	    // Base case - add a new node containing the data.
	    node = new TreeNode<T>(data);
	    // node.getCs().incrementCount();
	    this.size++;
	} else {
	    // Compare the node data against the insert data.
	    final int result = node.getdata().compareTo(data);
	    this.comparisons++;

	    if (result > 0) {
		// General case - check the left subtree.
		node.setLeft(this.insertAux(node.getLeft(), data));
	    } else if (result < 0) {
		// General case - check the right subtree.
		node.setRight(this.insertAux(node.getRight(), data));
	    }
	}
	node.updateHeight();
	return node;
    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An Popularity Tree must meet the BST validation conditions, and
     * additionally the counts of any node data must be greater than or equal to the
     * counts of its children.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {
	if (node == null)
	    return true;
	if (minNode != null && node.getdata().compareTo(minNode.getdata()) < 0
		|| maxNode != null && node.getdata().compareTo(maxNode.getdata()) > 0)
	    return false;
	if (minNode != null && node.getdata().getCount() < minNode.getdata().getCount()
		|| maxNode != null && node.getdata().getCount() > maxNode.getdata().getCount())
	    return false;
	if (this.isValidAux(node.getLeft(), minNode, node))
	    if (this.isValidAux(node.getRight(), node, maxNode))
		return true;

	return false;
    }

    /**
     * Determines whether two PopularityTrees are identical.
     *
     * @param target The PopularityTree to compare this PopularityTree against.
     * @return true if this PopularityTree and target contain nodes that match in
     *         position, item, count, and height, false otherwise.
     */
    public boolean equals(final PopularityTree<T> target) {
	return super.equals(target);
    }

    /**
     * Very similar to the BST retrieve, but increments the data count here instead
     * of in the insertion.
     *
     * @param key The key to search for.
     */
    @Override
    public CountedItem<T> retrieve(CountedItem<T> key) {

	this.root = retrieveAux(this.root, key);

	if (key.getCount() == 0) {
	    key = null;
	} else {
	    key = new CountedItem<T>(key);
	}
	return key;
    }
}
