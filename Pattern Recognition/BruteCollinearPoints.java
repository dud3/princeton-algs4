import java.util.*;

public class BruteCollinearPoints {

    private LineSegment[] segs;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        validate(points);

        ArrayList<LineSegment> foundSegments = new ArrayList<>();

        Point[] pointsC = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsC);

        for (int p = 0; p < pointsC.length - 3; p++) {
            for (int q = p + 1; q < pointsC.length - 2; q++) {
                for (int r = q + 1; r < pointsC.length - 1; r++) {
                    for (int s = r + 1; s < pointsC.length; s++) {
                        if (
                            pointsC[p].slopeTo(pointsC[q])
                            ==
                            pointsC[p].slopeTo(pointsC[r])
                            &&
                            pointsC[p].slopeTo(pointsC[q])
                            ==
                            pointsC[p].slopeTo(pointsC[s])
                        ) {

                            foundSegments.add(
                                new LineSegment(pointsC[p], pointsC[s])
                            );
                        }
                    }
                }
            }
        }

        segs = foundSegments.toArray(new LineSegment[foundSegments.size()]);
    }

    public int numberOfSegments() {
        return segs.length;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(segs, numberOfSegments());
    }

    private void validate(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < points.length - 1; i++) {
            if (points[i] == null) {
                throw new NullPointerException();
            }

            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }
}
