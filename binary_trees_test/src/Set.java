/**
 * Implementation of a set
 *
 * Author: Jocelyn Shen, Michelle Chao
 * Date: April 6, 2017
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Set<T extends Comparable<T>> extends BinarySearchTree<T> {
  public Set() {
  }

  /**
  * Test if the set is empty
  */
  @Override
  public boolean empty() {
    if (super.empty()) {
      return true;
    }
    return false;
  }

  /**
  * Empty the set
  */
  @Override
  public void clear() {
    super.clear();
  }

  /**
  * Add item. Returns whether item was successfully added,
  * which is false precisely when another copy of item is
  * already in the set.
  */
  @Override
  public boolean add(T item) {
    return super.setAdd(item);
  }

  /**
  * Remove item from the set
  */
  @Override
  public void remove(T item) {
    super.remove(item);
  }

  /**
  * Determines if item is in the set
  */
  @Override
  public boolean contains(T item) {
    return super.contains(item);
  }

  /**
  * Returns a sorted iterable
  */
  @Override
  public MyIterable<T> sorted(boolean reversed) {
    return super.sorted(reversed);
  }

  /**
  * performs the union of this with other, i.e. the set
  * containing all the elements of this or other.
  */
  public Set<T> union(Set<T> other) {
    Set<T> inter = new Set<T>();
    MyIterable<T> iterator1 = sorted(false);
    MyIterable<T> iterator2 = other.sorted(false);
    for(T i: iterator1) {
      if(inter.empty()) {
        inter.add(i);
      }
      else {
        if(!inter.contains(i)) {
          inter.add(i);
        }
      }
    }
    for (T j: iterator2) {
      if(!inter.contains(j)) {
        inter.add(j);
      }
    }
    return inter;
  }

  /**
  * performs the intersection of this with other, i.e. the set
  * containing all the elements present in both this and other.
  */
  public Set<T> intersection(Set<T> other) {
    Set<T> inter = new Set<T>();
    MyIterable<T> iterator1 = sorted(false);
    MyIterable<T> iterator2 = other.sorted(false);
    for(T i: iterator1) {
      for(T j: iterator2) {
        if(i.equals(j)) {
          inter.add(i);
        }
      }
    }
    return inter;
  }

  @Override
  public String toString() {
    return super.toString();
  }

  /**
  * Create a main method for Set that takes in the name of a text file via command-line argument
  * and creates an ordered dictionary of words in the text, output as another
  * text file with one word per line.
  */
  public static void main(String[] args) {
    Set<String> dictionary = new Set<String>();
    String fileName= args[0] + ".txt";
    try{
      FileReader inputFile = new FileReader(fileName);
      BufferedReader bufferReader = new BufferedReader(inputFile);
      String line;
      while ((line = bufferReader.readLine()) != null)   {
          String[] parts = line.split("\\s+");
          for (int i = 0; i < parts.length; i++) {
            dictionary.add(parts[i].toLowerCase());
          }
      }
      MyIterable<String> iter = dictionary.sorted(false);
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("TestOutput.txt")));
      for (String s: iter) {
        out.println(s);
        System.out.println(s);
      }
      bufferReader.close();
      out.close();
      }catch(Exception e){
          System.out.println("Error while reading file line by line:" + e.getMessage());
      }
  }
}
