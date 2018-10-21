package s2;

import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdDraw;

/*************************************************************************
 * Compilation: javac Point.java Execution: Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 * @author Magnus M. Halldorsson, email: mmh@ru.is
 * 
 * 10 
4000 30000
3500 28000
3000 26000
2000 22000
1000 18000
13000 21000
23000 16000
28000 13500
28000 5000
28000 1000
 *************************************************************************/
public class Point implements Comparable<Point> {

    public final int x, y;
    //public final Comparator<Point> SLOPE_ORDER;
    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SLOPE_ORDER();
    // create the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        //SLOPE_ORDER = new SLOPE_ORDER();
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        // TODO: Implement this

    	if(x == that.x && y == that.y) {
    		return Double.NEGATIVE_INFINITY;
    	}
    	else if(x == that.x) {
    		return Double.POSITIVE_INFINITY;
    	}
    	else if(y == that.y) {
    		return 0;
    	}
    	else {
    		return (double)(that.y - y) / (double)(that.x - x);
    	}
    }

    /**
     * Is this point lexicographically smaller than that one? comparing
     * y-coordinates and breaking ties by x-coordinates
     */
    public int compareTo(Point that) {
        // TODO: Implement this
		if(that == null){
			throw new java.lang.NullPointerException(); 
		}
		int compared;
		compared = y - that.y;
		if(compared == 0)
		{
			compared = x - that.x;
		}
		if(compared <= 0) {
			return -1;
		}
		else {return 1;}
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
	private class SLOPE_ORDER  implements Comparator<Point>
	{
		public int compare(Point point1, Point point2) {
			double slope1= slopeTo(point1);
			double slope2= slopeTo(point2);
			if(slope1< slope2) {
				return -1;
			}
			else if (slope1 > slope2) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}

    public static void main(String[] args) {
        /*
         * Do not modify
         */
        In in = new In();
        Out out = new Out();
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            points[i] = new Point(x, y);
        }
        out.printf("Testing slopeTo method...\n");
        for (int i = 1; i < points.length; i++) {
            out.println(points[i].slopeTo(points[i - 1]));
        }
        out.printf("Testing compareTo method...\n");
        for (int i = 1; i < points.length; i++) {
            out.println(points[i].compareTo(points[i - 1]));
        }
        out.printf("Testing SLOPE_ORDER comparator...\n");
        for (int i = 2; i < points.length; i++) {
            out.println(points[i].SLOPE_ORDER.compare(points[i - 1],
                    points[i - 2]));
        }
    }
}
