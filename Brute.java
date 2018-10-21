package s2;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class Brute{
	
	public static void main(String[] args){
		
		
        In in = new In();
        Out out = new Out();
        int N = in.readInt();
        Point[] points = new Point[N];
        Point[] pointSlope = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt(), y = in.readInt();
            points[i] = new Point(x, y);
            //points[i].draw();
        }
        //StdDraw.setXscale(0, 32768);
        //StdDraw.setYscale(0, 32768);
        
        // read in the input
        /*String filename = "src/s2/rs1423.txt";
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
        }*/
		//Stopwatch sw = new Stopwatch();
		Arrays.sort(points);
		for(int i = 0; i < N; i++) {
			Point p1 = points[i];
			for(int b = i + 1 ; b < N; b++) {
				Point p2 = points[b];
				double slope2 = p1.slopeTo(p2);
				for(int c = b + 1 ; c < N; c++) {
					Point p3 = points[c];
					double slope3 = p1.slopeTo(p3);
					if(slope2 == slope3) {
						for(int d = c + 1; d < N; d++) {
							Point p4 = points[d];
							double slope4 = p1.slopeTo(p4);
							if(slope2 == slope3 && slope2 == slope4 && slope3 == slope4) {
								StdOut.println(p1 + " -> "  + p2 + " -> " + p3 + " -> " + p4);
								//p1.drawTo(p2);
								//p1.drawTo(p3);
								//p1.drawTo(p4);
							}

						}
					}
				}
			}
		}
		//StdOut.println(sw.elapsedTime());
	}	
}