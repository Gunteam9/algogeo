import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import algo.Delaunay;
import base.Point;
import base.Polyedre;
import base.Segment;
import fenetre.Fenetre;
import fenetre.Panneau;

public class Main {
	
    private static Panneau panneau = new Panneau();
    
    private static Random rand = new Random();
    
	public static void main(String[] arg) {

        //convexPasOuf();
        //splitPolyedre();
		delaunay();

        Fenetre fenetre = new Fenetre(panneau);
        fenetre.setVisible(true);
	}
	
	public static void convexPasOuf() {
		Point A = new Point (100,100);
		Point B = new Point (100,10);
        Point C = new Point (10,10);
        Point D = new Point (10,100);
        Point X = new Point(55, 55);
        Point X1 = new Point(55, 160);

        List<Point> points = new ArrayList<>();
        //Convex PasOuf
        points.add(A);
        points.add(B);
        points.add(C);
	 	points.add(D);
        points.add(X);
        points.add(X1);
        
        panneau.add(points);
        
        //ConvexPasOuf
        List<Segment> convex = algo.Fonctions.findConvexPasOuf(points);
        System.out.println(convex);
        System.out.println(convex.size());
        panneau.addSegments(convex);
	}
	
	public static void splitPolyedre() {
		
		Point A = new Point(100, 100);
		Point B = new Point(120, 200);
		Point C = new Point(200, 160);
		Point D = new Point(160, 140);
		Point E = new Point(190, 70);
		Point F = new Point(140, 100);
		Point G = new Point(140, 20);
		Point H = new Point(120, 70);
		Point I = new Point(80, 60);
		Point J = new Point(50, 100);
		Point K = new Point(70, 170);
		
		Point[] points = new Point[] {A, B, C, D, E, F, G, H, I, J, K};
		
		System.out.println(new Segment(A, B).toVecteur().produitVectoriel(new Segment(B, C).toVecteur()));
		
        List<Polyedre> polys = algo.Fonctions.splitPolyedre(new Polyedre(points) );
        for (Polyedre polyedre : polys) {
			panneau.add(polyedre);
		}
		
		//panneau.add(new Polyedre(points));
	}
	
	public static void delaunay() {
		Point A = new Point(100, 100);
		Point B = new Point(120, 200);
		Point C = new Point(200, 160);
		Point D = new Point(160, 140);
		Point E = new Point(190, 70);
		Point F = new Point(140, 100);
		Point G = new Point(140, 20);
		Point H = new Point(120, 70);
		Point I = new Point(80, 60);
		Point J = new Point(50, 100);
		Point K = new Point(70, 170);
		
		int nbPoint = 2800;
		Point[] points = new Point[nbPoint];
		
		//Random points
		ArrayList<Point> newPoints = new ArrayList<Point>();
		for (int i = 0; i < nbPoint; i++) {
			newPoints.add(new Point(rand.nextInt(500), rand.nextInt(500)));
		}
		points = newPoints.toArray(points);
		
		//Already defined points
		//points = new Point[] {A, B, C, D, E, F, G, H, I, J, K};
		
		
		if (points.length < 50)
			panneau.add(Arrays.asList(points));
		
		int[] x = new int[points.length], y = new int[points.length];
		for (int i = 0; i < points.length; i++) {
			Point p = points[i];
			x[i] = (int)p.x;
			y[i] = (int)p.y;
		}
		
		Delaunay delaunay = new Delaunay(x, y);
		
		int ppv;
    	int[][] voisins = new int[x.length][x.length];
    	int[] longueur = new int[x.length];
    	int kk = 0;
    	int vsd, vsg;
    	int[] envconv = new int[x.length];
    	ArrayList<Polyedre> polys = new ArrayList<Polyedre>();
    	
    	for (int i = 0; i < x.length; i++) { /* on prend chaque site */ 
    		ppv = delaunay.plusProcheVoisin(i); 
    		voisins[i][0] = ppv;
    		longueur[i] = 1;
    		kk = 0;
    		
    		do {
    			vsd = delaunay.voisinSuivantDroite(i, voisins[i][kk]);
    			longueur[i]++; 
    			voisins[i][kk+1] = vsd;
    			kk++;
    		} while(vsd != ppv && vsd != -1);
    		
    		if (vsd == -1) {
    			envconv[i] = 1;
    			do { 
    				vsg = delaunay.voisinSuivantGauche(i, voisins[i][0]);
    				if (vsg != -1) { 
    					for (int kkk = longueur[i] - 1; kkk >=0; kkk--) 
    						voisins[i][kkk+1] = voisins[i][kkk];
    					voisins[i][0] = vsg;
    					longueur[i]++;
    				}
    			} while(vsg != -1);
    		}
    	}
    	
    	for (int i = 0; i < x.length; i++) /* dessin des triangles autour de i */
    		for (int ii = 0; ii < longueur[i] - 1; ii++) 
    			if (voisins[i][ii+1] != -1) {
//    				panneau.add(new Segment(new Point(x[i], y[i]), new Point(x[voisins[i][ii]], y[voisins[i][ii]])));
//    				panneau.add(new Segment(new Point(x[i], y[i]), new Point(x[voisins[i][ii+1]], y[voisins[i][ii+1]])));
//    				panneau.add(new Segment(new Point(x[voisins[i][ii]], y[voisins[i][ii]]), new Point(x[voisins[i][ii+1]], y[voisins[i][ii+1]])));
//    				
    				polys.add(new Polyedre(new Point[] {
    						new Point(x[i], y[i]),
    						new Point(x[voisins[i][ii]], y[voisins[i][ii]]),
    						new Point(x[voisins[i][ii+1]], y[voisins[i][ii+1]])
    				}));
    			} 
    		
    	polys.forEach(o -> panneau.add(o));
	}
}
