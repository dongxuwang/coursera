import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {

    private Point[] points;
    private int[][] segmentTupleIndexes;
    private int number;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] pointArray) {
        assureNotNull(pointArray);

        points = new Point[pointArray.length];
        System.arraycopy(pointArray, 0, points, 0, points.length);

        Arrays.sort(points);

        assureNotDuplicated(points);

        segmentTupleIndexes = new int[points.length][4];

        for (int i = 0; i < points.length - 3; ++i)
            for (int j = 1; j < points.length - 2; ++j)
                for (int k = 2; k < points.length - 1; ++k)
                    for (int m = 3; m < points.length; ++m) {
                        int[] tuple = new int[]{i, j, k, m};
                        if (isAsc(tuple) && slopesEqual(points, tuple)) {
                            segmentTupleIndexes[number++] = tuple;
                        }
                    }

    }

    private void assureNotNull(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();
        for (Point point : points)
            if (point == null)
                throw new IllegalArgumentException();

    }

    private void assureNotDuplicated(Point[] points) {
        for (int i = 0; i < points.length - 1; ++i)
            if (points[i].compareTo(points[i + 1]) == 0) throw new IllegalArgumentException();
    }

    private boolean isAsc(int[] tuple) {
        int p = tuple[0];
        int q = tuple[1];
        int r = tuple[2];
        int s = tuple[3];
        return p < q && q < r && r < s;
    }

    private boolean slopesEqual(Point[] points, int[] tuple) {
        int p = tuple[0];
        int q = tuple[1];
        int r = tuple[2];
        int s = tuple[3];
        double s1 = points[p].slopeTo(points[q]);
        double s2 = points[q].slopeTo(points[r]);
        double s3 = points[r].slopeTo(points[s]);
        return s1 == s2 && s2 == s3;
    }

    // the number of line segments
    public int numberOfSegments() {
        return number;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] lineSegments = new LineSegment[number];
        for (int i = 0; i < number; ++i) {
            lineSegments[i] = new LineSegment(points[segmentTupleIndexes[i][0]], points[segmentTupleIndexes[i][3]]);
        }
        return lineSegments;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        collinear.segments();
        collinear.segments();
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
        }
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
        }
        StdDraw.show();
    }
}