/**
 * Counts the number of occurrences of words in a text file
 *
 * Author: Jocelyn Shen
 * Date: May 4, 2017
 * Sources: https://docs.oracle.com/javase/tutorial/essential/io/file.html
*/
import java.lang.StringBuffer;
import java.io.*;
import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.*;
public class WordCounter {
  private String filename;
  private Map<String,Integer> wordCount;
  private Set<String> uniqueWords;

  public WordCounter(String f) {
    filename = f;
  }

  /**
  * Read in the file using the standard, basic I/O (input-output) for Java
  * Note: Streams are for reading and writing binary data from something
  */
  public String readFile(){
    StringBuffer str = new StringBuffer();                                      //StringBuffer is a mutable string
    Charset charset = Charset.forName("UTF-8");                                 //UTF-8 character encoding
    Path file = Paths.get(filename + ".txt");
    try (BufferedReader reader = Files.newBufferedReader(file, charset)) {      // Buffer: a memory area that buffered streams read
      String line = null;
      while ((line = reader.readLine()) != null) {                              //Readers and writers are on top of streams and converts bytes to characters and back, using a character encoding
        str.append(line + "\n");
      }
    }
    catch (IOException x) {
    System.err.format("IOException: %s%n", x);
    }
    return str.toString();
  }

  /**
  * Gets words from an input string
  */
  public static ArrayList<String> getWords(String input) {
    ArrayList<String> w = new ArrayList<String>();
    input = input.toLowerCase();
    String[] words = input.split("\\s+");
    for (int i = 0; i < words.length; i++) {
      words[i] = words[i].replaceAll("[^\\w]", "");
    }
    for(String elem: words) {
      w.add(elem);
    }
    return w;
  }

  /**
  * Returns a hashSet of unique words in input
  */
  public static Set<String> getUniqueWords(String input) {
    HashSet<String> hSet = new HashSet<String>();
    ArrayList<String> words = getWords(input);
    for(String word: words) {
      if(!hSet.contains(word)) {
        hSet.add(word);
      }
    }
    return hSet;
  }

  /**
  * Counts number of occurrences of word and adds information to a hashMap
  */
  public static Map<String,Integer> getCounts(ArrayList<String> words) {
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    ArrayList<String> uniqueWords = new ArrayList<String>();
    for(String word: words) {
      if(!uniqueWords.contains(word)) {
        map.put(word, Collections.frequency(words, word));
        uniqueWords.add(word);
      }
    }
    return map;
  }

  /**
  * Write out the contents of wordCount to a file
  */
  public void writeOut(Map<String, Integer> wordCount) {
    Path file = Paths.get(filename + "_word_count.txt");
    Charset charset = Charset.forName("UTF-8");
    try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
      for (Map.Entry<String, Integer> entry : wordCount.entrySet())
      {
        writer.write(entry.getKey() + " : " + entry.getValue());
        writer.newLine();
      }
    } catch (IOException x) {
      System.err.format("IOException: %s%n", x);
    }
  }

  /**
  * Write out the contents of uniqueWords to a file
  */
  public void writeOut(Set<String> uniqueWords) {
    Path file = Paths.get(filename + "_unique_words.txt");
    Charset charset = Charset.forName("UTF-8");
    try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
      for (String s : uniqueWords)
      {
        writer.write(s);
        writer.newLine();
      }
    } catch (IOException x) {
      System.err.format("IOException: %s%n", x);
    }
  }

  public static void main(String[] args) {
    //String inputFileName = args[0];
	String inputFileName = "test";
    WordCounter w = new WordCounter(inputFileName);
    w.writeOut(w.getCounts(w.getWords(w.readFile())));
    w.writeOut(w.getUniqueWords(w.readFile()));
  }
}
