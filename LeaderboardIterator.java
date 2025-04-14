//////////////// FILE HEADER ////////////////////////////////////////////////
//
// Title:LeaderboardIterator
// Course: CS 300 Fall 2024
//
// Author: saniya goyal
// Email: sagoyal@wisc.edu
// Lecturer: (Blerina Gkotse)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;

/**
 * The LeaderboardIterator class implements an iterator for traversing a binary search tree (BST) of
 * Player objects
 * 
 */
public class LeaderboardIterator implements Iterator<Player> {

  private BSTNode<Player> currentNode;
  private BSTNode<Player> root;
  private boolean firstCall;

  /**
   * Constructs a LeaderboardIterator for the given BST root.
   * 
   * @param root the root of the BST to traverse.
   */
  public LeaderboardIterator(BSTNode<Player> root) {
    this.root = root;
    this.currentNode = null;
    this.firstCall = true;
  }

  /**
   * Checks if there is a next element in the BST.
   * 
   * @return true if there are more nodes to traverse, false otherwise.
   */

  @Override
  public boolean hasNext() {
    return currentNode != null || root != null;
  }

  /**
   * Returns the next Player in in-order traversal.
   * 
   * @return the next Player object in the BST.
   * @throws java.util.NoSuchElementException if there are no more elements to traverse.
   */

  @Override
  public Player next() {
    if (!hasNext()) {
      throw new java.util.NoSuchElementException();
    }

    // First call: Start with the leftmost node (minimum score)
    if (firstCall) {
      firstCall = false;
      currentNode = getLeftMostNode(root);
    }

    // Return the data in the current node
    Player currentPlayer = currentNode.getData();

    // Move to the next node in the in-order traversal
    currentNode = getNextNode(currentNode);

    return currentPlayer;
  }

  /**
   * Finds the leftmost node in the subtree starting at the given node.
   * 
   * @param node the starting node of the subtree.
   * @return the leftmost node in the subtree.
   */
  private BSTNode<Player> getLeftMostNode(BSTNode<Player> node) {
    while (node != null && node.getLeft() != null) {
      node = node.getLeft();
    }
    return node;
  }

  /**
   * Finds the next node in in-order traversal.
   * 
   * @param node the current node.
   * @return the next node in in-order traversal, or null if no further nodes exist.
   */
  private BSTNode<Player> getNextNode(BSTNode<Player> node) {
    // If the current node has a right child, find its leftmost node
    if (node.getRight() != null) {
      return getLeftMostNode(node.getRight());
    }

    // Otherwise, go up to the parent (find the in-order successor)
    BSTNode<Player> parentNode = findParentNode(node);
    return parentNode;
  }

  /**
   * Finds the parent of the given node in the BST.
   * 
   * @param node the node whose parent is to be found.
   * @return the parent node, or null if the given node is the root.
   */
  private BSTNode<Player> findParentNode(BSTNode<Player> node) {
    BSTNode<Player> parentNode = null;
    BSTNode<Player> current = root;

    // Search for the parent of the current node
    while (current != null) {
      if (node.getData().compareTo(current.getData()) < 0) {
        parentNode = current;
        current = current.getLeft();
      } else if (node.getData().compareTo(current.getData()) > 0) {
        current = current.getRight();
      } else {
        break; // Found the node, break out of the loop
      }
    }

    return parentNode;
  }
}

