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
			Arrays.sort(points, i, N); // �arf a� sorta eftir Point st�r�um til a� finna �essa t�lu.
			Point p = points[i]; // n� � punktin eftir st�r�ar��
			Arrays.sort(points, i+1, N, p.SLOPE_ORDER); // Ra�a fylkin �tfr� st�r� � slope milli allra talana og P
			int origin = i+1;  // Origin punkturinn heldur um hva� vi� erum me� m�rg eins � r��. �etta er til a� h�gt s� a� kanna hvort jafnst�r st�k eru � fylkinu.
				for(int a = i+2; a < N; a++) {
					double a_slope = p.slopeTo(points[origin]);
					double b_slope = p.slopeTo(points[a]);
					if(a_slope != b_slope) { 
						if(a - origin > 2) { // Ef �etta stak er ekki �a� sama og s��asta og ef munurinn � origin og a er st�rri en 2
							// �a� ���ir a� 3 st�k � r�� voru alveg eins, en fj�r�a ekki. �� m� prenta �t st�kin � undarn
							printLine(p, Arrays.copyOfRange(points, origin-1, a));
						}
						origin = a; // origin �arf a� elta a EF a_slope er ekki �a� sama og b_slope
					}
					if(points.length - origin > 2 && N == (a+1)) {
						printLine(p, Arrays.copyOfRange(points, origin-1, N));// Ef �a� � a� prenta �ftustu �rj�(e�a fleiri) st�kin eru eins og �a� � a� prenta �au �t. �n �essaru if-setningu �� myndi
																				//hann aldrei prenta �etat �t �taf �a� er bara prenta� �t ef a_slope != b_slope. En � �essu tilfelli yr�i �a� aldrei satt.
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