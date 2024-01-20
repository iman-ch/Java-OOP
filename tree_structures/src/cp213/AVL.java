package cp213;

/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author Iman Chaudhry
 * @author David Brown
 * @version 2023-06-04
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

    /**
     * Returns the balance item of node. If greater than 1, then left heavy, if less
     * than -1, then right heavy. If in the range -1 to 1 inclusive, the node is
     * balanced. Used to determine whether to rotate a node upon insertion.
     *
     * @param node The TreeNode to analyze for balance.
     * @return A balance number.
     */
    private int balance(final TreeNode<T> node) {
	if (node == null) {
	    return 0;
	}

	int lH = 0;
	int rH = 0;

	if (node.getLeft() != null) {
	    lH = node.getLeft().getHeight();
	}
	
	if (node.getRight() != null) {
	    rH = node.getRight().getHeight();
	}

	return (lH - rH);
    }

    /**
     * Performs a left rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> node) {
	
	TreeNode<T> right = node.getRight();
	node.setRight(right.getLeft());
	right.setLeft(node);

	node.updateHeight();
	right.updateHeight();

	return right;
    }

    /**
     * Performs a right rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> node) {

	TreeNode<T> left = node.getLeft();
	node.setLeft(left.getRight());
	left.setRight(node);

	node.updateHeight();
	left.updateHeight();

	return left;
    }

    /**
     * Auxiliary method for insert. Inserts data into this AVL.
     *
     * @param node The current node (TreeNode).
     * @param data Data to be inserted into the node.
     * @return The inserted node.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedItem<T> data) {
	
	if (node == null) {
	    node = new TreeNode<T>(data);
	    node.getdata().incrementCount();
	    this.size++;
	} else {
	    this.comparisons++;
	    if (node.getdata().compareTo(data) > 0)
		node.setLeft(insertAux(node.getLeft(), data));
	    else if (node.getdata().compareTo(data) < 0)
		node.setRight(insertAux(node.getRight(), data));
	    else
		node.getdata().incrementCount();
	}

	node.updateHeight();
	int bF = balance(node);

	if (bF > 1) {

	    int LCbf = balance(node.getLeft());
	    if (LCbf < 0) {

		node.setLeft(rotateLeft(node.getLeft()));
		node = rotateRight(node);
	    } else {

		node = rotateRight(node);
	    }
	} else if (bF < -1) {

	    int RCbf = balance(node.getRight());
	    if (RCbf > 0) {

		node.setRight(rotateRight(node.getRight()));
		node = rotateLeft(node);
	    } else {

		node = rotateLeft(node);
	    }
	}

	return node;
    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An AVL must meet the BST validation conditions, and additionally be
     * balanced in all its subtrees - i.e. the difference in height between any two
     * children must be no greater than 1.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

	if (node == null)
	    return true;

	this.comparisons++;
	if (minNode != null && node.getdata().compareTo(minNode.getdata()) < 0
		|| maxNode != null && node.getdata().compareTo(maxNode.getdata()) > 0)
	    return false;

	int bF = balance(node);
	if (bF < -1 || bF > 1)
	    return false;

	if (this.isValidAux(node.getLeft(), minNode, node))
	    if (this.isValidAux(node.getRight(), node, maxNode))
		return true;

	return false;
    }

    /**
     * Determines whether two AVLs are identical.
     *
     * @param target The AVL to compare this AVL against.
     * @return true if this AVL and target contain nodes that match in position,
     *         item, count, and height, false otherwise.
     */
    public boolean equals(final AVL<T> target) {
	return super.equals(target);
    }

}
