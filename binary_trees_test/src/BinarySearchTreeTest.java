/**
 * Test class for BinarySearchTree
 */
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BinarySearchTreeTest {
  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Test
  public void testAdd() {
    BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
    bst.add(10); bst.add(20);
    for(int i = 0; i < 10; i++) {
      assertTrue(bst.add(i));
      assertTrue(bst.contains(i));
    }
    for(int i = 0; i < 10; i++) {
      assertTrue(bst.contains(i));
    }
  }

  @Test
  public void testRemove() {
    BinarySearchTree<Integer> bt = new BinarySearchTree<Integer>();
    for(int i = 0; i < 10; i++) {
      assertTrue(bt.add(i));
    }
    for(int i = 9; i >= 0; i--) {
      bt.remove(i);
      assertTrue(!bt.contains(i));
    }
    BinarySearchTree<String> strings = new BinarySearchTree<String>();
    strings.add("hello"); strings.add("computer science");
    strings.add("comp 500"); strings.add("Dr Z");
    strings.remove("hello");
    assertFalse(strings.contains("hello"));
  }
}
