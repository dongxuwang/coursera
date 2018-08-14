import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {

    private int numberOfSegments;
    private Point[][] linePoints;
    private Point[] points;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] pointArray) {
        assureNotNull(pointArray);
        int numberOfPoints = pointArray.length;
        points = new Point[numberOfPoints];
        Point[] slopePoints = new Point[numberOfPoints];
        System.arraycopy(pointArray, 0, points, 0, pointArray.length);
        System.arraycopy(pointArray, 0, slopePoints, 0, pointArray.length);
        Arrays.sort(points);
        assureNotDuplicated(points);
        linePoints = new Point[numberOfPoints][2];

        System.arraycopy(pointArray, 0, points, 0, pointArray.length);

        for (Point point : points) {
            Arrays.sort(slopePoints, point.slopeOrder());

            int segmentTail = 1, segmentHead = 1;
            while(segmentTail < numberOfPoints){
                if (segmentTail + 1 == numberOfPoints || point.slopeTo(slopePoints[segmentTail]) != point.slopeTo(slopePoints[segmentTail + 1])) {
                    if (segmentTail - segmentHead > 1) {
                        Point[] segmentPoints = new Point[segmentTail - segmentHead + 2];
                        segmentPoints[0] = point;
                        System.arraycopy(slopePoints, segmentHead, segmentPoints, 1, segmentTail - segmentHead + 1);
                        Arrays.sort(segmentPoints);

                        if (!exist(linePoints, segmentPoints[0], segmentPoints[segmentTail - segmentHead + 1])) {
                            linePoints[numberOfSegments][0] = segmentPoints[0];
                            linePoints[numberOfSegments][1] = segmentPoints[segmentTail - segmentHead + 1];
                            numberOfSegments++;
                        }
                    }
                    segmentHead = segmentTail + 1;
                }
                ++segmentTail;
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

    private boolean exist(Point[][] lineSegments, Point start, Point end) {
        for (int i = 0; i < numberOfSegments; ++i) {
            if (lineSegments[i][0].compareTo(start) == 0 && lineSegments[i][1].compareTo(end) == 0) {
                return true;
            }
        }
        return false;
    }

    // the number of line segments
    public int numberOfSegments() {
        return numberOfSegments;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] lineSegments = new LineSegment[numberOfSegments];
        for (int i = 0; i < numberOfSegments; ++i) {
            lineSegments[i] = new LineSegment(linePoints[i][0], linePoints[i][1]);
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}