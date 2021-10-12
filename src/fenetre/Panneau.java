package fenetre;
import java.awt.*;
import javax.swing.*;

import base.Point;
import base.Polyedre;
import base.Segment;

import java.util.*;
import java.util.List;

public class Panneau extends JPanel {
	ArrayList<Point> points = new ArrayList<>();
	ArrayList<Segment> segments = new ArrayList<>();
	ArrayList<Polyedre> polyedres = new ArrayList<>();
	
	Random rand = new Random();

	public static final int TAILLEPOINT = 2;
	
	public void add(Point point) {
		points.add(point);
	}

    public void add(List<Point> points) { for (Point point : points) this.add(point); }

	public void add(Segment segment) {
		segments.add(segment);
	}

    public void addSegments(List<Segment> segments) { for (Segment segment : segments) this.add(segment); }

	public void add(Polyedre polyedre) {
		polyedres.add(polyedre);
	}

	private void draw(Point point, Graphics g) {
		g.drawOval((int) point.x-TAILLEPOINT, (int) point.y-TAILLEPOINT, 2*TAILLEPOINT, 2*TAILLEPOINT);
		g.setColor(Color.red);
		g.fillOval((int) point.x-TAILLEPOINT, (int) point.y-TAILLEPOINT, 2*TAILLEPOINT, 2*TAILLEPOINT);
		g.setColor(Color.black);
	}
	
	private void draw(Segment segment, Graphics g) {
		g.drawLine((int) segment.debut.x, (int) segment.debut.y, (int) segment.fin.x, (int) segment.fin.y);
		
	}

	private void draw(Polyedre polyedre, Graphics g) {
		int taille = polyedre.points.length;
		int[] x = new int[taille];
		int[] y = new int[taille];
		
		for (int i = 0; i<taille; i++) {
			x[i] = (int) polyedre.points[i].x;
			y[i] = (int) polyedre.points[i].y;
		}
		
		g.drawPolygon(x, y, taille);
		if (rand.nextInt(100) < 40)
			g.setColor(new Color(100 + rand.nextInt(126), 100 + rand.nextInt(126), 100 + rand.nextInt(126)));
		else
			g.setColor(new Color(rand.nextInt(60), rand.nextInt(60), 70 + rand.nextInt(186)));
		//g.setColor(Color.yellow);
		g.fillPolygon(x, y, taille);
		g.setColor(Color.blue);
	}

	
	public void paint(Graphics g) {
		super.paint(g);

		for (Polyedre poly:polyedres)
			draw(poly,g);

		for (Segment s:segments)
			draw(s,g);

		for (Point p:points)
			draw(p,g);
		


	
	}
}
