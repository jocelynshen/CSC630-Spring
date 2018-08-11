/**
 * Implementation of a generic Binary Tree
 *
 * Author: Jocelyn Shen, Michelle Chao
 * Date: April 6, 2017
*/

import java.util.*;
public class BinaryTree<T extends Comparable<T>>{
    public Node<T> root;

    /**
     * The BinaryTree class utilizes the Node class to store items.
     */
    public class Node<T extends Comparable<T>> {
        private T contents;
        private Node<T> left;
        private Node<T> right;
        ArrayList<T> breadth_first;

        public Node(T obj) {
          contents = obj;
        }



        /**
         * Finds the leaf of lowest depth in the binary tree. Prevents unbalanced trees when adding.

         */
        public int findMinDepth(int count) {
          if(left != null && right != null) {
            return Math.min(left.findMinDepth(count+1), right.findMinDepth(count+1));
          }
          return count;
        }

        // Iterates through the tree to add a node at a particular depth probably should
        // rename depth parameter to something else
        public boolean addNode(int depth, T item) {
          if(depth == 0) {
            // Recursed to the bottom of the tree
            if(left == null || left.contents == null) {
              // Add item as left child
              left = new Node<T>(item);
              return true;
            }
            else if(right == null || right.contents == null) {
              // Add item as right child
              right = new Node<T>(item);
              return true;
            }
            // Not at the proper location in the tree
            return false;
          }
          
          if(left != null && left.addNode(depth-1,item)) {
            return true;
          }
          else {
            return right.addNode(depth-1,item);
          }
        }

        private boolean isLeaf() {
          return right == null && left == null;
        }

        public boolean bt_remove_helper(T toRemove) {
          if(contents != null && contents.equals(toRemove)) {
            // The current Node contains the contents to be removed
            if(right == null && left == null) {
              // No children, this means the current node is the root
              contents = null;
              return true;
            }
            else if(right != null) {
              // There is a child on the right, find the deepest node
              T new_contents = switchLeaf();
              contents = new_contents;
              return true;
            }
            else if(left != null) {
              // There is no child on the right but one on the left
              T new_contents = switchLeaf();
              contents = new_contents;
              return true;
            }
            else {
              // Should never happen
              System.out.println("returned false here");
              return false;
            }
          }
          if(left != null && left.contents != null && left.contents.equals(toRemove)) {
            // Remove the left child
            if(left.isLeaf()) {
              // Left has no children, so just remove the pointer to that Node
              left = null;
              return true;
            }
            else {
            // Left Node has children
            // Pointers to left's children stay the same, left's contents are replaced with
            // contents from a leaf
            T replacement = switchLeaf();
            left.contents = replacement;
            return true;
            }
          }
          else if(right != null && right.contents != null && right.contents.equals(toRemove)) {
            // Remove the right child
            if(right.isLeaf()) {
              // Right has no children, so just remove the pointer to it
              right = null;
              return true;
            }
            else {
            // Right node has children
            // Pointers to right's children stay the same, right's contents are replaced
            T replacement = switchLeaf();
            right.contents = replacement;
            return true;
            }
          }
          else {
            // Left and Right nodes don't contain the object, check deeper into the tree
            if(left != null) {
              if(left.bt_remove_helper(toRemove) == false && right != null) {
                return right.bt_remove_helper(toRemove);
              }
              else {
                return true;
              }
            }
            if(right != null) {
              return right.bt_remove_helper(toRemove);
            }
          }
          // Object does not exist in the tree
          //System.out.println("returned false " + toRemove );
          return false;
        }
        

        /**
         * Helper method for remove, finds a leaf on the binary tree and switches it with the
         * node to be removed
         */
        private T switchLeaf() {
          if(left == null && right == null) {
            // should not happen
          }
          else if(left != null && (left.left != null || left.right != null)) {
            return left.switchLeaf();
          }
          else if(left != null && left.left == null && left.right == null) {
            T stuff = left.contents;
            left = null;
            return stuff;
          }
          else if(right != null && (right.left != null || right.right != null)) {
            return right.switchLeaf();
          }
          else if(right != null && right.left == null && right.right == null) {
            T stuff = right.contents;
            right = null;
            return stuff;
          }
          System.out.println("returned null");
          // should never happen
          return null;
        }

        public T getContents() {
          return contents;
        }

        /**
         * Used for Heap, regularizes the heap after adding a new object
         */
        public void heapRegularize() {
          if(left != null) {
            if(compareTo(left) < 0) {
              switchNodes(left);
            }
            left.heapRegularize();
            if(compareTo(left) < 0) {
              switchNodes(left);
            }
          }
          if(right != null) {
            if(compareTo(right) < 0) {
              switchNodes(right);
            }
            right.heapRegularize();
            if(compareTo(right) < 0) {
              switchNodes(right);
            }
          }
        }

        /**
         * Private helper method for several other methods in the class, switches
         * the contents of two nodes, leaving the children the same.
         */
        private void switchNodes(Node<T> other) {
          T other_contents = other.contents;
          other.contents = contents;
          contents = other_contents;
        }

