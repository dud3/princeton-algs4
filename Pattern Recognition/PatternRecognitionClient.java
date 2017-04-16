import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class PatternRecognitionClient {

    public static void main(String[] args) {

        if(args.length != 2) {
            System.out.println("Usage E.x.1: java PatternRecognitionClient file.txt brute");
            System.out.println("Usage E.x.2: java PatternRecognitionClient file.txt fast");
            System.exit(0);
        }

        // read the N points from a file
        Point[] points = getPointsFromTestFile(args[0]);

        // draw the points
        // StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        if(args[1].equals("brute")) {
            BruteCollinearPoints brutecollinear = new BruteCollinearPoints(points);
            for (LineSegment segment : brutecollinear.segments()) {
                StdOut.println(segment);
                segment.draw();
            }
        }

        if(args[1].equals("fast")) {
            FastCollinearPoints collinear = new FastCollinearPoints(points);
            for (LineSegment segment : collinear.segments()) {
                StdOut.println(segment);
                segment.draw();
            }
        }

    }

    public static Point[] getPointsFromTestFile(String fileName) {
        In in = new In(fileName);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        return points;
    }

}
