/**
 * Test class for Set
 */
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SetTest {
  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Test
  public void testUnion() {
    Set<String> strings1 = new Set<String>();
    strings1.add("C"); strings1.add("A");
    strings1.add("B");
    strings1.add("A");
    Set<String> strings2 = new Set<String>();
    strings2.add("C");
    strings2.add("B");
    assertTrue(strings1.union(strings2).contains("A") && strings1.union(strings2).contains("B") && strings1.union(strings2).contains("C"));
  }

  @Test
  public void testIntersection() {
    Set<String> strings1 = new Set<String>();
    strings1.add("C"); strings1.add("A");
    strings1.add("B");
    strings1.add("A");
    Set<String> strings2 = new Set<String>();
    strings2.add("C");
    strings2.add("B");
    assertTrue(strings1.intersection(strings2).contains("B") && strings1.intersection(strings2).contains("C") && !strings1.intersection(strings2).contains("A"));
  }

  @Test
  public void sorted() {
    Set<String> strings = new Set<String>();
    strings.add("C"); strings.add("A");
    strings.add("B");
    MyIterable<String> iter = strings.sorted(false);
    ArrayList<String> t = new ArrayList<String>();
    for (String s: iter) {
      t.add(s);
    }
    ArrayList<String> n = new ArrayList<String>();
    n.add("A");
    n.add("B");
    n.add("C");
    assertTrue(t.equals(n));
  }
}