        /**
         * Basic compareTo function - chose not to implement Comparator
         */
        public int compareTo(Node<T> other) {
          if(other.contents == contents && contents == null) return 0;
          if(contents == null) return -1;
          if(other.contents == null) return 1;
          return this.contents.compareTo(other.contents);
        }

        /**
         * Helper method for implementing the size method for the Heap class
         */
        public int sizeHelper() {
          int count = 0;
          if(left != null) {
            count += 1 + left.sizeHelper();
          }
          if(right != null) {
            count += 1 + right.sizeHelper();
          }
          return count;
        }

        @Override
        public String toString() {
          return "Contents: " + contents;
        }

        private String printAll() {
          String s = "";
          if(contents != null) s = "Root Contents: " + this.contents.toString();
          if(left != null || right != null) {
            return s + "\n" + this.StringHelper(1);
          }
          return contents.toString() + "\n" + StringHelper(1);
        }

        /**
         * Helper method for printing out entire tree
         */
        private String StringHelper(int level) {
          String space = "";
          for(int i = 0; i < level; i++) {
            space += "  ";
          }
          String s = "";
          if(this.contents == null) return s;
          if(left != null && left.contents != null) {
            s += space + "L Level: " + level + "  Contents: " + left.contents.toString() + "\n \n";
            s += left.StringHelper(level+1);
          }
          if(right != null && right.contents != null) {
            s += space + "R Level: " + level + "  Contents: " + right.contents.toString() + "\n \n";
            s += right.StringHelper(level+1);
          }
          return s;
        }

        /**
         * Helper method for the contains method of BinaryTree
         */
        private boolean bt_contains_helper(T item) {
          if(this.contents == null) {
            return false;
          }
          if(left != null && left.contents != null) {
            if(left.contents.equals(item)) {
              return true;
            }
            if(left.bt_contains_helper(item) == true) return true;
          }
          if(right != null && right.contents != null) {
            if(right.contents.equals(item)) {
              return true;
            }
            else {
              return right.bt_contains_helper(item);
            }
          }
          return false;
        }

        /**
         * Helper method for the get method of BinaryTree
         */
        private Node<T> bt_get_helper(T item) {
          if(this.contents != null) {
            if(contents.equals(item)) {
              return this;
            }
          }
          if(left != null && left.contents != null) {
            if(left.contents.equals(item)) {
              return left;
            }
            Node<T> l = left.bt_get_helper(item);
            if(l != null) return l;
          }
          if(right != null && right.contents != null) {
            if(right.contents.equals(item)) {
              return right;
            }
            else {
              return right.bt_get_helper(item);
            }
          }
          return null;
        }

        /**
         * Helper method for the add method of BinarySearchTree
         */
        public boolean bst_add(T item) {
          if (item.compareTo(this.contents) < 0) {
                  if (left == null) {
                        left = new Node<T>(item);
                        return true;
                  } else
                        return left.bst_add(item);
            }
            if (item.compareTo(this.contents) >= 0) {
                  if (right == null) {
                        right = new Node<T>(item);
                        return true;
                  } else
                        return right.bst_add(item);
            }
            return false;
        }

        /**
         * Helper method for the add method of the Set class.
         */
        public boolean set_add(T item) {
          if(contents != null && item.equals(contents)) {
            return false;
          }
            if(item.compareTo(contents) < 0) {
                  if (left == null) {
                        left = new Node<T>(item);
                        return true;
                  }
                  return left.set_add(item);
            }
            if(item.compareTo(this.contents) >= 0) {
                  if (right == null) {
                        right = new Node<T>(item);
                        return true;
                  } else
                    return right.bst_add(item);
            }
            return false;
        }

        /**
        * Helper for binary search tree sorted iterator
        */
        public ArrayList<T> sortedList_helper() {
          ArrayList<T> l = new ArrayList<T>();
          if(this.contents == null) {
            return l;
          }
          if(left != null && left.contents != null) {
            l.add(left.contents);
            l.addAll(left.sortedList_helper());
          }
          if(right != null && right.contents != null) {
            l.add(right.contents);
            l.addAll(right.sortedList_helper());
          }
          return l;

        }

        /**
         * Adjusts nodes, only for BinarySearchTree
         */
        public void bst_normalize() {
          if(left != null && left.compareTo(this) > 0) {
            switchNodes(left);
            left.bst_normalize();
            bst_normalize();
          }
          if(right != null && right.compareTo(this) < 0) {
            switchNodes(right);
            right.bst_normalize();
            bst_normalize();
          }
        }
        
        public ArrayList<T> breadthFirst() {
          ArrayList<T> l = new ArrayList<T>();
          if(left != null && left.contents != null) {
            l.add(left.contents);
          }
          if(right != null && right.contents != null) {
            l.add(right.contents);
          }
          if(left != null) l.addAll(left.breadthFirst());
          if(right != null) l.addAll(right.breadthFirst());
          return l;
        }
        
