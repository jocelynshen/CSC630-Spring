/**
 * Test class for BinaryTree
 */
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BinaryTreeTest {
  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Test
  public void test() {
    BinaryTree<Integer> bt = new BinaryTree<Integer>();

    for(int i = 0; i < 10; i++) {
      bt.add(i);
      assertTrue(bt.contains(i));
    }
    for(int i = 0; i < 10; i++) {
      assertTrue(bt.get(i).getContents().equals(i));
    }

    for(int i = 0; i < 10; i++) {
      bt.remove(i);
    }
    System.out.println(bt);
    assertTrue(bt.empty());
    bt.add(100); bt.clear();
    assertTrue(bt.empty());
    assertTrue(bt.toString().equals("Empty tree"));

  }

  @Test
  public void testAdd() {
    BinaryTree<Integer> bt = new BinaryTree<Integer>();
    bt.add(10); bt.add(20);
    for(int i = 0; i < 10; i++) {
      assertTrue(bt.add(i));
      assertTrue(bt.contains(i));
    }
    for(int i = 0; i < 10; i++) {
      assertTrue(bt.contains(i));
    }

  }

  @Test
  public void testRemove() {
    BinaryTree<Integer> bt = new BinaryTree<Integer>();
    for(int i = 0; i < 10; i++) {
      assertTrue(bt.add(i));
    }
    for(int i = 9; i >= 0; i--) {
      bt.remove(i);
      assertTrue(!bt.contains(i));
    }
    BinaryTree<String> strings = new BinaryTree<String>();
    strings.add("hello"); strings.add("computer science");
    strings.add("comp 500"); strings.add("Dr Z");
    strings.remove(new String("hello"));
    exception.expect(NullPointerException.class);
    strings.remove(new String("s"));
  }

  @Test
  public void testContains() {
    BinaryTree<Integer> bt = new BinaryTree<Integer>();
    for(int i = 0; i < 1000; i++) {
      bt.add(i);
      assertTrue(bt.contains(i));
    }
    bt.remove(999); bt.remove(500); bt.remove(100);
    for(int i = 0; i < 1000; i++) {
      if(i == 999 || i == 500 || i == 100) continue;
      assertTrue(bt.contains(i));
    }
  }

  @Test
  public void testGet() {
    BinaryTree<Integer> bt = new BinaryTree<Integer>();
    for(int i = 0; i < 1000; i++) {
      bt.add(i);
      assertTrue(bt.get(i).getContents() == i);
    }
    bt.remove(999); bt.remove(500); bt.remove(100);
    for(int i = 0; i < 1000; i++) {
      if(i == 999 || i == 500 || i == 100) continue;
      assertTrue(bt.get(i).getContents() == i);
    }
  }

  @Test
  public void testBreadthFirst() {
    BinaryTree<Integer> bt = new BinaryTree<Integer>();
    Iterator<Integer> breadth = bt.breadthFirst();
    assertFalse(breadth.hasNext());
    for(int i = 0; i < 100; i++) {
      bt.add(i);
    }
    breadth = bt.breadthFirst();
    int count = 0;
    while(breadth.hasNext()) {
      count++;
      breadth.next();
    }
    assertTrue(count == 100);

  }

  @Test
  public void testDepthFirst() {
    BinaryTree<Integer> bt = new BinaryTree<Integer>();
    for(int i = 0; i < 100; i++) {
      bt.add(i);
    }
//    System.out.println(bt);
//    MyIterable<Integer> a = bt.breadthFirst();
//    for(Integer i: a) {
//      System.out.println(i);
//    }
    Iterator<Integer> d = bt.depthFirst();
//    for(Integer i: d) {
//      System.out.println(i);
//    }
  }

  @Test
  public void testClearEmpty() {
    BinaryTree<Integer> bt = new BinaryTree<Integer>();
    bt.clear();
    assertTrue(bt.empty());
    bt.add(1);
    bt.clear();
    assertTrue(bt.empty());
  }

}
