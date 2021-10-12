package base;


import java.util.Objects;

public class Point implements Comparable<Point>{
	public double x;
	public double y;
	
	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Point point = (Point) o;
		return Double.compare(point.x, x) == 0 &&
				Double.compare(point.y, y) == 0;
	}

	private static final float EPSILON = (float) 0.001;

	private static boolean proche(double x, double y) {
		return (x-y < EPSILON) && (y-x < EPSILON);
	}

	public int compareTo(Point p) {
		if (proche (x,p.x) && proche (y,p.y))
			return 0;

		if (y < p.y)
			return 1;
		else if (y>p.y)
			return -1;
		else if (x < p.x)
			return -1;
		else if (x>p.x)
			return 1;
		else
			return 0;
	}

	public Point translation(Segment t) {
		return new Point(
				x+t.fin.x-t.debut.x,
				y+t.fin.y-t.debut.y
		);
	}
}