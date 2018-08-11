/**
 * Implementation of a binary search tree
 *
 * Author: Jocelyn Shen, Michelle Chao
 * Date: April 6, 2017
*/
import java.util.ArrayList;
import java.util.Collections;
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

  public BinarySearchTree() {
  }

  /**
  * Test if this tree is empty
  */
  @Override
  public boolean empty() {
    return super.empty();
  }

  /**
  * Empty the tree
  */
  @Override
  public void clear() {
    super.clear();
  }

  /**
  * Add item in a valid spot. Returns if the item was added successfully
  */
  @Override
  public boolean add(T item) {
    boolean t = binarySearchTreeAdd(item);
    return t;
  }

  /**
  * Add to set; helper method for Set class
  */
  public boolean setAdd(T item) {
    return super.setAdd_helper(item);
  }

  /**
  * Remove from the tree one occurrence of item in the tree,
  * without removing anything else (Don't lop off entire limbs).
  */
  @Override
  public void remove(T item) {
    super.remove(item);
    root.bst_normalize();
  }

  /**
  * Determine if item is in the tree
  */
  @Override
  public boolean contains(T item) {
    return super.contains(item);
  }

  /**
  * Return an Arraylist of the elements of the binary tree sorted
  */
  @Override
  public ArrayList<T> sortedList() {
    return super.sortedList();
  }

  /**
  * Return an iterator over the tree in sorted order,
  * possibly reversed.
  */
  public MyIterable<T> sorted(boolean reversed) {
    MyIterable<T> it;
    if(reversed == true) {
      ArrayList<T> l = sortedList();
      Collections.reverse(l);
      it = new MyIterable<T>(l);
    }
    else {
      it = new MyIterable<T>(sortedList());
    }
    return it;
  }

  @Override
  public String toString(){
    return super.toString();
  }
}
