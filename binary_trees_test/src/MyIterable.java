import java.util.ArrayList;
import java.util.Iterator;

public class MyIterable<T extends Comparable<T>> implements Iterable<T> {
        private final ArrayList<T> stuffToIterateOver;

        public MyIterable(ArrayList<T> stuff) {
            stuffToIterateOver = stuff;
        }

        @Override
        public Iterator<T> iterator() {
            return new MyIterator<T>(stuffToIterateOver);
        }
    }