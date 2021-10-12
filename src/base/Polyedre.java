package base;

public class Polyedre {
	public Point[] points;

	public Polyedre(Point[] points) {
		super();
		this.points = points;
	}
	
	public boolean contains(Point p) {
		for(Point iter : this.points)
            if (iter.equals(p))
                return true;
        return false;
	}
}
