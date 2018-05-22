/**
 * Test class for WordCounter.
 */

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Test;

public class WordCounterTest {
  
  @Test
  /**
   * Test getWords method
   */
  public void testGetWords() {
	  WordCounter w = new WordCounter("test");
	  List<String> x = new ArrayList<>(Arrays.asList("its", "a", "little", "bit", "funny", "this", "feeling", "inside", "im", "not", "one", "of", "those", "who", "can", "easily", "hide"));
	  assertTrue(w.getWords(w.readFile()).equals(x));
  }
  
  @Test
  /**
   * Test getUniqueWords method
   */
  public void testGetUniqueWords() {
	  WordCounter w = new WordCounter("test");
	  List<String> x = new ArrayList<>(Arrays.asList("a", "im", "one", "this", "its", "feeling", "bit", "inside", "can", "not", "hide", "of", "easily", "funny", "those", "little", "who"));
	  Set<String> set = new HashSet<String>(x);
	  assertTrue(w.getUniqueWords(w.readFile()).equals(set));
  }
  
  @Test
  /**
   * Test getCounts
   */
  public void testGetCounts() {
	  WordCounter w = new WordCounter("test");
	  HashMap<String, Integer> map = new HashMap<String, Integer>();
	  HashMap<String, Integer> m_true = new HashMap<String, Integer>();
	  m_true.put("a", Integer.valueOf(1));
	  m_true.put("im", Integer.valueOf(1));
	  m_true.put("one", Integer.valueOf(1));
	  m_true.put("this", Integer.valueOf(1));
	  m_true.put("its", Integer.valueOf(1));
	  m_true.put("feeling", Integer.valueOf(1));
	  m_true.put("bit", Integer.valueOf(1));
	  m_true.put("inside", Integer.valueOf(1));
	  m_true.put("can", Integer.valueOf(1));
	  m_true.put("not", Integer.valueOf(1));
	  m_true.put("hide", Integer.valueOf(1));
	  m_true.put("of", Integer.valueOf(1));
	  m_true.put("easily", Integer.valueOf(1));
	  m_true.put("funny", Integer.valueOf(1));
	  m_true.put("those", Integer.valueOf(1));
	  m_true.put("little", Integer.valueOf(1));
	  m_true.put("who", Integer.valueOf(1));
	 assertTrue(w.getCounts(w.getWords(w.readFile())).equals(m_true));
  }
}
