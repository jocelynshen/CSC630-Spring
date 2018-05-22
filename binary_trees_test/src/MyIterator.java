import java.util.ArrayList;
import java.util.Iterator;

public class MyIterator<T extends Comparable<T>> implements Iterator<T> {
      // Need something to do with the T's, so we'll just make an array:
      private ArrayList<T> stuffToIterateOver;
      private int index = 0;
      public MyIterator(ArrayList<T> stuff) {
        stuffToIterateOver = stuff;
      }

      @Override
      public boolean hasNext() {
        return index < stuffToIterateOver.size();
      }

      @Override
      public T next() {
        return stuffToIterateOver.get(index++);
      }
}
