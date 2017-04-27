import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Equation {

    public class Lhs implements Comparable<Lhs> {
        private final int i;
        private final int j;
        private final long sum;

        public Lhs(int i, int j) {
            this.sum = (long) i + (long) 2*j*j;
            this.i = i;
            this.j = j;
        }

        public int compareTo(Lhs that) {
            if      (this.sum < that.sum) return -1;
            else if (this.sum > that.sum) return +1;
            else if (this.i < that.i)     return -1;
            else if (this.i > that.i)     return +1;
            else                          return  0;
        }

        public String toString() {
            return i + " + "  + "2*" + j + "^2";
        }

         public void debug() {
            StdOut.println("lhs i: " + i + " j: " + j);
            StdOut.println("lhs sum: " + sum);
            StdOut.println();
        }

    }

    public class Rhs implements Comparable<Rhs> {
        private final int i;
        private final int j;
        private final long sum;   // i^3 + j^3, cached to avoid recomputation

        public Rhs(int i, int j) {
            this.sum = (long) 3*i*i*i + (long) 4*j*j*j*j;
            this.i = i;
            this.j = j;
        }

        public int compareTo(Rhs that) {
            if      (this.sum < that.sum) return -1;
            else if (this.sum > that.sum) return +1;
            else if (this.i < that.i)     return -1;
            else if (this.i > that.i)     return +1;
            else                          return  0;
        }

        public String toString() {
            return "3*" + i + "^3" + " + " + "4*" + j + "^4";
        }

        public void debug() {
            StdOut.println("rhs i: " + i + " j: " + j);
            StdOut.println("rhs sum: " + sum);
            StdOut.println();
        }

    }

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);

        // Main class
        Equation equation = new Equation();

        // initialize priority queues
        // Lhs = Left hand side
        // Rhs = Right hand side
        MinPQ<Lhs> lhsq = new MinPQ<Lhs>();
        MinPQ<Rhs> rhsq = new MinPQ<Rhs>();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                lhsq.insert(equation.new Lhs(i, j));
                rhsq.insert(equation.new Rhs(i, j));
            }
        }

        if(!lhsq.isEmpty()) {

            Lhs lhs = lhsq.delMin();
            Rhs rhs = rhsq.delMin();

            while (!lhsq.isEmpty()) {

                while(lhs.sum > rhs.sum) {
                    rhs = rhsq.delMin();
                }

                if(lhs.sum == rhs.sum) {
                    StdOut.println(lhs.toString());
                    StdOut.println(rhs.toString());
                }

                lhs = lhsq.delMin();
            }

        }

    }

}