        public ArrayList<T> depthFirst() {
          ArrayList<T> l = new ArrayList<T>();
          if(left != null) {
            l.addAll(left.depthFirst());
          }
          if(contents != null) {
            l.add(contents);
          }
          if(right != null) {
            l.addAll(right.depthFirst());
          }
          return l;
        }
        
        public ArrayList<T> heapIterate() {
          ArrayList<T> l = depthFirst();
          Collections.sort(l,Collections.reverseOrder());
          return l;
        }
    }

    /**
    * Return an arraylist of the elements of the binary tree sorted breadth first
    */
    public ArrayList<T> breadthFirstList() {
      ArrayList<T> l = new ArrayList<T>();
      if(root != null && root.contents != null) {
        l.add(root.contents);
        l.addAll(root.breadthFirst());
        return l;
      }
      return l;
    }

    /**
    * Return an arraylist of the elements of the binary tree sorted depth first
    */
    public ArrayList<T> depthFirstList() {
      ArrayList<T> l = root.depthFirst();
      return l;
    }

    /**
    * Return an arraylist of the elements of the binary tree sorted
    */
    public ArrayList<T> sortedList() {
      if (root != null && root.contents != null) {
        ArrayList<T> l = root.sortedList_helper();
        l.add(root.contents);
        Collections.sort(l);
        return l;
      }
      return null;
    }

    public BinaryTree() {
    }

    /**
    * Test if the tree is empty
    */
    public boolean empty() {
      return root == null || root.contents == null;
    }
    
    /**
    * Add item as the left child of n, so long as n.left is currently null.
    * Returns whether item was successfully added.
    */
    public boolean addLeft(Node<T> n, T item){
      if(n != null && n.left != null && item != null) {
        n.left = new Node<T>(item);
        return true;
      }
      return false;

    }

    /**
    * Add item as the right child of n, so long as n.right is currently null.
    * Returns whether item was successfully added.
    */
    public boolean addRight(Node<T> n, T item){
      if(n != null && n.right != null && item != null) {
        n.right = new Node<T>(item);
        return true;
      }
      return false;
    }
    
    /**
    * Empty the tree
    */
    public void clear() {
      root = null;
    }

    /**
    * Add item to the first available spot in a breadth-first traversal
    * of the tree.
    * Returns whether item was successfully added (for now, always true).
    */
    public boolean add(T item) {
      if(root == null) {
        root = new Node<T>(item);
      }
      else {
        int depth = root.findMinDepth(0);
        root.addNode(depth, item);
      }
      return true;
    }

    public boolean binarySearchTreeAdd(T item) {
      if (root == null) {
        root = new Node<T>(item);
            return true;
      }
      else {
        return root.bst_add(item);
      }
    }

    public boolean setAdd_helper(T item) {
      if(root == null) {
        root = new Node<T>(item);
        return true;
      }
      else {
        return root.set_add(item);
      }
    }

    /**
    * Remove from the tree the first occurrence of item in a breadth-first
    * traversal of the tree.
    */
    public void remove(T item) throws NullPointerException {
      boolean successful = root.bt_remove_helper(item);
      if(!successful) {
        throw new NullPointerException("Object does not exist in the tree.");
      }
    }

    /**
    * Determines if item is in the tree. Throws a NullPointerException if no
    * nodes in the tree contain item.
    */
    public boolean contains(T item) {
      if(root != null && root.contents != null && root.contents.equals(item)) return true;
      return root.bt_contains_helper(item);
    }


    /**
    * Returns (a reference to) the first Node in the tree
    * (under a breadth-first search) containing item.
    * Throws a NullPointerException if no nodes in the tree contain item.
    */
    public Node<T> get(T item) throws NullPointerException {
      Node<T> obj = root.bt_get_helper(item);
      if(obj == null) {
        throw new NullPointerException();
      }
      return obj;
    }

    /**
    * Returns the root
    */
    public Node<T> getRoot() {
      return root;
    }

    /**
    * Return an iterator over the tree in a breadth-first order,
    * skipping null nodes.
    */
    public java.util.Iterator<T> breadthFirst() {
      return new MyIterable<T>(breadthFirstList()).iterator();
    }

    /**
    * Return an iterator over the tree in a depth-first order,
    * skipping null nodes.
    */
    public java.util.Iterator<T> depthFirst() {
      return new MyIterable<T>(depthFirstList()).iterator();
    }

    /**
     * Returns the String representation of the object
     */
    @Override
    public String toString() {
      if(root == null || root.contents == null) {
        return "Empty tree";
      }
      if(root.isLeaf()) {
        return "Root Contents: " + root.contents;
      }
      return root.printAll();
    }

    public static void main(String[] args) {
      BinaryTree<Integer> bt = new BinaryTree<Integer>();
      for(int i = 0; i < 10; i++) {
        bt.add(i);
      }
      for(int i = 0; i < 10; i++) {
        bt.remove(i);
      }
      System.out.println(bt);
    }
}
