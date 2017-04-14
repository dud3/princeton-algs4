import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> q = new RandomizedQueue<String>();

        String item;
        while (!StdIn.isEmpty())  {  // rather than hasNext()
            item = StdIn.readString();
            q.enqueue(item);
        }
        Iterator<String> itr = q.iterator();
        for (int i=0; i < k; i++) {
            StdOut.println(itr.next());
        }
    }

}
