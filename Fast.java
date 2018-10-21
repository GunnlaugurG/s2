package s2;

import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
/*
 * 15
10 0
8 2
2 8
0 10
20 0
18 2
2 18

10 20
30 0
0 30
20 10
13 0
11 3
5 12
9 6
 */
public class Fast{
	
	public static void main(String[] args) {
		
		//StdDraw.setXscale(0, 32768);
        //StdDraw.setYscale(0, 32768);    
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

        
        // read in the input
        /*String filename = "src/s2/input40.txt";
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
        }*/
        //Stopwatch sw1 = new Stopwatch();
		Arrays.sort(points);
		//StdOut.println(sw1.elapsedTime());
		//Stopwatch sw = new Stopwatch();
		
		for(int i = 0 ; i < N; i++) {
			Arrays.sort(points, i, N); // Þarf að sorta eftir Point stærðum til að finna þessa tölu.
			Point p = points[i]; // næ í punktin eftir stærðaröð
			Arrays.sort(points, i+1, N, p.SLOPE_ORDER); // Raða fylkin útfrá stærð á slope milli allra talana og P
			int origin = i+1;  // Origin punkturinn heldur um hvað við erum með mörg eins í röð. Þetta er til að hægt sé að kanna hvort jafnstór stök eru í fylkinu.
				for(int a = i+2; a < N; a++) {
					double a_slope = p.slopeTo(points[origin]);
					double b_slope = p.slopeTo(points[a]);
					if(a_slope != b_slope) { 
						if(a - origin > 2) { // Ef þetta stak er ekki það sama og síðasta og ef munurinn á origin og a er stærri en 2
							// það þýðir að 3 stök í röð voru alveg eins, en fjórða ekki. Þá má prenta út stökin á undarn
							printLine(p, Arrays.copyOfRange(points, origin-1, a));
						}
						origin = a; // origin þarf að elta a EF a_slope er ekki það sama og b_slope
					}
					if(points.length - origin > 2 && N == (a+1)) {
						printLine(p, Arrays.copyOfRange(points, origin-1, N));// Ef það á að prenta öftustu þrjú(eða fleiri) stökin eru eins og það á að prenta þau út. Án þessaru if-setningu þá myndi
																				//hann aldrei prenta þetat út útaf það er bara prentað út ef a_slope != b_slope. En í þessu tilfelli yrði það aldrei satt.
					}
				}
			}
		//StdOut.println("Time: " + sw1.elapsedTime());
	}
	private static void printLine(Point Origin, Point line[]) {

			line[0] = Origin;
			Arrays.sort(line);
			for(int i = 0; i < line.length-1; i++) {
				StdOut.print(line[i] + " -> ");
			}
			StdOut.println(line[line.length-1]);
			line[0].drawTo(line[line.length-1]);
	}
}