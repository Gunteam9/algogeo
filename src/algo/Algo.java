package algo;

import base.Point;
import base.Segment;
import base.Vecteur;

public class Algo {
	
	public static boolean isBetween(Segment seg, Point p) {
		Vecteur v1 = seg.toVecteur();
		Vecteur v2 = new Segment(seg.debut, p).toVecteur();
		
		double ps = v1.produitScalaire(v2);
		
		if (ps >= 0 && v1.produitScalaire(v1) >= ps) {
			return v1.produitVectoriel(v2) == 0;
		}
		return false;
	}

}
