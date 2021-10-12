package algo;

import base.Point;
import base.Polyedre;
import base.Segment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Fonctions {

    public static List<Segment> findConvexPasOuf(List<Point> points) {

        assert points.size() >= 3;

        List<Segment> candidate = new ArrayList<>();

        for (Point currentTestedPoint : points) {
            List<Point> neighbors = new ArrayList<>(points);
            neighbors.remove(currentTestedPoint);

            for (Point neighbor : neighbors) {
                Segment currentSegment = new Segment(currentTestedPoint, neighbor);

                List<Point> segmentNeighbors = new ArrayList<>(neighbors);
                segmentNeighbors.remove(neighbor);

                if (isSide(currentSegment, segmentNeighbors) && !candidate.contains(currentSegment)) 
                	candidate.add(currentSegment);
            }
        }


        return candidate;
    }

    private static boolean isSide(Segment segment, List<Point> neighbors) {
        double sign = 0;
        for (Point neighbor : neighbors) {
            double produitVectoriel = segment.toVecteur().produitVectoriel(new Segment(segment.debut, neighbor).toVecteur());

            if (produitVectoriel != 0) {
                if (sign == 0) sign += produitVectoriel;
                else {
                    if ((sign >= 0) != (produitVectoriel >= 0)) 
                    	return false;
                }
            }
        }
        return true;
    }

    public static Polyedre findConvexPlusMieux(List<Point> points) {

        assert points.size() >= 3;

        List<Point> candidate = new ArrayList<>();

        Point topmost = null;
        List<Point> neighbors = null;
        for (Point point : points) {
            List<Point> currentNeighbors = new ArrayList<>(points);
            currentNeighbors.remove(point);
            if (isTopMost(point, currentNeighbors)) {
                topmost = point;
                neighbors = currentNeighbors;
                break;
            }
        }

        assert topmost != null;

        Double min = null;
        for (Point p : neighbors) {

        }

        return null;
    }

    private static boolean isTopMost(Point p, List<Point> neighbors) {
        for (Point neighbor : neighbors)
            if (neighbor.y < p.y) return false;
        return true;
    }
    
    public static List<Polyedre> splitPolyedre(Polyedre poly) {
    	List<Polyedre> res = new ArrayList<Polyedre>();
    	
    	List<Point> allPointExceptFirst = new ArrayList<>(Arrays.asList(poly.points));
    	Point firstPoint = poly.points[0];
    	
    	for (int i = 0; i < allPointExceptFirst.size()-2; i++) {
			Point currentPoint = allPointExceptFirst.get(i);
			
			Segment ab = null;
			Segment bc = null;
			
			if (i < allPointExceptFirst.size() - 1) {
				ab = new Segment(currentPoint, allPointExceptFirst.get(i+1));
				bc = new Segment(allPointExceptFirst.get(i+1), allPointExceptFirst.get(i+2));
				
			}
			else {
				ab = new Segment(currentPoint, allPointExceptFirst.get(i+1));
				bc = new Segment(allPointExceptFirst.get(i+1), firstPoint);
			}
						
			if (ab.toVecteur().produitVectoriel(bc.toVecteur()) < 0) {
				res.add(
						new Polyedre(
								new Point[] {ab.debut, ab.fin, bc.fin}
								)
						);
			}	
		}
    	
    	return res;
    }    	
}
